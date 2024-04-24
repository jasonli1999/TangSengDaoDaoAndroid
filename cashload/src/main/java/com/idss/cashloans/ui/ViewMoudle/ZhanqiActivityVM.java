package com.idss.cashloans.ui.ViewMoudle;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.azhon.basic.lifecycle.BaseViewModel;
import com.idss.cashloans.api.BaseApplication;
import com.idss.cashloans.api.HttpCallback;
import com.idss.cashloans.api.HttpRequest;
import com.idss.cashloans.api.moudle.FaceVerifyModel;
import com.idss.cashloans.api.moudle.PaymentModel;

public class ZhanqiActivityVM extends BaseViewModel {

    protected MutableLiveData<PaymentModel> paymentModelMutableLiveData = new MutableLiveData<>();

    public void getpaymentMethod() {
        HttpRequest.getInstance().getpaymentMethod("getIdCard", new HttpCallback<PaymentModel>() {
            @Override
            public void onSuccess(PaymentModel paymentModel) {
                paymentModelMutableLiveData.setValue(paymentModel);
            }

            @Override
            public void onFailure(String msgCode, String errorMsg) {
                Toast.makeText(BaseApplication.getAppContext(), errorMsg, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public MutableLiveData<PaymentModel> getMutableLiveData() {
        return paymentModelMutableLiveData;
    }


    public void repayment(Context context, String alias, String orderNo, String type, String userId) {
        HttpRequest.getInstance().repayment("repayment", alias, orderNo, type, userId, new HttpCallback<FaceVerifyModel>() {
            @Override
            public void onSuccess(FaceVerifyModel data) {

            }

            @Override
            public void onFailure(String msgCode, String errorMsg) {
                Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show();
            }
        });
    }


}
