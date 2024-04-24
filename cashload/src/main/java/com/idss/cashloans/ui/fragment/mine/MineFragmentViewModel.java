package com.idss.cashloans.ui.fragment.mine;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.azhon.basic.lifecycle.BaseViewModel;
import com.idss.cashloans.api.HttpCallback;
import com.idss.cashloans.api.HttpRequest;
import com.idss.cashloans.api.moudle.CaiwuIdModel;
import com.idss.cashloans.api.moudle.PaopaoModel;

public class MineFragmentViewModel extends BaseViewModel {

    /**
     * 获取聊天软件
     */
    private final MutableLiveData<PaopaoModel> paopaoModelMutableLiveData = new MutableLiveData<>();

    public void getPaopao() {
        HttpRequest.getInstance().getPaopao("getPaopao", new HttpCallback<PaopaoModel>() {
            @Override
            public void onSuccess(PaopaoModel paopaoModel) {
                paopaoModelMutableLiveData.setValue(paopaoModel);
            }

            @Override
            public void onFailure(String msgCode, String errorMsg) {

            }
        });
    }

    public LiveData<PaopaoModel> getPaoPao() {
        return paopaoModelMutableLiveData;
    }

    /**---------------------------------------------获取财务id---------------------------------------------*/
    /**
     * 获取财务id
     */

    private final MutableLiveData<CaiwuIdModel> caiwuIdModelMutableLiveData = new MutableLiveData<>();

    public void getCaiwuid() {
        HttpRequest.getInstance().getCaiwuId("getcaiwudi", new HttpCallback<CaiwuIdModel>() {
            @Override
            public void onSuccess(CaiwuIdModel caiwuIdModel) {
                caiwuIdModelMutableLiveData.setValue(caiwuIdModel);
            }

            @Override
            public void onFailure(String msgCode, String errorMsg) {

            }
        });
    }

    public LiveData<CaiwuIdModel> getCaiwu() {
        return caiwuIdModelMutableLiveData;
    }
}