package com.idss.cashloans.ui.ViewMoudle;

import android.app.Activity;
import android.content.Context;
import android.provider.Settings;
import android.widget.Button;

import androidx.lifecycle.MutableLiveData;

import com.azhon.basic.lifecycle.BaseViewModel;
import com.idss.cashloans.api.Constants;
import com.idss.cashloans.api.HttpCallback;
import com.idss.cashloans.api.HttpRequest;
import com.idss.cashloans.api.moudle.EmailCodeModel;
import com.idss.cashloans.api.moudle.LoginAuthModel;
import com.idss.cashloans.api.moudle.LoginModel;
import com.idss.cashloans.api.utils.ActivityManager;
import com.idss.cashloans.api.utils.SharePreferencesUtil;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class LoginActivityVM extends BaseViewModel {
    private Disposable disposable;

    /**---------------------------------------------请求验证码---------------------------------------------*/

    /**
     * 请求验证码
     */
    public void requestCode(Activity context, String phone, Button button) {

//        Disposable disposable = HttpRequest.getApiService().Regist(body)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(newsBean -> {
////                    showDialog.setValue(false);
////                    news.setValue(String.valueOf(newsBean));
//                }, throwable -> {
//                    showDialog.setValue(false);
//                    //发生了错误，通知UI层
//                    error.setValue("发生错误了");
//                });
//        addDisposable(disposable);

        showDialog.setValue(true, "加载中");
        HttpRequest.getInstance().sendCode("sendCode", phone, new HttpCallback<EmailCodeModel>() {
            @Override
            public void onSuccess(EmailCodeModel data) {
                showDialog.setValue(false);
                error.setValue(data.getMsg());
                button.setClickable(false);
                Observable<Long> observable = Observable.interval(1, TimeUnit.SECONDS).take(60);

                Observable<Long> countdown = observable.map(time -> 60 - time);
                Observer<Long> observer = new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // 当Observer订阅Observable时，会调用该方法
                        disposable = d;
                    }

                    @Override
                    public void onNext(Long time) {
                        // 当Observer接收到事件时，会调用该方法
                        // 在这里可以更新UI显示倒计时的剩余时间
                        context.runOnUiThread(() -> button.setText(time + "s"));
                    }

                    @Override
                    public void onError(Throwable e) {
                        // 当Observable发生错误时，会调用该方法
                        button.setText("再次发送");
                    }

                    @Override
                    public void onComplete() {
                        // 当Observable完成发射事件时，会调用该方法
                        // 在这里可以处理倒计时结束的情况
                        context.runOnUiThread(() -> button.setText("再次发送"));

                        button.setClickable(true);
                    }
                };

                countdown.subscribe(observer);
            }

            @Override
            public void onFailure(String msgCode, String errorMsg) {
                showDialog.setValue(false);
                error.setValue(errorMsg);
//                context.startActivity(new Intent(context, AuthenticationActivity.class));

            }
        });

    }


    /**---------------------------------------------查看用户是否验证过身份信息---------------------------------------------*/

    /**
     * 查看用户是否验证过身份信息
     */
    protected MutableLiveData<LoginAuthModel> loginAuthModelMutableLiveData = new MutableLiveData<>();

    public void getLoginAuth(Activity context, String phone) {
        HttpRequest.getInstance().getLoginAuth("LoginVM", phone, Constants.tenantCode, new HttpCallback<LoginAuthModel>() {
            @Override
            public void onSuccess(LoginAuthModel data) {
                loginAuthModelMutableLiveData.setValue(data);
            }

            @Override
            public void onFailure(String msgCode, String errorMsg) {
                showDialog.setValue(false);
                error.setValue(errorMsg);

            }
        });
    }

    public MutableLiveData<LoginAuthModel> getLoginAuthModelMutableLiveData() {
        return loginAuthModelMutableLiveData;
    }


    /**
     * ---------------------------------------------登录---------------------------------------------
     */


    protected MutableLiveData<LoginModel> loginModelMutableLiveData = new MutableLiveData<>();

    public void requestLogin(Context context, String phone, String code, String longitude, String latitude) {
        showDialog.setValue(true, "加载中");
        HttpRequest.getInstance().login("requestLogin", phone, code, longitude, latitude, Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID), new HttpCallback<LoginModel>() {
            @Override
            public void onSuccess(LoginModel data) {
                //1是老用户
                SharePreferencesUtil.addInteger(context, Constants.IS_OLDUSER, data.getUserInfo().getIsOldUser());

                loginModelMutableLiveData.setValue(data);
                showDialog.setValue(false);
                error.setValue(data.getMsg());
                SharePreferencesUtil.addString(context, Constants.TOKEN, data.getToken());
                SharePreferencesUtil.addBoolean(context, Constants.IF_LOGIN, true);
                SharePreferencesUtil.addString(context, Constants.PHONE, data.getUserInfo().getPhone());
                SharePreferencesUtil.addString(context, Constants.USER_NAME, data.getUserInfo().getNameTrue());
                SharePreferencesUtil.addString(context, Constants.MEMBERID, data.getUserInfo().getId());
                if (null != disposable) disposable.dispose();
                ActivityManager.getInstance().getCurrentActivity().finish();
            }

            @Override
            public void onFailure(String msgCode, String errorMsg) {
                showDialog.setValue(false);
                error.setValue(errorMsg);
                SharePreferencesUtil.addBoolean(context, Constants.IF_LOGIN, false);
            }
        });

    }

    public MutableLiveData<LoginModel> getLoginModelMutableLiveData() {
        return loginModelMutableLiveData;
    }


}
