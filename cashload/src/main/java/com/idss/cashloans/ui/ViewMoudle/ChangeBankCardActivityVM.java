package com.idss.cashloans.ui.ViewMoudle;

import android.app.Activity;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.azhon.basic.lifecycle.BaseViewModel;
import com.idss.cashloans.api.BaseApplication;
import com.idss.cashloans.api.HttpCallback;
import com.idss.cashloans.api.HttpRequest;
import com.idss.cashloans.api.moudle.BankcardsModel;

public class ChangeBankCardActivityVM extends BaseViewModel {
    protected MutableLiveData<BankcardsModel> mutableLiveData = new MutableLiveData<>();

    public void getBankCard(Activity activity) {
        HttpRequest.getInstance().getIdCards("getIdCards", new HttpCallback<BankcardsModel>() {
            @Override
            public void onSuccess(BankcardsModel data) {
                mutableLiveData.setValue(data);
            }

            @Override
            public void onFailure(String msgCode, String errorMsg) {
                Toast.makeText(BaseApplication.getAppContext(), errorMsg, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public MutableLiveData<BankcardsModel> getBankCard() {
        return mutableLiveData;
    }
}
