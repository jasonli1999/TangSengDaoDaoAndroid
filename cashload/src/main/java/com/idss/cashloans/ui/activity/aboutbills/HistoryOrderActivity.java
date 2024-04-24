package com.idss.cashloans.ui.activity.aboutbills;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.azhon.basic.base.BaseActivity;
import com.idss.cashloans.api.moudle.HistoryOrdersModel;
import com.idss.cashloans.databinding.ActivityHistoryOrderBinding;
import com.idss.cashloans.ui.ViewMoudle.HistoryOrderActivityVM;
import com.idss.cashloans.ui.adapter.HistoryOrderAdapter;

import java.util.List;

public class HistoryOrderActivity extends BaseActivity<HistoryOrderActivityVM, ActivityHistoryOrderBinding> {
    private List<HistoryOrdersModel.DataDTO.RecordsDTO> records;
    private HistoryOrderAdapter historyOrderAdapter;

    @Override
    protected void onCreate(View view, Bundle savedInstanceState) {
        binding.ivBack.setOnClickListener(v -> finish());
    }

    @Override
    protected void initView() {
        viewModel.getHistoryOrders();
    }

    @Override
    protected void initData() {
        viewModel.getHistoryOrdersLiveData().observe(this, new Observer<HistoryOrdersModel>() {
            @Override
            public void onChanged(HistoryOrdersModel historyOrdersModel) {
                if (null==historyOrdersModel.getData()){
                    return;
                }
                records = historyOrdersModel.getData().getRecords();
                if (!records.isEmpty()) {
                    LinearLayoutManager layoutManager = new LinearLayoutManager(HistoryOrderActivity.this);
                    //竖直方向
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    //设置item显示方法
                    binding.recyclerView.setLayoutManager(layoutManager);
                    historyOrderAdapter = new HistoryOrderAdapter(HistoryOrderActivity.this, records);
                    binding.recyclerView.setAdapter(historyOrderAdapter);
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