package com.harmonywisdom.dshbcbp.office.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *人员管理
 */
@Entity
@Table(name = "HW_CONTACTS")
public class Contacts implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(length = 32)
    private String id;

    /**
     * 排序：值越大越靠前
     */
    @Column(name = "sort")
    private Integer sort;

    /**
     * 通讯录类型 0:通讯录人员 1:网格人员
     */
    @Column(name = "TYPE",length = 1)
    private String type;

    /**
     * 头像
     */
    @Column(name = "head_image",columnDefinition = "mediumtext")
    private String headImage;
    /**
     *姓名
     */
    @Column(name = "NAME",length = 100)
    private String name;
    /**
     *性别
     */
    @Column(name = "SEX",length = 1)
    private String sex;
    /**
     *部门
     */
    @Column(name = "DEPARTMENT",length = 100)
    private String department;
    /**
     *职务
     */
    @Column(name = "POSITION",length = 100)
    private String position;
    /**
     *单位地址
     */
    @Column(name = "ADDRESS",length = 100)
    private String address;
    /**
     *座机号码
     */
    @Column(name = "TEL",length = 100)
    private String tel;
    /**
     *手机号码
     */
    @Column(name = "PHONE",length = 100)
    private String phone;
    /**
     *所属组织机构id
     */
    @Column(name = "ORG_ID",length = 100)
    private String orgId;

    /**
     *关联apportal用户id
     */
    @Column(name = "APPORTAL_USER_Id",length = 100)
    private String apportalUserId;

    /**
     *关联apportal用户PersonId
     */
    @Column(name = "APPORTAL_PERSON_Id",length = 100)
    private String apportalPersonId;

    /**
     *关联apportal用户name
     */
    @Column(name = "APPORTAL_USER_NAME",length = 100)
    private String apportalUserName;

    /**
     * 网格级别
     */
    @Column(name = "block_level_id")
    private String blockLevelId;

    /**
     * 所属网格
     */
    @Column(name = "block_id")
    private String blockId;

    /**
     * 拼音字符串，逗号分隔
     */
    @Column(name = "PINYIN_CODES",length = 100)
    private String pinyinCodes;

    /**
     * 人员简介
     */
    @Column(name = "summary",columnDefinition = "mediumtext")
    private String summary;

    /**
     * 职责
     */
    @Column(name="BLOCK_DUTY",length = 255)
    private String blockDuty;

    /**
     * `mobile_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "mobile_timestamp",nullable = false,columnDefinition = "CURRENT_TIMESTAMP")
    private Date mobileTimestamp;


    /**
     *附件
     */
    @Transient
    private String attachmentIds;
    @Transient
    private String[] ids;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getAttachmentIds() {
        return attachmentIds;
    }

    public void setAttachmentIds(String attachmentIds) {
        this.attachmentIds = attachmentIds;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPinyinCodes() {
        return pinyinCodes;
    }

    public void setPinyinCodes(String pinyinCodes) {
        this.pinyinCodes = pinyinCodes;
    }

    public String getApportalUserId() {
        return apportalUserId;
    }

    public void setApportalUserId(String apportalUserId) {
        this.apportalUserId = apportalUserId;
    }

    public String getBlockLevelId() {
        return blockLevelId;
    }

    public void setBlockLevelId(String blockLevelId) {
        this.blockLevelId = blockLevelId;
    }

    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
    }

    public String getApportalUserName() {
        return apportalUserName;
    }

    public void setApportalUserName(String apportalUserName) {
        this.apportalUserName = apportalUserName;
    }

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getBlockDuty() {
        return blockDuty;
    }

    public void setBlockDuty(String blockDuty) {
        this.blockDuty = blockDuty;
    }

    public String getApportalPersonId() {
        return apportalPersonId;
    }

    public void setApportalPersonId(String apportalPersonId) {
        this.apportalPersonId = apportalPersonId;
    }

    public Date getMobileTimestamp() {
        return mobileTimestamp;
    }

    public void setMobileTimestamp(Date mobileTimestamp) {
        this.mobileTimestamp = mobileTimestamp;
    }
}