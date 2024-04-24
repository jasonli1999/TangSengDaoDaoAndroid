package com.idss.cashloans.api.moudle;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Certification implements Serializable {

    @SerializedName("address")
    private String address;
    @SerializedName("addressBookList")
    private List<AddressBookListDTO> addressBookList;
    @SerializedName("bankCard")
    private String bankCard;
    @SerializedName("bankCardName")
    private String bankCardName;
    @SerializedName("bindingDeviceModel")
    private String bindingDeviceModel;
    @SerializedName("cid")
    private String cid;
    @SerializedName("city")
    private String city;
    @SerializedName("clientInfo")
    private String clientInfo;
    @SerializedName("latitude")
    private String latitude;
    @SerializedName("longitude")
    private String longitude;
    @SerializedName("memberAppList")
    private List memberAppList;
    @SerializedName("memberCallLogList")
    private List<MemberCallLogListDTO> memberCallLogList;
    @SerializedName("memberEmergencyContactList")
    private List<MemberEmergencyContactListDTO> memberEmergencyContactList;
    @SerializedName("memberSmsList")
    private List<MemberSmsListDTO> memberSmsList;
    @SerializedName("province")
    private String province;
    @SerializedName("qq")
    private String qq;
    @SerializedName("realNameDto")
    private RealNameDtoDTO realNameDto;
    @SerializedName("registerDeviceCode")
    private String registerDeviceCode;
    @SerializedName("unitName")
    private String unitName;
    @SerializedName("unitPhone")
    private String unitPhone;
    @SerializedName("workAddress")
    private String workAddress;
    @SerializedName("workCity")
    private String workCity;
    @SerializedName("workProvince")
    private String workProvince;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<AddressBookListDTO> getAddressBookList() {
        return addressBookList;
    }

    public void setAddressBookList(List<AddressBookListDTO> addressBookList) {
        this.addressBookList = addressBookList;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getBankCardName() {
        return bankCardName;
    }

    public void setBankCardName(String bankCardName) {
        this.bankCardName = bankCardName;
    }

    public String getBindingDeviceModel() {
        return bindingDeviceModel;
    }

    public void setBindingDeviceModel(String bindingDeviceModel) {
        this.bindingDeviceModel = bindingDeviceModel;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(String clientInfo) {
        this.clientInfo = clientInfo;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public List getMemberAppList() {
        return memberAppList;
    }

    public void setMemberAppList(List memberAppList) {
        this.memberAppList = memberAppList;
    }

    public List<MemberCallLogListDTO> getMemberCallLogList() {
        return memberCallLogList;
    }

    public void setMemberCallLogList(List<MemberCallLogListDTO> memberCallLogList) {
        this.memberCallLogList = memberCallLogList;
    }

    public List<MemberEmergencyContactListDTO> getMemberEmergencyContactList() {
        return memberEmergencyContactList;
    }

    public void setMemberEmergencyContactList(List<MemberEmergencyContactListDTO> memberEmergencyContactList) {
        this.memberEmergencyContactList = memberEmergencyContactList;
    }

    public List<MemberSmsListDTO> getMemberSmsList() {
        return memberSmsList;
    }

    public void setMemberSmsList(List<MemberSmsListDTO> memberSmsList) {
        this.memberSmsList = memberSmsList;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public RealNameDtoDTO getRealNameDto() {
        return realNameDto;
    }

    public void setRealNameDto(RealNameDtoDTO realNameDto) {
        this.realNameDto = realNameDto;
    }

    public String getRegisterDeviceCode() {
        return registerDeviceCode;
    }

    public void setRegisterDeviceCode(String registerDeviceCode) {
        this.registerDeviceCode = registerDeviceCode;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitPhone() {
        return unitPhone;
    }

    public void setUnitPhone(String unitPhone) {
        this.unitPhone = unitPhone;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    public String getWorkCity() {
        return workCity;
    }

    public void setWorkCity(String workCity) {
        this.workCity = workCity;
    }

    public String getWorkProvince() {
        return workProvince;
    }

    public void setWorkProvince(String workProvince) {
        this.workProvince = workProvince;
    }

    public static class MemberAppListDTO {

    }

    public static class RealNameDtoDTO {
        @SerializedName("idCard")
        private String idCard;
        @SerializedName("realName")
        private String realName;

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }
    }

    public static class AddressBookListDTO implements Serializable {
        @SerializedName("contactPerson")
        private String contactPerson;
        @SerializedName("count")
        private int count;
        @SerializedName("createBy")
        private String createBy;
        @SerializedName("createTime")
        private String createTime;
        @SerializedName("id")
        private String id;
        @SerializedName("memberId")
        private int memberId;
        @SerializedName("phone")
        private String phone;
        @SerializedName("relation")
        private int relation;
        @SerializedName("relationship")
        private String relationship;
        @SerializedName("remark")
        private String remark;
        @SerializedName("searchValue")
        private String searchValue;
        @SerializedName("tenantId")
        private int tenantId;
        @SerializedName("type")
        private int type;
        @SerializedName("updateBy")
        private String updateBy;
        @SerializedName("updateTime")
        private String updateTime;

        public String getContactPerson() {
            return contactPerson;
        }

        public void setContactPerson(String contactPerson) {
            this.contactPerson = contactPerson;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getRelation() {
            return relation;
        }

        public void setRelation(int relation) {
            this.relation = relation;
        }

        public String getRelationship() {
            return relationship;
        }

        public void setRelationship(String relationship) {
            this.relationship = relationship;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getSearchValue() {
            return searchValue;
        }

        public void setSearchValue(String searchValue) {
            this.searchValue = searchValue;
        }

        public int getTenantId() {
            return tenantId;
        }

        public void setTenantId(int tenantId) {
            this.tenantId = tenantId;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
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
    }

    public static class MemberCallLogListDTO implements Serializable {
        @SerializedName("callTime")
        private String callTime;
        @SerializedName("dialType")
        private String dialType;
        @SerializedName("duration")
        private int duration;
        @SerializedName("location")
        private String location;
        @SerializedName("name")
        private String name;
        @SerializedName("peerNumber")
        private String peerNumber;

        public String getCallTime() {
            return callTime;
        }

        public void setCallTime(String callTime) {
            this.callTime = callTime;
        }

        public String getDialType() {
            return dialType;
        }

        public void setDialType(String dialType) {
            this.dialType = dialType;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPeerNumber() {
            return peerNumber;
        }

        public void setPeerNumber(String peerNumber) {
            this.peerNumber = peerNumber;
        }
    }

    public static class MemberEmergencyContactListDTO {
        @SerializedName("contactPerson")
        private String contactPerson;
        @SerializedName("count")
        private int count;
        @SerializedName("createBy")
        private String createBy;
        @SerializedName("createTime")
        private String createTime;
        @SerializedName("id")
        private int id;
        @SerializedName("memberId")
        private int memberId;
        @SerializedName("phone")
        private String phone;
        @SerializedName("relation")
        private int relation;
        @SerializedName("relationship")
        private String relationship;
        @SerializedName("remark")
        private String remark;
        @SerializedName("searchValue")
        private String searchValue;
        @SerializedName("tenantId")
        private int tenantId;
        @SerializedName("type")
        private int type;
        @SerializedName("updateBy")
        private String updateBy;
        @SerializedName("updateTime")
        private String updateTime;

        public String getContactPerson() {
            return contactPerson;
        }

        public void setContactPerson(String contactPerson) {
            this.contactPerson = contactPerson;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getRelation() {
            return relation;
        }

        public void setRelation(int relation) {
            this.relation = relation;
        }

        public String getRelationship() {
            return relationship;
        }

        public void setRelationship(String relationship) {
            this.relationship = relationship;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getSearchValue() {
            return searchValue;
        }

        public void setSearchValue(String searchValue) {
            this.searchValue = searchValue;
        }

        public int getTenantId() {
            return tenantId;
        }

        public void setTenantId(int tenantId) {
            this.tenantId = tenantId;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
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
    }

    public static class MemberSmsListDTO implements Serializable {

        @SerializedName("createTime")
        private String createTime;

        @SerializedName("memberId")
        private int memberId;
        @SerializedName("mobile")
        private String mobile;
        @SerializedName("name")
        private String name;
        @SerializedName("remark")
        private String remark;
        @SerializedName("searchValue")
        private String searchValue;
        @SerializedName("smsContext")
        private String smsContext;
        @SerializedName("smsTime")
        private String smsTime;
        @SerializedName("tenantId")
        private int tenantId;
        @SerializedName("type")
        private String type;
        @SerializedName("updateBy")
        private String updateBy;
        @SerializedName("updateTime")
        private String updateTime;


        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }


        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getSearchValue() {
            return searchValue;
        }

        public void setSearchValue(String searchValue) {
            this.searchValue = searchValue;
        }

        public String getSmsContext() {
            return smsContext;
        }

        public void setSmsContext(String smsContext) {
            this.smsContext = smsContext;
        }

        public String getSmsTime() {
            return smsTime;
        }

        public void setSmsTime(String smsTime) {
            this.smsTime = smsTime;
        }

        public int getTenantId() {
            return tenantId;
        }

        public void setTenantId(int tenantId) {
            this.tenantId = tenantId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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
    }
}
