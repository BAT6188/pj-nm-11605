package com.harmonywisdom.dshbcbp.exelaw.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 现场检查
 */
@Entity
@Table(name = "HW_SITE_MONITORING")
public class SiteMonitoring implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    private String id;

    /**
     * 企业id
     */
    @Column(name = "enterprise_id",length=32)
    private String enterpriseId;

    /**
     *企业名称
     */
    @Column(name="ENTERPRISE_NAME",length=100)
    private String enterpriseName;

    /**
     * 网格级别，
     */
    @Column(name = "block_level_id")
    private String blockLevelId;
    @Column(name = "block_level_name")
    private String blockLevelName;

    /**
     * 所属网格，
     */
    @Column(name = "block_id", length = 32)
    private String blockId;
    @Column(name = "block_name")
    private String blockName;

    /**
     * 监察人员
     */
    @Column(name = "CHECK_PEOPLE", length = 100)
    private String checkPeople;

    /**
     * 监察时间
     */
    @Column(name="MONITORING_TIME")
    private Date monitoringTime;

    /**
     * 是否存在问题
     * 1:是，2：否
     */
    @Column(name="IS_NOT_PROBLEM")
    private String isNotProblem;

    /**
     * 备注
     */
    @Column(name="SEND_REMARK",length=1000)
    private String sendRemark;

    /**
     * 登录用户id
     */
    @Column(name="USERID")
    private String userId;

    @Transient
    private String attachmentIds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }


    public String getCheckPeople() {
        return checkPeople;
    }

    public void setCheckPeople(String checkPeople) {
        this.checkPeople = checkPeople;
    }

    public Date getMonitoringTime() {
        return monitoringTime;
    }

    public void setMonitoringTime(Date monitoringTime) {
        this.monitoringTime = monitoringTime;
    }

    public String getIsNotProblem() {
        return isNotProblem;
    }

    public void setIsNotProblem(String isNotProblem) {
        this.isNotProblem = isNotProblem;
    }

    public String getSendRemark() {
        return sendRemark;
    }

    public void setSendRemark(String sendRemark) {
        this.sendRemark = sendRemark;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAttachmentIds() {
        return attachmentIds;
    }

    public void setAttachmentIds(String attachmentIds) {
        this.attachmentIds = attachmentIds;
    }

    public String getBlockLevelId() {
        return blockLevelId;
    }

    public void setBlockLevelId(String blockLevelId) {
        this.blockLevelId = blockLevelId;
    }

    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
    }

    public String getBlockLevelName() {
        return blockLevelName;
    }

    public void setBlockLevelName(String blockLevelName) {
        this.blockLevelName = blockLevelName;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }
}