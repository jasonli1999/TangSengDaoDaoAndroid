package com.idss.cashloans.api.moudle;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PersonalModel extends BaseModel {

    @SerializedName("data")
    private DataDTO data;

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public static class DataDTO implements Serializable {
        @SerializedName("sysUser")
        private SysUserDTO sysUser;
        @SerializedName("permissions")
        private List<String> permissions;
        @SerializedName("roles")
        private List<String> roles;

        public SysUserDTO getSysUser() {
            return sysUser;
        }

        public void setSysUser(SysUserDTO sysUser) {
            this.sysUser = sysUser;
        }

        public List<String> getPermissions() {
            return permissions;
        }

        public void setPermissions(List<String> permissions) {
            this.permissions = permissions;
        }

        public List<String> getRoles() {
            return roles;
        }

        public void setRoles(List<String> roles) {
            this.roles = roles;
        }

        public static class SysUserDTO implements Serializable{
            @SerializedName("userId")
            private String userId;
            @SerializedName("username")
            private String username;
            @SerializedName("password")
            private String password;
            @SerializedName("createBy")
            private String createBy;
            @SerializedName("updateBy")
            private String updateBy;
            @SerializedName("createTime")
            private String createTime;
            @SerializedName("updateTime")
            private String updateTime;
            @SerializedName("delFlag")
            private String delFlag;
            @SerializedName("lockFlag")
            private String lockFlag;
            @SerializedName("phone")
            private Object phone;
            @SerializedName("countryCode")
            private String countryCode;
            @SerializedName("avatar")
            private Object avatar;
            @SerializedName("tenantId")
            private String tenantId;
            @SerializedName("wxOpenid")
            private Object wxOpenid;
            @SerializedName("miniOpenid")
            private Object miniOpenid;
            @SerializedName("gmailLogin")
            private Object gmailLogin;
            @SerializedName("nickname")
            private Object nickname;
            @SerializedName("name")
            private Object name;
            @SerializedName("email")
            private Object email;
            @SerializedName("parentId")
            private Object parentId;
            @SerializedName("parentIds")
            private Object parentIds;
            @SerializedName("inviteCode")
            private String inviteCode;
            @SerializedName("h5Url")
            private String h5Url;
            @SerializedName("tenantName")
            private String tenantName;
            @SerializedName("localAddress")
            private Object localAddress;
            @SerializedName("ownerAddress")
            private Object ownerAddress;
            @SerializedName("balance")
            private int balance;
            @SerializedName("firstRechargeAmount")
            private int firstRechargeAmount;
            @SerializedName("setCashPassword")
            private boolean setCashPassword;
            @SerializedName("bankAccount")
            private Object bankAccount;
            @SerializedName("bankName")
            private Object bankName;
            @SerializedName("realName")
            private Object realName;
            @SerializedName("accountType")
            private Object accountType;
            @SerializedName("registIp")
            private String registIp;
            @SerializedName("lastLoginIp")
            private String lastLoginIp;
            @SerializedName("googleSecret")
            private Object googleSecret;
            @SerializedName("googleQRBarcodeURL")
            private Object googleQRBarcodeURL;

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getCreateBy() {
                return createBy;
            }

            public void setCreateBy(String createBy) {
                this.createBy = createBy;
            }

            public String getUpdateBy() {
                return updateBy;
            }

            public void setUpdateBy(String updateBy) {
                this.updateBy = updateBy;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public String getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(String delFlag) {
                this.delFlag = delFlag;
            }

            public String getLockFlag() {
                return lockFlag;
            }

            public void setLockFlag(String lockFlag) {
                this.lockFlag = lockFlag;
            }

            public Object getPhone() {
                return phone;
            }

            public void setPhone(Object phone) {
                this.phone = phone;
            }

            public String getCountryCode() {
                return countryCode;
            }

            public void setCountryCode(String countryCode) {
                this.countryCode = countryCode;
            }

            public Object getAvatar() {
                return avatar;
            }

            public void setAvatar(Object avatar) {
                this.avatar = avatar;
            }

            public String getTenantId() {
                return tenantId;
            }

            public void setTenantId(String tenantId) {
                this.tenantId = tenantId;
            }

            public Object getWxOpenid() {
                return wxOpenid;
            }

            public void setWxOpenid(Object wxOpenid) {
                this.wxOpenid = wxOpenid;
            }

            public Object getMiniOpenid() {
                return miniOpenid;
            }

            public void setMiniOpenid(Object miniOpenid) {
                this.miniOpenid = miniOpenid;
            }

            public Object getGmailLogin() {
                return gmailLogin;
            }

            public void setGmailLogin(Object gmailLogin) {
                this.gmailLogin = gmailLogin;
            }

            public Object getNickname() {
                return nickname;
            }

            public void setNickname(Object nickname) {
                this.nickname = nickname;
            }

            public Object getName() {
                return name;
            }

            public void setName(Object name) {
                this.name = name;
            }

            public Object getEmail() {
                return email;
            }

            public void setEmail(Object email) {
                this.email = email;
            }

            public Object getParentId() {
                return parentId;
            }

            public void setParentId(Object parentId) {
                this.parentId = parentId;
            }

            public Object getParentIds() {
                return parentIds;
            }

            public void setParentIds(Object parentIds) {
                this.parentIds = parentIds;
            }

            public String getInviteCode() {
                return inviteCode;
            }

            public void setInviteCode(String inviteCode) {
                this.inviteCode = inviteCode;
            }

            public String getH5Url() {
                return h5Url;
            }

            public void setH5Url(String h5Url) {
                this.h5Url = h5Url;
            }

            public String getTenantName() {
                return tenantName;
            }

            public void setTenantName(String tenantName) {
                this.tenantName = tenantName;
            }

            public Object getLocalAddress() {
                return localAddress;
            }

            public void setLocalAddress(Object localAddress) {
                this.localAddress = localAddress;
            }

            public Object getOwnerAddress() {
                return ownerAddress;
            }

            public void setOwnerAddress(Object ownerAddress) {
                this.ownerAddress = ownerAddress;
            }

            public int getBalance() {
                return balance;
            }

            public void setBalance(int balance) {
                this.balance = balance;
            }

            public int getFirstRechargeAmount() {
                return firstRechargeAmount;
            }

            public void setFirstRechargeAmount(int firstRechargeAmount) {
                this.firstRechargeAmount = firstRechargeAmount;
            }

            public boolean isSetCashPassword() {
                return setCashPassword;
            }

            public void setSetCashPassword(boolean setCashPassword) {
                this.setCashPassword = setCashPassword;
            }

            public Object getBankAccount() {
                return bankAccount;
            }

            public void setBankAccount(Object bankAccount) {
                this.bankAccount = bankAccount;
            }

            public Object getBankName() {
                return bankName;
            }

            public void setBankName(Object bankName) {
                this.bankName = bankName;
            }

            public Object getRealName() {
                return realName;
            }

            public void setRealName(Object realName) {
                this.realName = realName;
            }

            public Object getAccountType() {
                return accountType;
            }

            public void setAccountType(Object accountType) {
                this.accountType = accountType;
            }

            public String getRegistIp() {
                return registIp;
            }

            public void setRegistIp(String registIp) {
                this.registIp = registIp;
            }

            public String getLastLoginIp() {
                return lastLoginIp;
            }

            public void setLastLoginIp(String lastLoginIp) {
                this.lastLoginIp = lastLoginIp;
            }

            public Object getGoogleSecret() {
                return googleSecret;
            }

            public void setGoogleSecret(Object googleSecret) {
                this.googleSecret = googleSecret;
            }

            public Object getGoogleQRBarcodeURL() {
                return googleQRBarcodeURL;
            }

            public void setGoogleQRBarcodeURL(Object googleQRBarcodeURL) {
                this.googleQRBarcodeURL = googleQRBarcodeURL;
            }
        }
    }
}
