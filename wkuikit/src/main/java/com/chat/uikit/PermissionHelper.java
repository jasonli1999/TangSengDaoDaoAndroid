package com.chat.uikit;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;

import androidx.core.content.ContextCompat;

public class PermissionHelper {
    public static final int REQUEST_CODE_SYSTEM_ALERT_WINDOW = 999;

    public static boolean checkSystemAlertWindowPermission(Context context) {
        return ContextCompat.checkSelfPermission(context, Manifest.permission.SYSTEM_ALERT_WINDOW) == PackageManager.PERMISSION_GRANTED;
    }

    public static void requestSystemAlertWindowPermission(Activity activity) {
//        if (!checkSystemAlertWindowPermission(activity)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + activity.getPackageName()));
            activity.startActivityForResult(intent, REQUEST_CODE_SYSTEM_ALERT_WINDOW);
//        }
    }
}
