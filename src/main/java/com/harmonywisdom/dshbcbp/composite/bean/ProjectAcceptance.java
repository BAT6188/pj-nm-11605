package com.harmonywisdom.dshbcbp.composite.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 验收
 */
@Entity
@Table(name = "HW_PROJECT_ACCEPTANCE")
public class ProjectAcceptance implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    private String id;
    /**
     *所属项目
     */
    @Column(name = "PROJECT_ID",length = 100)
    private String projectId;
    /**
     *批复时间
     */
    @Column(name = "REPLY_ACC_TIME")
    private Date replyAccTime;
    /**
     *验收批复文号
     */
    @Column(name = "REPLY_ACC_CODE",length = 100)
    private String replyAccCode;
    /**
     *审批部门
     */
    @Column(name = "REPLY_ACC_ORG",length = 100)
    private String replyAccOrg;
    /**
     *是否许可
     */
    @Column(name = "IS_ACC_LICENSE",length = 100)
    private String isAccLicense;
    /**
     *批复意见
     */
    @Column(name = "REPLY_ACC_OPINION",length = 100)
    private String replyAccOpinion;
    /**
     * 环评批复项目名称
     */
    @Column(name = "EIA_NAME_EIA",length = 100)
    private String eiaNameEIA;
    /**
     * 环评批复单位
     */
    @Column(name = "EU_NAME_EIA",length = 100)
    private String euNameEIA;

    /**
     * 环评批复文号
     */
    @Column(name = "REPLY_CODE_EIA",length = 100)
    private String replyCodeEIA;

    /**
     * 环评批复时间
     */
    @Column(name = "REPLY_TIME_EIA",length = 100)
    private String replyTimeEIA;


    @Transient
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

    public Date getReplyAccTime() {
        return replyAccTime;
    }

    public void setReplyAccTime(Date replyAccTime) {
        this.replyAccTime = replyAccTime;
    }

    public String getReplyAccCode() {
        return replyAccCode;
    }

    public void setReplyAccCode(String replyAccCode) {
        this.replyAccCode = replyAccCode;
    }

    public String getReplyAccOrg() {
        return replyAccOrg;
    }

    public void setReplyAccOrg(String replyAccOrg) {
        this.replyAccOrg = replyAccOrg;
    }

    public String getIsAccLicense() {
        return isAccLicense;
    }

    public void setIsAccLicense(String isAccLicense) {
        this.isAccLicense = isAccLicense;
    }

    public String getReplyAccOpinion() {
        return replyAccOpinion;
    }

    public void setReplyAccOpinion(String replyAccOpinion) {
        this.replyAccOpinion = replyAccOpinion;
    }

    public BuildProject getBuildProject() {
        return buildProject;
    }

    public void setBuildProject(BuildProject buildProject) {
        this.buildProject = buildProject;
    }

    public String getEiaNameEIA() {
        return eiaNameEIA;
    }

    public void setEiaNameEIA(String eiaNameEIA) {
        this.eiaNameEIA = eiaNameEIA;
    }

    public String getEuNameEIA() {
        return euNameEIA;
    }

    public void setEuNameEIA(String euNameEIA) {
        this.euNameEIA = euNameEIA;
    }

    public String getReplyCodeEIA() {
        return replyCodeEIA;
    }

    public void setReplyCodeEIA(String replyCodeEIA) {
        this.replyCodeEIA = replyCodeEIA;
    }

    public String getReplyTimeEIA() {
        return replyTimeEIA;
    }

    public void setReplyTimeEIA(String replyTimeEIA) {
        this.replyTimeEIA = replyTimeEIA;
    }
}