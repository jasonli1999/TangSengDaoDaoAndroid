package com.idss.cashloans.ui.ViewMoudle;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.azhon.basic.lifecycle.BaseViewModel;
import com.idss.cashloans.api.BaseApplication;
import com.idss.cashloans.api.HttpCallback;
import com.idss.cashloans.api.HttpRequest;
import com.idss.cashloans.api.moudle.AwaitingRepaymentModel;

public class AwaitingRepaymentVM extends BaseViewModel {
    protected MutableLiveData<AwaitingRepaymentModel> awaitingRepaymentModelMutableLiveData = new MutableLiveData<>();

    public void getawaitingRepayment() {
        HttpRequest.getInstance().getawaitingRepayment("getawaitingRepayment", new HttpCallback<AwaitingRepaymentModel>() {
            @Override
            public void onSuccess(AwaitingRepaymentModel awaitingRepaymentModel) {
                awaitingRepaymentModelMutableLiveData.setValue(awaitingRepaymentModel);
            }

            @Override
            public void onFailure(String msgCode, String errorMsg) {
                Toast.makeText(BaseApplication.getAppContext(), errorMsg, Toast.LENGTH_SHORT).show();
            }
        });

    }


    public MutableLiveData<AwaitingRepaymentModel> getAwaitingRepaymentModelMutableLiveData() {
        return awaitingRepaymentModelMutableLiveData;
    }
}
