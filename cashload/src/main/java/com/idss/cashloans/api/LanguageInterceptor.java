package com.idss.cashloans.api;

import androidx.annotation.NonNull;


import com.idss.cashloans.api.utils.SharePreferencesUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class LanguageInterceptor implements Interceptor {

    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        String language= SharePreferencesUtil.getString(BaseApplication.getAppContext(),"lang","zh_CN");
        //设置版本使用的语言
        builder.header("lang", language);
        request = builder.build();
        return chain.proceed(request);
    }
}
