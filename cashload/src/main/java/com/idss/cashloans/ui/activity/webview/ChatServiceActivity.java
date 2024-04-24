package com.idss.cashloans.ui.activity.webview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.azhon.basic.base.BaseActivity;
import com.idss.cashloans.R;
import com.idss.cashloans.api.Constants;
import com.idss.cashloans.api.utils.LogUtil;
import com.idss.cashloans.api.utils.SharePreferencesUtil;
import com.idss.cashloans.databinding.ActivityServiceBinding;
import com.idss.cashloans.ui.ViewMoudle.ServiceActivityVM;
import com.meiqia.meiqiasdk.util.MQIntentBuilder;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * 联系客服
 */
public class ChatServiceActivity extends BaseActivity<ServiceActivityVM, ActivityServiceBinding> {
    private Disposable mDDisposable;
    private String jump_url;
    private String phone;


    @Override
    protected void onCreate(View view, Bundle savedInstanceState) {
        phone = getIntent().getStringExtra("phone");
        LogUtil.e(phone);
//        viewModel.requestService(phone);
        binding.ivBack.setOnClickListener(v -> finish());

    }

    @Override
    protected void initView() {

        binding.btContactService.setEnabled(false);

        Observable<Long> observable = Observable
                .interval(1, TimeUnit.SECONDS)
                .take(5);

        Observable<Long> countdown = observable.map(time -> 5 - time);
        Observer<Long> observer = new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                // 当Observer订阅Observable时，会调用该方法
                mDDisposable = d;
            }

            @Override
            public void onNext(Long time) {
                // 当Observer接收到事件时，会调用该方法
                // 在这里可以更新UI显示倒计时的剩余时间
                runOnUiThread(() -> binding.tvSenconds.setText(time + "秒"));
            }

            @Override
            public void onError(Throwable e) {
                // 当Observable发生错误时，会调用该方法

            }

            @Override
            public void onComplete() {
                // 当Observable完成发射事件时，会调用该方法
                // 在这里可以处理倒计时结束的情况
                runOnUiThread(() -> {
                    binding.tvSenconds.setVisibility(View.GONE);
                    binding.btContactService.setEnabled(true);
                    binding.btContactService.setBackground(getResources().getDrawable(R.drawable.login_button_bg));
                });

            }
        };
        countdown.subscribe(observer);


        binding.btContactService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.btContactService.isEnabled()) {
                    try {
//                        if (null != Objects.requireNonNull(viewModel.getMutableLiveData().getValue()).getData().getLink()) {
//                            Intent intent = new Intent(ServiceActivity.this, WebViewActivity.class);
//                            intent.putExtra("jump_url", viewModel.getMutableLiveData().getValue().getData().getLink());
//                            LogUtil.e(jump_url + ":" + viewModel.getMutableLiveData().getValue().getData().getLink());
//                            intent.putExtra("title_name", "官方认证联系方式");
//                            startActivity(intent);
//                            finish();
//                        }

                        if (SharePreferencesUtil.getString(getApplicationContext(), Constants.TOKEN, "").isEmpty()) {
                            LogUtil.e("====================isEmpty===================");
                            HashMap<String, String> clientInfo = new HashMap<>();
                            clientInfo.put("name", "游客" + phone + "(" + SharePreferencesUtil.getString(context, Constants.USER_MEIQIA_STATUE, "") + ")");
                            clientInfo.put("avatar", "https://img2.baidu.com/it/u=3772316818,588533834&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=889");
                            clientInfo.put("tel", SharePreferencesUtil.getString(context, Constants.PHONE, ""));
                            clientInfo.put("deviceOS", "Android");
                            clientInfo.put("comment", Constants.tenantCode);
                            Intent intent = new MQIntentBuilder(getApplicationContext())
                                    .setClientInfo(clientInfo)
                                    .build();
                            startActivity(intent);
                        } else {
                            LogUtil.e("====================登录状态===================");
                            HashMap<String, String> clientInfo = new HashMap<>();
                            LogUtil.e(SharePreferencesUtil.getString(context, Constants.USER_NAME, ""));
                            clientInfo.put("name", SharePreferencesUtil.getString(context, Constants.USER_NAME, "") + "(" + SharePreferencesUtil.getString(context, Constants.USER_MEIQIA_STATUE, "") + ")");
                            clientInfo.put("avatar", "https://img2.baidu.com/it/u=3772316818,588533834&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=889");
                            clientInfo.put("tel", SharePreferencesUtil.getString(context, Constants.PHONE, ""));
                            clientInfo.put("deviceOS", "Android");
                            clientInfo.put("comment", Constants.tenantCode);
                            Intent intent = new MQIntentBuilder(getApplicationContext())
                                    .setClientInfo(clientInfo)
                                    .build();
                            startActivity(intent);
                        }
                    } catch (Exception exception) {
                        exception.printStackTrace();
                        Toast.makeText(getApplicationContext(), "获取客服链接失败，请重试。", Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });
    }

    @Override
    protected void initData() {
        //数据请求成功通知
        viewModel.getMutableLiveData().observe(this, jump_url -> Objects.requireNonNull(viewModel.getMutableLiveData().getValue().getData().getLink()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //避免倒计时时间关闭导致空指针
        mDDisposable.dispose();
        mDDisposable = null;
    }


}
