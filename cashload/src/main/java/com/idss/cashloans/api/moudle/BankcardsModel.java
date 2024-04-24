package com.idss.cashloans.api.moudle;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BankcardsModel extends BaseModel{


    @SerializedName("data")
    public DataDTO data;

    public static class DataDTO {
        @SerializedName("records")
        public List<RecordsDTO> records;
        @SerializedName("total")
        public String total;
        @SerializedName("size")
        public String size;
        @SerializedName("current")
        public String current;
        @SerializedName("orders")
        public List<?> orders;
        @SerializedName("optimizeCountSql")
        public boolean optimizeCountSql;
        @SerializedName("searchCount")
        public boolean searchCount;
        @SerializedName("countId")
        public String countId;
        @SerializedName("maxLimit")
        public String maxLimit;
        @SerializedName("pages")
        public String pages;

        public List<RecordsDTO> getRecords() {
            return records;
        }

        public void setRecords(List<RecordsDTO> records) {
            this.records = records;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getCurrent() {
            return current;
        }

        public void setCurrent(String current) {
            this.current = current;
        }

        public List<?> getOrders() {
            return orders;
        }

        public void setOrders(List<?> orders) {
            this.orders = orders;
        }

        public boolean isOptimizeCountSql() {
            return optimizeCountSql;
        }

        public void setOptimizeCountSql(boolean optimizeCountSql) {
            this.optimizeCountSql = optimizeCountSql;
        }

        public boolean isSearchCount() {
            return searchCount;
        }

        public void setSearchCount(boolean searchCount) {
            this.searchCount = searchCount;
        }

        public String getCountId() {
            return countId;
        }

        public void setCountId(String countId) {
            this.countId = countId;
        }

        public String getMaxLimit() {
            return maxLimit;
        }

        public void setMaxLimit(String maxLimit) {
            this.maxLimit = maxLimit;
        }

        public String getPages() {
            return pages;
        }

        public void setPages(String pages) {
            this.pages = pages;
        }

        public static class RecordsDTO {
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
            @SerializedName("id")
            public String id;
            @SerializedName("memberId")
            public String memberId;
            @SerializedName("memberUsername")
            public String memberUsername;
            @SerializedName("bankCardName")
            public String bankCardName;
            @SerializedName("bankCardNo")
            public String bankCardNo;
            @SerializedName("bankCardUsername")
            public String bankCardUsername;
            @SerializedName("enable")
            public int enable;
            @SerializedName("isDefault")
            public int isDefault;

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

            public String getMemberId() {
                return memberId;
            }

            public void setMemberId(String memberId) {
                this.memberId = memberId;
            }

            public String getMemberUsername() {
                return memberUsername;
            }

            public void setMemberUsername(String memberUsername) {
                this.memberUsername = memberUsername;
            }

            public String getBankCardName() {
                return bankCardName;
            }

            public void setBankCardName(String bankCardName) {
                this.bankCardName = bankCardName;
            }

            public String getBankCardNo() {
                return bankCardNo;
            }

            public void setBankCardNo(String bankCardNo) {
                this.bankCardNo = bankCardNo;
            }

            public String getBankCardUsername() {
                return bankCardUsername;
            }

            public void setBankCardUsername(String bankCardUsername) {
                this.bankCardUsername = bankCardUsername;
            }

            public int getEnable() {
                return enable;
            }

            public void setEnable(int enable) {
                this.enable = enable;
            }

            public int getIsDefault() {
                return isDefault;
            }

            public void setIsDefault(int isDefault) {
                this.isDefault = isDefault;
            }

            public String getSearchValue() {
                return searchValue;
            }

            public void setSearchValue(String searchValue) {
                this.searchValue = searchValue;
            }
        }
    }
}
