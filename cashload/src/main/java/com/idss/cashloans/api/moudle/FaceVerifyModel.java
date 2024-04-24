package com.idss.cashloans.api.moudle;

import com.google.gson.annotations.SerializedName;

public class FaceVerifyModel extends BaseModel{


    @SerializedName("data")
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
