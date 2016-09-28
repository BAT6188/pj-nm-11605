package com.harmonywisdom.dshbcbp.composite.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 环评
 */
@Entity
@Table(name = "HW_PROJECT_EIA")
public class ProjectEIA implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 64)
    private String id;
    /**
     *所属项目ID
     */
    @Column(name = "PROJECT_ID",length = 100)
    private String projectId;
    /**
     *评价单位名称
     */
    @Column(name = "EU_NAME",length = 100)
    private String euName;
    /**
     *评价单位联系电话
     */
    @Column(name = "EU_TEL",length = 100)
    private String euTel;
    /**
     *评价地址
     */
    @Column(name = "EU_ADDRESS",length = 100)
    private String euAddress;
    /**
     *邮政编码
     */
    @Column(name = "EU_ZIP_CODE",length = 100)
    private String euZipCode;
    /**
     *证书编号
     */
    @Column(name = "CERTIFICATE_CODE",length = 100)
    private String certificateCode;
    /**
     *评价经费（万元）
     */
    @Column(name = "CERTIFICATE_MONEY",length = 100)
    private Double certificateMoney;
    /**
     *批复时间
     */
    @Column(name = "REPLY_TIME",length = 100)
    private Date replyTime;
    /**
     *批复文号
     */
    @Column(name = "REPLY_CODE",length = 100)
    private String replyCode;
    /**
     *审批部门
     */
    @Column(name = "REPLY_ORG",length = 100)
    private String replyOrg;
    /**
     *是否许可
     * 0:否
     * 1:是
     *
     */
    @Column(name = "is_License",length = 100)
    private String isLicense;
    /**
     *批复意见
     */
    @Column(name = "REPLY_OPINION",length = 100)
    private String replyOpinion;

    public BuildProject buildProject;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getEuName() {
        return euName;
    }

    public void setEuName(String euName) {
        this.euName = euName;
    }

    public String getEuTel() {
        return euTel;
    }

    public void setEuTel(String euTel) {
        this.euTel = euTel;
    }

    public String getEuAddress() {
        return euAddress;
    }

    public void setEuAddress(String euAddress) {
        this.euAddress = euAddress;
    }

    public String getEuZipCode() {
        return euZipCode;
    }

    public void setEuZipCode(String euZipCode) {
        this.euZipCode = euZipCode;
    }

    public String getCertificateCode() {
        return certificateCode;
    }

    public void setCertificateCode(String certificateCode) {
        this.certificateCode = certificateCode;
    }

    public Double getCertificateMoney() {
        return certificateMoney;
    }

    public void setCertificateMoney(Double certificateMoney) {
        this.certificateMoney = certificateMoney;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public String getReplyCode() {
        return replyCode;
    }

    public void setReplyCode(String replyCode) {
        this.replyCode = replyCode;
    }

    public String getReplyOrg() {
        return replyOrg;
    }

    public void setReplyOrg(String replyOrg) {
        this.replyOrg = replyOrg;
    }

    public String getIsLicense() {
        return isLicense;
    }

    public void setIsLicense(String isLicense) {
        this.isLicense = isLicense;
    }

    public String getReplyOpinion() {
        return replyOpinion;
    }

    public void setReplyOpinion(String replyOpinion) {
        this.replyOpinion = replyOpinion;
    }
}