package com.idss.cashloans.api.moudle;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class LoanProgressModel extends BaseModel{

    @SerializedName("data")
    private List<DataDTO> data;

    public List<DataDTO> getData() {
        return data;
    }

    public void setData(List<DataDTO> data) {
        this.data = data;
    }

    public static class DataDTO implements Serializable {
        @SerializedName("name")
        private String name;
        @SerializedName("time")
        private String time;
        @SerializedName("isNow")
        private String isNow;
        @SerializedName("type")
        private String type;
        @SerializedName("desc")
        private String desc;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getIsNow() {
            return isNow;
        }

        public void setIsNow(String isNow) {
            this.isNow = isNow;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
