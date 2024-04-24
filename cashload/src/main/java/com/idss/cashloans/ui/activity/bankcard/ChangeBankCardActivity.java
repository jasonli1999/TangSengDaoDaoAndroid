package com.idss.cashloans.ui.activity.bankcard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.azhon.basic.base.BaseActivity;
import com.idss.cashloans.api.moudle.BankcardsModel;
import com.idss.cashloans.databinding.ActivityChangeBankCardBinding;
import com.idss.cashloans.ui.ViewMoudle.ChangeBankCardActivityVM;
import com.idss.cashloans.ui.activity.interfaces.changeBankcardtInterface;
import com.idss.cashloans.ui.adapter.BankCardsAdapter;

/**
 * 更换银行卡
 */
public class ChangeBankCardActivity extends BaseActivity<ChangeBankCardActivityVM, ActivityChangeBankCardBinding> implements changeBankcardtInterface {
    @Override
    protected void onCreate(View view, Bundle savedInstanceState) {
        binding.ivBack.setOnClickListener(v -> finish());
        binding.ivAddBankCard.setOnClickListener(v -> startActivity(new Intent(this, AddCardActivity.class)));
    }

    @Override
    protected void initView() {
        viewModel.getBankCard(ChangeBankCardActivity.this);
    }

    @Override
    protected void initData() {
        viewModel.getBankCard().observe(this, new Observer<BankcardsModel>() {
            @Override
            public void onChanged(BankcardsModel bankcardsModel) {
                if (!bankcardsModel.data.records.isEmpty()) {
                    LinearLayoutManager layoutManager = new LinearLayoutManager(ChangeBankCardActivity.this);
                    //竖直方向
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    //设置item显示方法
                    binding.recyclerView.setLayoutManager(layoutManager);
                    BankCardsAdapter bankCardAdapter = new BankCardsAdapter(ChangeBankCardActivity.this, bankcardsModel.data.records,getSupportFragmentManager());
                    binding.recyclerView.setAdapter(bankCardAdapter);


                    binding.recyclerView.setVisibility(View.VISIBLE);
                    binding.ivNodata.setVisibility(View.GONE);

                } else {
                    binding.recyclerView.setVisibility(View.GONE);
                    binding.ivNodata.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    @Override
    public void noticePoit(int code) {
        initView();
    }
}