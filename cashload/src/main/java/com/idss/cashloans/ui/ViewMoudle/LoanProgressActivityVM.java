package com.idss.cashloans.ui.ViewMoudle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.azhon.basic.lifecycle.BaseViewModel;
import com.idss.cashloans.api.HttpCallback;
import com.idss.cashloans.api.HttpRequest;
import com.idss.cashloans.api.moudle.LoanProgressModel;

public class LoanProgressActivityVM extends BaseViewModel {
    private final MutableLiveData<LoanProgressModel> mutableLiveData = new MutableLiveData<>();

    public void getLoanProgress(String id) {
        HttpRequest.getInstance().getLoanProgress("getOrderDetail", id, new HttpCallback<LoanProgressModel>() {
            @Override
            public void onSuccess(LoanProgressModel loanProgressModel) {
                mutableLiveData.setValue(loanProgressModel);
            }

            @Override
            public void onFailure(String msgCode, String errorMsg) {

            }
        });
    }

    public LiveData<LoanProgressModel> getLoanProgress() {
        return mutableLiveData;
    }
}
