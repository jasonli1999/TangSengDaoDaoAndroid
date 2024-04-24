package com.idss.cashloans.api.moudle;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class APPversionModel extends BaseModel {
    @SerializedName("data")
    private List<DataDTO> data;

    public List<DataDTO> getData() {
        return data;
    }

    public void setData(List<DataDTO> data) {
        this.data = data;
    }

    public static class DataDTO {
        @SerializedName("id")
        private String id;
        @SerializedName("versionCode")
        private String versionCode;
        @SerializedName("appName")
        private String appName;
        @SerializedName("version")
        private String version;
        @SerializedName("url")
        private String url;
        @SerializedName("type")
        private String type;
        @SerializedName("onOff")
        private int onOff;
        @SerializedName("tenantId")
        private String tenantId;

        public String getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(String versionCode) {
            this.versionCode = versionCode;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAppName() {
            return appName;
        }

        public void setAppName(String appName) {
            this.appName = appName;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getOnOff() {
            return onOff;
        }

        public void setOnOff(int onOff) {
            this.onOff = onOff;
        }

        public String getTenantId() {
            return tenantId;
        }

        public void setTenantId(String tenantId) {
            this.tenantId = tenantId;
        }
    }
}
