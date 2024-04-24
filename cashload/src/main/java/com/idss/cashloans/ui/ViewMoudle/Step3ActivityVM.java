package com.idss.cashloans.ui.ViewMoudle;

import android.app.Activity;
import android.widget.Toast;

import com.azhon.basic.lifecycle.BaseViewModel;
import com.idss.cashloans.api.BaseApplication;
import com.idss.cashloans.api.HttpCallback;
import com.idss.cashloans.api.HttpRequest;
import com.idss.cashloans.api.moudle.Certification;
import com.idss.cashloans.api.moudle.FaceVerifyModel;

import java.util.List;

public class Step3ActivityVM extends BaseViewModel {
    /**
     * 信息认证
     */
    public void certification3(Activity activity, List<Certification.MemberEmergencyContactListDTO> memberEmergencyContactListDTOList) {

        HttpRequest.getInstance().certification3("certification3", memberEmergencyContactListDTOList, new HttpCallback<FaceVerifyModel>() {
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
