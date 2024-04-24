package com.idss.cashloans.ui.activity.bankcard;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.azhon.basic.base.BaseActivity;
import com.idss.cashloans.databinding.ActivityAddbankcardBinding;
import com.idss.cashloans.ui.ViewMoudle.AddBankcardVM;
import com.idss.cashloans.ui.fragment.BankcardNoticeFragment;

public class AddCardActivity extends BaseActivity<AddBankcardVM, ActivityAddbankcardBinding> {
    private String bankname, bankcard, bankCardUsername;

    @Override
    protected void onCreate(View view, Bundle savedInstanceState) {
        binding.ivBack.setOnClickListener(v -> finish());
    }

    @Override
    protected void initView() {
        binding.tvNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BankcardNoticeFragment bankcardNoticeFragment = new BankcardNoticeFragment(AddCardActivity.this);
                bankcardNoticeFragment.show(AddCardActivity.this.getSupportFragmentManager(), "bankcardNoticeFragment");
            }
        });


        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bankname = binding.editextName.getText().toString();
                bankcard = binding.editextCardnum.getText().toString();
                bankCardUsername = binding.editextBankcardname.getText().toString();
                if (bankname.length() < 3 || bankcard.length() < 16 || bankCardUsername.length() < 2) {
                    Toast.makeText(context, "请检查输入是否正确", Toast.LENGTH_SHORT).show();
                    return;
                }
                //添加银行卡1是默认
                viewModel.addBankcard(AddCardActivity.this, bankname, bankcard, bankCardUsername, 1);
            }
        });
    }

    @Override
    protected void initData() {

    }
}