package com.idss.cashloans.ui.ViewMoudle;

import androidx.lifecycle.MutableLiveData;

import com.azhon.basic.lifecycle.BaseViewModel;
import com.idss.cashloans.api.Constants;
import com.idss.cashloans.api.HttpCallback;
import com.idss.cashloans.api.HttpRequest;
import com.idss.cashloans.api.moudle.ServiceModel;

public class ServiceActivityVM extends BaseViewModel {

    protected MutableLiveData<ServiceModel> mutableLiveData = new MutableLiveData<>();

    public MutableLiveData<ServiceModel> getMutableLiveData() {
        return mutableLiveData;
    }


    public void requestService(String phone) {
        showDialog.setValue(true, "加载中");
        HttpRequest.getInstance().getService("ServiceActivityVM",phone, Constants.tenantCode, new HttpCallback<ServiceModel>() {
            @Override
            public void onSuccess(ServiceModel serviceModel) {
                mutableLiveData.setValue(serviceModel);
                showDialog.setValue(false);

            }

            @Override
            public void onFailure(String msgCode, String errorMsg) {
                showDialog.setValue(false);
            }
        });

    }
}
