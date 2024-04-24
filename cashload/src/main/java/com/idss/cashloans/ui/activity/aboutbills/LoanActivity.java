package com.idss.cashloans.ui.activity.aboutbills;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import androidx.lifecycle.Observer;

import com.azhon.basic.base.BaseActivity;
import com.idss.cashloans.api.Constants;
import com.idss.cashloans.api.moudle.LoanLimitModel;
import com.idss.cashloans.api.utils.SharePreferencesUtil;
import com.idss.cashloans.api.utils.SoundUtils;
import com.idss.cashloans.databinding.ActivityLoanBinding;
import com.idss.cashloans.ui.ViewMoudle.LoanActivityVM;
import com.idss.cashloans.ui.activity.main.MainActivity;

/**
 * 申请借款
 */
public class LoanActivity extends BaseActivity<LoanActivityVM, ActivityLoanBinding> {
    private LoanLimitModel loanLimitModel;
    private int progressing;
    private int limitAmount;  //最大金额
    private int lowerAmount;   //最低金额
    private int serialAmount;  //加减幅度


    @Override
    protected void onCreate(View view, Bundle savedInstanceState) {
        binding.ivBack.setOnClickListener(v -> finish());
        binding.ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != loanLimitModel) {
                    progressing = progressing + serialAmount;
                    if (progressing > limitAmount) {
                        progressing = limitAmount;
                    }
                    binding.sbSeekBar.setProgress(progressing);
                    binding.tvLoans.setText(" ¥ " + String.valueOf(progressing));
                    if (SharePreferencesUtil.getInteger(LoanActivity.this, Constants.IS_OLDUSER, 999) == 0) {
                        binding.llGetbalance.setVisibility(View.GONE);
                        binding.llServicefee.setVisibility(View.GONE);
                    } else {
                        binding.llGetbalance.setVisibility(View.VISIBLE);
                        binding.llServicefee.setVisibility(View.VISIBLE);
                        binding.tvGetMoney.setText(" ¥ " + Math.round(progressing * (1 - loanLimitModel.getData().getServiceFee())));
                        binding.tvServiceFee.setText(" ¥ " + Math.round(progressing * loanLimitModel.getData().getServiceFee()));
                    }

                    binding.tvRepayment.setText(" ¥ " + String.valueOf(progressing));
                }
            }
        });

        binding.ivReduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != loanLimitModel) {
                    progressing = progressing - serialAmount;
                    if (progressing < lowerAmount) {
                        progressing = lowerAmount;
                    }
                    binding.sbSeekBar.setProgress(progressing);
                    binding.tvLoans.setText(" ¥ " + progressing);
                    if (SharePreferencesUtil.getInteger(LoanActivity.this, Constants.IS_OLDUSER, 999) == 0) {
                        binding.llGetbalance.setVisibility(View.GONE);
                        binding.llServicefee.setVisibility(View.GONE);
                    } else {
                        binding.llGetbalance.setVisibility(View.VISIBLE);
                        binding.llServicefee.setVisibility(View.VISIBLE);
                        binding.tvGetMoney.setText(" ¥ " + Math.round(progressing * (1 - loanLimitModel.getData().getServiceFee())));
                        binding.tvServiceFee.setText(" ¥ " + Math.round(progressing * loanLimitModel.getData().getServiceFee()));
                    }
                    binding.tvRepayment.setText(" ¥ " + progressing);
                }
            }
        });


    }

    @Override
    protected void initView() {
        viewModel.getLoanLimit();

        binding.sbSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (null != loanLimitModel) {
                    progressing = progress * serialAmount;
                    if (progressing > limitAmount) progressing = limitAmount;
                    if (progressing < lowerAmount) progressing = lowerAmount;
                    binding.tvLoans.setText(" ¥ " + progressing);
                    if (SharePreferencesUtil.getInteger(LoanActivity.this, Constants.IS_OLDUSER, 999) == 0) {
                        binding.llGetbalance.setVisibility(View.GONE);
                        binding.llServicefee.setVisibility(View.GONE);
                    } else {
                        binding.llGetbalance.setVisibility(View.VISIBLE);
                        binding.llServicefee.setVisibility(View.VISIBLE);
                        binding.tvGetMoney.setText(" ¥ " + Math.round(progressing * (1 - loanLimitModel.getData().getServiceFee())));
                        binding.tvServiceFee.setText(" ¥ " + Math.round(progressing * loanLimitModel.getData().getServiceFee()));
                    }
                    binding.tvRepayment.setText(" ¥ " + progressing);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


        binding.btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.createOrder(LoanActivity.this, String.valueOf(progressing));
            }
        });
    }

    @Override
    protected void initData() {
        viewModel.getLoanLimitData().observe(this, new Observer<LoanLimitModel>() {
            @Override
            public void onChanged(LoanLimitModel loanLimitModeldata) {
                loanLimitModel = loanLimitModeldata;
                progressing = loanLimitModeldata.getData().getInitAmount();
                lowerAmount = loanLimitModeldata.getData().getLowerAmount();
                limitAmount = loanLimitModeldata.getData().getLimitAmount();
                serialAmount = loanLimitModeldata.getData().getSerialAmount();

                binding.tvLoans.setText(" ¥ " + loanLimitModeldata.getData().getInitAmount());
                binding.tvDays.setText(loanLimitModeldata.getData().getLengPeriod() + "天");
                binding.tvRepaydate.setText(loanLimitModeldata.getData().getRepaymentTime());
                if (SharePreferencesUtil.getInteger(LoanActivity.this, Constants.IS_OLDUSER, 999) == 0) {
                    binding.llGetbalance.setVisibility(View.GONE);
                    binding.llServicefee.setVisibility(View.GONE);
                } else {
                    binding.llGetbalance.setVisibility(View.VISIBLE);
                    binding.llServicefee.setVisibility(View.VISIBLE);
                    binding.tvGetMoney.setText(" ¥ " + Math.round(progressing * (1 - loanLimitModel.getData().getServiceFee())));
                    binding.tvServiceFee.setText(" ¥ " + Math.round(progressing * loanLimitModel.getData().getServiceFee()));
                }
                binding.tvRepayment.setText(" ¥ " + progressing);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.marqueeView.startMarquee();
        /**
         *  播放音頻
         */
        SoundUtils soundUtils = new SoundUtils();
        soundUtils.playAudio_money(LoanActivity.this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        binding.marqueeView.stopMarquee();
    }


}