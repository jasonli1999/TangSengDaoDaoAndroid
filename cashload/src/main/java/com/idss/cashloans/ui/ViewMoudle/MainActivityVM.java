package com.idss.cashloans.ui.ViewMoudle;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.azhon.basic.lifecycle.BaseViewModel;
import com.idss.cashloans.api.BaseApplication;
import com.idss.cashloans.api.Constants;
import com.idss.cashloans.api.HttpCallback;
import com.idss.cashloans.api.HttpRequest;
import com.idss.cashloans.api.moudle.APPversionModel;
import com.idss.cashloans.api.moudle.CertificationModel;
import com.idss.cashloans.api.moudle.SystemNoticeMoudel;
import com.idss.cashloans.api.utils.LogUtil;
import com.idss.cashloans.api.utils.SharePreferencesUtil;

public class MainActivityVM extends BaseViewModel {
    /**---------------------------------------------getapplist---------------------------------------------*/
    protected MutableLiveData<APPversionModel> apPversionModelMutableLiveData = new MutableLiveData<>();

    public void getAPPLiSt(Activity activity) {

        HttpRequest.getInstance().getapplist("getAPPLiSt", new HttpCallback<APPversionModel>() {
            @Override
            public void onSuccess(APPversionModel apPversionModel) {
                apPversionModelMutableLiveData.setValue(apPversionModel);
            }

            @Override
            public void onFailure(String msgCode, String errorMsg) {
                Toast.makeText(BaseApplication.getAppContext(), errorMsg, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public MutableLiveData<APPversionModel> getApPversionModelMutableLiveData() {
        return apPversionModelMutableLiveData;
    }



    /**---------------------------------------------getSystemNotice---------------------------------------------*/
    private final MutableLiveData<SystemNoticeMoudel> systemNoticeMoudelMutableLiveData = new MutableLiveData<>();

    public void getSystemNotice() {
        HttpRequest.getInstance().getSystemNotice("getSystemNotice", new HttpCallback<SystemNoticeMoudel>() {
            @Override
            public void onSuccess(SystemNoticeMoudel systemNoticeMoudel) {
                systemNoticeMoudelMutableLiveData.setValue(systemNoticeMoudel);
            }

            @Override
            public void onFailure(String msgCode, String errorMsg) {

            }
        });
    }

    public LiveData<SystemNoticeMoudel> getSystemNotices() {
        return systemNoticeMoudelMutableLiveData;
    }
    /**---------------------------------------------certification_info---------------------------------------------*/
    private final MutableLiveData<Integer> integerMutableLiveData = new MutableLiveData<>();

    public void requestCertification(Context context) {
        HttpRequest.getInstance().certification_info("requestCertification", new HttpCallback<CertificationModel>() {
            @Override
            public void onSuccess(CertificationModel certificationModel) {
                LogUtil.e(certificationModel.getData()+"=====certification_info========");
                //0为认证完成了
                integerMutableLiveData.setValue(certificationModel.getData());
                SharePreferencesUtil.addInteger(context, Constants.CERTIFICATION,certificationModel.getData());
            }

            @Override
            public void onFailure(String msgCode, String errorMsg) {

            }
        });
    }

    public LiveData<Integer> getAuthSteps() {
        return integerMutableLiveData;
    }
}
