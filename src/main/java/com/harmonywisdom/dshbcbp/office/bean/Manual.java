package com.harmonywisdom.dshbcbp.office.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 环保手册知识
 */
@Entity
@Table(name = "HW_MANUAL")
public class Manual implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    private String id;
    /**
     *文件名称
     */
    @Column(name = "FILE_NAME",length = 100)
    private String fileName;
    /**
     *创建时间
     */
    @Column(name = "CREATE_TIME")
    private Date createTime;
    /**
     * 类型:
     * 1:法律法规
     * 2:行业标准
     * 3:监察指南
     * 4:知识案例
     */
    @Column(name = "TYPE",length = 100)
    private String type;
    /**
     * 级别:
     * 1:一级
     * 2:二级
     *
     */
    @Column(name = "LEVEL",length = 100)
    private String level;
    /**
     *制订单位
     */
    @Column(name = "enact_Org_Name",length = 100)
    private String enactOrgName;
    /**
     *内容摘要
     */
    @Column(name = "CONTENT",length = 200)
    private String content;
    /**
     *发布时间
     */
    @Column(name = "PUB_TIME")
    private Date pubTime;
    /**
     *适用范围
     */
    @Column(name = "FIT_RANGE",length = 100)
    private String fitRange;
    /**
     *备注
     */
    @Column(name = "remark",length = 200)
    private String remark;
    /**
     *附件
     */
    @Transient
    private String attachmentIds;

    /**
     * 父节点 pid
     */
    @Column(name = "pid",length = 200)
    private String pid;


    /**
     * `mobile_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "mobile_timestamp",columnDefinition = "CURRENT_TIMESTAMP")
    private Date mobileTimestamp;


    @Transient
    private List<Manual> children;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public List<Manual> getChildren() {
        return children;
    }

    public void setChildren(List<Manual> children) {
        this.children = children;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getEnactOrgName() {
        return enactOrgName;
    }

    public void setEnactOrgName(String enactOrgName) {
        this.enactOrgName = enactOrgName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPubTime() {
        return pubTime;
    }

    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
    }

    public String getFitRange() {
        return fitRange;
    }

    public void setFitRange(String fitRange) {
        this.fitRange = fitRange;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAttachmentIds() {
        return attachmentIds;
    }

    public void setAttachmentIds(String attachmentIds) {
        this.attachmentIds = attachmentIds;
    }

    public Date getMobileTimestamp() {
        return mobileTimestamp;
    }

    public void setMobileTimestamp(Date mobileTimestamp) {
        this.mobileTimestamp = mobileTimestamp;
    }
}