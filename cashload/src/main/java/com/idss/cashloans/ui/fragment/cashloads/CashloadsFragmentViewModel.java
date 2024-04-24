package com.idss.cashloans.ui.fragment.cashloads;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.azhon.basic.lifecycle.BaseViewModel;
import com.idss.cashloans.api.HttpCallback;
import com.idss.cashloans.api.HttpRequest;
import com.idss.cashloans.api.moudle.HistoryOrdersModel;

public class CashloadsFragmentViewModel extends BaseViewModel {

    private final MutableLiveData<HistoryOrdersModel> historyOrdersModelMutableLiveData = new MutableLiveData<>();

    public void getHistoryOrders() {
        HttpRequest.getInstance().getHistoryOrders("getHistoryOrders", new HttpCallback<HistoryOrdersModel>() {
            @Override
            public void onSuccess(HistoryOrdersModel historyOrdersModel) {
                historyOrdersModelMutableLiveData.setValue(historyOrdersModel);
            }

            @Override
            public void onFailure(String msgCode, String errorMsg) {

            }
        });
    }

    public LiveData<HistoryOrdersModel> getHistoryOrdersLiveData() {
        return historyOrdersModelMutableLiveData;
    }
}