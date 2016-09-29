package com.harmonywisdom.dshbcbp.exelaw.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 行政处罚
 */
@Entity
@Table(name = "HW_PUNISH")
public class Punish implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    private String id;

    /**
     * 案件名称
     */
    @Column(name = "case_name", length = 32)
    private String caseName;
    /**
     * 立案时间
     */
    @Column(name = "filing_date")
    private Date filingDate;
    /**
     * 决定书文号
     */
    @Column(name = "decide_code", length = 32)
    private String decideCode;
    /**
     * 案件来源
     */
    @Column(name = "case_source", length = 32)
    private String caseSource;
    /**
     * 案由
     */
    @Column(name = "case_reason")
    private String caseReason;
    /**
     * 违反条款
     */
    @Column(name = "provision")
    private String provision;
    /**
     * 执行情况
     */
    @Column(name = "exe_desc")
    private String exeDesc;
    /**
     * 处罚类型
     */
    @Column(name = "type", length = 2)
    private String type;
    /**
     * 处罚金额
     */
    @Column(name = "money")
    private Double money;
    /**
     * 处罚执行时间
     */
    @Column(name = "exe_date")
    private Date exeDate;
    /**
     * 处罚终止时间
     */
    @Column(name = "end_date")
    private Date endDate;
    /**
     * 经办人
     */
    @Column(name = "attn", length = 32)
    private String attn;
    /**
     * 结案日期
     */
    @Column(name = "closeed_date")
    private Date closeedDate;
    /**
     * 决定书处罚内容
     */
    @Column(name = "content")
    private String content;
    /**
     * 附件
     */
    @Column(name = "attachment_id", length = 32)
    private String attachmentId;

    /**
     * 调度任务id
     */
    @Column(name = "dispath_task_id", length = 32)
    private String dispathTaskId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public Date getFilingDate() {
        return filingDate;
    }

    public void setFilingDate(Date filingDate) {
        this.filingDate = filingDate;
    }

    public String getDecideCode() {
        return decideCode;
    }

    public void setDecideCode(String decideCode) {
        this.decideCode = decideCode;
    }

    public String getCaseSource() {
        return caseSource;
    }

    public void setCaseSource(String caseSource) {
        this.caseSource = caseSource;
    }

    public String getCaseReason() {
        return caseReason;
    }

    public void setCaseReason(String caseReason) {
        this.caseReason = caseReason;
    }

    public String getProvision() {
        return provision;
    }

    public void setProvision(String provision) {
        this.provision = provision;
    }

    public String getExeDesc() {
        return exeDesc;
    }

    public void setExeDesc(String exeDesc) {
        this.exeDesc = exeDesc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Date getExeDate() {
        return exeDate;
    }

    public void setExeDate(Date exeDate) {
        this.exeDate = exeDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getAttn() {
        return attn;
    }

    public void setAttn(String attn) {
        this.attn = attn;
    }

    public Date getCloseedDate() {
        return closeedDate;
    }

    public void setCloseedDate(Date closeedDate) {
        this.closeedDate = closeedDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getDispathTaskId() {
        return dispathTaskId;
    }

    public void setDispathTaskId(String dispathTaskId) {
        this.dispathTaskId = dispathTaskId;
    }



}