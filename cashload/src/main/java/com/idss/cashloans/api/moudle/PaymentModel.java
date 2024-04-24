package com.idss.cashloans.api.moudle;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PaymentModel extends BaseModel{

    @SerializedName("data")
    private List<DataDTO> data;

    public List<DataDTO> getData() {
        return data;
    }

    public void setData(List<DataDTO> data) {
        this.data = data;
    }

    public static class DataDTO implements Serializable {
        @SerializedName("searchValue")
        private String searchValue;
        @SerializedName("createBy")
        private String createBy;
        @SerializedName("createTime")
        private String createTime;
        @SerializedName("updateBy")
        private String updateBy;
        @SerializedName("updateTime")
        private String updateTime;
        @SerializedName("remark")
        private String remark;
        @SerializedName("tenantId")
        private String tenantId;
        @SerializedName("id")
        private String id;
        @SerializedName("platformId")
        private String platformId;
        @SerializedName("platformName")
        private String platformName;
        @SerializedName("method")
        private String method;
        @SerializedName("alias")
        private String alias;
        @SerializedName("payChannel")
        private String payChannel;
        @SerializedName("status")
        private int status;
        @SerializedName("platforms")
        private String platforms;

        public String getSearchValue() {
            return searchValue;
        }

        public void setSearchValue(String searchValue) {
            this.searchValue = searchValue;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getTenantId() {
            return tenantId;
        }

        public void setTenantId(String tenantId) {
            this.tenantId = tenantId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPlatformId() {
            return platformId;
        }

        public void setPlatformId(String platformId) {
            this.platformId = platformId;
        }

        public String getPlatformName() {
            return platformName;
        }

        public void setPlatformName(String platformName) {
            this.platformName = platformName;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getPayChannel() {
            return payChannel;
        }

        public void setPayChannel(String payChannel) {
            this.payChannel = payChannel;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getPlatforms() {
            return platforms;
        }

        public void setPlatforms(String platforms) {
            this.platforms = platforms;
        }
    }
}
