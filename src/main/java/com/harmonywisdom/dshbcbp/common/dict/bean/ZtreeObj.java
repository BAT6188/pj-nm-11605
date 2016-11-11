package com.harmonywisdom.dshbcbp.common.dict.bean;

/**
 * Created by Administrator on 2016/11/1.
 */
public class ZtreeObj {

    private String id;

    private String name;

    private String job;

    private String parentId;

    private String userId;

    private String mobilePhone;

    private String officePhone;

    private String department;

    private String icon;
    private String iconOpen;
    private String iconClose;

    private String pinyinCodes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIconOpen() {
        return iconOpen;
    }

    public void setIconOpen(String iconOpen) {
        this.iconOpen = iconOpen;
    }

    public String getIconClose() {
        return iconClose;
    }

    public void setIconClose(String iconClose) {
        this.iconClose = iconClose;
    }

    public String getPinyinCodes() {
        return pinyinCodes;
    }

    public void setPinyinCodes(String pinyinCodes) {
        this.pinyinCodes = pinyinCodes;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ZtreeObj() {
    }

    public ZtreeObj(String id, String name, String parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    public ZtreeObj(String id, String name, String parentId, String icon, String iconOpen, String iconClose) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.icon = icon;
        this.iconOpen = iconOpen;
        this.iconClose = iconClose;
    }

    public ZtreeObj(String id, String name, String job, String parentId, String mobilePhone, String officePhone, String department, String icon, String iconOpen, String iconClose, String pinyinCodes) {
        this.id = id;
        this.name = name;
        this.job = job;
        this.parentId = parentId;
        this.mobilePhone = mobilePhone;
        this.officePhone = officePhone;
        this.department = department;
        this.icon = icon;
        this.iconOpen = iconOpen;
        this.iconClose = iconClose;
        this.pinyinCodes = pinyinCodes;
    }
}
