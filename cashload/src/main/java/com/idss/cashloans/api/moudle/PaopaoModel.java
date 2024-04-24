package com.idss.cashloans.api.moudle;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PaopaoModel extends BaseModel {
    @SerializedName("data")
    private DataDTO data;
    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public static class DataDTO implements Serializable {
        @SerializedName("records")
        private List<RecordsDTO> records;
        @SerializedName("total")
        private String total;
        @SerializedName("size")
        private String size;
        @SerializedName("current")
        private String current;
        @SerializedName("orders")
        private List<?> orders;
        @SerializedName("optimizeCountSql")
        private boolean optimizeCountSql;
        @SerializedName("searchCount")
        private boolean searchCount;
        @SerializedName("countId")
        private String countId;
        @SerializedName("maxLimit")
        private String maxLimit;
        @SerializedName("pages")
        private String pages;

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
            @SerializedName("appLogo")
            private String appLogo;
            @SerializedName("appName")
            private String appName;
            @SerializedName("downloadUrl")
            private String downloadUrl;
            @SerializedName("sort")
            private int sort;
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

            public String getAppLogo() {
                return appLogo;
            }

            public void setAppLogo(String appLogo) {
                this.appLogo = appLogo;
            }

            public String getAppName() {
                return appName;
            }

            public void setAppName(String appName) {
                this.appName = appName;
            }

            public String getDownloadUrl() {
                return downloadUrl;
            }

            public void setDownloadUrl(String downloadUrl) {
                this.downloadUrl = downloadUrl;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public String getTenantId() {
                return tenantId;
            }

            public void setTenantId(String tenantId) {
                this.tenantId = tenantId;
            }
        }
    }
}
