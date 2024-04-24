package com.idss.cashloans.api.moudle;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ServiceModel extends BaseModel{

    @SerializedName("data")
    private DataDTO data;

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
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
        @SerializedName("id")
        private String id;
        @SerializedName("name")
        private String name;
        @SerializedName("lable")
        private String lable;
        @SerializedName("link")
        private String link;
        @SerializedName("linkGroup")
        private String linkGroup;
        @SerializedName("url")
        private String url;
        @SerializedName("tenantId")
        private String tenantId;

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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLable() {
            return lable;
        }

        public void setLable(String lable) {
            this.lable = lable;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getLinkGroup() {
            return linkGroup;
        }

        public void setLinkGroup(String linkGroup) {
            this.linkGroup = linkGroup;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTenantId() {
            return tenantId;
        }

        public void setTenantId(String tenantId) {
            this.tenantId = tenantId;
        }
    }
}
