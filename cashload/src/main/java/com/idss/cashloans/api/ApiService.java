package com.idss.cashloans.api;

import com.idss.cashloans.api.moudle.APPversionModel;
import com.idss.cashloans.api.moudle.AwaitingRepaymentModel;
import com.idss.cashloans.api.moudle.BankCardModel;
import com.idss.cashloans.api.moudle.BankcardsModel;
import com.idss.cashloans.api.moudle.BannerModel;
import com.idss.cashloans.api.moudle.BorrowingModel;
import com.idss.cashloans.api.moudle.CaiwuIdModel;
import com.idss.cashloans.api.moudle.CancelModel;
import com.idss.cashloans.api.moudle.CertificationModel;
import com.idss.cashloans.api.moudle.ChangePasswordModel;
import com.idss.cashloans.api.moudle.EmailCodeModel;
import com.idss.cashloans.api.moudle.FaceVerifyModel;
import com.idss.cashloans.api.moudle.ForgetPswModel;
import com.idss.cashloans.api.moudle.HistoryOrdersModel;
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

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ApiService {

    @GET("https://iron-downpoint-1322403631.cos.ap-shanghai.myqcloud.com/gtx.json")
    Call<OfficialUrl> getOfficalUrl();

    /**
     * 用户注册
     */
    @POST("/apid/login/registration")
    Call<RegistModel> Register(@Body RequestBody body);

    /**
     * 登录
     */
    @POST("/apid/login/login")
    Call<LoginModel> login(@Body RequestBody body);

    /**
     * 发送验证码
     */
    @GET("/apid/login/getCode")
    Call<EmailCodeModel> sendCode(@Query("phone") String phone, @Query("tenantCode") String tenantCode);

    /**
     * 获取客服链接
     */
    @GET("/apid/member/customer/service/unlogin")
    Call<ServiceModel> getService(@Query("phone") String phone, @Query("tenantCode") String tenantCode);

    @GET("/apid/login/check/auth")
    Call<LoginAuthModel> getLoginAuth(@Query("phone") String phone, @Query("tenantCode") String tenantCode);

    /**
     * 获取信息认证步骤
     */

    @GET("/apid/member/certification/info")
    Call<CertificationModel> certification_info();

    /**
     * 人脸识别初始化
     */
    @POST("/apid/member/faceVerify/initFaceVerify")
    Call<FaceVerifyModel> initFaceVerify(@Body RequestBody body);

    @POST("/apid/member/repayment")
    Call<FaceVerifyModel> repayment(@Body RequestBody body);

    /**
     * 实名认证点击次数统计
     */
    @GET("/apid/member/isClick/edit")
    Call<CertificationModel> edit();

    @POST("/apid/member/certification")
    Call<FaceVerifyModel> certification(@Body RequestBody body);

    @POST("/apid/bank/card")
    Call<FaceVerifyModel> addBankCard(@Body RequestBody body);

    @PUT("/apid/bank/card")
    Call<FaceVerifyModel> changeBankCard(@Body RequestBody body);


    @POST("/apid/member/createOrder")
    Call<FaceVerifyModel> createOrder(@Body RequestBody body);

    @GET("/apid/member/order/detail")
    Call<OrderDetailModel> getOrderDetail(@Query("id") String id);

    @GET("/apid/member/loanPeriod")
    Call<LoanProgressModel> getLoanProgress(@Query("id") String id);

    /**
     * 获取银行卡信息
     */
    @GET("/apid/member/idCard")
    Call<BankCardModel> getIdCard();

    @GET("/apid/bank/card/list")
    Call<BankcardsModel> getIdCards();

    @GET("/apid/member/paymentMethod")
    Call<PaymentModel> getpaymentMethod();

    @GET("/apid/member/myBorrowings")
    Call<BorrowingModel> getmyBorrowings();

    @GET("/apid/member/awaitingRepayment")
    Call<AwaitingRepaymentModel> awaitingRepayment();

    /**
     * 获取个人信息
     */
    @GET("/admin/user/info")
    Call<PersonalModel> getPersonal();

    @GET("/apid/common/app/list")
    Call<APPversionModel> getapplist();


    @GET("/apid/member/quota")
    Call<LoanLimitModel> getLoanLimit();

    @GET("/apid/member/latestOrderStatus")
    Call<StatueModel> getLatestOrderStatus();

    @GET("/apid/member/myOrder")
    Call<HistoryOrdersModel> getHistoryOrders();

    /**
     * 登出
     */
    @DELETE("/auth/token/logout")
    Call<LogoutModel> logout();

    /**
     * 修改密码
     */
    @POST("/admin/vpn/user/edit")
    Call<ChangePasswordModel> changePassword(@Body RequestBody body);

    /**
     * 分享界面
     */
    @GET("/admin/vpn/unlogin/share")
    Call<ShareModel> unlogingetShare();

    @GET("/admin/vpn/share")
    Call<ShareModel> getShare();

    /**
     * 注销用户
     */
    @POST("/admin/vpn/cancel/user")
    Call<CancelModel> canceluser(@Body RequestBody body);

    @GET("/admin/vpn/app/version?type=android")
    Call<APPversionModel> getAPPversion();

    /**
     * 获取开屏广告
     */
    @GET("/admin/vpn/getBanner")
    Call<BannerModel> getBanner();

    /**
     * 获取开屏广告
     */
    @GET("/apid/member/paopao/list")
    Call<PaopaoModel> getPaopao();

    @GET("/apid/common/getSysNotice")
    Call<SystemNoticeMoudel> getSystemNotice();

    @GET("/apid/member/loanPeriod")
    Call<PaopaoModel> getLoanPeriod();

    @GET("/apid/member/finance")
    Call<CaiwuIdModel> getCaiwuId();

    /**
     * 忘记密码
     */
    @POST("/admin/vpn/user/password/forget")
    Call<ForgetPswModel> forgetPsw(@Body RequestBody body);
}
