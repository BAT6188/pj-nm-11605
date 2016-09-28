package com.harmonywisdom.dshbcbp.dispathtask.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 监察大队执行的任务
 */
@Entity
@Table(name = "HW_DISPATH_TASK")
public class DispathTask implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    private String id;
    /**
     * 事件来源
     */
    private String source;
    /**
     * 接电时间
     */
    private Date connTime;
    /**
     * 接电人
     */
    private String answer;
    /**
     * 所属网格级别
     */
    private String blockLevelId;
    /**
     * 所属网格id
     */
    private String blockId;
    /**
     * 投诉对象
     */
    private String targetName;
    /**
     * 投诉对象负责人
     */
    private String targetLinkman;
    private String targetLinkPhone;
    /**
     * 来电详情
     */
    private String content;
    /**
     * 发送人id
     */
    private String senderId;
    private String senderName;
    private Date sendTime;
    /**
     * 发送备注
     */
    private String sendRemark;
    /**
     * 执法人员
     */
    private String lawerId;
    /**
     * 执法人员姓名
     */
    private String lawerName;
    private Date exeTime;
    /**
     * 执法情况
     */
    private String exeDesc;
    /**
     * 执法情况附件
     */
    private int exeAttrId;
    /**
     * 1:未调度
     * 2:已发送
     * 3:已调度
     * 3:已反馈
     * 4:已处罚
     * 5:已办结
     * 状态
     */
    private String status;
    /**
     * 处理人id
     */
    private String handlerId;
    /**
     * 处理人姓名
     */
    private String handlerName;
    /**
     * 处理人电话
     */
    private String handlerPhone;

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

    public Date getConnTime() {
        return connTime;
    }

    public void setConnTime(Date connTime) {
        this.connTime = connTime;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
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

    public String getLawerId() {
        return lawerId;
    }

    public void setLawerId(String lawerId) {
        this.lawerId = lawerId;
    }

    public String getLawerName() {
        return lawerName;
    }

    public void setLawerName(String lawerName) {
        this.lawerName = lawerName;
    }

    public Date getExeTime() {
        return exeTime;
    }

    public void setExeTime(Date exeTime) {
        this.exeTime = exeTime;
    }

    public String getExeDesc() {
        return exeDesc;
    }

    public void setExeDesc(String exeDesc) {
        this.exeDesc = exeDesc;
    }

    public int getExeAttrId() {
        return exeAttrId;
    }

    public void setExeAttrId(int exeAttrId) {
        this.exeAttrId = exeAttrId;
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
}