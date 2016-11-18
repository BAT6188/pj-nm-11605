package com.harmonywisdom.dshbcbp.office.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *  工作总结
 */
@Entity
@Table(name = "HW_WORK_SUM")
public class WorkSum implements Serializable {
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
     *发布单位Id
     */
    @Column(name = "PUB_ORG_ID",length = 100)
    private String pubOrgId;
    /**
     *发布单位名称
     */
    @Column(name = "PUB_ORG_NAME",length = 100)
    private String pubOrgName;
    /**
     *本单位发布状态 0:未发布 1已发布
     */
    @Column(name = "PUBLISH_STATUS",length = 100)
    private String publishStatus;
    /**
     *是否为公共资源 0:不可看 1可看
     */
    @Column(name = "IS_COMMON_DATA",length = 100)
    private String isCommonData;

    /**
     *类型  1：计划 2：进度 3:总结
     */
    @Column(name = "TYPE",length = 100)
    private String type;
    /**
     *描述
     */
    @Column(name = "DESCRIPTION",length = 500)
    private String description;
    /**
     *附件
     */
    @Transient
    private String attachmentIds;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAttachmentIds() {
        return attachmentIds;
    }

    public void setAttachmentIds(String attachmentIds) {
        this.attachmentIds = attachmentIds;
    }

    public String getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(String publishStatus) {
        this.publishStatus = publishStatus;
    }

    public String getIsCommonData() {
        return isCommonData;
    }

    public void setIsCommonData(String isCommonData) {
        this.isCommonData = isCommonData;
    }
}