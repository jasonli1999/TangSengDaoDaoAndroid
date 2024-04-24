package com.idss.cashloans.ui.activity.aboutbills;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;

import com.azhon.basic.base.BaseActivity;
import com.idss.cashloans.R;
import com.idss.cashloans.api.moudle.OrderDetailModel;
import com.idss.cashloans.databinding.ActivityOrderDetailBinding;
import com.idss.cashloans.ui.ViewMoudle.OrderDetailActivityVM;

/**
 * 订单详情
 */
public class OrderDetailActivity extends BaseActivity<OrderDetailActivityVM, ActivityOrderDetailBinding> {

    @Override
    protected void onCreate(View view, Bundle savedInstanceState) {
        binding.ivBack.setOnClickListener(v -> finish());
    }

    @Override
    protected void initView() {
        viewModel.getOrderDetail(getIntent().getStringExtra("id"));
    }

    @Override
    protected void initData() {
        viewModel.getOrderDetail().observe(this, dataDTO -> {
            binding.tvOrdernum.setText(dataDTO.getOrderNo());
            binding.tvBanknum.setText(dataDTO.getTenantId());
            binding.tvLendTime.setText(dataDTO.getCreateTime());
            binding.tvRepayloans.setText(dataDTO.getTotalAmount());
            binding.tvInterest.setText(dataDTO.getLatePaymentFee());
            binding.tvOverdueFee.setText(dataDTO.getLatePaymentFee());
            binding.tvRepaymentTime.setText(dataDTO.getExpirationTime());

            /**
             * 审核状态（0 未提交 1已提交未审核 2初审通过待复审 3复审中  4黑名单 5复审通过待终审 6终审中 7被拒 8终审通过放款中 9放款成功待还款 10已结清 11放款失败 12已逾期")
             */
            switch (dataDTO.getAuditStatus()) {
                case 0:
                    binding.tvStatue.setText("未提交");
                    binding.ivStatue.setImageResource(R.mipmap.yuqi);
                    binding.tvStatue.setTextColor(getResources().getColor(R.color.yellow_e9910d));
                    break;
                case 1:
                    binding.tvStatue.setText("已提交未审核");
                    binding.ivStatue.setImageResource(R.mipmap.yuqi);
                    binding.tvStatue.setTextColor(getResources().getColor(R.color.yellow_e9910d));
                    break;
                case 2:
                    binding.tvStatue.setText("初审通过待复审");
                    binding.ivStatue.setImageResource(R.mipmap.yuqi);
                    binding.tvStatue.setTextColor(getResources().getColor(R.color.yellow_e9910d));
                    break;
                case 3:
                    binding.tvStatue.setText("复审中");
                    binding.ivStatue.setImageResource(R.mipmap.yuqi);
                    binding.tvStatue.setTextColor(getResources().getColor(R.color.yellow_e9910d));

                    break;
                case 4:
                    binding.tvStatue.setText("黑名单");
                    binding.ivStatue.setImageResource(R.mipmap.yuqi);
                    binding.tvStatue.setTextColor(getResources().getColor(R.color.red_d30700));
                    break;
                case 5:
                    binding.tvStatue.setText("复审通过待终审");
                    binding.ivStatue.setImageResource(R.mipmap.yuqi);
                    binding.tvStatue.setTextColor(getResources().getColor(R.color.yellow_e9910d));
                    break;
                case 6:
                    binding.tvStatue.setText("终审中");
                    binding.ivStatue.setImageResource(R.mipmap.yuqi);
                    binding.tvStatue.setTextColor(getResources().getColor(R.color.yellow_e9910d));
                    break;
                case 7:
                    binding.tvStatue.setText("被拒");
                    binding.ivStatue.setImageResource(R.mipmap.yuqi);
                    binding.tvStatue.setTextColor(getResources().getColor(R.color.red_d30700));

                    break;
                case 8:
                    binding.tvStatue.setText("终审通过放款中");
                    binding.ivStatue.setImageResource(R.mipmap.yuqi);
                    binding.tvStatue.setTextColor(getResources().getColor(R.color.yellow_e9910d));
                    break;
                case 9:
                    binding.tvStatue.setText("放款成功待还款");
                    binding.tvStatue.setTextColor(getResources().getColor(R.color.yellow_e9910d));
                    binding.ivStatue.setImageResource(R.mipmap.daihuankuan);
                    break;
                case 10:
                    binding.tvStatue.setText("已结清");
                    binding.tvStatue.setTextColor(getResources().getColor(R.color.green_2d9500));
                    binding.ivStatue.setImageResource(R.mipmap.yihuankuan);
                    break;
                case 11:
                    binding.ivStatue.setImageResource(R.mipmap.yuqi);
                    binding.tvStatue.setText("放款失败");
                    binding.tvStatue.setTextColor(getResources().getColor(R.color.red_d30700));
                    break;
                case 12:
                    binding.tvStatue.setText("已逾期");
                    binding.tvStatue.setTextColor(getResources().getColor(R.color.red_d30700));
                    binding.ivStatue.setImageResource(R.mipmap.yuqi);
                    break;
            }

        });
    }
}