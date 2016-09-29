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
    private int pubOrgId;
    /**
     *发布单位名称
     */
    @Column(name = "PUB_ORG_NAME",length = 100)
    private String pubOrgName;
    /**
     *类型
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
    private String attachmentId;

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

    public int getPubOrgId() {
        return pubOrgId;
    }

    public void setPubOrgId(int pubOrgId) {
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

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }
}