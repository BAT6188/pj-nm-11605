package com.harmonywisdom.dshbcbp.detect.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
     * 标题
     */
    @Column(name = "tilte", length = 64)
    private String tilte;
    /**
     * 提醒时间
     */
    @Column(name = "alert_time")
    private Date alertTime;
    /**
     * 人员姓名
     */
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

    @Column(name = "attachment_id", length = 32)
    private String attachmentId;
    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

    @Column(name = "create_time")
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTilte() {
        return tilte;
    }

    public void setTilte(String tilte) {
        this.tilte = tilte;
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

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}