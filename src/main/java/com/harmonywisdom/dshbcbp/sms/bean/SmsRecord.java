package com.harmonywisdom.dshbcbp.sms.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 短信记录
 */
@Entity
@Table(name = "HW_SMS_RECORD")
public class SmsRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    private String id;

    /**
     *发送人ID
     **/
    @Column(name = "SENDER_ID",length = 32)
    private String senderId;
    /**
     *发送人名称
     **/
    @Column(name = "SENDER_NAME", length = 20)
    private String senderName;
    /**
     *短信内容
     **/
    @Column(length = 2000)
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}