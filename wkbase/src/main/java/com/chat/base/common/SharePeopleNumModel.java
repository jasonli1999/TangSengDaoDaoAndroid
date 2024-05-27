package com.chat.base.common;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SharePeopleNumModel {

    @SerializedName("upper_info")
    public Object upperInfo;
    @SerializedName("total_under_count")
    public int totalUnderCount;
    @SerializedName("under_info")
    public List<List<UnderInfoDTO>> underInfo;

    public static class UnderInfoDTO {
        @SerializedName("uid")
        public String uid;
        @SerializedName("phone")
        public String phone;
        @SerializedName("name")
        public String name;
        @SerializedName("username")
        public String username;
        @SerializedName("short_no")
        public String shortNo;
        @SerializedName("avatar")
        public String avatar;
        @SerializedName("status")
        public int status;
    }
}
