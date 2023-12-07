package com.xinbida.tsdd.demo.push;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.chat.push.SharePreferencesUtil;
import com.chat.uikit.TabActivity;

import cn.jpush.android.api.CustomMessage;
import cn.jpush.android.api.NotificationMessage;
import cn.jpush.android.service.JPushMessageService;

public class PushMessageReceiver extends JPushMessageService {
    private static final String TAG = "===============jpush===";


    /**
     * 自定义消息接收
     *
     * @param context       上下文
     * @param customMessage 自定义消息
     */
    @Override
    public void onMessage(Context context, CustomMessage customMessage) {
        Log.d(TAG, "onMessage customMessage=" + customMessage);
    }

    /**
     * 通知消息接收
     *
     * @param context             上下文
     * @param notificationMessage 通知消息
     */
    @Override
    public void onNotifyMessageArrived(Context context, NotificationMessage notificationMessage) {
        Log.d(TAG, "onNotifyMessageArrived notificationMessage=" + notificationMessage);
    }

    /**
     * 点击通知消息
     *
     * @param context             上下文
     * @param notificationMessage 通知消息
     */
    @Override
    public void onNotifyMessageOpened(Context context, NotificationMessage notificationMessage) {
        Log.d(TAG, "onNotifyMessageOpened notificationMessage=" + notificationMessage);

        //打开自定义的Activity
        Intent i = new Intent(context, TabActivity.class);
        Bundle bundle = new Bundle();
//            bundle.putString(JPushInterface.EXTRA_NOTIFICATION_TITLE,message.notificationTitle);
//            bundle.putString(JPushInterface.EXTRA_ALERT,message.notificationContent);
        i.putExtras(bundle);
        //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(i);

    }

    /**
     * 清除通知消息
     *
     * @param context             上下文
     * @param notificationMessage 通知消息
     */
    @Override
    public void onNotifyMessageDismiss(Context context, NotificationMessage notificationMessage) {
        Log.d(TAG, "onNotifyMessageDismiss notificationMessage=" + notificationMessage);
    }

    /**
     * 注册成功
     *
     * @param context        上下文
     * @param registrationId 注册id
     */
    @Override
    public void onRegister(Context context, String registrationId) {
        Log.e(TAG, "onRegister registrationId=" + registrationId);

        SharePreferencesUtil.addString(getApplicationContext(), "device_token", registrationId);

    }

    /**
     * 长连接状态变化
     *
     * @param context     上下文
     * @param isConnected 长连接状态，true表示已连接
     */
    @Override
    public void onConnected(Context context, boolean isConnected) {
        Log.d(TAG, "onConnected isConnected=" + isConnected);
    }
}
