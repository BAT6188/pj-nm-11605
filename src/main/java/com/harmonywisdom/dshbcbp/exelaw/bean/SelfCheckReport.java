package com.harmonywisdom.dshbcbp.exelaw.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 自查自报
 */
@Entity
@Table(name = "HW_SELF_CHECK_REPORT")
public class SelfCheckReport implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    private String id;

    @Column(name = "enterprise_id", length = 32)
    private String enterpriseId;

    @Column(name = "enterprise_name", length = 32)
    private String enterpriseName;
    /** 隐患名称*/
    @Column(name = "name")
    private String name;
    /** 隐患部位*/
    @Column(name = "place")
    private String place;
    /** 隐患信息描述*/
    @Column(name = "description")
    private String description;
    /** 整改负责人*/
    @Column(name = "principal", length = 32)
    private String principal;
    /** 联系方式*/
    @Column(name = "link_phone", length = 11)
    private String linkPhone;
    /** 发现日期*/
    @Column(name = "find_date")
    private Date findDate;
    /** 整改完成日期*/
    @Column(name = "finish_date")
    private Date finishDate;
    /** 整改措施方案*/
    @Column(name = "solution")
    private String solution;

    @Column(name = "attachment_id", length = 32)
    private String attachmentId;
    /** 反馈单位*/
    @Column(name = "feedback_org")
    private String feedbackOrg;
    /** 反馈时间*/
    @Column(name = "feedback_time")
    private Date feedbackTime;
    /** 反馈内容*/
    @Column(name = "feedback_content")
    private String feedbackContent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }

    public Date getFindDate() {
        return findDate;
    }

    public void setFindDate(Date findDate) {
        this.findDate = findDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getFeedbackOrg() {
        return feedbackOrg;
    }

    public void setFeedbackOrg(String feedbackOrg) {
        this.feedbackOrg = feedbackOrg;
    }

    public Date getFeedbackTime() {
        return feedbackTime;
    }

    public void setFeedbackTime(Date feedbackTime) {
        this.feedbackTime = feedbackTime;
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }
}