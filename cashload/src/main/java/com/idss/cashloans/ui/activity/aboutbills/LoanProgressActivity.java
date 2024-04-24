package com.idss.cashloans.ui.activity.aboutbills;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.azhon.basic.base.BaseActivity;
import com.idss.cashloans.api.moudle.LoanProgressModel;
import com.idss.cashloans.databinding.ActivityLoanProgressBinding;
import com.idss.cashloans.ui.ViewMoudle.LoanProgressActivityVM;
import com.idss.cashloans.ui.adapter.LoanProgressAdapter;

public class LoanProgressActivity extends BaseActivity<LoanProgressActivityVM, ActivityLoanProgressBinding> {
    @Override
    protected void onCreate(View view, Bundle savedInstanceState) {
        binding.ivBack.setOnClickListener(v -> finish());
    }

    @Override
    protected void initView() {
        viewModel.getLoanProgress(getIntent().getStringExtra("id"));
    }

    @Override
    protected void initData() {
        viewModel.getLoanProgress().observe(this, new Observer<LoanProgressModel>() {
            @Override
            public void onChanged(LoanProgressModel loanProgressModel) {
                if (null == loanProgressModel.getData()) {
                    return;
                }
                if (!loanProgressModel.getData().isEmpty()) {
                    LinearLayoutManager layoutManager = new LinearLayoutManager(LoanProgressActivity.this);
                    //竖直方向
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    //设置item显示方法
                    binding.recyclerView.setLayoutManager(layoutManager);
                    LoanProgressAdapter adapter = new LoanProgressAdapter(LoanProgressActivity.this, loanProgressModel);
                    binding.recyclerView.setAdapter(adapter);
                    binding.recyclerView.setVisibility(View.VISIBLE);
                    binding.ivNodata.setVisibility(View.GONE);
                } else {
                    binding.recyclerView.setVisibility(View.GONE);
                    binding.ivNodata.setVisibility(View.VISIBLE);
                }

            }
        });
    }
}