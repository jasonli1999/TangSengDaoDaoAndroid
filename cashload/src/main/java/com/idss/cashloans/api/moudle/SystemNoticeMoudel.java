package com.idss.cashloans.api.moudle;

import com.google.gson.annotations.SerializedName;

public class SystemNoticeMoudel extends BaseModel{


    @SerializedName("data")
    public DataDTO data;

    public static class DataDTO {
        @SerializedName("searchValue")
        public String searchValue;
        @SerializedName("createBy")
        public String createBy;
        @SerializedName("createTime")
        public String createTime;
        @SerializedName("updateBy")
        public String updateBy;
        @SerializedName("updateTime")
        public String updateTime;
        @SerializedName("remark")
        public String remark;
        @SerializedName("tenantId")
        public String tenantId;
        @SerializedName("noticeId")
        public String noticeId;
        @SerializedName("noticeTitle")
        public String noticeTitle;
        @SerializedName("noticeType")
        public String noticeType;
        @SerializedName("noticeContent")
        public String noticeContent;
        @SerializedName("status")
        public String status;

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

        public String getNoticeId() {
            return noticeId;
        }

        public void setNoticeId(String noticeId) {
            this.noticeId = noticeId;
        }

        public String getNoticeTitle() {
            return noticeTitle;
        }

        public void setNoticeTitle(String noticeTitle) {
            this.noticeTitle = noticeTitle;
        }

        public String getNoticeType() {
            return noticeType;
        }

        public void setNoticeType(String noticeType) {
            this.noticeType = noticeType;
        }

        public String getNoticeContent() {
            return noticeContent;
        }

        public void setNoticeContent(String noticeContent) {
            this.noticeContent = noticeContent;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }
}
