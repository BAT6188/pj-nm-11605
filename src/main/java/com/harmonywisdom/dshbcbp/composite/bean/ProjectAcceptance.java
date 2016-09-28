package com.harmonywisdom.dshbcbp.composite.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
    @Column(length = 64)
    private String id;
    /**
     *所属项目
     */
    @Column(name = "PROJECT_ID",length = 100)
    private String projectId;
    /**
     *批复时间
     */
    @Column(name = "REPLY_TIME",length = 100)
    private Date replyTime;
    /**
     *验收批复文号
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
     */
    @Column(name = "IS_LICENSE",length = 100)
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

    public BuildProject getBuildProject() {
        return buildProject;
    }

    public void setBuildProject(BuildProject buildProject) {
        this.buildProject = buildProject;
    }
}