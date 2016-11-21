package com.harmonywisdom.dshbcbp.enterprise.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 *环境自测方案
 */
@Entity
@Table(name = "HW_ENV_TEST_PROGRAM")
public class EnvTestProgram implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    private String id;

    /**
     * 年份
     */
    @Column(name = "year", length = 6)
    private Integer year;

    /**
     * 发布状态 0:未发布 1:已发布
     */
    @Column(name = "pub_status", length = 1)
    private String pubStatus;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 附件名称
     */
    @Column(name = "attachment_name", length = 32)
    private String attachmentName;

    /**
     * 附件
     */
    @Column(name = "attachment_id", length = 32)
    private String attachmentId;
    /**
     * 主要内容
     */
    @Column(name = "content")
    private String content;
    /**
     * 企业id
     */
    @Column(name = "enterprise_id", length = 32)
    private String enterpriseId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPubStatus() {
        return pubStatus;
    }

    public void setPubStatus(String pubStatus) {
        this.pubStatus = pubStatus;
    }
}