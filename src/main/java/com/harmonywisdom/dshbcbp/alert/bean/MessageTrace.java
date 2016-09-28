package com.harmonywisdom.dshbcbp.alert.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 提醒消息状态跟踪
 **/
@Entity
@Table(name = "HW_MESSAGE_TRACE")
public class MessageTrace implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    private String id;

    /**
     * 主表消息id
     **/
    @Column(name = "MSG_ID", length = 32)
    private String msgId;
    /**
     * 接收人id
     **/
    @Column(name = "RECEIVER_ID", length = 32)
    private String receiverId;
    /**
     * 接收人姓名
     **/
    @Column(name = "RECEIVER_NAME", length = 20)
    private String receiverName;
    /**
     * 接收状态
     * 1:未接收（默认）
     * 2:已接收
     **/
    @Column(name = "RECEIVE_STATUS", length = 1)
    private String receiveStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiveStatus() {
        return receiveStatus;
    }

    public void setReceiveStatus(String receiveStatus) {
        this.receiveStatus = receiveStatus;
    }
}