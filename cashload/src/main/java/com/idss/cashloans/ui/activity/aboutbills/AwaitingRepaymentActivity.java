package com.idss.cashloans.ui.activity.aboutbills;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;

import com.azhon.basic.base.BaseActivity;
import com.idss.cashloans.R;
import com.idss.cashloans.api.moudle.AwaitingRepaymentModel;
import com.idss.cashloans.databinding.ActivityAwaitingRepaymentBinding;
import com.idss.cashloans.ui.ViewMoudle.AwaitingRepaymentVM;

public class AwaitingRepaymentActivity extends BaseActivity<AwaitingRepaymentVM, ActivityAwaitingRepaymentBinding> {
    private AwaitingRepaymentModel awaitingRepaymentModels;

    @Override
    protected void onCreate(View view, Bundle savedInstanceState) {
        binding.ivBack.setOnClickListener(v -> finish());
        binding.btnZhanqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AwaitingRepaymentActivity.this, ZhanqiActivity.class);
                intent.putExtra("awaitingRepaymentModels", awaitingRepaymentModels);
                startActivity(intent);
            }
        });

        binding.btnRepayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AwaitingRepaymentActivity.this, RepaymentActivity.class);
                intent.putExtra("awaitingRepaymentModels", awaitingRepaymentModels);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void initView() {
        viewModel.getawaitingRepayment();
    }

    @Override
    protected void initData() {
        viewModel.getAwaitingRepaymentModelMutableLiveData().observe(this, new Observer<AwaitingRepaymentModel>() {
            @Override
            public void onChanged(AwaitingRepaymentModel awaitingRepaymentModel) {
                awaitingRepaymentModels = awaitingRepaymentModel;
                binding.tvLoanStatue.setText("已逾期:" + awaitingRepaymentModel.getData().getOverdueDays() + "天");
                binding.tvLoanStatue.setTextColor(getResources().getColor(R.color.red_d30700));
                binding.tvLoans.setText(" ¥ " + String.valueOf(awaitingRepaymentModel.getData().getRepayableAmount()));
                binding.tvGetMoney.setText(String.valueOf(awaitingRepaymentModel.getData().getActualAmount()));
                binding.tvServiceFee.setText(String.valueOf(awaitingRepaymentModel.getData().getServiceFee()));
                binding.tvLenddate.setText(String.valueOf(awaitingRepaymentModel.getData().getReleaseTime()));
                binding.tvRepaydate.setText(String.valueOf(awaitingRepaymentModel.getData().getExpirationTime()));
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        binding.marqueeView.startMarquee();
    }

    @Override
    protected void onStop() {
        super.onStop();
        binding.marqueeView.stopMarquee();
    }
}