package com.harmonywisdom.dshbcbp.dispatch.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 执法管理列表
 */
@Entity
@Table(name = "HW_DISPATCH_TASK")
public class DispatchTask implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 未调度
     */
    public static final String status_1="1";
    /**
     * 已发送
     */
    public static final String status_2="2";
    /**
     *已反馈
     */
    public static final String status_3="3";
    /**
     *已处罚
     */
    public static final String status_4="4";
    /**
     *已办结
     */
    public static final String status_5="5";

    @Id
    @Column(length = 32)
    private String id;

    /**
     * 1:未调度
     * 2:已发送
     * 3:已反馈
     * 4:已处罚
     * 5:已办结
     * 状态
     */
    @Column(name = "status", length = 2)
    private String status;


//--------------  现场监察监测报告  --------------------------//
    /**
     * 监测报告是否填写状态：0未填写，1已填写
     */
    @Column(name = "monitor_report_status", length = 2)
    private String monitorReportStatus;

    /**
     * 监测报告读取状态：0未读，1已读
     */
    @Column(name = "monitor_report_read_status", length = 2)
    private String monitorReportReadStatus;


    /**
     * 报送人
     */
    @Column(name = "submit_person")
    private String submitPerson;
    @Column(name = "submit_person_phone")
    private String submitPersonPhone;

    /**
     * 检测报告备注
     */
    @Column(name = "monitor_report_remark")
    private String monitorReportRemark;

//--------------  现场监察监测报告  --------------------------//

    //////////////////////////////////////////////////////////


    @Column(name = "monitor_case_id")
    private String monitorCaseId;

    /**
     * 从监控中心，监察大队办公室发送过来的人员  监察大队长人员列表
     */
    @Column(name = "monitor_mastor_person_list")
    private String monitorMastorPersonList;

    /**
     * 选择的发送给环保站的人员  环保站人员列表
     */
    @Column(name="env_pro_sta_person_list")
    private String envProStaPersonList;

    /**
     * 1：所有人都能看到这条数据
     */
    @Column(name = "all_person",length = 2)
    private String allPerson;


    /**
     * 事件来源，信息来源
     * 监察大队办公司： 1：12369   2：区长热线   3：市长热线   4：现场监察
     * 监控中心：0
     */
    @Column(name = "source")
    private String source;


    /**
     * 企业，投诉对象
     */
    @Column(name = "enterprise_id")
    private String enterpriseId;

    @Column(name = "enterprise_name")
    private String enterpriseName;

    /**
     * 事件时间,接电时间
     */
    @Column(name = "event_time")
    private Date eventTime;

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
     * 接电人
     */
    @Column(name = "answer")
    private String answer;

    /**
     * 监管人员
     */
    @Column(name = "supervisor")
    private String supervisor;

    @Column(name = "supervisor_phone")
    private String supervisorPhone;


    @Column(name = "reason", length = 2)
    private String reason;

    /**
     * 超标值
     */
    @Column(name = "over_value")
    private Double overValue;
    /**
     * 超标阈值
     */
    @Column(name = "thr_value")
    private Double thrValue;
    /**
     * 事件内容
     */
    @Column(name = "content")
    private String content;

    /**
     * 处理人、发送人
     */
    @Column(name = "sender_id", length = 32)
    private String senderId;

    @Column(name = "sender_name", length = 20)
    private String senderName;

    @Column(name = "send_time")
    private Date sendTime;

    @Column(name = "send_phone")
    private String sendPhone;

    /**
     * 备注
     */
    @Column(name = "send_remark")
    private String sendRemark;


    ///////////////////////////////////////////////////

    /**
     * 投诉对象负责人
     */
    @Column(name = "target_linkman", length = 20)
    private String targetLinkman;

    @Column(name = "target_linkphone", length = 11)
    private String targetLinkPhone;



    /**
     * 处理人id
     */
    @Column(name = "handler_id", length = 32)
    private String handlerId;
    /**
     * 处理人姓名
     */
    @Column(name = "handler_name", length = 32)
    private String handlerName;
    /**
     * 处理人电话
     */
    @Column(name = "handler_phone", length = 11)
    private String handlerPhone;

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

    public String getMonitorMastorPersonList() {
        return monitorMastorPersonList;
    }

    public void setMonitorMastorPersonList(String selectPeopleIds) {
        this.monitorMastorPersonList = selectPeopleIds;
    }

    public String getMonitorCaseId() {
        return monitorCaseId;
    }

    public void setMonitorCaseId(String monitorCaseId) {
        this.monitorCaseId = monitorCaseId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
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


    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getSupervisorPhone() {
        return supervisorPhone;
    }

    public void setSupervisorPhone(String supervisorPhone) {
        this.supervisorPhone = supervisorPhone;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Double getOverValue() {
        return overValue;
    }

    public void setOverValue(Double overValue) {
        this.overValue = overValue;
    }

    public Double getThrValue() {
        return thrValue;
    }

    public void setThrValue(Double thrValue) {
        this.thrValue = thrValue;
    }

    public String getSendPhone() {
        return sendPhone;
    }

    public void setSendPhone(String sendPhone) {
        this.sendPhone = sendPhone;
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


    public String getTargetLinkman() {
        return targetLinkman;
    }

    public void setTargetLinkman(String targetLinkman) {
        this.targetLinkman = targetLinkman;
    }

    public String getTargetLinkPhone() {
        return targetLinkPhone;
    }

    public void setTargetLinkPhone(String targetLinkPhone) {
        this.targetLinkPhone = targetLinkPhone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getSendRemark() {
        return sendRemark;
    }

    public void setSendRemark(String sendRemark) {
        this.sendRemark = sendRemark;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(String handlerId) {
        this.handlerId = handlerId;
    }

    public String getHandlerName() {
        return handlerName;
    }

    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName;
    }

    public String getHandlerPhone() {
        return handlerPhone;
    }

    public void setHandlerPhone(String handlerPhone) {
        this.handlerPhone = handlerPhone;
    }

    public String getEnvProStaPersonList() {
        return envProStaPersonList;
    }

    public void setEnvProStaPersonList(String envProStaPersonList) {
        this.envProStaPersonList = envProStaPersonList;
    }

    public String getAllPerson() {
        return allPerson;
    }

    public void setAllPerson(String allPerson) {
        this.allPerson = allPerson;
    }

    public String getMonitorReportStatus() {
        return monitorReportStatus;
    }

    public void setMonitorReportStatus(String monitorReportStatus) {
        this.monitorReportStatus = monitorReportStatus;
    }

    public String getMonitorReportReadStatus() {
        return monitorReportReadStatus;
    }

    public void setMonitorReportReadStatus(String monitorReportReadStatus) {
        this.monitorReportReadStatus = monitorReportReadStatus;
    }

    public String getSubmitPerson() {
        return submitPerson;
    }

    public void setSubmitPerson(String submitPerson) {
        this.submitPerson = submitPerson;
    }

    public String getSubmitPersonPhone() {
        return submitPersonPhone;
    }

    public void setSubmitPersonPhone(String submitPersonPhone) {
        this.submitPersonPhone = submitPersonPhone;
    }

    public String getMonitorReportRemark() {
        return monitorReportRemark;
    }

    public void setMonitorReportRemark(String monitorReportRemark) {
        this.monitorReportRemark = monitorReportRemark;
    }
}