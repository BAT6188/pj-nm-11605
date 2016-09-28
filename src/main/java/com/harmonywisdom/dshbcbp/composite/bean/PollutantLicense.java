package com.harmonywisdom.dshbcbp.composite.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 排污许可
 */
@Entity
@Table(name = "HW_POLLUTANT_LICENSE")
public class PollutantLicense implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    private String id;
    /**
     *许可证号
     */
    @Column(name = "CODE",length = 100)
    private String code;
    /**
     *许可证类型
     */
    @Column(name = "TYPE",length = 100)
    private String type;
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
     *氧化硫（吨/年）
     */
    @Column(name = "SULFUR",length = 100)
    private Double sulfur;
    /**
     *氮氧化物
     */
    @Column(name = "NITROGEN",length = 100)
    private Double nitrogen;
    /**
     *化学需氧量
     */
    @Column(name = "OXYGEN",length = 100)
    private Double oxygen;
    /**
     *氨氮需量
     */
    @Column(name = "AMMONIA",length = 100)
    private Double ammonia;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Double getSulfur() {
        return sulfur;
    }

    public void setSulfur(Double sulfur) {
        this.sulfur = sulfur;
    }

    public Double getNitrogen() {
        return nitrogen;
    }

    public void setNitrogen(Double nitrogen) {
        this.nitrogen = nitrogen;
    }

    public Double getOxygen() {
        return oxygen;
    }

    public void setOxygen(Double oxygen) {
        this.oxygen = oxygen;
    }

    public Double getAmmonia() {
        return ammonia;
    }

    public void setAmmonia(Double ammonia) {
        this.ammonia = ammonia;
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