package com.idss.cashloans.api.moudle;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AppBeans implements Serializable {
    @SerializedName("icon")
    private String icon;
    @SerializedName("lastDate")
    private long lastDate;
    @SerializedName("version")
    private String version;
    @SerializedName("firstDate")
    private long firstDate;
    @SerializedName("size")
    private long size;
    @SerializedName("packageName")
    private String packageName;
    @SerializedName("name")
    private String name;
    @SerializedName("path")
    private String path;
    @SerializedName("isSystem")
    private boolean isSystem;

    public boolean isSystem() {
        return isSystem;
    }

    public void setSystem(boolean system) {
        isSystem = system;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public long getLastDate() {
        return lastDate;
    }

    public void setLastDate(long lastDate) {
        this.lastDate = lastDate;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public long getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(long firstDate) {
        this.firstDate = firstDate;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "AppBeans{" +
                "icon='" + icon + '\'' +
                ", lastDate=" + lastDate +
                ", version='" + version + '\'' +
                ", firstDate=" + firstDate +
                ", size=" + size +
                ", packageName='" + packageName + '\'' +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", isSystem=" + isSystem +
                '}';
    }
}
