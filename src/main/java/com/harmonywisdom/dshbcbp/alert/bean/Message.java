package com.harmonywisdom.dshbcbp.alert.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 系统内消息提醒；
 * 日程提醒
 * 会议通知
 * 信息公告等消息通知
 **/
@Entity
@Table(name = "HW_MESSAGE")
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String MSG_TYPE_SCHEDULE = "1";
    public static final String MSG_TYPE_MEETINGNOTICE = "2";
    public static final String MSG_TYPE_PUBINFO = "3";
    public static final String MSG_TYPE_POLLUTANTPAYMENT = "4";
    public static final String MSG_TYPE_CREATEMODEDETAIL = "5";

    @Id
    @Column(length = 32)
    private String id;

    /**
     * 标题
     **/
    @Column(length = 120)
    private String title;
    /**
     * 消息内容
     **/
    @Column(length = 2000)
    private String content;
    /** 消息类型；
     * 1:日程提醒；
     * 2.会议通知
     * 3.信息公告
     * 4.排污申报提醒
     * 5.创模建设详情
     **/
    @Column(name = "msg_Type", length = 20)
    private String msgType;
    /**
     * 查看情况url
     **/
    @Column(name = "DETAILS_URL", length = 200)
    private String detailsUrl;

    /**
     * 业务数据id
     */
    @Column(name = "BUSINESS_ID", length = 32)
    private String businessId;

    /**
     * 发送人id
     **/
    @Column(name = "SENDER_ID", length = 32)
    private String senderId;
    /**
     * 发送人姓名
     **/
    @Column(name = "SENDER_NAME", length = 20)
    private String senderName;

    /**
     * 提醒时间
     */
    @Column(name = "ALERT_TIME")
    private Date alertTime;

    /**
     * 系统创建时间
     */
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * `mobile_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "mobile_timestamp",columnDefinition = "CURRENT_TIMESTAMP")
    private Date mobileTimestamp;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getDetailsUrl() {
        return detailsUrl;
    }

    public void setDetailsUrl(String detailsUrl) {
        this.detailsUrl = detailsUrl;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public Date getAlertTime() {
        return alertTime;
    }

    public void setAlertTime(Date alertTime) {
        this.alertTime = alertTime;
    }

    public Date getMobileTimestamp() {
        return mobileTimestamp;
    }

    public void setMobileTimestamp(Date mobileTimestamp) {
        this.mobileTimestamp = mobileTimestamp;
    }
}