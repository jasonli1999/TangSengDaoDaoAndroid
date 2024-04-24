package com.idss.cashloans.ui.fragment.home;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.azhon.basic.lifecycle.BaseViewModel;
import com.idss.cashloans.api.BaseApplication;
import com.idss.cashloans.api.Constants;
import com.idss.cashloans.api.HttpCallback;
import com.idss.cashloans.api.HttpRequest;
import com.idss.cashloans.api.moudle.AwaitingRepaymentModel;
import com.idss.cashloans.api.moudle.BorrowingModel;
import com.idss.cashloans.api.moudle.CertificationModel;
import com.idss.cashloans.api.moudle.StatueModel;
import com.idss.cashloans.api.utils.LogUtil;
import com.idss.cashloans.api.utils.SharePreferencesUtil;

public class HomeViewModel extends BaseViewModel {


    /**---------------------------------------------getLatestOrderStatus---------------------------------------------*/
    private final MutableLiveData<Integer> latestOrderStatus = new MutableLiveData<>();

    public void getLatestOrderStatus() {

        HttpRequest.getInstance().getLatestOrderStatus("getLatestOrderStatus", new HttpCallback<StatueModel>() {
            @Override
            public void onSuccess(StatueModel statueModel) {
                latestOrderStatus.setValue(statueModel.getData());
            }

            @Override
            public void onFailure(String msgCode, String errorMsg) {

            }
        });
    }

    public LiveData<Integer> getlatestOrderStatus() {
        return latestOrderStatus;
    }


    /**---------------------------------------------certification_info---------------------------------------------*/
    private final MutableLiveData<Integer> integerMutableLiveData = new MutableLiveData<>();

    public void requestCertification(Context context) {
        HttpRequest.getInstance().certification_info("requestCertification", new HttpCallback<CertificationModel>() {
            @Override
            public void onSuccess(CertificationModel certificationModel) {
                LogUtil.e(certificationModel.getData()+"=====certification_info========");
                //0为认证完成了
                integerMutableLiveData.setValue(certificationModel.getData());
                SharePreferencesUtil.addInteger(context, Constants.CERTIFICATION,certificationModel.getData());
            }

            @Override
            public void onFailure(String msgCode, String errorMsg) {

            }
        });
    }

    public LiveData<Integer> getAuthSteps() {
        return integerMutableLiveData;
    }

    /**---------------------------------------------getawaitingRepayment---------------------------------------------*/

    private final MutableLiveData<AwaitingRepaymentModel> awaitingRepaymentModelMutableLiveData = new MutableLiveData<>();

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

    public LiveData<AwaitingRepaymentModel> getAwaitingRepaymentModel() {
        return awaitingRepaymentModelMutableLiveData;
    }

    /**---------------------------------------------getmyBorrowings---------------------------------------------*/
    private final MutableLiveData<BorrowingModel> borrowingModelMutableLiveData = new MutableLiveData<>();

    public void getmyBorrowings() {
        HttpRequest.getInstance().getmyBorrowings("getmyBorrowings", new HttpCallback<BorrowingModel>() {
            @Override
            public void onSuccess(BorrowingModel borrowingModel) {
                borrowingModelMutableLiveData.setValue(borrowingModel);
            }

            @Override
            public void onFailure(String msgCode, String errorMsg) {
                Toast.makeText(BaseApplication.getAppContext(), errorMsg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public LiveData<BorrowingModel> getBorrowingModelMutableLiveData() {
        return borrowingModelMutableLiveData;
    }


}