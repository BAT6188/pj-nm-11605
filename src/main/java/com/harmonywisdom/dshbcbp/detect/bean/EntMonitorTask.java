package com.harmonywisdom.dshbcbp.detect.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 企业委托任务
 */
@Entity
@Table(name = "HW_ENT_MONITOR_TASK")
public class EntMonitorTask implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    private String id;

    /**
     * 标题
     */
    @Column(name = "title")
    private String title;

    @Column(name = "enterprise_id", length = 32)
    private String enterpriseId;

    @Column(name = "enterprise_name", length = 32)
    private String enterpriseName;
    /**
     * 委托单位地址
     */
    @Column(name = "enterprise_address")
    private String enterpriseAddress;
    /**
     * 委托人
     */
    @Column(name = "Entruster", length = 32)
    private String Entruster;
    /**
     * 联系方式
     */
    @Column(name = "link_phone", length = 11)
    private String linkPhone;
    /**
     * 监测时间
     */
    @Column(name = "time")
    private Date time;
    /**
     * 监测地址
     */
    @Column(name = "address")
    private String address;
    /**
     * 监测内容详情
     */
    @Column(name = "content")
    private String content;
    /**
     * 监测结果
     */
    @Column(name = "result")
    private String result;

    @Column(name = "attachment_id", length = 32)
    private String attachmentId;

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

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getEnterpriseAddress() {
        return enterpriseAddress;
    }

    public void setEnterpriseAddress(String enterpriseAddress) {
        this.enterpriseAddress = enterpriseAddress;
    }

    public String getEntruster() {
        return Entruster;
    }

    public void setEntruster(String entruster) {
        Entruster = entruster;
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }


}