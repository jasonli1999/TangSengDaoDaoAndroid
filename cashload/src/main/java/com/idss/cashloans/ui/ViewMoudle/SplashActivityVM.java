package com.idss.cashloans.ui.ViewMoudle;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.azhon.basic.lifecycle.BaseViewModel;
import com.google.gson.Gson;
import com.idss.cashloans.api.Constants;
import com.idss.cashloans.api.moudle.OfficialUrl;

public class SplashActivityVM extends BaseViewModel {
    protected MutableLiveData<OfficialUrl> officialUrlMutableLiveData = new MutableLiveData<>();

    public void getOfficialUrl(Context context) {

        RequestQueue mQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Constants.OFFICAL_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        OfficialUrl officialUrl = gson.fromJson(response, OfficialUrl.class);
                        officialUrlMutableLiveData.setValue(officialUrl);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("=======", error.getMessage(), error);
            }
        });
        mQueue.add(stringRequest);

    }

    public MutableLiveData<OfficialUrl> getOfficialUrlMutableLiveData() {
        return officialUrlMutableLiveData;
    }
}
