package com.idss.cashloans.api.moudle;

import com.google.gson.annotations.SerializedName;

public class StatueModel extends BaseModel{

    @SerializedName("data")
    private int data;

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
