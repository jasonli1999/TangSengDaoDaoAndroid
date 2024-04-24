package com.idss.cashloans.api.moudle;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginModel extends BaseModel {
    @SerializedName("userInfo")
    private UserInfoDTO userInfo;
    @SerializedName("token")
    private String token;

    public UserInfoDTO getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoDTO userInfo) {
        this.userInfo = userInfo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class UserInfoDTO implements Serializable {
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
        @SerializedName("userName")
        private String userName;
        @SerializedName("nameTrue")
        private String nameTrue;
        @SerializedName("nameNick")
        private String nameNick;
        @SerializedName("password")
        private String password;
        @SerializedName("phone")
        private String phone;
        @SerializedName("idCardNo")
        private String idCardNo;
        @SerializedName("email")
        private String email;
        @SerializedName("province")
        private String province;
        @SerializedName("city")
        private String city;
        @SerializedName("address")
        private String address;
        @SerializedName("balance")
        private String balance;
        @SerializedName("bankName")
        private String bankName;
        @SerializedName("bankCardNo")
        private String bankCardNo;
        @SerializedName("bankPhone")
        private String bankPhone;
        @SerializedName("amountConfigId")
        private String amountConfigId;
        @SerializedName("channelId")
        private String channelId;
        @SerializedName("channelName")
        private String channelName;
        @SerializedName("browser")
        private String browser;
        @SerializedName("status")
        private int status;
        @SerializedName("enabled")
        private String enabled;
        @SerializedName("borrowingNum")
        private String borrowingNum;
        @SerializedName("qqNum")
        private String qqNum;
        @SerializedName("region")
        private String region;
        @SerializedName("longitude")
        private String longitude;
        @SerializedName("latitude")
        private String latitude;
        @SerializedName("bindingDeviceModel")
        private String bindingDeviceModel;
        @SerializedName("clientInfo")
        private String clientInfo;
        @SerializedName("registerDeviceCode")
        private String registerDeviceCode;
        @SerializedName("loginDeviceCode")
        private String loginDeviceCode;
        @SerializedName("loginException")
        private String loginException;
        @SerializedName("isBlack")
        private int isBlack;
        @SerializedName("isWhiteList")
        private int isWhiteList;
        @SerializedName("isLoginAbnormal")
        private int isLoginAbnormal;
        @SerializedName("isBoxRiskDataUploaded")
        private String isBoxRiskDataUploaded;
        @SerializedName("isOldUser")
        private int isOldUser;
        @SerializedName("isHasOrder")
        private int isHasOrder;
        @SerializedName("customerSources")
        private String customerSources;
        @SerializedName("annualIncome")
        private String annualIncome;
        @SerializedName("companyName")
        private String companyName;
        @SerializedName("companyPhone")
        private String companyPhone;
        @SerializedName("companyProvince")
        private String companyProvince;
        @SerializedName("companyCity")
        private String companyCity;
        @SerializedName("companyAddress")
        private String companyAddress;
        @SerializedName("windControl")
        private String windControl;
        @SerializedName("isClick")
        private int isClick;
        @SerializedName("userStatus")
        private int userStatus;
        @SerializedName("deviceLock")
        private int deviceLock;
        @SerializedName("periodNum")
        private int periodNum;
        @SerializedName("cid")
        private String cid;
        @SerializedName("sendSms")
        private int sendSms;
        @SerializedName("statusDevice")
        private int statusDevice;
        @SerializedName("statusAuth")
        private int statusAuth;
        @SerializedName("statusContacts")
        private int statusContacts;
        @SerializedName("statusBank")
        private int statusBank;
        @SerializedName("statusIdcard")
        private int statusIdcard;
        @SerializedName("statusYys")
        private int statusYys;
        @SerializedName("statusZhimafen")
        private int statusZhimafen;
        @SerializedName("userSource")
        private String userSource;
        @SerializedName("customerSystemNotifying")
        private int customerSystemNotifying;
        @SerializedName("customerSystemNotifyMessage")
        private String customerSystemNotifyMessage;
        @SerializedName("loginIp")
        private String loginIp;
        @SerializedName("registerIp")
        private String registerIp;
        @SerializedName("registerIpAddress")
        private String registerIpAddress;
        @SerializedName("loginTime")
        private String loginTime;
        @SerializedName("successCount")
        private String successCount;

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

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getNameTrue() {
            return nameTrue;
        }

        public void setNameTrue(String nameTrue) {
            this.nameTrue = nameTrue;
        }

        public String getNameNick() {
            return nameNick;
        }

        public void setNameNick(String nameNick) {
            this.nameNick = nameNick;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getIdCardNo() {
            return idCardNo;
        }

        public void setIdCardNo(String idCardNo) {
            this.idCardNo = idCardNo;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

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

        public String getBankPhone() {
            return bankPhone;
        }

        public void setBankPhone(String bankPhone) {
            this.bankPhone = bankPhone;
        }

        public String getAmountConfigId() {
            return amountConfigId;
        }

        public void setAmountConfigId(String amountConfigId) {
            this.amountConfigId = amountConfigId;
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

        public String getBrowser() {
            return browser;
        }

        public void setBrowser(String browser) {
            this.browser = browser;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getEnabled() {
            return enabled;
        }

        public void setEnabled(String enabled) {
            this.enabled = enabled;
        }

        public String getBorrowingNum() {
            return borrowingNum;
        }

        public void setBorrowingNum(String borrowingNum) {
            this.borrowingNum = borrowingNum;
        }

        public String getQqNum() {
            return qqNum;
        }

        public void setQqNum(String qqNum) {
            this.qqNum = qqNum;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getBindingDeviceModel() {
            return bindingDeviceModel;
        }

        public void setBindingDeviceModel(String bindingDeviceModel) {
            this.bindingDeviceModel = bindingDeviceModel;
        }

        public String getClientInfo() {
            return clientInfo;
        }

        public void setClientInfo(String clientInfo) {
            this.clientInfo = clientInfo;
        }

        public String getRegisterDeviceCode() {
            return registerDeviceCode;
        }

        public void setRegisterDeviceCode(String registerDeviceCode) {
            this.registerDeviceCode = registerDeviceCode;
        }

        public String getLoginDeviceCode() {
            return loginDeviceCode;
        }

        public void setLoginDeviceCode(String loginDeviceCode) {
            this.loginDeviceCode = loginDeviceCode;
        }

        public String getLoginException() {
            return loginException;
        }

        public void setLoginException(String loginException) {
            this.loginException = loginException;
        }

        public int getIsBlack() {
            return isBlack;
        }

        public void setIsBlack(int isBlack) {
            this.isBlack = isBlack;
        }

        public int getIsWhiteList() {
            return isWhiteList;
        }

        public void setIsWhiteList(int isWhiteList) {
            this.isWhiteList = isWhiteList;
        }

        public int getIsLoginAbnormal() {
            return isLoginAbnormal;
        }

        public void setIsLoginAbnormal(int isLoginAbnormal) {
            this.isLoginAbnormal = isLoginAbnormal;
        }

        public String getIsBoxRiskDataUploaded() {
            return isBoxRiskDataUploaded;
        }

        public void setIsBoxRiskDataUploaded(String isBoxRiskDataUploaded) {
            this.isBoxRiskDataUploaded = isBoxRiskDataUploaded;
        }

        public int getIsOldUser() {
            return isOldUser;
        }

        public void setIsOldUser(int isOldUser) {
            this.isOldUser = isOldUser;
        }

        public int getIsHasOrder() {
            return isHasOrder;
        }

        public void setIsHasOrder(int isHasOrder) {
            this.isHasOrder = isHasOrder;
        }

        public String getCustomerSources() {
            return customerSources;
        }

        public void setCustomerSources(String customerSources) {
            this.customerSources = customerSources;
        }

        public String getAnnualIncome() {
            return annualIncome;
        }

        public void setAnnualIncome(String annualIncome) {
            this.annualIncome = annualIncome;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getCompanyPhone() {
            return companyPhone;
        }

        public void setCompanyPhone(String companyPhone) {
            this.companyPhone = companyPhone;
        }

        public String getCompanyProvince() {
            return companyProvince;
        }

        public void setCompanyProvince(String companyProvince) {
            this.companyProvince = companyProvince;
        }

        public String getCompanyCity() {
            return companyCity;
        }

        public void setCompanyCity(String companyCity) {
            this.companyCity = companyCity;
        }

        public String getCompanyAddress() {
            return companyAddress;
        }

        public void setCompanyAddress(String companyAddress) {
            this.companyAddress = companyAddress;
        }

        public String getWindControl() {
            return windControl;
        }

        public void setWindControl(String windControl) {
            this.windControl = windControl;
        }

        public int getIsClick() {
            return isClick;
        }

        public void setIsClick(int isClick) {
            this.isClick = isClick;
        }

        public int getUserStatus() {
            return userStatus;
        }

        public void setUserStatus(int userStatus) {
            this.userStatus = userStatus;
        }

        public int getDeviceLock() {
            return deviceLock;
        }

        public void setDeviceLock(int deviceLock) {
            this.deviceLock = deviceLock;
        }

        public int getPeriodNum() {
            return periodNum;
        }

        public void setPeriodNum(int periodNum) {
            this.periodNum = periodNum;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public int getSendSms() {
            return sendSms;
        }

        public void setSendSms(int sendSms) {
            this.sendSms = sendSms;
        }

        public int getStatusDevice() {
            return statusDevice;
        }

        public void setStatusDevice(int statusDevice) {
            this.statusDevice = statusDevice;
        }

        public int getStatusAuth() {
            return statusAuth;
        }

        public void setStatusAuth(int statusAuth) {
            this.statusAuth = statusAuth;
        }

        public int getStatusContacts() {
            return statusContacts;
        }

        public void setStatusContacts(int statusContacts) {
            this.statusContacts = statusContacts;
        }

        public int getStatusBank() {
            return statusBank;
        }

        public void setStatusBank(int statusBank) {
            this.statusBank = statusBank;
        }

        public int getStatusIdcard() {
            return statusIdcard;
        }

        public void setStatusIdcard(int statusIdcard) {
            this.statusIdcard = statusIdcard;
        }

        public int getStatusYys() {
            return statusYys;
        }

        public void setStatusYys(int statusYys) {
            this.statusYys = statusYys;
        }

        public int getStatusZhimafen() {
            return statusZhimafen;
        }

        public void setStatusZhimafen(int statusZhimafen) {
            this.statusZhimafen = statusZhimafen;
        }

        public String getUserSource() {
            return userSource;
        }

        public void setUserSource(String userSource) {
            this.userSource = userSource;
        }

        public int getCustomerSystemNotifying() {
            return customerSystemNotifying;
        }

        public void setCustomerSystemNotifying(int customerSystemNotifying) {
            this.customerSystemNotifying = customerSystemNotifying;
        }

        public String getCustomerSystemNotifyMessage() {
            return customerSystemNotifyMessage;
        }

        public void setCustomerSystemNotifyMessage(String customerSystemNotifyMessage) {
            this.customerSystemNotifyMessage = customerSystemNotifyMessage;
        }

        public String getLoginIp() {
            return loginIp;
        }

        public void setLoginIp(String loginIp) {
            this.loginIp = loginIp;
        }

        public String getRegisterIp() {
            return registerIp;
        }

        public void setRegisterIp(String registerIp) {
            this.registerIp = registerIp;
        }

        public String getRegisterIpAddress() {
            return registerIpAddress;
        }

        public void setRegisterIpAddress(String registerIpAddress) {
            this.registerIpAddress = registerIpAddress;
        }

        public String getLoginTime() {
            return loginTime;
        }

        public void setLoginTime(String loginTime) {
            this.loginTime = loginTime;
        }

        public String getSuccessCount() {
            return successCount;
        }

        public void setSuccessCount(String successCount) {
            this.successCount = successCount;
        }
    }
}
