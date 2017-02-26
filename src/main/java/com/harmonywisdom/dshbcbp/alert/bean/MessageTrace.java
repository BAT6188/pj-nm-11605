package com.harmonywisdom.dshbcbp.alert.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 提醒消息状态跟踪
 **/
@Entity
@Table(name = "HW_MESSAGE_TRACE")
public class MessageTrace implements Serializable {
    private static final long serialVersionUID = 1L;

    public final static String RECEIVE_STATUS_UNRECEIVE = "1";
    public final static String RECEIVE_STATUS_RECEIVED = "2";

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
     * web端接收状态
     * 1:未接收（默认）
     * 2:已接收
     **/
    @Column(name = "RECEIVE_STATUS", length = 1)
    private String receiveStatus;

    /**
     * 手机端接收状态
     * 1:未接收（默认）
     * 2:已接收
     **/
    @Column(name = "MOBILE_RECEIVE_STATUS", length = 1)
    private String mobileReceiveStatus;

    /**
     * 接收时间
     */
    @Column(name = "RECEIVE_TIME")
    private Date receiveTime;

    /**
     * 所属消息
     */
    @Transient
    private Message message;

    /**
     * `mobile_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "mobile_timestamp",nullable = false,columnDefinition = "CURRENT_TIMESTAMP")
    private Date mobileTimestamp;


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

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Date getMobileTimestamp() {
        return mobileTimestamp;
    }

    public void setMobileTimestamp(Date mobileTimestamp) {
        this.mobileTimestamp = mobileTimestamp;
    }

    public String getMobileReceiveStatus() {
        return mobileReceiveStatus;
    }

    public void setMobileReceiveStatus(String mobileReceiveStatus) {
        this.mobileReceiveStatus = mobileReceiveStatus;
    }
}