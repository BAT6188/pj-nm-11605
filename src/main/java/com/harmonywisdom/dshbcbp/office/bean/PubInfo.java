package com.harmonywisdom.dshbcbp.office.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *信息公告
 */
@Entity
@Table(name = "HW_PUB_INFO")
public class PubInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    private String id;
    /**
     *标题
     */
    @Column(name = "TITLE",length = 100)
    private String title;
    /**
     *发布时间
     */
    @Column(name = "PUB_TIME")
    private Date pubTime;
    /**
     *发布单位id
     */
    @Column(name = "PUB_ORG_ID",length = 100)
    private String pubOrgId;
    /**
     *发布单位名称
     */
    @Column(name = "PUB_ORG_NAME",length = 100)
    private String pubOrgName;

    @Column(name = "USER_ID",length = 100)
    private String userID;
    @Column(name = "USER_NAME",length = 100)
    private String userName;
    /**
     *公告类型
     */
    @Column(name = "TYPE",length = 100)
    private String type;
    /**
     * 查看权限:
     * 1:环保局
     * 2:局机关
     * 3:监查大队
     * 4:监测站
     * 5:企业
      */
    @Column(name = "GRADE",columnDefinition = "mediumtext")
    private String grade;
    /**
     *公告详情
     */
    @Column(name = "CONTENT",length = 255)
    private String content;
    /**
     * 0：未发布
     * 1：已发布
     */
    @Column(name = "STATUS",length = 1)
    private String status;


    /**
     * `mobile_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "mobile_timestamp",nullable = false,columnDefinition = "CURRENT_TIMESTAMP")
    private Date mobileTimestamp;

    @Transient
    private String startTime;
    @Transient
    private String endTime;
    @Transient
    private String attachmentIds;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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

    public Date getPubTime() {
        return pubTime;
    }

    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getMobileTimestamp() {
        return mobileTimestamp;
    }

    public void setMobileTimestamp(Date mobileTimestamp) {
        this.mobileTimestamp = mobileTimestamp;
    }
}