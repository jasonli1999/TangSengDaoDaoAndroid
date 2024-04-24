package com.idss.cashloans.ui.ViewMoudle;

import androidx.lifecycle.MutableLiveData;

import com.azhon.basic.lifecycle.BaseViewModel;
import com.idss.cashloans.api.HttpCallback;
import com.idss.cashloans.api.HttpRequest;
import com.idss.cashloans.api.moudle.CertificationModel;

public class AuthenticationActivityVM extends BaseViewModel {

    protected MutableLiveData<Integer> integerMutableLiveData = new MutableLiveData<>();

    public void requestCertification() {

        HttpRequest.getInstance().certification_info("requestCertification", new HttpCallback<CertificationModel>() {
            @Override
            public void onSuccess(CertificationModel certificationModel) {
                showDialog.setValue(false);
                //0为认证完成了
                integerMutableLiveData.setValue(certificationModel.getData());
            }

            @Override
            public void onFailure(String msgCode, String errorMsg) {
                showDialog.setValue(false);

            }
        });
    }

    public MutableLiveData<Integer> getMutableLiveData() {
        return integerMutableLiveData;
    }


}
