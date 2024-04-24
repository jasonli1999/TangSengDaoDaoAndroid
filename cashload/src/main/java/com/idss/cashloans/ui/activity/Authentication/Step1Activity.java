package com.idss.cashloans.ui.activity.Authentication;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;

import com.azhon.basic.base.BaseActivity;
import com.idss.cashloans.api.Constants;
import com.idss.cashloans.api.utils.SharePreferencesUtil;
import com.idss.cashloans.databinding.ActivityIdcardBinding;
import com.idss.cashloans.ui.ViewMoudle.Step1ActivityVM;

/**
 * 验证身份证
 */
public class Step1Activity extends BaseActivity<Step1ActivityVM, ActivityIdcardBinding> {
    private static final String[] PERMISSIONS_STORAGE = {"android.permission.CAMERA", "android.permission.WRITE_SETTINGS"};
    private static final int REQUEST_CODE = 1;


    @Override
    protected void onCreate(View view, Bundle savedInstanceState) {
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (!isChinese(source.charAt(i))) {
                        return "";
                    }
                }
                return null;
            }

            private boolean isChinese(char c) {
                return c >= 0x4E00 && c <= 0x9FA5;
            }
        };
        binding.editextName.setFilters(new InputFilter[]{filter});

        binding.ivBack.setOnClickListener(v -> finish());
    }

    @Override
    protected void initView() {
        //申请权限
        ActivityCompat.requestPermissions(Step1Activity.this, PERMISSIONS_STORAGE, REQUEST_CODE);

        binding.btnFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTrue = binding.editextName.getText().toString();
                String idCardNo = binding.editextCardnum.getText().toString();
                if (!nameTrue.isEmpty() && idCardNo.length() == 18) {
                    SharePreferencesUtil.addString(context, Constants.USER_NAME, nameTrue);
                    //获取id
                    viewModel.requestCertifyId(nameTrue, idCardNo);
                } else {
                    Toast.makeText(context, "请检查输入是否正确.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void initData() {
        //数据请求成功通知
        viewModel.getMutableLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String certifyId) {
                //统计次数
                viewModel.edit();
                //人脸识别
                viewModel.requestFace(Step1Activity.this, certifyId);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            //权限的申请结果返回
            case REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    //未授权
                    Toast.makeText(this, "申请相机权限被拒绝,这将导致审核失败！", Toast.LENGTH_SHORT).show();
                }
            }
            break;
        }
    }


}