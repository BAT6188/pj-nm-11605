package com.harmonywisdom.dshbcbp.detect.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 日程安排
 */
@Entity
@Table(name = "HW_SCHEDULE")
public class Schedule implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    private String id;

    /**
     * 提醒状态。 暂时不用
     * 0未提醒  1已提醒
     */
    @Column(name = "status", length = 2)
    private String status;

    /**
     * 日程提醒名称
     */
    @Column(name = "title", length = 64)
    private String title;
    /**
     * 提醒时间
     */
    @Column(name = "alert_time")
    private Date alertTime;
    /**
     * 设置提醒人员
     */
    @Column(name = "seterId", length = 32)
    private String seterId;
    @Column(name = "seter", length = 32)
    private String seter;
    /**
     * 职务
     */
    @Column(name = "position")
    private String position;
    /**
     * 联系方式
     */
    @Column(name = "link_phone", length = 11)
    private String linkPhone;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

    @Transient
    private String attachmentIds;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getAlertTime() {
        return alertTime;
    }

    public void setAlertTime(Date alertTime) {
        this.alertTime = alertTime;
    }

    public String getSeter() {
        return seter;
    }

    public void setSeter(String seter) {
        this.seter = seter;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAttachmentIds() {
        return attachmentIds;
    }

    public void setAttachmentIds(String attachmentIds) {
        this.attachmentIds = attachmentIds;
    }

    public String getSeterId() {
        return seterId;
    }

    public void setSeterId(String seterId) {
        this.seterId = seterId;
    }
}