package com.harmonywisdom.dshbcbp.composite.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 清洁生产许可
 */
@Entity
@Table(name = "HW_CLEAN_LICENSE")
public class CleanLicense implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    private String id;
    /**
     *清洁生产审核名称
     */
    @Column(name = "NAME",length = 100)
    private String name;
    /**
     *有效期起始日期
     */
    @Column(name = "START_DATE",length = 100)
    private Date startDate;
    /**
     *有效期结束日期
     */
    @Column(name = "END_DATE",length = 100)
    private Date endDate;
    /**
     *发证机关
     */
    @Column(name = "PUB_ORG",length = 100)
    private String pubOrg;
    /**
     *发证日期
     */
    @Column(name = "PUB_DATE",length = 100)
    private Date pubDate;
    /**
     *附件
     */
    @Transient
    private String attachmentId;
    /**
     *所属企业id
     */
    @Column(name = "ENTERPRISE_ID",length = 100)
    private String enterpriseId;

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getPubOrg() {
        return pubOrg;
    }

    public void setPubOrg(String pubOrg) {
        this.pubOrg = pubOrg;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
}