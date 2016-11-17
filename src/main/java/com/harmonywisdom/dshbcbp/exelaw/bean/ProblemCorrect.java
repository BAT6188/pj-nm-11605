package com.harmonywisdom.dshbcbp.exelaw.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 问题整改
 */
@Entity
@Table(name = "HW_PROBLEM_CORRECT")
public class ProblemCorrect implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 台账编号
     */
    @Id
    @Column(length = 32)
    private String id;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 存在的问题类型
     * 1. 安全隐患
     * 2. 总量减排
     * 3. 非法排污
     * 4. 未批先建
     */
    @Column(name = "problem_type", length = 2)
    private String problemType;

    /**
     * 问题描述
     */
    @Column(name = "problem_desc")
    private String problemDesc;
    /**
     * 整改情况
     */
    @Column(name = "correct_desc")
    private String correctDesc;
    /**
     * 问题进度：
     * 1. 暂存
     * 2. 已消耗
     */
    @Column(name = "progress", length = 2)
    private String progress;

    @Column(name = "enterprise_id", length = 32)
    private String enterpriseId;

    @Transient
    private String attachmentIds;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getProblemType() {
        return problemType;
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType;
    }

    public String getProblemDesc() {
        return problemDesc;
    }

    public void setProblemDesc(String problemDesc) {
        this.problemDesc = problemDesc;
    }

    public String getCorrectDesc() {
        return correctDesc;
    }

    public void setCorrectDesc(String correctDesc) {
        this.correctDesc = correctDesc;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getAttachmentIds() {
        return attachmentIds;
    }

    public void setAttachmentIds(String attachmentIds) {
        this.attachmentIds = attachmentIds;
    }
}