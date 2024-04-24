package com.idss.cashloans.api;

import android.content.Intent;

import androidx.annotation.NonNull;

import com.google.gson.JsonParseException;
import com.idss.cashloans.api.moudle.BaseModel;
import com.idss.cashloans.api.utils.ActivityManager;
import com.idss.cashloans.api.utils.SharePreferencesUtil;
import com.idss.cashloans.ui.activity.login.LoginActivity;

import org.json.JSONException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import javax.net.ssl.SSLHandshakeException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class HttpCallback<T extends BaseModel> implements Callback<T> {
    @Override
    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
        if (call.isCanceled()) {
            return;
        }
        if (response.isSuccessful()) {
            T model = response.body();
            if (model == null) {
                return;
            }

            if (model.getCode() == 200) {
                onSuccess(model);
            } else {
                if (model.getCode() == 401) {
                    SharePreferencesUtil.addString(BaseApplication.getAppContext(), Constants.TOKEN, "");
                    SharePreferencesUtil.addBoolean(BaseApplication.getAppContext(), Constants.IF_LOGIN, false);
                    Intent intent = new Intent(ActivityManager.getInstance().getCurrentActivity(), LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    ActivityManager.getInstance().getCurrentActivity().startActivity(intent);
                } else {
                    onApiFailure(model);
                }
            }
        } else {
            onFailure(call, new IOException("Unexpected code " + response.code()));
        }
    }

    private void onApiFailure(T model) {
        String code = String.valueOf(model.getCode());
        String errorMessage = model.getMsg();
        onFailure(code, errorMessage);
    }

    @Override
    public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
        t.printStackTrace();
        if (call.isCanceled()) {
            return;
        }
        if (t instanceof JSONException || t instanceof JsonParseException || t instanceof ParseException) {
            onFailure("10011", "JSONException or JsonParseException or ParseException");
        } else if (t instanceof ConnectException) {
            onFailure("10012", "ConnectException");
        } else if (t instanceof SSLHandshakeException) {
            onFailure("10013", "SSLHandshakeException");
        } else if (t instanceof UnknownHostException) {
            onFailure("10014", "UnknownHostException");
        } else if (t instanceof SocketTimeoutException) {
            onFailure("10015", "SocketTimeoutException");
        } else {
            onFailure("10016", "您的网络连接超时，请稍后再试！");
        }
    }


    public abstract void onSuccess(T data);

    public abstract void onFailure(String msgCode, String errorMsg);
}
