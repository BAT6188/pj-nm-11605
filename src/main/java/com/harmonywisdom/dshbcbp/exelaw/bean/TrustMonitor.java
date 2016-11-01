package com.harmonywisdom.dshbcbp.exelaw.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 委托监测
 */
@Entity
@Table(name = "HW_TRUST_MONITOR")
public class TrustMonitor implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    private String id;


    @Column(name = "enterprise_id", length = 32)
    private String enterpriseId;

    @Column(name = "enterprise_name", length = 100)
    private String enterpriseName;

    /**
     * 网格级别
     */
    @Column(name = "block_level_id")
    private String blockLevelId;
    @Column(name = "block_level_name")
    private String blockLevelName;

    /**
     * 所属网格
     */
    @Column(name = "block_id", length = 32)
    private String blockId;
    @Column(name = "block_name")
    private String blockName;

    /**
     * 环保站 选择人员列表
     */
    @Column(name = "environmental_protection_station_select_person_list")
    private String environmentalProtectionStationSelectPersonList;


    /**
     * 申请单位
     */
    @Column(name = "apply_org_id")
    private String applyOrgId;
    @Column(name = "apply_org")
    private String applyOrg;

    /**
     * 申请人
     */
    @Column(name = "applicant")
    private String applicant;

    /**
     * 申请人联系方式
     */
    @Column(name = "applicant_phone", length = 11)
    private String applicantPhone;

    /**
     * 委托单位
     */
    @Column(name = "trust_org")
    private String trustOrg;
    /**
     * 委托单位地点
     */
    @Column(name = "trust_org_address")
    private String trustOrgAddress;
    /**
     * 委托人
     */
    @Column(name = "truster")
    private String truster;
    /**
     * 委托人联系方式
     */
    @Column(name = "truster_phone", length = 11)
    private String trusterPhone;
    /**
     * 监测时间
     */
    @Column(name = "monitor_time")
    private Date monitorTime;
    /**
     * 监测地点
     */
    @Column(name = "monitor_address")
    private String monitorAddress;
    /**
     * 监测内容
     */
    @Column(name = "monitor_content")
    private String monitorContent;

    /**
     * 监测内容详情
     */
    @Column(name = "monitor_content_detail")
    private String monitorContentDetail;

    /**
     * 审核人
     */
    @Column(name = "auditor")
    private String auditor;
    /**
     * 审核时间
     */
    @Column(name = "audit_time")
    private Date auditTime;
    /**
     * 审核人职务
     */
    @Column(name = "audit_position")
    private String auditPosition;
    /**
     * 联系方式
     */
    @Column(name = "auditor_phone", length = 11)
    private String auditorPhone;

    @Column(name = "attachment_id", length = 32)
    private String attachmentId;
    /**
     * 反馈单位
     */
    @Column(name = "feedback_org")
    private int feedbackOrg;
    /**
     * 反馈内容
     */
    @Column(name = "feedback_content")
    private int feedbackContent;
    /**
     * 监测详情
     */
    @Column(name = "result_desc")
    private int resultDesc;
    /**
     * 监测人
     */
    @Column(name = "monitor", length = 32)
    private int monitor;
    /**
     * 联系方式
     */
    @Column(name = "monitor_phone", length = 11)
    private int monitorPhone;
    /**
     * 监测报告（附件）
     */
    @Column(name = "report_attr_id", length = 32)
    private int reportAttrId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getApplyOrg() {
        return applyOrg;
    }

    public void setApplyOrg(String applyOrg) {
        this.applyOrg = applyOrg;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getApplicantPhone() {
        return applicantPhone;
    }

    public void setApplicantPhone(String applicantPhone) {
        this.applicantPhone = applicantPhone;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getTrustOrg() {
        return trustOrg;
    }

    public void setTrustOrg(String trustOrg) {
        this.trustOrg = trustOrg;
    }

    public String getTrustOrgAddress() {
        return trustOrgAddress;
    }

    public void setTrustOrgAddress(String trustOrgAddress) {
        this.trustOrgAddress = trustOrgAddress;
    }

    public String getTruster() {
        return truster;
    }

    public void setTruster(String truster) {
        this.truster = truster;
    }

    public String getTrusterPhone() {
        return trusterPhone;
    }

    public void setTrusterPhone(String trusterPhone) {
        this.trusterPhone = trusterPhone;
    }

    public Date getMonitorTime() {
        return monitorTime;
    }

    public void setMonitorTime(Date monitorTime) {
        this.monitorTime = monitorTime;
    }

    public String getMonitorAddress() {
        return monitorAddress;
    }

    public void setMonitorAddress(String monitorAddress) {
        this.monitorAddress = monitorAddress;
    }

    public String getMonitorContent() {
        return monitorContent;
    }

    public void setMonitorContent(String monitorContent) {
        this.monitorContent = monitorContent;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditPosition() {
        return auditPosition;
    }

    public void setAuditPosition(String auditPosition) {
        this.auditPosition = auditPosition;
    }

    public String getAuditorPhone() {
        return auditorPhone;
    }

    public void setAuditorPhone(String auditorPhone) {
        this.auditorPhone = auditorPhone;
    }

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    public int getFeedbackOrg() {
        return feedbackOrg;
    }

    public void setFeedbackOrg(int feedbackOrg) {
        this.feedbackOrg = feedbackOrg;
    }

    public int getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(int feedbackContent) {
        this.feedbackContent = feedbackContent;
    }

    public int getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(int resultDesc) {
        this.resultDesc = resultDesc;
    }

    public int getMonitor() {
        return monitor;
    }

    public void setMonitor(int monitor) {
        this.monitor = monitor;
    }

    public int getMonitorPhone() {
        return monitorPhone;
    }

    public void setMonitorPhone(int monitorPhone) {
        this.monitorPhone = monitorPhone;
    }

    public int getReportAttrId() {
        return reportAttrId;
    }

    public void setReportAttrId(int reportAttrId) {
        this.reportAttrId = reportAttrId;
    }

    public String getMonitorContentDetail() {
        return monitorContentDetail;
    }

    public void setMonitorContentDetail(String monitorContentDetail) {
        this.monitorContentDetail = monitorContentDetail;
    }

    public String getEnvironmentalProtectionStationSelectPersonList() {
        return environmentalProtectionStationSelectPersonList;
    }

    public void setEnvironmentalProtectionStationSelectPersonList(String environmentalProtectionStationSelectPersonList) {
        this.environmentalProtectionStationSelectPersonList = environmentalProtectionStationSelectPersonList;
    }

    public String getApplyOrgId() {
        return applyOrgId;
    }

    public void setApplyOrgId(String applyOrgId) {
        this.applyOrgId = applyOrgId;
    }

    public String getBlockLevelId() {
        return blockLevelId;
    }

    public void setBlockLevelId(String blockLevelId) {
        this.blockLevelId = blockLevelId;
    }

    public String getBlockLevelName() {
        return blockLevelName;
    }

    public void setBlockLevelName(String blockLevelName) {
        this.blockLevelName = blockLevelName;
    }

    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }
}