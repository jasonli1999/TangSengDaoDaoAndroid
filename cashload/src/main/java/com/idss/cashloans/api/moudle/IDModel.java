package com.idss.cashloans.api.moudle;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class IDModel implements Serializable{
    @SerializedName("holdCardPhoto")
    private String holdCardPhoto;
    @SerializedName("idCardNo")
    private String idCardNo;
    @SerializedName("idcardBackPhoto")
    private String idcardBackPhoto;
    @SerializedName("idcardFrontPhoto")
    private String idcardFrontPhoto;
    @SerializedName("metaInfo")
    private MetaInfoDTO metaInfo;
    @SerializedName("nameTrue")
    private String nameTrue;

    public String getHoldCardPhoto() {
        return holdCardPhoto;
    }

    public void setHoldCardPhoto(String holdCardPhoto) {
        this.holdCardPhoto = holdCardPhoto;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getIdcardBackPhoto() {
        return idcardBackPhoto;
    }

    public void setIdcardBackPhoto(String idcardBackPhoto) {
        this.idcardBackPhoto = idcardBackPhoto;
    }

    public String getIdcardFrontPhoto() {
        return idcardFrontPhoto;
    }

    public void setIdcardFrontPhoto(String idcardFrontPhoto) {
        this.idcardFrontPhoto = idcardFrontPhoto;
    }

    public MetaInfoDTO getMetaInfo() {
        return metaInfo;
    }

    public void setMetaInfo(MetaInfoDTO metaInfo) {
        this.metaInfo = metaInfo;
    }

    public String getNameTrue() {
        return nameTrue;
    }

    public void setNameTrue(String nameTrue) {
        this.nameTrue = nameTrue;
    }

    public static class MetaInfoDTO implements Serializable {
        @SerializedName("apdidToken")
        private String apdidToken;
        @SerializedName("appName")
        private String appName;
        @SerializedName("appVersion")
        private String appVersion;
        @SerializedName("bioMetaInfo")
        private String bioMetaInfo;
        @SerializedName("deviceModel")
        private String deviceModel;
        @SerializedName("deviceType")
        private String deviceType;
        @SerializedName("osVersion")
        private String osVersion;
        @SerializedName("sdkVersion")
        private String sdkVersion;
        @SerializedName("zimVer")
        private String zimVer;

        public String getApdidToken() {
            return apdidToken;
        }

        public void setApdidToken(String apdidToken) {
            this.apdidToken = apdidToken;
        }

        public String getAppName() {
            return appName;
        }

        public void setAppName(String appName) {
            this.appName = appName;
        }

        public String getAppVersion() {
            return appVersion;
        }

        public void setAppVersion(String appVersion) {
            this.appVersion = appVersion;
        }

        public String getBioMetaInfo() {
            return bioMetaInfo;
        }

        public void setBioMetaInfo(String bioMetaInfo) {
            this.bioMetaInfo = bioMetaInfo;
        }

        public String getDeviceModel() {
            return deviceModel;
        }

        public void setDeviceModel(String deviceModel) {
            this.deviceModel = deviceModel;
        }

        public String getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
        }

        public String getOsVersion() {
            return osVersion;
        }

        public void setOsVersion(String osVersion) {
            this.osVersion = osVersion;
        }

        public String getSdkVersion() {
            return sdkVersion;
        }

        public void setSdkVersion(String sdkVersion) {
            this.sdkVersion = sdkVersion;
        }

        public String getZimVer() {
            return zimVer;
        }

        public void setZimVer(String zimVer) {
            this.zimVer = zimVer;
        }
    }
}
