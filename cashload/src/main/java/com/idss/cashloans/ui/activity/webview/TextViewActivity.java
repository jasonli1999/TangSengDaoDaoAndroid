package com.idss.cashloans.ui.activity.webview;

import android.os.Bundle;
import android.view.View;

import com.azhon.basic.base.BaseActivity;
import com.idss.cashloans.api.utils.LogUtil;
import com.idss.cashloans.databinding.ActivityTextViewBinding;
import com.idss.cashloans.ui.ViewMoudle.TextViewActivityVM;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class TextViewActivity extends BaseActivity<TextViewActivityVM, ActivityTextViewBinding> {
    @Override
    protected void onCreate(View view, Bundle savedInstanceState) {
        binding.ivBack.setOnClickListener(v -> finish());
        binding.tvMsg.setText(getIntent().getStringExtra("name"));
    }

    @Override
    protected void initView() {
        try {
            InputStream inputStream = getAssets().open(Objects.requireNonNull(getIntent().getStringExtra("textname"))); // 替换为你的txt文件名
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String content = new String(buffer, StandardCharsets.UTF_8);
            binding.txtContent.setText(content);
        } catch (IOException e) {
            LogUtil.e(e.toString());
            e.printStackTrace();
        }
    }


    @Override
    protected void initData() {

    }
}