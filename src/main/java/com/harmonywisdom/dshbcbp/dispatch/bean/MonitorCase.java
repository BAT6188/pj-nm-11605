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

    @Id
    @Column(length = 32)
    private String id;

    /**
     * 事件来源，信息来源
     * 监察大队办公司： 12369：1, 区长热线：2， 市长热线：3
     * 监测中心：0
     */
    @Column(name = "source")
    private String source;

    /**
     * 状态
     * 0：未调度
     * 1：已调度
     * 2：已反馈
     */
    @Column(name = "status", length = 2)
    private int status;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}