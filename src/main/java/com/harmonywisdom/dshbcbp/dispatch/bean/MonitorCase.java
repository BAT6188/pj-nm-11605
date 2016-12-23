package com.harmonywisdom.dshbcbp.dispatch.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 监控中心、监察大队办公室
 */
@Entity
@Table(name = "HW_MONITOR_CASE")
public class MonitorCase implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 未调度
     */
    public static final String status_0="0";
    /**
     * 已调度
     */
    public static final String status_1="1";
    /**
     * 已反馈
     */
    public static final String status_2="2";

    @Id
    @Column(length = 32)
    private String id;

    /**
     * hw_dispatch_task 表id
     */
    @Column(name = "dispatch_id", length = 32)
    private String dispatchId;

    /**
     * 办结状态
     */
    @Column(name = "over_status", length = 2)
    private String overStatus;

    /**
     * 办结时间
     */
    @Column(name = "over_time")
    private Date overTime;

    /**
     * 办结意见
     */
    @Column(name = "over_suggestion")
    private String overSuggestion;

    /**
     * 选择的 监察大队人员
     */
    @Column(name = "monitor_office_person_id")
    private String monitorOfficePersonId;
    @Column(name = "monitor_office_person_name")
    private String monitorOfficePersonName;

    /**
     * 短信人员
     */
    @Column(name = "sms_person_id")
    private String smsPersonId;
    @Column(name = "sms_person_name")
    private String smsPersonName;

    /**
     * 读取状态（自己读取状态）：0未读，1已读
     */
    @Column(name = "self_read_status")
    private String selfReadStatus;

    /**
     * 下级接收状态：0未接收，1已接收
     */
    @Column(name = "receive_status")
    private String receiveStatus;

    /**
     * 状态
     * 0：未调度
     * 1：已调度
     * 2：已反馈
     */
    @Column(name = "status", length = 2)
    private String status;

    /**
     * 事件来源，信息来源
     * 监察大队办公司： 1：12369   2：区长热线   3：市长热线   4：现场监察
     * 监控中心：0
     */
    @Column(name = "source")
    private String source;

    /**
     * 企业，投诉对象  数据库只保存id，name一般情况下不保存
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

    /**
     * 事件原因 caseReason
     * 1.异常
     * 2.超标
     */
    @Column(name = "reason", length = 2)
    private String reason;

    /**
     * 排口名称
     */
    @Column(name = "port_name")
    private Double portName;
    /**
     * 污染源类型
     */
    @Column(name = "pollutant_type")
    private Double pollutantType;

    /**
     * 超标项
     */
    @Column(name = "over_obj")
    private Double overObj;

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
     * 事件内容,来电详情
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
     * 发送备注
     */
    @Column(name = "send_remark")
    private String sendRemark;

    @Column(name = "informer")
    private String informer;
    @Column(name = "informer_phone")
    private String informerPhone;

    @Transient
    private String attachmentIds;

    @Transient
    private String startConnTime;

    @Transient
    private String  endConnTime;

    @Transient
    private String startSendTime;

    @Transient
    private String  endSendTime;

    public String getStartSendTime() {
        return startSendTime;
    }

    public void setStartSendTime(String startSendTime) {
        this.startSendTime = startSendTime;
    }

    public String getEndSendTime() {
        return endSendTime;
    }

    public void setEndSendTime(String endSendTime) {
        this.endSendTime = endSendTime;
    }

    public String getStartConnTime() {
        return startConnTime;
    }

    public void setStartConnTime(String startConnTime) {
        this.startConnTime = startConnTime;
    }

    public String getEndConnTime() {
        return endConnTime;
    }

    public void setEndConnTime(String endConnTime) {
        this.endConnTime = endConnTime;
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


    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSupervisorPhone() {
        return supervisorPhone;
    }

    public void setSupervisorPhone(String supervisorPhone) {
        this.supervisorPhone = supervisorPhone;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getBlockLevelName() {
        return blockLevelName;
    }

    public void setBlockLevelName(String blockLevelName) {
        this.blockLevelName = blockLevelName;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    public String getSendPhone() {
        return sendPhone;
    }

    public void setSendPhone(String sendPhone) {
        this.sendPhone = sendPhone;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
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

    public String getDispatchId() {
        return dispatchId;
    }

    public void setDispatchId(String dispatchId) {
        this.dispatchId = dispatchId;
    }

    public String getMonitorOfficePersonId() {
        return monitorOfficePersonId;
    }

    public void setMonitorOfficePersonId(String monitorOfficePersonId) {
        this.monitorOfficePersonId = monitorOfficePersonId;
    }

    public String getMonitorOfficePersonName() {
        return monitorOfficePersonName;
    }

    public void setMonitorOfficePersonName(String monitorOfficePersonName) {
        this.monitorOfficePersonName = monitorOfficePersonName;
    }

    public String getSelfReadStatus() {
        return selfReadStatus;
    }

    public void setSelfReadStatus(String selfReadStatus) {
        this.selfReadStatus = selfReadStatus;
    }

    public String getReceiveStatus() {
        return receiveStatus;
    }

    public void setReceiveStatus(String receiveStatus) {
        this.receiveStatus = receiveStatus;
    }

    public String getSmsPersonId() {
        return smsPersonId;
    }

    public void setSmsPersonId(String smsPersonId) {
        this.smsPersonId = smsPersonId;
    }

    public String getSmsPersonName() {
        return smsPersonName;
    }

    public void setSmsPersonName(String smsPersonName) {
        this.smsPersonName = smsPersonName;
    }

    public String getInformer() {
        return informer;
    }

    public void setInformer(String informer) {
        this.informer = informer;
    }

    public String getInformerPhone() {
        return informerPhone;
    }

    public void setInformerPhone(String informerPhone) {
        this.informerPhone = informerPhone;
    }

    public String getOverStatus() {
        return overStatus;
    }

    public void setOverStatus(String overStatus) {
        this.overStatus = overStatus;
    }

    public Double getPortName() {
        return portName;
    }

    public void setPortName(Double portName) {
        this.portName = portName;
    }

    public Double getPollutantType() {
        return pollutantType;
    }

    public void setPollutantType(Double pollutantType) {
        this.pollutantType = pollutantType;
    }

    public Double getOverObj() {
        return overObj;
    }

    public void setOverObj(Double overObj) {
        this.overObj = overObj;
    }

    public Date getOverTime() {
        return overTime;
    }

    public void setOverTime(Date overTime) {
        this.overTime = overTime;
    }

    public String getOverSuggestion() {
        return overSuggestion;
    }

    public void setOverSuggestion(String overSuggestion) {
        this.overSuggestion = overSuggestion;
    }
}