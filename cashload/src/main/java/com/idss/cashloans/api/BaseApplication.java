package com.idss.cashloans.api;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.idss.cashloans.api.utils.ActivityManager;
import com.idss.cashloans.api.utils.LogUtil;
import com.meiqia.core.callback.OnInitCallback;
import com.meiqia.meiqiasdk.util.MQConfig;
import com.umeng.commonsdk.UMConfigure;

import java.util.ArrayList;
import java.util.List;


public class BaseApplication extends MultiDexApplication {

    public static BaseApplication baseApplication;
    public List<Activity> activityList = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
        registerActivityLifecycleCallbacks();
//        initWebView();
        // 主要是添加下面这句代码
        MultiDex.install(this);
        LogUtil.e("==============BaseApplication==========");
        //初始化组件化基础库, 所有友盟业务SDK都必须调用此初始化接口。
        UMConfigure.init(this, "65faef6f8d21b86a18438e5c", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "");

        /**
         * 美洽客服系统
         */
        MQConfig.init(this, "98b9893c219c7a8fd5c35a9cad615bd0", new OnInitCallback() {
            @Override
            public void onSuccess(String clientId) {
//                Toast.makeText(baseApplication, "init success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int code, String message) {
//                Toast.makeText(baseApplication, "int failure", Toast.LENGTH_SHORT).show();
            }
        });

    }

    //处理Android7（N）webview导致应用内语言失效的问题
    private void initWebView() {
        new WebView(this).destroy();
    }


    public static BaseApplication getAppContext() {
        return baseApplication;
    }


    /**
     * 获取当前activity的接口
     */
    private void registerActivityLifecycleCallbacks() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, Bundle savedInstanceState) {
                activityList.add(activity);
            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {
            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {
                ActivityManager.getInstance().setCurrentActivity(activity);
            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {
            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {
            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {
                activityList.remove(activity);
            }
        });
    }

    public List<Activity> getactivityList() {
        return activityList;
    }

}

