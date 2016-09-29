package com.harmonywisdom.dshbcbp.dispatch.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
     * 事件来源
     */
    @Column(name = "source")
    private String source;
    /**
     * 所属网格级别
     */
    @Column(name = "block_level_id", length = 32)
    private String blockLevelId;
    /**
     * 所属网格id
     */
    @Column(name = "block_id", length = 32)
    private String blockId;
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
    /**
     * 1:未发送
     * 2:已发送
     * 状态
     */
    @Column(name = "status", length = 2)
    private int status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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