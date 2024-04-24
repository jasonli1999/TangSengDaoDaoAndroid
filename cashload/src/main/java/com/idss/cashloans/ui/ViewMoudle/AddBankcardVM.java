package com.idss.cashloans.ui.ViewMoudle;

import android.app.Activity;
import android.widget.Toast;

import com.azhon.basic.lifecycle.BaseViewModel;
import com.idss.cashloans.api.BaseApplication;
import com.idss.cashloans.api.HttpCallback;
import com.idss.cashloans.api.HttpRequest;
import com.idss.cashloans.api.moudle.FaceVerifyModel;

public class AddBankcardVM extends BaseViewModel {
    /**
     * 信息认证
     */
    public void addBankcard(Activity activity, String bankname, String bankcard, String bankCardUsername, int isDefault) {

        HttpRequest.getInstance().addBankcard("addBankcard", bankname, bankcard, bankCardUsername, isDefault, new HttpCallback<FaceVerifyModel>() {
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
