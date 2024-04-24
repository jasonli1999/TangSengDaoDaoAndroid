package com.idss.cashloans.ui.activity.Authentication;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.azhon.basic.base.BaseActivity;
import com.idss.cashloans.databinding.ActivityStep4Binding;
import com.idss.cashloans.ui.ViewMoudle.Step4ActivityVM;
import com.idss.cashloans.ui.fragment.BankcardNoticeFragment;

public class Step4BingdingCardActivity extends BaseActivity<Step4ActivityVM, ActivityStep4Binding> {
    private String bankname, bankcard;

    @Override
    protected void onCreate(View view, Bundle savedInstanceState) {
        binding.ivBack.setOnClickListener(v -> finish());
    }

    @Override
    protected void initView() {
        binding.tvNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BankcardNoticeFragment bankcardNoticeFragment = new BankcardNoticeFragment(Step4BingdingCardActivity.this);
                bankcardNoticeFragment.show(Step4BingdingCardActivity.this.getSupportFragmentManager(), "bankcardNoticeFragment");
            }
        });


        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bankname = binding.editextName.getText().toString();
                bankcard = binding.editextCardnum.getText().toString();
                if (bankname.length() < 3 || bankcard.length() < 16) {
                    Toast.makeText(context, "请检查输入是否正确", Toast.LENGTH_SHORT).show();
                    return;
                }
                viewModel.certification4(Step4BingdingCardActivity.this, bankname, bankcard);
            }
        });
    }

    @Override
    protected void initData() {

    }
}