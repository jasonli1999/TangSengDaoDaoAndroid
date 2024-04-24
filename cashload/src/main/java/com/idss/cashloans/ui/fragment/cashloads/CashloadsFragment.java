package com.idss.cashloans.ui.fragment.cashloads;

import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.azhon.basic.base.BaseFragment;
import com.idss.cashloans.api.moudle.HistoryOrdersModel;
import com.idss.cashloans.databinding.FragmentDashboardBinding;
import com.idss.cashloans.ui.adapter.CashfragmentAdapter;

import java.util.List;

public class CashloadsFragment extends BaseFragment<CashloadsFragmentViewModel, FragmentDashboardBinding> {
    private List<HistoryOrdersModel.DataDTO.RecordsDTO> records;
    private CashfragmentAdapter historyOrderAdapter;

    @Override
    protected void initView(View view) {
        viewModel.getHistoryOrders();
    }

    @Override
    protected void initData() {
        viewModel.getHistoryOrdersLiveData().observe(this, new Observer<HistoryOrdersModel>() {
            @Override
            public void onChanged(HistoryOrdersModel historyOrdersModel) {
                records = historyOrdersModel.getData().getRecords();
                if (!records.isEmpty()) {
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    //竖直方向
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    //设置item显示方法
                    binding.recyclerView.setLayoutManager(layoutManager);
                    historyOrderAdapter = new CashfragmentAdapter(getActivity(), records);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}