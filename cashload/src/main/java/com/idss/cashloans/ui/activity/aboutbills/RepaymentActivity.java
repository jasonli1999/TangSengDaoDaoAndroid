package com.idss.cashloans.ui.activity.aboutbills;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.Observer;

import com.azhon.basic.base.BaseActivity;
import com.idss.cashloans.api.Constants;
import com.idss.cashloans.api.moudle.AwaitingRepaymentModel;
import com.idss.cashloans.api.moudle.PaymentModel;
import com.idss.cashloans.api.utils.SharePreferencesUtil;
import com.idss.cashloans.databinding.ActivityRepaymentBinding;
import com.idss.cashloans.ui.ViewMoudle.ZhanqiActivityVM;

public class RepaymentActivity extends BaseActivity<ZhanqiActivityVM, ActivityRepaymentBinding> {
    private AwaitingRepaymentModel awaitingRepaymentModel;
    private PaymentModel paymentModels;

    @Override
    protected void onCreate(View view, Bundle savedInstanceState) {
        binding.ivBack.setOnClickListener(v -> finish());
        awaitingRepaymentModel = (AwaitingRepaymentModel) getIntent().getSerializableExtra("awaitingRepaymentModels");
        binding.tvOverdueFee.setText(" ¥ " + awaitingRepaymentModel.getData().getLatePaymentFee());
        binding.tvPaid.setText(" ¥ " + awaitingRepaymentModel.getData().getRepaymentAmount());
        binding.tvLoans.setText(" ¥ " + awaitingRepaymentModel.getData().getRepayableAmount());

        binding.btnRepayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null == awaitingRepaymentModel || null == paymentModels) {
                    Toast.makeText(RepaymentActivity.this, "数据配置错误", Toast.LENGTH_SHORT).show();
                } else {
                    viewModel.repayment(RepaymentActivity.this, paymentModels.getData().get(0).getAlias(), awaitingRepaymentModel.getData().getOrderNo(), "2", SharePreferencesUtil.getString(RepaymentActivity.this, Constants.MEMBERID, ""));

                }
            }
        });
    }

    @Override
    protected void initView() {
        viewModel.getpaymentMethod();
    }

    @Override
    protected void initData() {
        viewModel.getMutableLiveData().observe(this, new Observer<PaymentModel>() {
            @Override
            public void onChanged(PaymentModel paymentModel) {
                paymentModels = paymentModel;
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