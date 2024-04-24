package com.idss.cashloans.api.moudle;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BannerModel extends BaseModel{


    @SerializedName("data")
    private DataDTO data;

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public static class DataDTO implements Serializable {
        @SerializedName("id")
        private String id;
        @SerializedName("url")
        private String url;
        @SerializedName("urlEn")
        private String urlEn;
        @SerializedName("clickUrl")
        private String clickUrl;
        @SerializedName("countdown")
        private int countdown;
        @SerializedName("createTime")
        private Object createTime;
        @SerializedName("updateTime")
        private Object updateTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrlEn() {
            return urlEn;
        }

        public void setUrlEn(String urlEn) {
            this.urlEn = urlEn;
        }

        public String getClickUrl() {
            return clickUrl;
        }

        public void setClickUrl(String clickUrl) {
            this.clickUrl = clickUrl;
        }

        public int getCountdown() {
            return countdown;
        }

        public void setCountdown(int countdown) {
            this.countdown = countdown;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }
    }
}
