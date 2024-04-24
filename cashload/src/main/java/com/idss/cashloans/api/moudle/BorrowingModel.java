package com.idss.cashloans.api.moudle;

import com.google.gson.annotations.SerializedName;

public class BorrowingModel extends BaseModel{

    @SerializedName("data")
    private DataDTO data;

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public static class DataDTO {
        @SerializedName("applicationTime")
        private String applicationTime;
        @SerializedName("applicationAmount")
        private int applicationAmount;
        @SerializedName("loanStatus")
        private int loanStatus;

        public String getApplicationTime() {
            return applicationTime;
        }

        public void setApplicationTime(String applicationTime) {
            this.applicationTime = applicationTime;
        }

        public int getApplicationAmount() {
            return applicationAmount;
        }

        public void setApplicationAmount(int applicationAmount) {
            this.applicationAmount = applicationAmount;
        }

        public int getLoanStatus() {
            return loanStatus;
        }

        public void setLoanStatus(int loanStatus) {
            this.loanStatus = loanStatus;
        }
    }
}
