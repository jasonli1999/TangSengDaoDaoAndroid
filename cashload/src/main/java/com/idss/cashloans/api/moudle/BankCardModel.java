package com.idss.cashloans.api.moudle;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class BankCardModel extends BaseModel{

    @SerializedName("data")
    private List<DataDTO> data;

    public List<DataDTO> getData() {
        return data;
    }

    public void setData(List<DataDTO> data) {
        this.data = data;
    }

    public static class DataDTO implements Serializable {
        @SerializedName("bankName")
        private String bankName;
        @SerializedName("bankCardNo")
        private String bankCardNo;

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getBankCardNo() {
            return bankCardNo;
        }

        public void setBankCardNo(String bankCardNo) {
            this.bankCardNo = bankCardNo;
        }
    }
}
