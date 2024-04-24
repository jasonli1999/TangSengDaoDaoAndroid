package com.idss.cashloans.api.moudle;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoanLimitModel extends BaseModel {

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
        @SerializedName("tenantId")
        private String tenantId;
        @SerializedName("id")
        private String id;
        @SerializedName("label")
        private String label;
        @SerializedName("initAmount")
        private int initAmount;
        @SerializedName("limitAmount")
        private int limitAmount;
        @SerializedName("lowerAmount")
        private int lowerAmount;
        @SerializedName("serialAmount")
        private int serialAmount;
        @SerializedName("serviceFee")
        private double serviceFee;
        @SerializedName("loanPeriod")
        private int loanPeriod;
        @SerializedName("lengRate")
        private double lengRate;
        @SerializedName("lengPeriod")
        private int lengPeriod;
        @SerializedName("loanTimesLimit")
        private int loanTimesLimit;
        @SerializedName("type")
        private int type;
        @SerializedName("memberId")
        private String memberId;
        @SerializedName("repaymentTime")
        private String repaymentTime;

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

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public int getInitAmount() {
            return initAmount;
        }

        public void setInitAmount(int initAmount) {
            this.initAmount = initAmount;
        }

        public int getLimitAmount() {
            return limitAmount;
        }

        public void setLimitAmount(int limitAmount) {
            this.limitAmount = limitAmount;
        }

        public int getLowerAmount() {
            return lowerAmount;
        }

        public void setLowerAmount(int lowerAmount) {
            this.lowerAmount = lowerAmount;
        }

        public int getSerialAmount() {
            return serialAmount;
        }

        public void setSerialAmount(int serialAmount) {
            this.serialAmount = serialAmount;
        }

        public double getServiceFee() {
            return serviceFee;
        }

        public void setServiceFee(double serviceFee) {
            this.serviceFee = serviceFee;
        }

        public int getLoanPeriod() {
            return loanPeriod;
        }

        public void setLoanPeriod(int loanPeriod) {
            this.loanPeriod = loanPeriod;
        }

        public double getLengRate() {
            return lengRate;
        }

        public void setLengRate(double lengRate) {
            this.lengRate = lengRate;
        }

        public int getLengPeriod() {
            return lengPeriod;
        }

        public void setLengPeriod(int lengPeriod) {
            this.lengPeriod = lengPeriod;
        }

        public int getLoanTimesLimit() {
            return loanTimesLimit;
        }

        public void setLoanTimesLimit(int loanTimesLimit) {
            this.loanTimesLimit = loanTimesLimit;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getMemberId() {
            return memberId;
        }

        public void setMemberId(String memberId) {
            this.memberId = memberId;
        }

        public String getRepaymentTime() {
            return repaymentTime;
        }

        public void setRepaymentTime(String repaymentTime) {
            this.repaymentTime = repaymentTime;
        }
    }
}
