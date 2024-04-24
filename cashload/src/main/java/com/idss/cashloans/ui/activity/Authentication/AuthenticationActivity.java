package com.idss.cashloans.ui.activity.Authentication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;

import com.azhon.basic.base.BaseActivity;
import com.idss.cashloans.R;
import com.idss.cashloans.api.Constants;
import com.idss.cashloans.api.utils.SharePreferencesUtil;
import com.idss.cashloans.databinding.ActivityAuthenticationBinding;
import com.idss.cashloans.ui.ViewMoudle.AuthenticationActivityVM;

/**
 * 认证流程面页
 */
public class AuthenticationActivity extends BaseActivity<AuthenticationActivityVM, ActivityAuthenticationBinding> {
    private int step = 0;

    @Override
    protected void onCreate(View view, Bundle savedInstanceState) {
    }

    @Override
    protected void initView() {
        binding.ivBack.setOnClickListener(v -> finish());
        binding.btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (step) {
                    case 1:  //  先身份证 再人脸
                        startActivity(new Intent(AuthenticationActivity.this, Step1Activity.class));
                        break;
                    case 2:  // 通讯录
                        startActivity(new Intent(AuthenticationActivity.this, Step2Activity.class));
                        break;
                    case 3:   // 紧急联系人
                        startActivity(new Intent(AuthenticationActivity.this, Step3Activity.class));
                        break;
                    case 4:  // 绑定银行卡
                        startActivity(new Intent(AuthenticationActivity.this, Step4BingdingCardActivity.class));
                        break;
                    case 5:   //运营商认证
                        startActivity(new Intent(AuthenticationActivity.this, Step5WebViewActivity.class));
                        break;

                }
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        viewModel.requestCertification();
    }

    @Override
    protected void initData() {
        //数据请求成功通知,判断是认证的第几步
        viewModel.getMutableLiveData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                step = integer;
                switch (step) {
                    case 1:
                        binding.tvMsg.setText("实人认证");
                        binding.circleYellow1.setBackgroundResource(R.mipmap.yellow);
                        binding.circleYellow2.setBackgroundResource(R.mipmap.gray);
                        binding.circleYellow3.setBackgroundResource(R.mipmap.gray);
                        binding.circleYellow4.setBackgroundResource(R.mipmap.gray);
                        binding.circleYellow5.setBackgroundResource(R.mipmap.gray);

                        binding.tvStep1.setTextColor(getColor(R.color.white));
                        binding.tvStep2.setTextColor(getColor(R.color.gray_8b8b8b));
                        binding.tvStep3.setTextColor(getColor(R.color.gray_8b8b8b));
                        binding.tvStep4.setTextColor(getColor(R.color.gray_8b8b8b));
                        binding.tvStep5.setTextColor(getColor(R.color.gray_8b8b8b));

                        binding.tvName1.setTextColor(getColor(R.color.yellow_ffc78b06));
                        binding.tvName2.setTextColor(getColor(R.color.gray_ff8b8b8b));
                        binding.tvName3.setTextColor(getColor(R.color.gray_ff8b8b8b));
                        binding.tvName4.setTextColor(getColor(R.color.gray_ff8b8b8b));
                        binding.tvName5.setTextColor(getColor(R.color.gray_ff8b8b8b));
                        binding.ivStep.setImageResource(R.mipmap.step1);
                        break;
                    case 2:
                        binding.circleYellow1.setBackgroundResource(R.mipmap.duihao);
                        binding.circleYellow2.setBackgroundResource(R.mipmap.yellow);
                        binding.circleYellow3.setBackgroundResource(R.mipmap.gray);
                        binding.circleYellow4.setBackgroundResource(R.mipmap.gray);
                        binding.circleYellow5.setBackgroundResource(R.mipmap.gray);

                        binding.tvStep1.setVisibility(View.GONE);
                        binding.tvStep2.setTextColor(getColor(R.color.white));
                        binding.tvStep3.setTextColor(getColor(R.color.gray_8b8b8b));
                        binding.tvStep4.setTextColor(getColor(R.color.gray_8b8b8b));
                        binding.tvStep5.setTextColor(getColor(R.color.gray_8b8b8b));

                        binding.tvMsg.setText("通讯信息");
                        binding.tvName1.setTextColor(getColor(R.color.yellow_ffc78b06));
                        binding.tvName2.setTextColor(getColor(R.color.yellow_ffc78b06));
                        binding.tvName3.setTextColor(getColor(R.color.gray_ff8b8b8b));
                        binding.tvName4.setTextColor(getColor(R.color.gray_ff8b8b8b));
                        binding.tvName5.setTextColor(getColor(R.color.gray_ff8b8b8b));
                        binding.ivStep.setImageResource(R.mipmap.step2);
                        break;
                    case 3:
                        binding.circleYellow1.setBackgroundResource(R.mipmap.duihao);
                        binding.circleYellow2.setBackgroundResource(R.mipmap.duihao);
                        binding.circleYellow3.setBackgroundResource(R.mipmap.yellow);
                        binding.circleYellow4.setBackgroundResource(R.mipmap.gray);
                        binding.circleYellow5.setBackgroundResource(R.mipmap.gray);

                        binding.tvStep1.setVisibility(View.GONE);
                        binding.tvStep2.setVisibility(View.GONE);
                        binding.tvStep3.setTextColor(getColor(R.color.white));
                        binding.tvStep4.setTextColor(getColor(R.color.gray_8b8b8b));
                        binding.tvStep5.setTextColor(getColor(R.color.gray_8b8b8b));


                        binding.tvMsg.setText("紧急联系信息");
                        binding.tvName1.setTextColor(getColor(R.color.yellow_ffc78b06));
                        binding.tvName2.setTextColor(getColor(R.color.yellow_ffc78b06));
                        binding.tvName3.setTextColor(getColor(R.color.yellow_ffc78b06));
                        binding.tvName4.setTextColor(getColor(R.color.gray_ff8b8b8b));
                        binding.tvName5.setTextColor(getColor(R.color.gray_ff8b8b8b));
                        binding.ivStep.setImageResource(R.mipmap.step3);
                        break;
                    case 4:
                        binding.circleYellow1.setBackgroundResource(R.mipmap.duihao);
                        binding.circleYellow2.setBackgroundResource(R.mipmap.duihao);
                        binding.circleYellow3.setBackgroundResource(R.mipmap.duihao);
                        binding.circleYellow4.setBackgroundResource(R.mipmap.yellow);
                        binding.circleYellow5.setBackgroundResource(R.mipmap.gray);
                        binding.tvStep1.setVisibility(View.GONE);
                        binding.tvStep2.setVisibility(View.GONE);
                        binding.tvStep3.setVisibility(View.GONE);
                        binding.tvStep4.setTextColor(getColor(R.color.white));
                        binding.tvStep5.setTextColor(getColor(R.color.gray_8b8b8b));
                        binding.tvMsg.setText("绑定银行卡");
                        binding.tvName1.setTextColor(getColor(R.color.yellow_ffc78b06));
                        binding.tvName2.setTextColor(getColor(R.color.yellow_ffc78b06));
                        binding.tvName3.setTextColor(getColor(R.color.yellow_ffc78b06));
                        binding.tvName4.setTextColor(getColor(R.color.yellow_ffc78b06));
                        binding.tvName5.setTextColor(getColor(R.color.gray_ff8b8b8b));
                        binding.ivStep.setImageResource(R.mipmap.step4);
                        break;
                    case 5:
                        binding.circleYellow1.setBackgroundResource(R.mipmap.duihao);
                        binding.circleYellow2.setBackgroundResource(R.mipmap.duihao);
                        binding.circleYellow3.setBackgroundResource(R.mipmap.duihao);
                        binding.circleYellow4.setBackgroundResource(R.mipmap.duihao);
                        binding.circleYellow5.setBackgroundResource(R.mipmap.yellow);
                        binding.tvStep1.setVisibility(View.GONE);
                        binding.tvStep2.setVisibility(View.GONE);
                        binding.tvStep3.setVisibility(View.GONE);
                        binding.tvStep4.setVisibility(View.GONE);
                        binding.tvStep5.setTextColor(getColor(R.color.white));

                        binding.tvMsg.setText("运营商认证");
                        binding.tvName1.setTextColor(getColor(R.color.yellow_ffc78b06));
                        binding.tvName2.setTextColor(getColor(R.color.yellow_ffc78b06));
                        binding.tvName3.setTextColor(getColor(R.color.yellow_ffc78b06));
                        binding.tvName4.setTextColor(getColor(R.color.yellow_ffc78b06));
                        binding.tvName5.setTextColor(getColor(R.color.yellow_ffc78b06));
                        binding.ivStep.setImageResource(R.mipmap.step5);
                        break;
                }
            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}