package com.xinbida.tsdd.changyan.push;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class InviteDataModel implements Serializable {

    @SerializedName("inviteCode")
    private String inviteCode;

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }
}
