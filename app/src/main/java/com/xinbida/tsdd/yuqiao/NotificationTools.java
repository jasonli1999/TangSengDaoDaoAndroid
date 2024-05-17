package com.xinbida.tsdd.yuqiao;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class NotificationTools {

    private Context context;
    private static NotificationTools utils;

    public NotificationTools(Context context) {
        this.context = context;
    }

    public static NotificationTools getInstance(Context context) {
        if (utils == null) {
            synchronized (NotificationTools.class) {
                if (utils == null) {
                    utils = new NotificationTools(context);
                }
            }
        }
        return utils;
    }


    /**
     * 发送通知
     *
     * @param url 启动的H5链接
     * @param cla 通知启动的Activity
     */
    public void sendNotification(String title, String content, String url, Class<?> cla) {
        NotificationManager notificationManager = NotificationUtile.getNotificationManager(context);
        if (notificationManager == null) {
            return;
        }

        if (!NotificationUtile.isOpenPermission(context)) {
            return;
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "push");
        Intent intent = new Intent(context, cla);//点击通知的时候启动Activity的意图
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("key", url);//传递字段
        intent.putExtra("fromPush", "true");//传递字段
        int pushid = (int) System.currentTimeMillis();
        PendingIntent pendingIntent = PendingIntent.getActivity(context, pushid, intent, PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        builder.setContentTitle(title);
        builder.setContentText(content);
        builder.setDefaults(NotificationCompat.DEFAULT_VIBRATE);
        builder.setAutoCancel(true);//点击通知后，状态栏是否自动删除通知。
        builder.setSmallIcon(R.mipmap.logo);//设置小图标
//        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.logo));//设置大图
//        builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(context.getResources(), R.mipmap.logo)));
        builder.setOngoing(false);//设置他为一个正在进行的通知。他们通常是用来表示一个后台任务，用户积极参与或以某种方式正在等待，因此占用设备。（当设置为true的时候就无法清除通知栏，若为false则可以清除。）
        builder.setWhen(System.currentTimeMillis());
        builder.setContentIntent(pendingIntent);
        builder.setChannelId("push");
        //判断是否是8.0Android.O
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel chan1 = new NotificationChannel("push", "Test Channel", NotificationManager.IMPORTANCE_HIGH);
            chan1.enableLights(true);
            chan1.enableVibration(true);
            chan1.setDescription("push");
            chan1.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            notificationManager.createNotificationChannel(chan1);
        }
        notificationManager.notify(pushid, builder.build());
    }
}