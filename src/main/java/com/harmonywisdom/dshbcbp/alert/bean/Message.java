package com.harmonywisdom.dshbcbp.alert.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

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
}