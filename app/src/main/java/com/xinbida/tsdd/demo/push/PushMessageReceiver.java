package com.xinbida.tsdd.demo.push;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;

import com.chat.push.SharePreferencesUtil;
import com.chat.uikit.TabActivity;
import com.xinbida.tsdd.demo.MainActivity;
import com.xinbida.tsdd.demo.NotificationTools;

import cn.jpush.android.api.CmdMessage;
import cn.jpush.android.api.CustomMessage;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.JPushMessage;
import cn.jpush.android.api.NotificationMessage;
import cn.jpush.android.service.JPushMessageService;

public class PushMessageReceiver extends JPushMessageService {
    private static final String TAG = "com.odds.yueyan"+"123push";


    /**
     * 自定义消息接收
     *
     * @param context       上下文
     * @param customMessage 自定义消息
     */
    @Override
    public void onMessage(Context context, CustomMessage customMessage) {
        Log.e(TAG, "onMessage customMessage=" + customMessage);
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
//        BasicPushNotificationBuilder builder = new BasicPushNotificationBuilder(this.getApplicationContext());
//        builder.statusBarDrawable = R.mipmap.logo;
//        builder.notificationFlags = Notification.FLAG_AUTO_CANCEL
//                | Notification.FLAG_SHOW_LIGHTS;  //设置为自动消失和呼吸灯闪烁
//        builder.notificationDefaults = Notification.DEFAULT_SOUND
//                | Notification.DEFAULT_VIBRATE
//                | Notification.DEFAULT_LIGHTS;  // 设置为铃声、震动、呼吸灯闪烁都要
//        JPushInterface.setPushNotificationBuilder(1, builder);
        wakeUpScreen();
        NotificationTools notificationTools = NotificationTools.getInstance(this.getApplicationContext());
        notificationTools.sendNotification(notificationMessage.notificationTitle, notificationMessage.notificationContent, "https://www.baidu.com", MainActivity.class);
    }

    /**
     * 点击通知消息
     *
     * @param context             上下文
     * @param notificationMessage 通知消息
     */
    @Override
    public void onNotifyMessageOpened(Context context, NotificationMessage notificationMessage) {
        Log.e(TAG, "[onNotifyMessageOpened] " + notificationMessage);
        try {
            //打开自定义的Activity
            Intent i = new Intent(context, TabActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString(JPushInterface.EXTRA_NOTIFICATION_TITLE, notificationMessage.notificationTitle);
            bundle.putString(JPushInterface.EXTRA_ALERT, notificationMessage.notificationContent);
            i.putExtras(bundle);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(i);
        } catch (Throwable ignored) {

        }

    }

    /**
     * 清除通知消息
     *
     * @param context             上下文
     * @param notificationMessage 通知消息
     */
    @Override
    public void onNotifyMessageDismiss(Context context, NotificationMessage notificationMessage) {
        Log.e(TAG, "onNotifyMessageDismiss notificationMessage=" + notificationMessage);
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
        Log.e(TAG, "onConnected isConnected=" + isConnected);
    }

    @Override
    public void onMultiActionClicked(Context context, Intent intent) {
        Log.e(TAG, "[onMultiActionClicked] 用户点击了通知栏按钮");
        String nActionExtra = intent.getExtras().getString(JPushInterface.EXTRA_NOTIFICATION_ACTION_EXTRA);

        //开发者根据不同 Action 携带的 extra 字段来分配不同的动作。
        if (nActionExtra == null) {
            Log.d(TAG, "ACTION_NOTIFICATION_CLICK_ACTION nActionExtra is null");
            return;
        }
        if (nActionExtra.equals("my_extra1")) {
            Log.e(TAG, "[onMultiActionClicked] 用户点击通知栏按钮一");
        } else if (nActionExtra.equals("my_extra2")) {
            Log.e(TAG, "[onMultiActionClicked] 用户点击通知栏按钮二");
        } else if (nActionExtra.equals("my_extra3")) {
            Log.e(TAG, "[onMultiActionClicked] 用户点击通知栏按钮三");
        } else {
            Log.e(TAG, "[onMultiActionClicked] 用户点击通知栏按钮未定义");
        }
    }


    @Override
    public void onCommandResult(Context context, CmdMessage cmdMessage) {
        Log.e(TAG, "[onCommandResult] " + cmdMessage);
    }

    @Override
    public void onTagOperatorResult(Context context, JPushMessage jPushMessage) {
//        TagAliasOperatorHelper.getInstance().onTagOperatorResult(context,jPushMessage);
        super.onTagOperatorResult(context, jPushMessage);
    }

    @Override
    public void onCheckTagOperatorResult(Context context, JPushMessage jPushMessage) {
//        TagAliasOperatorHelper.getInstance().onCheckTagOperatorResult(context,jPushMessage);
        super.onCheckTagOperatorResult(context, jPushMessage);
    }

    @Override
    public void onAliasOperatorResult(Context context, JPushMessage jPushMessage) {
//        TagAliasOperatorHelper.getInstance().onAliasOperatorResult(context,jPushMessage);
        super.onAliasOperatorResult(context, jPushMessage);
    }

    @Override
    public void onMobileNumberOperatorResult(Context context, JPushMessage jPushMessage) {
//        TagAliasOperatorHelper.getInstance().onMobileNumberOperatorResult(context,jPushMessage);
        super.onMobileNumberOperatorResult(context, jPushMessage);
    }

    @Override
    public void onNotificationSettingsCheck(Context context, boolean isOn, int source) {
        super.onNotificationSettingsCheck(context, isOn, source);
        Log.e(TAG, "[onNotificationSettingsCheck] isOn:" + isOn + ",source:" + source);
    }

    /**
     * 唤醒
     * 屏幕
     */
    private void wakeUpScreen() {
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "tag");
        wl.acquire();
        wl.acquire(1000L);
    }
}
