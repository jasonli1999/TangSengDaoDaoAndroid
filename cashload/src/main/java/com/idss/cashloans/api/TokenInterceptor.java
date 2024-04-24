package com.idss.cashloans.api;

import androidx.annotation.NonNull;

import com.idss.cashloans.api.utils.LogUtil;
import com.idss.cashloans.api.utils.SharePreferencesUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {
    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        String token = SharePreferencesUtil.getString(BaseApplication.getAppContext(), Constants.TOKEN, "");
        LogUtil.e("TokenInterceptor:" + token);
        builder.header("Authorization", "Bearer " + token);
        request = builder.build();
        return chain.proceed(request);
    }
}
