package com.idss.cashloans.api.moudle;

import com.google.gson.annotations.SerializedName;

public class OfficialUrl extends BaseModel {

    @SerializedName("url")
    public String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
