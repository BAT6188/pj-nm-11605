package com.harmonywisdom.dshbcbp.office.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 会议通知
 */
@Entity
@Table(name = "HW_MEETING_NOTICE")
public class MeetingNotice implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    private String id;
    /**
     * 标题
     */
    @Column(name = "TITLE",length = 100)
    private String title;
    /**
     * 地点
     */
    @Column(name = "ADDRESS",length = 100)
    private String address;
    /**
     * 会议类型:
     * 1:会场会议;
     * 2:视频会议
     */
    @Column(name = "TYPE",length = 100)
    private String type;
    /**
     * 联系人
     */
    @Column(name = "LINK_MAN",length = 100)
    private String linkMan;
    /**
     * 联系方式
     */
    @Column(name = "LINK_PHONE",length = 100)
    private String linkPhone;
    /**
     * 会议时间
     */
    @Column(name = "TIME")
    private Date time;
    /**
     * 发布单位Id
     */
    @Column(name = "PUB_ORG_ID",length = 100)
    private String pubOrgId;
    /**
     * 发布单位名称
     */
    @Column(name = "PUB_ORG_NAME",length = 100)
    private String pubOrgName;
    /**
     * 会议内容
     */
    @Column(name = "CONTENT",length = 1000)
    private String content;
    /**
     * 是否短信通知
     * 0:未发送
     * 1：已发送
     */
    @Column(name = "IS_SMS",length = 100)
    private String isSms;
    /**
     * createTime
     */
    @Column(name = "CREATE_TIME")
    private Date createTime;
    /**
     * 参会人员id(,号分隔)
     */
    @Column(name = "PERSON_IDS",length = 100)
    private String personIds;
    /**
     * 参会人员名称(,号分隔)
     */
    @Column(name = "PERSON_NAMES",length = 100)
    private String personNames;
    @Transient
    private String attachmentIds;

    public String getAttachmentIds() {
        return attachmentIds;
    }

    public void setAttachmentIds(String attachmentIds) {
        this.attachmentIds = attachmentIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getPubOrgId() {
        return pubOrgId;
    }

    public void setPubOrgId(String pubOrgId) {
        this.pubOrgId = pubOrgId;
    }

    public String getPubOrgName() {
        return pubOrgName;
    }

    public void setPubOrgName(String pubOrgName) {
        this.pubOrgName = pubOrgName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIsSms() {
        return isSms;
    }

    public void setIsSms(String isSms) {
        this.isSms = isSms;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPersonIds() {
        return personIds;
    }

    public void setPersonIds(String personIds) {
        this.personIds = personIds;
    }

    public String getPersonNames() {
        return personNames;
    }

    public void setPersonNames(String personNames) {
        this.personNames = personNames;
    }
}