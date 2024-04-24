package com.idss.cashloans.ui.activity.login;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;

import com.azhon.basic.base.BaseActivity;
import com.idss.cashloans.api.Constants;
import com.idss.cashloans.api.moudle.LoginAuthModel;
import com.idss.cashloans.api.moudle.LoginModel;
import com.idss.cashloans.api.utils.AppUtils;
import com.idss.cashloans.api.utils.LogUtil;
import com.idss.cashloans.api.utils.SharePreferencesUtil;
import com.idss.cashloans.databinding.ActivityLoginBinding;
import com.idss.cashloans.ui.ViewMoudle.LoginActivityVM;
import com.idss.cashloans.ui.activity.Authentication.AuthenticationActivity;
import com.idss.cashloans.ui.activity.main.MainActivity;
import com.idss.cashloans.ui.activity.webview.ChatServiceActivity;
import com.idss.cashloans.ui.activity.webview.TextViewActivity;

public class LoginActivity extends BaseActivity<LoginActivityVM, ActivityLoginBinding> {
    private static final int REQUEST_CODE = 1;
    private static final String[] PERMISSIONS_STORAGE = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"};
    private String phone, code;
    private String longitude, latitude;

    @Override
    protected void onCreate(View view, Bundle savedInstanceState) {
        //申请权限
        ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_CODE);

        binding.tvAppversion.setText(AppUtils.getVersionName(LoginActivity.this));
    }

    @Override
    protected void initView() {
        binding.btCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone = binding.etAccount.getText().toString();
                if (phone.length() < 11) {
                    Toast.makeText(context, "请检查手机号码输入是否正确", Toast.LENGTH_SHORT).show();
                } else {
                    SharePreferencesUtil.addString(context, Constants.PHONE, phone);
                    viewModel.getLoginAuth(LoginActivity.this, phone);
                }
            }
        });


        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone = binding.etAccount.getText().toString();
                code = binding.etCode.getText().toString();
                if (phone.isEmpty() || code.isEmpty()) {
                    Toast.makeText(context, "请检查输入是否正确", Toast.LENGTH_SHORT).show();
                } else {
                    viewModel.requestLogin(LoginActivity.this, phone, code, longitude, latitude);
                }

            }
        });

        binding.tvXieyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, TextViewActivity.class);
                intent.putExtra("name", "用户协议");
                intent.putExtra("textname", "用户协议.txt");
                startActivity(intent);
            }
        });

        binding.tvZhengce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, TextViewActivity.class);
                intent.putExtra("name", "隐私政策");
                intent.putExtra("textname", "隐私政策.txt");
                startActivity(intent);
            }
        });


        binding.tvService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ChatServiceActivity.class);
                phone = binding.etAccount.getText().toString();
                intent.putExtra("phone", phone);
                LogUtil.e(phone);
                startActivity(intent);
            }
        });


    }


    @Override
    protected void initData() {
        //数据请求成功通知
        viewModel.getLoginModelMutableLiveData().observe(this, new Observer<LoginModel>() {
            @Override
            public void onChanged(LoginModel loginModel) {
                if (SharePreferencesUtil.getBoolean(LoginActivity.this, Constants.IF_AUTHENTICATION, false)) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                } else {
                    startActivity(new Intent(LoginActivity.this, AuthenticationActivity.class));
                }

            }
        });

        viewModel.getLoginAuthModelMutableLiveData().observe(this, new Observer<LoginAuthModel>() {
            @Override
            public void onChanged(LoginAuthModel loginAuthModel) {
                //判断是否认证过,认证过就发验证码，没认证就直接跳转认证界面
                SharePreferencesUtil.addBoolean(LoginActivity.this, Constants.IF_AUTHENTICATION, loginAuthModel.data);
                if (loginAuthModel.data) {
                    viewModel.requestCode(LoginActivity.this, phone, binding.btCode);
                } else {
                    phone = binding.etAccount.getText().toString();
                    code = "";
                    if (phone.isEmpty()) {
                        Toast.makeText(context, "请检查输入是否正确", Toast.LENGTH_SHORT).show();
                    } else {
                        viewModel.requestLogin(LoginActivity.this, phone, code, longitude, latitude);
                    }
                }
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        binding.etAccount.setText(SharePreferencesUtil.getString(LoginActivity.this, Constants.PHONE, ""));
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            //权限的申请结果返回
            case REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    try {
                        getLocations();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }

                } else {
                    //未授权
                    Toast.makeText(this, "授权被拒绝！", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    /**
     * 获取经纬度
     */
    private void getLocations() {
        LocationManager mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE); // 位置
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location mlocation = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER); // 网络

        Log.d("111111LoctianActivity>>", "mLocationManager>>:" + mLocationManager);
        Log.d("111111LoctianActivity>>", "mlocation>>:" + mlocation);

        // 每隔5秒 , 100米 的距离
        Log.d("111111LoctianActivity>>", "点击按钮获取当前经纬度" + "经度:" + mlocation.getLongitude() + "纬度:" + mlocation.getLatitude());
        LogUtil.e("点击按钮获取当前经纬度：" + "经度:" + mlocation.getLongitude() + "纬度:" + mlocation.getLatitude());

        // 求俩个经纬度的距离
        // float[] results=new float[3];
        // Location.distanceBetween(100, 200, 200, 400, results);
        // loctianTv.setText(results[0]+"米");

        // 每隔5秒  2000 米的距离,更新当前位置
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 2000, new LocationListener() {

            //在用户禁用具有定位功能的硬件时被调用
            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                // TODO Auto-generated method stub

            }

            // 位置服务可用
            // 在用户启动具有定位功能的硬件是被调用
            @Override
            public void onProviderEnabled(@NonNull String provider) {
                // TODO Auto-generated method stub
                if (ActivityCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                updateLocation(mLocationManager.getLastKnownLocation(provider));
            }

            //在提供定位功能的硬件状态改变是被调用
            @Override
            public void onProviderDisabled(@NonNull String provider) {
            }

            // 位置改变
            // 在设备的位置改变时被调用
            @Override
            public void onLocationChanged(@NonNull Location location) {
                updateLocation(location);
            }
        });
    }

    private void updateLocation(Location mlocation) {
        if (mlocation != null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("经度:" + String.valueOf(mlocation.getLongitude()));
            stringBuffer.append("纬度:" + mlocation.getLatitude());
            stringBuffer.append("海拔:" + mlocation.getAltitude());
            stringBuffer.append("速度:" + mlocation.getSpeed());
            stringBuffer.append("方向:" + mlocation.getBearing());
            LogUtil.e("过几秒更新当前位置的经纬度：" + stringBuffer);
            longitude = String.valueOf(mlocation.getLongitude());
            latitude = String.valueOf(mlocation.getLatitude());
            Log.d("111111LoctianActivity>>", "stringBuffer:" + stringBuffer);
        } else {
            LogUtil.e("正在获取位置信息");
        }
    }


}