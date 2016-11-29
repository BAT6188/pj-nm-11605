package com.harmonywisdom.dshbcbp.sms.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 *短信接收状态
 */
@Entity
@Table(name = "HW_SMS_SEND_STATUS")
public class SmsSendStatus implements Serializable {
    private static final long serialVersionUID = 1L;

    public final static String SEND_STATUS_NO_SENT = "0";
    public final static String SEND_STATUS_SENT = "1";
    public final static String SEND_STATUS_RECEIVED = "2";
    public final static String SEND_STATUS_FAILED = "3";

    @Id
    @Column(length = 32)
    private String id;

    /**
     * 接收人ID
     **/
    @Column(name = "RECEIVER_ID", length = 32)
    private String receiverId;
    /**
     * 接收人姓名
     **/
    @Column(name = "RECEIVER_NAME", length = 20)
    private String receiverName;
    /**
     * 接收人电话
     **/
    @Column(name = "RECEIVER_PHONE", length = 20)
    private String receiverPhone;
    /**
     * 接收人来源
     * 1:网格；2:通讯录；3日程提醒
     **/
    @Column(name = "RECEIVER_SOURCE", length = 1)
    private String receiverSource;
    /**
     * 状态
     * 0:未发送；1:已发送，2:已接收；3:发送失败;
     **/
    @Column(name = "status", length = 1)
    private String status;

    /**
     * 回执编码
     */
    @Column(name = "rpt_code", length = 2)
    private String rptCode;
    /**
     *关联短信记录ID
     **/
    @Column(name = "SMS_RECORD_ID", length = 32)
    private String smsRecordId;

    /**
     * 短信发送接口api_mt_<xxx>表记录的SM_ID
     */
    @Column(name = "MT_SM_ID")
    private String mtSmId;

    /**
     * 短信发送接口 api_mt_<xxx>表主键
     */
    @Column(name = "MT_AUTO_SN")
    private Long mtAutoSn;

    public Long getMtAutoSn() {
        return mtAutoSn;
    }

    public void setMtAutoSn(Long mtAutoSn) {
        this.mtAutoSn = mtAutoSn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverSource() {
        return receiverSource;
    }

    public void setReceiverSource(String receiverSource) {
        this.receiverSource = receiverSource;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSmsRecordId() {
        return smsRecordId;
    }

    public void setSmsRecordId(String smsRecordId) {
        this.smsRecordId = smsRecordId;
    }

    public String getMtSmId() {
        return mtSmId;
    }

    public void setMtSmId(String mtSmId) {
        this.mtSmId = mtSmId;
    }

    public String getRptCode() {
        return rptCode;
    }

    public void setRptCode(String rptCode) {
        this.rptCode = rptCode;
    }
}