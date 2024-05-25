package com.xinbida.tsdd.yuqiao;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class LinesModel implements Serializable {


    @SerializedName("lines")
    private List<LinesDTO> lines;

    public List<LinesDTO> getLines() {
        return lines;
    }

    public void setLines(List<LinesDTO> lines) {
        this.lines = lines;
    }

    public static class LinesDTO {
        @SerializedName("name")
        private String name;
        @SerializedName("address")
        private String address;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
