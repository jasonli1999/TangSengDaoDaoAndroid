package com.idss.cashloans.api.moudle;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HistoryOrdersModel extends BaseModel{


    @SerializedName("data")
    private DataDTO data;

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public static class DataDTO {
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
            @SerializedName("tenantId")
            private String tenantId;
            @SerializedName("id")
            private String id;
            @SerializedName("orderNo")
            private String orderNo;
            @SerializedName("memberId")
            private String memberId;
            @SerializedName("userName")
            private String userName;
            @SerializedName("phone")
            private String phone;
            @SerializedName("channelId")
            private String channelId;
            @SerializedName("channelName")
            private String channelName;
            @SerializedName("oldUser")
            private int oldUser;
            @SerializedName("limitAmount")
            private int limitAmount;
            @SerializedName("applicationAmount")
            private int applicationAmount;
            @SerializedName("actualAmount")
            private int actualAmount;
            @SerializedName("totalAmount")
            private int totalAmount;
            @SerializedName("repayableAmount")
            private int repayableAmount;
            @SerializedName("latePaymentFee")
            private int latePaymentFee;
            @SerializedName("borrowingPeriod")
            private int borrowingPeriod;
            @SerializedName("serviceFee")
            private int serviceFee;
            @SerializedName("serviceRate")
            private double serviceRate;
            @SerializedName("handlingFee")
            private int handlingFee;
            @SerializedName("applicationTime")
            private String applicationTime;
            @SerializedName("releaseTime")
            private String releaseTime;
            @SerializedName("expirationTime")
            private String expirationTime;
            @SerializedName("repaymentTime")
            private String repaymentTime;
            @SerializedName("isOverdue")
            private int isOverdue;
            @SerializedName("isOverdueInfo")
            private int isOverdueInfo;
            @SerializedName("auditorId")
            private String auditorId;
            @SerializedName("auditor")
            private String auditor;
            @SerializedName("auditStatus")
            private int auditStatus;
            @SerializedName("auditSuspend")
            private int auditSuspend;
            @SerializedName("repaymentAmount")
            private int repaymentAmount;
            @SerializedName("repaymentNum")
            private int repaymentNum;
            @SerializedName("reductionAmount")
            private int reductionAmount;
            @SerializedName("extensionNum")
            private int extensionNum;
            @SerializedName("extensionFee")
            private int extensionFee;
            @SerializedName("extensionName")
            private String extensionName;
            @SerializedName("extensionParentOrderNo")
            private String extensionParentOrderNo;
            @SerializedName("extensionOrderNo")
            private String extensionOrderNo;
            @SerializedName("extensionPhase")
            private String extensionPhase;
            @SerializedName("extensionStatus")
            private int extensionStatus;
            @SerializedName("extensionLast")
            private int extensionLast;
            @SerializedName("extensionTime")
            private String extensionTime;
            @SerializedName("collectionStatus")
            private int collectionStatus;
            @SerializedName("collectorName")
            private String collectorName;
            @SerializedName("collectorId")
            private String collectorId;
            @SerializedName("allocateTime")
            private String allocateTime;
            @SerializedName("deptName")
            private String deptName;
            @SerializedName("deptId")
            private String deptId;
            @SerializedName("assignorName")
            private String assignorName;
            @SerializedName("assignorId")
            private String assignorId;
            @SerializedName("examiner2Name")
            private String examiner2Name;
            @SerializedName("examiner3Name")
            private String examiner3Name;
            @SerializedName("latestOrders")
            private int latestOrders;
            @SerializedName("isOffLineOrder")
            private String isOffLineOrder;

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

            public String getOrderNo() {
                return orderNo;
            }

            public void setOrderNo(String orderNo) {
                this.orderNo = orderNo;
            }

            public String getMemberId() {
                return memberId;
            }

            public void setMemberId(String memberId) {
                this.memberId = memberId;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getChannelId() {
                return channelId;
            }

            public void setChannelId(String channelId) {
                this.channelId = channelId;
            }

            public String getChannelName() {
                return channelName;
            }

            public void setChannelName(String channelName) {
                this.channelName = channelName;
            }

            public int getOldUser() {
                return oldUser;
            }

            public void setOldUser(int oldUser) {
                this.oldUser = oldUser;
            }

            public int getLimitAmount() {
                return limitAmount;
            }

            public void setLimitAmount(int limitAmount) {
                this.limitAmount = limitAmount;
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

            public int getTotalAmount() {
                return totalAmount;
            }

            public void setTotalAmount(int totalAmount) {
                this.totalAmount = totalAmount;
            }

            public int getRepayableAmount() {
                return repayableAmount;
            }

            public void setRepayableAmount(int repayableAmount) {
                this.repayableAmount = repayableAmount;
            }

            public int getLatePaymentFee() {
                return latePaymentFee;
            }

            public void setLatePaymentFee(int latePaymentFee) {
                this.latePaymentFee = latePaymentFee;
            }

            public int getBorrowingPeriod() {
                return borrowingPeriod;
            }

            public void setBorrowingPeriod(int borrowingPeriod) {
                this.borrowingPeriod = borrowingPeriod;
            }

            public int getServiceFee() {
                return serviceFee;
            }

            public void setServiceFee(int serviceFee) {
                this.serviceFee = serviceFee;
            }

            public double getServiceRate() {
                return serviceRate;
            }

            public void setServiceRate(double serviceRate) {
                this.serviceRate = serviceRate;
            }

            public int getHandlingFee() {
                return handlingFee;
            }

            public void setHandlingFee(int handlingFee) {
                this.handlingFee = handlingFee;
            }

            public String getApplicationTime() {
                return applicationTime;
            }

            public void setApplicationTime(String applicationTime) {
                this.applicationTime = applicationTime;
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

            public String getRepaymentTime() {
                return repaymentTime;
            }

            public void setRepaymentTime(String repaymentTime) {
                this.repaymentTime = repaymentTime;
            }

            public int getIsOverdue() {
                return isOverdue;
            }

            public void setIsOverdue(int isOverdue) {
                this.isOverdue = isOverdue;
            }

            public int getIsOverdueInfo() {
                return isOverdueInfo;
            }

            public void setIsOverdueInfo(int isOverdueInfo) {
                this.isOverdueInfo = isOverdueInfo;
            }

            public String getAuditorId() {
                return auditorId;
            }

            public void setAuditorId(String auditorId) {
                this.auditorId = auditorId;
            }

            public String getAuditor() {
                return auditor;
            }

            public void setAuditor(String auditor) {
                this.auditor = auditor;
            }

            public int getAuditStatus() {
                return auditStatus;
            }

            public void setAuditStatus(int auditStatus) {
                this.auditStatus = auditStatus;
            }

            public int getAuditSuspend() {
                return auditSuspend;
            }

            public void setAuditSuspend(int auditSuspend) {
                this.auditSuspend = auditSuspend;
            }

            public int getRepaymentAmount() {
                return repaymentAmount;
            }

            public void setRepaymentAmount(int repaymentAmount) {
                this.repaymentAmount = repaymentAmount;
            }

            public int getRepaymentNum() {
                return repaymentNum;
            }

            public void setRepaymentNum(int repaymentNum) {
                this.repaymentNum = repaymentNum;
            }

            public int getReductionAmount() {
                return reductionAmount;
            }

            public void setReductionAmount(int reductionAmount) {
                this.reductionAmount = reductionAmount;
            }

            public int getExtensionNum() {
                return extensionNum;
            }

            public void setExtensionNum(int extensionNum) {
                this.extensionNum = extensionNum;
            }

            public int getExtensionFee() {
                return extensionFee;
            }

            public void setExtensionFee(int extensionFee) {
                this.extensionFee = extensionFee;
            }

            public String getExtensionName() {
                return extensionName;
            }

            public void setExtensionName(String extensionName) {
                this.extensionName = extensionName;
            }

            public String getExtensionParentOrderNo() {
                return extensionParentOrderNo;
            }

            public void setExtensionParentOrderNo(String extensionParentOrderNo) {
                this.extensionParentOrderNo = extensionParentOrderNo;
            }

            public String getExtensionOrderNo() {
                return extensionOrderNo;
            }

            public void setExtensionOrderNo(String extensionOrderNo) {
                this.extensionOrderNo = extensionOrderNo;
            }

            public String getExtensionPhase() {
                return extensionPhase;
            }

            public void setExtensionPhase(String extensionPhase) {
                this.extensionPhase = extensionPhase;
            }

            public int getExtensionStatus() {
                return extensionStatus;
            }

            public void setExtensionStatus(int extensionStatus) {
                this.extensionStatus = extensionStatus;
            }

            public int getExtensionLast() {
                return extensionLast;
            }

            public void setExtensionLast(int extensionLast) {
                this.extensionLast = extensionLast;
            }

            public String getExtensionTime() {
                return extensionTime;
            }

            public void setExtensionTime(String extensionTime) {
                this.extensionTime = extensionTime;
            }

            public int getCollectionStatus() {
                return collectionStatus;
            }

            public void setCollectionStatus(int collectionStatus) {
                this.collectionStatus = collectionStatus;
            }

            public String getCollectorName() {
                return collectorName;
            }

            public void setCollectorName(String collectorName) {
                this.collectorName = collectorName;
            }

            public String getCollectorId() {
                return collectorId;
            }

            public void setCollectorId(String collectorId) {
                this.collectorId = collectorId;
            }

            public String getAllocateTime() {
                return allocateTime;
            }

            public void setAllocateTime(String allocateTime) {
                this.allocateTime = allocateTime;
            }

            public String getDeptName() {
                return deptName;
            }

            public void setDeptName(String deptName) {
                this.deptName = deptName;
            }

            public String getDeptId() {
                return deptId;
            }

            public void setDeptId(String deptId) {
                this.deptId = deptId;
            }

            public String getAssignorName() {
                return assignorName;
            }

            public void setAssignorName(String assignorName) {
                this.assignorName = assignorName;
            }

            public String getAssignorId() {
                return assignorId;
            }

            public void setAssignorId(String assignorId) {
                this.assignorId = assignorId;
            }

            public String getExaminer2Name() {
                return examiner2Name;
            }

            public void setExaminer2Name(String examiner2Name) {
                this.examiner2Name = examiner2Name;
            }

            public String getExaminer3Name() {
                return examiner3Name;
            }

            public void setExaminer3Name(String examiner3Name) {
                this.examiner3Name = examiner3Name;
            }

            public int getLatestOrders() {
                return latestOrders;
            }

            public void setLatestOrders(int latestOrders) {
                this.latestOrders = latestOrders;
            }

            public String getIsOffLineOrder() {
                return isOffLineOrder;
            }

            public void setIsOffLineOrder(String isOffLineOrder) {
                this.isOffLineOrder = isOffLineOrder;
            }
        }
    }
}
