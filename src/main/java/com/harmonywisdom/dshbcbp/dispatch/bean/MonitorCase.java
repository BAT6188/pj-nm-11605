package com.harmonywisdom.dshbcbp.dispatch.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 监控中心监控的事件
 */
@Entity
@Table(name = "HW_MONITOR_CASE")
public class MonitorCase implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    private String id;


    /**
     * 状态
     * 1:未发送
     * 2:已发送
     */
    @Column(name = "status", length = 2)
    private int status;

    @Column(name = "enterprise_id")
    private String enterpriseId;

    @Transient
    private String enterpriseName;

    @Column(name = "event_time")
    private String eventTime;

    @Transient
    private String supervisor;

    @Transient
    private String supervisorPhone;


    /**
     * 所属网格id
     */
    @Column(name = "block_id", length = 32)
    private String blockId;

    @Transient
    private String blockName;

    @Transient
    private String blockLevelName;

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
     * 发送人id
     */
    @Column(name = "sender_id", length = 32)
    private String senderId;
    /**
     * 发送人姓名
     */
    @Column(name = "sender_name", length = 20)
    private String senderName;

    @Column(name = "send_time")
    private Date sendTime;
    /**
     * 发送备注
     */
    @Column(name = "send_remark")
    private String sendRemark;


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

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
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