package com.harmonywisdom.dshbcbp.office.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "HW_DSHBCBP_PUB_INFO_REL_TABLE")
public class PubInfoRelTable implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 64)
    private String id;

    @Column(name = "org_id",length = 64)
    private String orgId;

    @Column(name = "pub_info_id",length = 64)
    private String pubInfoId;

    @Column(name = "PUB_TIME")
    private Date pubTime;

    @Column(name = "TYPE",length = 100)
    private String type;

    @Column(name = "TITLE",length = 100)
    private String title;

    @Column(name = "STATUS",length = 1)
    private String status;

    /**
     * `mobile_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "mobile_timestamp",nullable = false,columnDefinition = "CURRENT_TIMESTAMP")
    private Date mobileTimestamp;
    /**
     * 发布部门
     */
    @Column(name = "PUB_ORG_NAME",length = 100)
    private String pubOrgName;

    @Transient
    private String startTime;
    @Transient
    private String endTime;
    @Transient
    private PubInfo pubInfo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getPubInfoId() {
        return pubInfoId;
    }

    public void setPubInfoId(String pubInfoId) {
        this.pubInfoId = pubInfoId;
    }

    public Date getPubTime() {
        return pubTime;
    }

    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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

    public String getPubOrgName() {
        return pubOrgName;
    }

    public void setPubOrgName(String pubOrgName) {
        this.pubOrgName = pubOrgName;
    }

    public PubInfoRelTable() {
    }

    public PubInfoRelTable(String orgId,String pubOrgName, String pubInfoId, Date pubTime, String type, String title, String status) {
        this.orgId = orgId;
        this.pubOrgName=pubOrgName;
        this.pubInfoId = pubInfoId;
        this.pubTime = pubTime;
        this.type = type;
        this.title = title;
        this.status = status;
    }

    public PubInfo getPubInfo() {
        return pubInfo;
    }

    public void setPubInfo(PubInfo pubInfo) {
        this.pubInfo = pubInfo;
    }

    public Date getMobileTimestamp() {
        return mobileTimestamp;
    }

    public void setMobileTimestamp(Date mobileTimestamp) {
        this.mobileTimestamp = mobileTimestamp;
    }
}