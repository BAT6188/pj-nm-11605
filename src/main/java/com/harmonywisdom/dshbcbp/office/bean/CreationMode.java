package com.harmonywisdom.dshbcbp.office.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 创模建设信息管理
 */
@Entity
@Table(name = "HW_CREATION_MODE")
public class CreationMode implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    private String id;
    /**
     * 标题
     */
    @Column(name = "TITLE", length = 100)
    private String title;
    /**
     * 发布单位id
     */
    @Column(name = "PUB_ORG_ID", length = 100)
    private int pubOrgId;
    /**
     * 发布单位
     */
    @Column(name = "PUB_ORG_NAME", length = 100)
    private String pubOrgName;
    /**
     * 发布时间
     */
    @Column(name = "PUB_TIME")
    private Date pubTime;
    /**
     * 内容概要
     */
    @Column(name = "CONTENT", length = 200)
    private String content;
    /**
     * 附件ID
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

    public Date getPubTime() {
        return pubTime;
    }

    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAttachmentIds() {
        return attachmentIds;
    }

    public void setAttachmentIds(String attachmentIds) {
        this.attachmentIds = attachmentIds;
    }
}