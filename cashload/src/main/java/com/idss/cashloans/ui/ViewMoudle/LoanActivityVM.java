package com.idss.cashloans.ui.ViewMoudle;

import android.app.Activity;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.azhon.basic.lifecycle.BaseViewModel;
import com.idss.cashloans.api.BaseApplication;
import com.idss.cashloans.api.HttpCallback;
import com.idss.cashloans.api.HttpRequest;
import com.idss.cashloans.api.moudle.FaceVerifyModel;
import com.idss.cashloans.api.moudle.LoanLimitModel;

public class LoanActivityVM extends BaseViewModel {
    /**---------------------------------------------getLoanLimit---------------------------------------------*/
    private final MutableLiveData<LoanLimitModel> loanLimitModelMutableLiveData = new MutableLiveData<>();

    public void getLoanLimit() {
        HttpRequest.getInstance().getLoanLimit("getLoanLimit", new HttpCallback<LoanLimitModel>() {
            @Override
            public void onSuccess(LoanLimitModel certificationModel) {
                loanLimitModelMutableLiveData.setValue(certificationModel);
            }

            @Override
            public void onFailure(String msgCode, String errorMsg) {

            }
        });
    }

    public LiveData<LoanLimitModel> getLoanLimitData() {
        return loanLimitModelMutableLiveData;
    }


    /**---------------------------------------------createOrder---------------------------------------------*/


    public void createOrder(Activity activity, String sumMoney) {

        HttpRequest.getInstance().createOrder("createOrder", sumMoney, new HttpCallback<FaceVerifyModel>() {
            @Override
            public void onSuccess(FaceVerifyModel data) {
                activity.finish();
            }

            @Override
            public void onFailure(String msgCode, String errorMsg) {
                Toast.makeText(BaseApplication.getAppContext(), errorMsg, Toast.LENGTH_SHORT).show();
            }
        });

    }

}
