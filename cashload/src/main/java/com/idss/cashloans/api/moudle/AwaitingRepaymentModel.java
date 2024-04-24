package com.idss.cashloans.api.moudle;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AwaitingRepaymentModel extends BaseModel  {


    @SerializedName("data")
    private DataDTO data;

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public static class DataDTO implements Serializable{
        @SerializedName("orderNo")
        private String orderNo;
        @SerializedName("applicationAmount")
        private int applicationAmount;
        @SerializedName("actualAmount")
        private int actualAmount;
        @SerializedName("repayableAmount")
        private int repayableAmount;
        @SerializedName("serviceFee")
        private int serviceFee;
        @SerializedName("releaseTime")
        private String releaseTime;
        @SerializedName("expirationTime")
        private String expirationTime;
        @SerializedName("extensionTime")
        private String extensionTime;
        @SerializedName("extensionFee")
        private int extensionFee;
        @SerializedName("latePaymentFee")
        private int latePaymentFee;
        @SerializedName("repaymentAmount")
        private int repaymentAmount;
        @SerializedName("overdueDays")
        private int overdueDays;

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public int getApplicationAmount() {
            return applicationAmount;
        }

        public void setApplicationAmount(int applicationAmount) {
            this.applicationAmount = applicationAmount;
        }

        public int getActualAmount() {
            return actualAmount;
        }

        public void setActualAmount(int actualAmount) {
            this.actualAmount = actualAmount;
        }

        public int getRepayableAmount() {
            return repayableAmount;
        }

        public void setRepayableAmount(int repayableAmount) {
            this.repayableAmount = repayableAmount;
        }

        public int getServiceFee() {
            return serviceFee;
        }

        public void setServiceFee(int serviceFee) {
            this.serviceFee = serviceFee;
        }

        public String getReleaseTime() {
            return releaseTime;
        }

        public void setReleaseTime(String releaseTime) {
            this.releaseTime = releaseTime;
        }

        public String getExpirationTime() {
            return expirationTime;
        }

        public void setExpirationTime(String expirationTime) {
            this.expirationTime = expirationTime;
        }

        public String getExtensionTime() {
            return extensionTime;
        }

        public void setExtensionTime(String extensionTime) {
            this.extensionTime = extensionTime;
        }

        public int getExtensionFee() {
            return extensionFee;
        }

        public void setExtensionFee(int extensionFee) {
            this.extensionFee = extensionFee;
        }

        public int getLatePaymentFee() {
            return latePaymentFee;
        }

        public void setLatePaymentFee(int latePaymentFee) {
            this.latePaymentFee = latePaymentFee;
        }

        public int getRepaymentAmount() {
            return repaymentAmount;
        }

        public void setRepaymentAmount(int repaymentAmount) {
            this.repaymentAmount = repaymentAmount;
        }

        public int getOverdueDays() {
            return overdueDays;
        }

        public void setOverdueDays(int overdueDays) {
            this.overdueDays = overdueDays;
        }
    }
}
