package com.idss.cashloans.api.utils;


import android.content.Context;

public class DpUtil {
    public static int dp2px(Context context,int dpVal) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (scale * dpVal + 0.5f);
    }
}
