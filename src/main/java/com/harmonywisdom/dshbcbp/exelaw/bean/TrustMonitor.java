package com.harmonywisdom.dshbcbp.exelaw.bean;

import javax.persistence.*;
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

    /**
     * 流程状态，审批意见
     *
     * 0. 申请委托检测未发送
     * 1. 申请委托检测已发送
     * 2. 监察大队审批同意
     * 3. 监察大队审批不同意
     * 4. 监察大队发送给监测站办公室
     * 5. 监测站办公室发送给监测站站长
     * 6. 监测站站长发送给检测人员
     * 7. 检测人员反馈
     */
    @Column(length = 4)
    private String status;

    /**
     * 审批意见状态：
     * 1同意
     * 2不同意
     */
    @Column(name = "audit_suggestion_status",length = 2)
    private String auditSuggestionStatus;


    /**
     * 是否是企业委托监测数据：1是   null或0不是
     */
    @Column(name = "enterprise_self",length = 2)
    private String enterpriseSelf;

    /**
     * 企业
     */
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

    //******************** 选择的人员列表 *****************************//

    /**
     * 环保站选择的 监察大队人员列表
     */
    @Column(name = "environmental_protection_station_select_person_list")
    private String environmentalProtectionStationSelectPersonList;
    @Column(name = "environmental_protection_station_select_person_name_list")
    private String environmentalProtectionStationSelectPersonNameList;

    /**
     * 监测站办公室人员列表
     */
    @Column(name = "monitoring_station_office_person_list")
    private String monitoringStationOfficePersonList;
    @Column(name = "monitoring_station_office_person_name_list")
    private String monitoringStationOfficePersonNameList;


    /**
     * 监测站站长人员列表
     */
    @Column(name = "monitoring_station_master_person_list")
    private String monitoringStationMasterPersonList;
    @Column(name = "monitoring_station_master_person_name_list")
    private String monitoringStationMasterPersonNameList;


    /**
     * 检测人员列表
     */
    @Column(name = "monitoring_station_person_list")
    private String monitoringStationPersonList;
    @Column(name = "monitoring_station_person_name_list")
    private String monitoringStationPersonNameList;

    //********************* 人员读取状态 ********************//

    /**
     * 监察大队 人员读取状态
     */
    @Column(name = "self_read_status_for_JianChaDaDui")
    private String selfReadStatusForJianchadadui;

    /**
     * 监测站办公室 人员读取状态
     */
    @Column(name = "self_read_status_for_monitor_office")
    private String selfReadStatusForMonitorOffice;

    /**
     * 监测站站长 人员读取状态
     */
    @Column(name = "self_read_status_for_monitor_master")
    private String selfReadStatusForMonitorMaster;

    /**
     * 监测人员读取状态
     */
    @Column(name = "self_read_status_for_monitor_person")
    private String selfReadStatusForMonitorPerson;

//********************************************************//

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
     * 审批人员
     */
    @Column(name = "auditor")
    private String auditor;
    /**
     * 审批时间
     */
    @Column(name = "audit_time")
    private Date auditTime;
    /**
     * 审批人员职务
     */
    @Column(name = "audit_position")
    private String auditPosition;
    /**
     * 审批人员联系方式
     */
    @Column(name = "auditor_phone", length = 11)
    private String auditorPhone;

    /**
     * 审批意见
     */
    @Column(name = "audit_suggestion")
    private String auditSuggestion;

    /**
     * 反馈单位
     */
    @Column(name = "feedback_org")
    private String feedbackOrg;
    /**
     * 反馈内容
     */
    @Column(name = "feedback_content")
    private String feedbackContent;
    /**
     * 监测详情
     */
    @Column(name = "result_desc")
    private String resultDesc;
    /**
     * 监测人
     */
    @Column(name = "monitor", length = 32)
    private String monitor;
    /**
     * 监测人联系方式
     */
    @Column(name = "monitor_phone", length = 11)
    private String monitorPhone;

    @Transient
    private String attachmentIds;

    public String getAttachmentIds() {
        return attachmentIds;
    }

    public void setAttachmentIds(String attachmentIds) {
        this.attachmentIds = attachmentIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMonitoringStationOfficePersonList() {
        return monitoringStationOfficePersonList;
    }

    public void setMonitoringStationOfficePersonList(String monitorOfficeAndMasterPersonList) {
        this.monitoringStationOfficePersonList = monitorOfficeAndMasterPersonList;
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


    public String getFeedbackOrg() {
        return feedbackOrg;
    }

    public void setFeedbackOrg(String feedbackOrg) {
        this.feedbackOrg = feedbackOrg;
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public String getMonitor() {
        return monitor;
    }

    public void setMonitor(String monitor) {
        this.monitor = monitor;
    }

    public String getMonitorPhone() {
        return monitorPhone;
    }

    public void setMonitorPhone(String monitorPhone) {
        this.monitorPhone = monitorPhone;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuditSuggestion() {
        return auditSuggestion;
    }

    public void setAuditSuggestion(String auditSuggestion) {
        this.auditSuggestion = auditSuggestion;
    }

    public String getMonitoringStationMasterPersonList() {
        return monitoringStationMasterPersonList;
    }

    public void setMonitoringStationMasterPersonList(String monitoringStationMasterPersonList) {
        this.monitoringStationMasterPersonList = monitoringStationMasterPersonList;
    }

    public String getMonitoringStationPersonList() {
        return monitoringStationPersonList;
    }

    public void setMonitoringStationPersonList(String monitoringStationPersonList) {
        this.monitoringStationPersonList = monitoringStationPersonList;
    }

    public String getEnterpriseSelf() {
        return enterpriseSelf;
    }

    public void setEnterpriseSelf(String enterpriseSelf) {
        this.enterpriseSelf = enterpriseSelf;
    }

    public String getEnvironmentalProtectionStationSelectPersonNameList() {
        return environmentalProtectionStationSelectPersonNameList;
    }

    public void setEnvironmentalProtectionStationSelectPersonNameList(String environmentalProtectionStationSelectPersonNameList) {
        this.environmentalProtectionStationSelectPersonNameList = environmentalProtectionStationSelectPersonNameList;
    }

    public String getMonitoringStationOfficePersonNameList() {
        return monitoringStationOfficePersonNameList;
    }

    public void setMonitoringStationOfficePersonNameList(String monitoringStationOfficePersonNameList) {
        this.monitoringStationOfficePersonNameList = monitoringStationOfficePersonNameList;
    }

    public String getMonitoringStationMasterPersonNameList() {
        return monitoringStationMasterPersonNameList;
    }

    public void setMonitoringStationMasterPersonNameList(String monitoringStationMasterPersonNameList) {
        this.monitoringStationMasterPersonNameList = monitoringStationMasterPersonNameList;
    }

    public String getMonitoringStationPersonNameList() {
        return monitoringStationPersonNameList;
    }

    public void setMonitoringStationPersonNameList(String monitoringStationPersonNameList) {
        this.monitoringStationPersonNameList = monitoringStationPersonNameList;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSelfReadStatusForJianchadadui() {
        return selfReadStatusForJianchadadui;
    }

    public void setSelfReadStatusForJianchadadui(String selfReadStatusForJianchadadui) {
        this.selfReadStatusForJianchadadui = selfReadStatusForJianchadadui;
    }

    public String getSelfReadStatusForMonitorOffice() {
        return selfReadStatusForMonitorOffice;
    }

    public void setSelfReadStatusForMonitorOffice(String selfReadStatusForMonitorOffice) {
        this.selfReadStatusForMonitorOffice = selfReadStatusForMonitorOffice;
    }

    public String getSelfReadStatusForMonitorMaster() {
        return selfReadStatusForMonitorMaster;
    }

    public void setSelfReadStatusForMonitorMaster(String selfReadStatusForMonitorMaster) {
        this.selfReadStatusForMonitorMaster = selfReadStatusForMonitorMaster;
    }

    public String getSelfReadStatusForMonitorPerson() {
        return selfReadStatusForMonitorPerson;
    }

    public void setSelfReadStatusForMonitorPerson(String selfReadStatusForMonitorPerson) {
        this.selfReadStatusForMonitorPerson = selfReadStatusForMonitorPerson;
    }

    public String getAuditSuggestionStatus() {
        return auditSuggestionStatus;
    }

    public void setAuditSuggestionStatus(String auditSuggestionStatus) {
        this.auditSuggestionStatus = auditSuggestionStatus;
    }
}