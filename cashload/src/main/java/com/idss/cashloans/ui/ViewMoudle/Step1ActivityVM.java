package com.idss.cashloans.ui.ViewMoudle;

import static com.idss.cashloans.api.utils.AppUtils.getAppName;
import static com.idss.cashloans.api.utils.AppUtils.getVersionName;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.alipay.face.api.ZIMCallback;
import com.alipay.face.api.ZIMFacade;
import com.alipay.face.api.ZIMFacadeBuilder;
import com.alipay.face.api.ZIMResponse;
import com.azhon.basic.lifecycle.BaseViewModel;
import com.idss.cashloans.api.BaseApplication;
import com.idss.cashloans.api.HttpCallback;
import com.idss.cashloans.api.HttpRequest;
import com.idss.cashloans.api.moudle.Certification;
import com.idss.cashloans.api.moudle.CertificationModel;
import com.idss.cashloans.api.moudle.FaceVerifyModel;
import com.idss.cashloans.api.moudle.IDModel;
import com.idss.cashloans.api.utils.LogUtil;
import com.idss.cashloans.api.utils.SharePreferencesUtil;

public class Step1ActivityVM extends BaseViewModel {
    /**
     * ---------------------------------------------requestCertifyId---------------------------------------------
     */
    protected MutableLiveData<String> mutableLiveData = new MutableLiveData<>();

    /**
     * 获取阿里人脸验证的CertifyId
     */
    public void requestCertifyId(String nameTrue, String idCardNo) {
        showDialog.setValue(true, "加载中");

        IDModel.MetaInfoDTO metaInfoDTO = new IDModel.MetaInfoDTO();
        metaInfoDTO.setBioMetaInfo("");
        metaInfoDTO.setApdidToken(SharePreferencesUtil.getString(BaseApplication.getAppContext(), "TOKEN", ""));
        metaInfoDTO.setAppName(getAppName(BaseApplication.getAppContext()));
        metaInfoDTO.setAppVersion(getVersionName(BaseApplication.getAppContext()));
        metaInfoDTO.setDeviceType("android");
        metaInfoDTO.setSdkVersion("");
        metaInfoDTO.setBioMetaInfo("");
        metaInfoDTO.setZimVer("");
        metaInfoDTO.setDeviceModel("android");

        HttpRequest.getInstance().initFaceVerify("initFaceVerify", nameTrue, idCardNo, metaInfoDTO, new HttpCallback<FaceVerifyModel>() {
            @Override
            public void onSuccess(FaceVerifyModel data) {
                showDialog.setValue(false);
                SharePreferencesUtil.addString(BaseApplication.getAppContext(), "nameTrue", nameTrue);
                SharePreferencesUtil.addString(BaseApplication.getAppContext(), "idCardNo", idCardNo);
                //certifyId
                mutableLiveData.setValue(data.getData());

            }

            @Override
            public void onFailure(String msgCode, String errorMsg) {
                showDialog.setValue(false);
                error.setValue(errorMsg);

            }
        });

    }

    public MutableLiveData<String> getMutableLiveData() {
        return mutableLiveData;
    }

    /**
     * ---------------------------------------------createOrder---------------------------------------------
     */


    public void requestFace(Activity context, String certifyId) {
        Context ctx = context.getApplicationContext();
        // 初始化SDK
        ZIMFacade.install(ctx);
        // 获取MetaInfos
        String metaInfos = ZIMFacade.getMetaInfos(ctx);
        LogUtil.e(metaInfos);
        // 将MetaInfos发送到App服务器端，调用云端InitFaceVerify接口获取CertifyId。
        // certifyId = getCertifyIdFromServer(metaInfo); // 需客户自己实现
        // 开始验证
        ZIMFacade zimFacade = ZIMFacadeBuilder.create(ctx);
        zimFacade.verify(certifyId, true, null, new ZIMCallback() {
            @Override
            public boolean response(ZIMResponse response) {
                switch (response.code) {
                    case 1000:
                        Log.d("AliyunFace", "认证成功");
                        Toast.makeText(context, "认证成功.", Toast.LENGTH_SHORT).show();
                        //信息认证
                        certification(context);
                        break;
                    case 1001:
                        Log.e("AliyunFace", "系统错误");
                        Toast.makeText(context, "系统错误.", Toast.LENGTH_SHORT).show();
                        break;
                    case 1003:
                        Log.e("AliyunFace", "验证中断");
                        Toast.makeText(context, "验证中断.", Toast.LENGTH_SHORT).show();
                        break;
                    case 2002:
                        Log.e("AliyunFace", "网络错误");
                        Toast.makeText(context, "网络错误.", Toast.LENGTH_SHORT).show();
                        break;
                    case 2003:
                        Log.e("AliyunFace", "客户端设备时间错误");
                        Toast.makeText(context, "客户端设备时间错误.", Toast.LENGTH_SHORT).show();
                        break;
                    case 2006:
                        Log.e("AliyunFace", "刷脸失败");
                        Toast.makeText(context, "刷脸失败.", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Log.e("AliyunFace", "未知错误");
                        Toast.makeText(context, "未知错误.", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }


        });

    }


    /**
     * 人脸认证完统计次数
     */
    public void edit() {
        HttpRequest.getInstance().edit("requestCertification", new HttpCallback<CertificationModel>() {
            @Override
            public void onSuccess(CertificationModel certificationModel) {
                //0为认证完成了
            }

            @Override
            public void onFailure(String msgCode, String errorMsg) {

            }
        });
    }


    /**
     * 信息认证
     */
    private void certification(Activity activity) {
        String nameTrue = SharePreferencesUtil.getString(BaseApplication.getAppContext(), "nameTrue", "");
        String idCardNo = SharePreferencesUtil.getString(BaseApplication.getAppContext(), "idCardNo", "");
        Certification.RealNameDtoDTO realNameDtoDTO = new Certification.RealNameDtoDTO();
        realNameDtoDTO.setRealName(nameTrue);
        realNameDtoDTO.setIdCard(idCardNo);
        HttpRequest.getInstance().certification("certification", realNameDtoDTO, new HttpCallback<FaceVerifyModel>() {
            @Override
            public void onSuccess(FaceVerifyModel data) {
                activity.finish();
            }

            @Override
            public void onFailure(String msgCode, String errorMsg) {
                Toast.makeText(BaseApplication.getAppContext(), errorMsg, Toast.LENGTH_SHORT).show();
            }
        });

    }

}
