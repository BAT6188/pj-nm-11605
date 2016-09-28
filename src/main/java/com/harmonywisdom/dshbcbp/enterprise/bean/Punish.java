package com.harmonywisdom.dshbcbp.enterprise.bean;

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
    private String caseName;
    /**
     * 立案时间
     */
    private Date filingDate;
    /**
     * 决定书文号
     */
    private String decideCode;
    /**
     * 案件来源
     */
    private String caseSource;
    /**
     * 案由
     */
    private String caseReason;
    /**
     * 违反条款
     */
    private String provision;
    /**
     * 执行情况
     */
    private String exeDesc;
    /**
     * 处罚类型
     */
    private String type;
    /**
     * 处罚金额
     */
    private Double money;
    /**
     * 处罚执行时间
     */
    private Date exeDate;
    /**
     * 处罚终止时间
     */
    private Date endDate;
    /**
     * 经办人
     */
    private String attn;
    /**
     * 结案日期
     */
    private Date closeedDate;
    /**
     * 决定书处罚内容
     */
    private String content;
    /**
     * 附件
     */
    private String attachmentId;

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

    /**
     * 调度任务id
     */
    private String dispathTaskId;

}