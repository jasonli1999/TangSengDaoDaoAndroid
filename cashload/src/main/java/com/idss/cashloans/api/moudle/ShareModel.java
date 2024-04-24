package com.idss.cashloans.api.moudle;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ShareModel extends BaseModel {

    @SerializedName("data")
    private DataDTO data;

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public static class DataDTO implements Serializable {
        @SerializedName("vpn_time")
        private String vpnTime;
        @SerializedName("share_list")
        private List<ShareListDTO> shareList;
        @SerializedName("sharUrl")
        private String sharUrl;
        @SerializedName("sharTitle")
        private String sharTitle;
        @SerializedName("shareNum")
        private int shareNum;

        public String getVpnTime() {
            return vpnTime;
        }

        public void setVpnTime(String vpnTime) {
            this.vpnTime = vpnTime;
        }

        public List<ShareListDTO> getShareList() {
            return shareList;
        }

        public void setShareList(List<ShareListDTO> shareList) {
            this.shareList = shareList;
        }

        public String getSharUrl() {
            return sharUrl;
        }

        public void setSharUrl(String sharUrl) {
            this.sharUrl = sharUrl;
        }

        public String getSharTitle() {
            return sharTitle;
        }

        public void setSharTitle(String sharTitle) {
            this.sharTitle = sharTitle;
        }

        public int getShareNum() {
            return shareNum;
        }

        public void setShareNum(int shareNum) {
            this.shareNum = shareNum;
        }

        public static class ShareListDTO implements Serializable{
            @SerializedName("id")
            private String id;
            @SerializedName("shareNum")
            private int shareNum;
            @SerializedName("days")
            private int days;
            @SerializedName("createTime")
            private String createTime;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public int getShareNum() {
                return shareNum;
            }

            public void setShareNum(int shareNum) {
                this.shareNum = shareNum;
            }

            public int getDays() {
                return days;
            }

            public void setDays(int days) {
                this.days = days;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }
        }
    }
}
