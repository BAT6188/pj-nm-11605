package com.harmonywisdom.dshbcbp.exelaw.bean;

import javax.persistence.*;
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

    @Column(name = "enterprise_id")
    private String enterpriseId;


    /**
     * 信访时间
     */
    @Column(name = "visit_time")
    private Date visitTime;
    /**
     * 来访人姓名
     */
    @Column(name = "visit_person", length = 32)
    private String visitPerson;
    /**
     * 来访人联系电话
     */
    @Column(name = "visit_phone", length = 11)
    private String visitPhone;
    /**
     * 登记人
     */
    @Column(name = "register_person", length = 32)
    private String registerPerson;
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

    @Transient
    private String attachmentIds;

    public String getAttachmentIds() {
        return attachmentIds;
    }

    public void setAttachmentIds(String attachmentIds) {
        this.attachmentIds = attachmentIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    public String getVisitPerson() {
        return visitPerson;
    }

    public void setVisitPerson(String visitPerson) {
        this.visitPerson = visitPerson;
    }

    public String getVisitPhone() {
        return visitPhone;
    }

    public void setVisitPhone(String visitPhone) {
        this.visitPhone = visitPhone;
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

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getRegisterPerson() {
        return registerPerson;
    }

    public void setRegisterPerson(String registerPerson) {
        this.registerPerson = registerPerson;
    }
}