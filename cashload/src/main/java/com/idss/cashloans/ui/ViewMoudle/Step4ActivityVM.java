package com.idss.cashloans.ui.ViewMoudle;

import android.app.Activity;
import android.widget.Toast;

import com.azhon.basic.lifecycle.BaseViewModel;
import com.idss.cashloans.api.BaseApplication;
import com.idss.cashloans.api.HttpCallback;
import com.idss.cashloans.api.HttpRequest;
import com.idss.cashloans.api.moudle.FaceVerifyModel;

public class Step4ActivityVM extends BaseViewModel {
    /**
     * 信息认证
     */
    public void certification4(Activity activity, String bankname, String bankcard) {

        HttpRequest.getInstance().certification4("certification4", bankname, bankcard, new HttpCallback<FaceVerifyModel>() {
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
