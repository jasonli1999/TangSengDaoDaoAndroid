package com.idss.cashloans.api;

import static com.idss.cashloans.api.Constants.tenantCode;

import androidx.collection.ArrayMap;

import com.idss.cashloans.api.moudle.APPversionModel;
import com.idss.cashloans.api.moudle.AwaitingRepaymentModel;
import com.idss.cashloans.api.moudle.BankCardModel;
import com.idss.cashloans.api.moudle.BankcardsModel;
import com.idss.cashloans.api.moudle.BannerModel;
import com.idss.cashloans.api.moudle.BorrowingModel;
import com.idss.cashloans.api.moudle.CaiwuIdModel;
import com.idss.cashloans.api.moudle.CancelModel;
import com.idss.cashloans.api.moudle.Certification;
import com.idss.cashloans.api.moudle.CertificationModel;
import com.idss.cashloans.api.moudle.ChangePasswordModel;
import com.idss.cashloans.api.moudle.EmailCodeModel;
import com.idss.cashloans.api.moudle.FaceVerifyModel;
import com.idss.cashloans.api.moudle.ForgetPswModel;
import com.idss.cashloans.api.moudle.HistoryOrdersModel;
import com.idss.cashloans.api.moudle.IDModel;
import com.idss.cashloans.api.moudle.LoanLimitModel;
import com.idss.cashloans.api.moudle.LoanProgressModel;
import com.idss.cashloans.api.moudle.LoginAuthModel;
import com.idss.cashloans.api.moudle.LoginModel;
import com.idss.cashloans.api.moudle.LogoutModel;
import com.idss.cashloans.api.moudle.OfficialUrl;
import com.idss.cashloans.api.moudle.OrderDetailModel;
import com.idss.cashloans.api.moudle.PaopaoModel;
import com.idss.cashloans.api.moudle.PaymentModel;
import com.idss.cashloans.api.moudle.PersonalModel;
import com.idss.cashloans.api.moudle.RegistModel;
import com.idss.cashloans.api.moudle.ServiceModel;
import com.idss.cashloans.api.moudle.ShareModel;
import com.idss.cashloans.api.moudle.StatueModel;
import com.idss.cashloans.api.moudle.SystemNoticeMoudel;
import com.idss.cashloans.api.utils.JsonUtil;
import com.idss.cashloans.api.utils.LogUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;


/**
 * 描述：网络请求类，将网络请求的方法放到此处统一管理  单例
 * 每个请求方法都传了tag标签,Activity和Fragment中请传this,方便生命周期管理.
 */

public class HttpRequest {

    private static final ApiService mService = ApiClient.getInstance().mApiService;
    private static final ArrayMap<Object, List<Call>> mCallMap = new ArrayMap<>();


    private static class SingletonHolder {
        private static HttpRequest instance = new HttpRequest();
    }

    public static HttpRequest getInstance() {
        return SingletonHolder.instance;
    }

    public static ApiService getApiService() {
        return mService;
    }

    private synchronized void putCall(Object tag, Call call) {
        if (tag == null) {
            return;
        }
        List<Call> callList = mCallMap.get(tag);
        if (callList == null) {
            callList = Collections.synchronizedList(new ArrayList<>());
        }
        callList.add(call);
        mCallMap.put(tag, callList);
    }

    public synchronized void cancel(Object tag) {
        if (tag == null) {
            return;
        }
        List<Call> callList = mCallMap.get(tag);
        if (callList == null || callList.size() == 0) {
            return;
        }
        for (Call call : callList) {
            if (!call.isCanceled()) {
                call.cancel();
            }
        }
        mCallMap.remove(tag);
    }


    public static class RequestBodyBuilder {
        Map<String, Object> params;

        public RequestBodyBuilder() {
            params = new HashMap<>();
        }

        public RequestBodyBuilder addParam(String key, Object value) {
            params.put(key, value);
            return this;
        }

        public RequestBody build() {
            return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JsonUtil.toJson(params));
        }

    }


    public void getOfficalUrl(Object tag, HttpCallback<OfficialUrl> callback) {
        Call<OfficialUrl> call = mService.getOfficalUrl();
        putCall(tag, call);
        call.enqueue(callback);
    }




    /**
     * 注册
     */
    public void register(Object tag, String username, String password, String deviceCode, String code, HttpCallback<RegistModel> callback) {
        RequestBody body = new RequestBodyBuilder()
                .addParam("username", username)
                .addParam("password", password)
                .addParam("deviceCode", deviceCode)
                .addParam("code", code)
                .addParam("device", "android")
                .build();
        Call<RegistModel> call = mService.Register(body);
        putCall(tag, call);
        call.enqueue(callback);
    }

    /**
     * 登录账号
     */
    public void login(Object tag, String phone, String code, String longitude, String latitude, String equipmentNum, HttpCallback<LoginModel> callback) {
        RequestBody body = new RequestBodyBuilder()
                .addParam("phone", phone)
                .addParam("code", code)
                .addParam("tenantCode", tenantCode)
                .addParam("longitude", longitude)
                .addParam("latitude", latitude)
                .addParam("equipmentNum", equipmentNum)
                .addParam("systemType", "android")

                .build();
        Call<LoginModel> call = mService.login(body);
        putCall(tag, call);
        call.enqueue(callback);
    }

    /**
     * 获取个人信息
     *
     * @param tag
     * @param callback
     */
    public void getPersonal(Object tag, HttpCallback<PersonalModel> callback) {
        Call<PersonalModel> call = mService.getPersonal();
        putCall(tag, call);
        call.enqueue(callback);
    }


    public void getapplist(Object tag, HttpCallback<APPversionModel> callback) {
        Call<APPversionModel> call = mService.getapplist();
        putCall(tag, call);
        call.enqueue(callback);
    }

    /**
     * 登出
     */

    public void logout(Object tag, HttpCallback<LogoutModel> callback) {
        Call<LogoutModel> call = mService.logout();
        putCall(tag, call);
        call.enqueue(callback);
    }

    /**
     * 修改密码
     */
    public void changePassword(Object tag, String password, String newPassword, HttpCallback<ChangePasswordModel> callback) {
        RequestBody body = new RequestBodyBuilder()
                .addParam("password", password)
                .addParam("newPassword", newPassword)
                .build();
        Call<ChangePasswordModel> call = mService.changePassword(body);
        putCall(tag, call);
        call.enqueue(callback);
    }

    /**
     * 获取分享链接
     */
    public void getShareInfo(Object tag, HttpCallback<ShareModel> callback) {
        Call<ShareModel> call = mService.getShare();
        putCall(tag, call);
        call.enqueue(callback);
    }

    public void unlogingetShareInfo(Object tag, HttpCallback<ShareModel> callback) {
        Call<ShareModel> call = mService.unlogingetShare();
        putCall(tag, call);
        call.enqueue(callback);
    }


    /**
     * 获取客服链接
     */
    public void getService(Object tag, String phone, String tenantCode, HttpCallback<ServiceModel> callback) {
        Call<ServiceModel> call = mService.getService(phone, tenantCode);
        putCall(tag, call);
        call.enqueue(callback);
    }

    /**
     * 查看用户是否验证过身份信息
     */
    public void getLoginAuth(Object tag, String phone, String tenantCode, HttpCallback<LoginAuthModel> callback) {
        Call<LoginAuthModel> call = mService.getLoginAuth(phone, tenantCode);
        putCall(tag, call);
        call.enqueue(callback);
    }

    /**
     * 获取信息认证步骤
     */
    public void certification_info(Object tag, HttpCallback<CertificationModel> callback) {
        Call<CertificationModel> call = mService.certification_info();
        putCall(tag, call);
        call.enqueue(callback);
    }


    public void initFaceVerify(Object tag, String nameTrue, String idCardNo, IDModel.MetaInfoDTO metaInfo, HttpCallback<FaceVerifyModel> callback) {
        RequestBody body = new RequestBodyBuilder()
                .addParam("nameTrue", nameTrue)
                .addParam("idCardNo", idCardNo)
                .addParam("metaInfo", metaInfo)
                .build();
        LogUtil.e(body.toString());
        Call<FaceVerifyModel> call = mService.initFaceVerify(body);
        putCall(tag, call);
        call.enqueue(callback);
    }


    /**
     * 还款支付  支付类型 1展期 2还款
     */
    public void repayment(Object tag, String alias, String orderNo, String type, String userId, HttpCallback<FaceVerifyModel> callback) {
        RequestBody body = new RequestBodyBuilder()
                .addParam("alias", alias)
                .addParam("orderNo", orderNo)
                .addParam("type", type)
                .addParam("userId", userId)
                .build();
        LogUtil.e(body.toString());
        Call<FaceVerifyModel> call = mService.repayment(body);
        putCall(tag, call);
        call.enqueue(callback);
    }

    /**
     * 实名认证点击
     */
    public void edit(Object tag, HttpCallback<CertificationModel> callback) {
        Call<CertificationModel> call = mService.edit();
        putCall(tag, call);
        call.enqueue(callback);
    }

    /**
     * 获取贷款额度
     */
    public void getLoanLimit(Object tag, HttpCallback<LoanLimitModel> callback) {
        Call<LoanLimitModel> call = mService.getLoanLimit();
        putCall(tag, call);
        call.enqueue(callback);
    }

    /**
     * 获取订单状态
     * /apid/member/latestOrderStatus 查订单状态0 代表没有订单 就查获取认证的接口 认证接口为0为认证完就查额度，1-5为继续认证；
     * 订单状态为1,就是有订单 查订单接口/apid/member/myBorrowings；
     * 订单状态2就是待还款，查还款信息 /apid/member/awaitingRepayment
     */
    public void getLatestOrderStatus(Object tag, HttpCallback<StatueModel> callback) {
        Call<StatueModel> call = mService.getLatestOrderStatus();
        putCall(tag, call);
        call.enqueue(callback);
    }

    /**
     * 历史订单
     */
    public void getHistoryOrders(Object tag, HttpCallback<HistoryOrdersModel> callback) {
        Call<HistoryOrdersModel> call = mService.getHistoryOrders();
        putCall(tag, call);
        call.enqueue(callback);
    }

    /**
     * 订单详情
     */
    public void getOrderDetail(Object tag, String id, HttpCallback<OrderDetailModel> callback) {
        Call<OrderDetailModel> call = mService.getOrderDetail(id);
        putCall(tag, call);
        call.enqueue(callback);
    }


    /**
     * 获取贷款进度
     */
    public void getLoanProgress(Object tag, String id, HttpCallback<LoanProgressModel> callback) {
        Call<LoanProgressModel> call = mService.getLoanProgress(id);
        putCall(tag, call);
        call.enqueue(callback);
    }

    /**
     * 获取银行卡信息
     */
    public void getIdCard(Object tag, HttpCallback<BankCardModel> callback) {
        Call<BankCardModel> call = mService.getIdCard();
        putCall(tag, call);
        call.enqueue(callback);
    }

    public void getIdCards(Object tag, HttpCallback<BankcardsModel> callback) {
        Call<BankcardsModel> call = mService.getIdCards();
        putCall(tag, call);
        call.enqueue(callback);
    }

    public void getIdCardList(Object tag, HttpCallback<BankCardModel> callback) {
        Call<BankCardModel> call = mService.getIdCard();
        putCall(tag, call);
        call.enqueue(callback);
    }



    /**
     * 获取支付方式
     */
    public void getpaymentMethod(Object tag, HttpCallback<PaymentModel> callback) {
        Call<PaymentModel> call = mService.getpaymentMethod();
        putCall(tag, call);
        call.enqueue(callback);
    }


    /**
     * 获取我的借款
     */
    public void getmyBorrowings(Object tag, HttpCallback<BorrowingModel> callback) {
        Call<BorrowingModel> call = mService.getmyBorrowings();
        putCall(tag, call);
        call.enqueue(callback);
    }

    /**
     * 获取我的代还款
     */
    public void getawaitingRepayment(Object tag, HttpCallback<AwaitingRepaymentModel> callback) {
        Call<AwaitingRepaymentModel> call = mService.awaitingRepayment();
        putCall(tag, call);
        call.enqueue(callback);
    }


    /**
     * 人脸识别完成后上传信息认证
     */
    public void certification(Object tag, Certification.RealNameDtoDTO realNameDtoDTO, HttpCallback<FaceVerifyModel> callback) {
        RequestBody body = new RequestBodyBuilder()
                .addParam("realNameDto", realNameDtoDTO)
                .build();
        Call<FaceVerifyModel> call = mService.certification(body);
        putCall(tag, call);
        call.enqueue(callback);
    }


    public void certification2(Object tag, Certification certification, HttpCallback<FaceVerifyModel> callback) {
        RequestBody body = new RequestBodyBuilder()
                .addParam("memberAppList", certification.getMemberAppList())
                .addParam("addressBookList", certification.getAddressBookList())
                .addParam("memberSmsList", certification.getMemberSmsList())
                .addParam("memberCallLogList", certification.getMemberCallLogList())
                .build();
        Call<FaceVerifyModel> call = mService.certification(body);
        putCall(tag, call);
        call.enqueue(callback);
    }

    public void certification3(Object tag, List<Certification.MemberEmergencyContactListDTO> list, HttpCallback<FaceVerifyModel> callback) {
        RequestBody body = new RequestBodyBuilder()
                .addParam("memberEmergencyContactList", list)
                .build();
        Call<FaceVerifyModel> call = mService.certification(body);
        putCall(tag, call);
        call.enqueue(callback);
    }

    public void certification4(Object tag, String bankname, String bankcard, HttpCallback<FaceVerifyModel> callback) {
        RequestBody body = new RequestBodyBuilder()
                .addParam("bankCardName", bankname)
                .addParam("bankCard", bankcard)
                .build();
        Call<FaceVerifyModel> call = mService.certification(body);
        putCall(tag, call);
        call.enqueue(callback);
    }

    /**
     * 添加银行卡
     */
    public void addBankcard(Object tag, String bankname, String bankcard, String bankCardUsername,int isDefault, HttpCallback<FaceVerifyModel> callback) {
        RequestBody body = new RequestBodyBuilder()
                .addParam("bankCardName", bankname)
                .addParam("bankCardNo", bankcard)
                .addParam("bankCardUsername", bankCardUsername)
                .addParam("isDefault", isDefault)
                .build();
        Call<FaceVerifyModel> call = mService.addBankCard(body);
        putCall(tag, call);
        call.enqueue(callback);
    }


    /**
     * 修改银行卡
     */
    public void changeBankcard(Object tag, String bankid,int isDefault, HttpCallback<FaceVerifyModel> callback) {
        RequestBody body = new RequestBodyBuilder()
                .addParam("enable", 1)
                .addParam("id", bankid)
                .addParam("isDefault", isDefault)
                .build();
        Call<FaceVerifyModel> call = mService.changeBankCard(body);
        putCall(tag, call);
        call.enqueue(callback);
    }


    public void createOrder(Object tag, String sumMoney, HttpCallback<FaceVerifyModel> callback) {
        RequestBody body = new RequestBodyBuilder()
                .addParam("sumMoney", sumMoney)
                .build();
        Call<FaceVerifyModel> call = mService.createOrder(body);
        putCall(tag, call);
        call.enqueue(callback);
    }


    /**
     * 注销用户
     */
    public void canceluser(Object tag, String username, HttpCallback<CancelModel> callback) {
        RequestBody body = new RequestBodyBuilder()
                .addParam("username", username)
                .build();
        Call<CancelModel> call = mService.canceluser(body);
        putCall(tag, call);
        call.enqueue(callback);
    }


    /**
     * 获取Banner
     */
    public void getBanner(Object tag, HttpCallback<BannerModel> callback) {
        Call<BannerModel> call = mService.getBanner();
        putCall(tag, call);
        call.enqueue(callback);
    }


    /**
     * 获取聊天软件信息
     */
    public void getPaopao(Object tag, HttpCallback<PaopaoModel> callback) {
        Call<PaopaoModel> call = mService.getPaopao();
        putCall(tag, call);
        call.enqueue(callback);
    }

    public void getSystemNotice(Object tag, HttpCallback<SystemNoticeMoudel> callback) {
        Call<SystemNoticeMoudel> call = mService.getSystemNotice();
        putCall(tag, call);
        call.enqueue(callback);
    }

    /**
     * 获取贷款最新进度
     */
    public void getLoanPeriod(Object tag, HttpCallback<PaopaoModel> callback) {
        Call<PaopaoModel> call = mService.getLoanPeriod();
        putCall(tag, call);
        call.enqueue(callback);
    }

    /**
     *   获取财务id
     */
    public void getCaiwuId(Object tag, HttpCallback<CaiwuIdModel> callback) {
        Call<CaiwuIdModel> call = mService.getCaiwuId();
        putCall(tag, call);
        call.enqueue(callback);
    }


    /**
     * 发送email
     */
    public void sendCode(Object tag, String phone, HttpCallback<EmailCodeModel> callback) {
        Call<EmailCodeModel> call = mService.sendCode(phone, tenantCode);
        putCall(tag, call);
        call.enqueue(callback);
    }

    /**
     * 找回密码
     */
    public void forgetPsw(Object tag, String email, String password, String code, HttpCallback<ForgetPswModel> callback) {
        RequestBody body = new RequestBodyBuilder()
                .addParam("email", email)
                .addParam("password", password)
                .addParam("code", code)
                .build();
        Call<ForgetPswModel> call = mService.forgetPsw(body);
        putCall(tag, call);
        call.enqueue(callback);
    }
}
