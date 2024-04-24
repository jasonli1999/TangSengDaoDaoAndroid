package com.idss.cashloans.ui.ViewMoudle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.azhon.basic.lifecycle.BaseViewModel;
import com.idss.cashloans.api.HttpCallback;
import com.idss.cashloans.api.HttpRequest;
import com.idss.cashloans.api.moudle.OrderDetailModel;

public class OrderDetailActivityVM extends BaseViewModel {

    private final MutableLiveData<OrderDetailModel.DataDTO> mutableLiveData = new MutableLiveData<>();

    public void getOrderDetail(String id) {
        HttpRequest.getInstance().getOrderDetail("getOrderDetail", id, new HttpCallback<OrderDetailModel>() {
            @Override
            public void onSuccess(OrderDetailModel orderDetailModel) {
                mutableLiveData.setValue(orderDetailModel.getData());
            }

            @Override
            public void onFailure(String msgCode, String errorMsg) {

            }
        });
    }

    public LiveData<OrderDetailModel.DataDTO> getOrderDetail() {
        return mutableLiveData;
    }

}
