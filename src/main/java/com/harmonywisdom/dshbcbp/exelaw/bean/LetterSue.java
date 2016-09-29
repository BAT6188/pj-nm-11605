package com.harmonywisdom.dshbcbp.exelaw.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 信访投诉
 */
@Entity
@Table(name = "HW_LETTER_SUE")
public class LetterSue implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    private String id;

    /**
     * 信访时间
     */
    @Column(name = "time")
    private Date time;
    /**
     * 来访人姓名
     */
    @Column(name = "person", length = 32)
    private String person;
    /**
     * 来访人联系电话
     */
    @Column(name = "phone", length = 11)
    private String phone;
    /**
     * 登记人
     */
    @Column(name = "accepter", length = 32)
    private String accepter;
    /**
     * 信访概要
     */
    @Column(name = "content")
    private String content;
    /**
     * 调查处理情况
     */
    @Column(name = "solution")
    private String solution;

    @Column(name = "attachment_id", length = 32)
    private String attachmentId;

    @Column(name = "enterprise_id", length = 32)
    private String enterpriseId;

    @Column(name = "enterprise_name", length = 64)
    private String enterpriseName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAccepter() {
        return accepter;
    }

    public void setAccepter(String accepter) {
        this.accepter = accepter;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
}