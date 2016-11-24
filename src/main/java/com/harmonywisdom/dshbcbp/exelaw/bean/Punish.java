package com.harmonywisdom.dshbcbp.exelaw.bean;

import javax.persistence.*;
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

    @Column(name = "enterprise_id")
    private String enterpriseId;

    /**
     * 调度任务id
     */
    @Column(name = "dispatch_task_id", length = 32)
    private String dispatchTaskId;

    /**
     * 立案号
     */
    @Column(length = 32)
    private String code;

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
     * 履行情况
     * 1 代履行
     * 2 自觉履行
     * 3 申请法院强制执行
     * 4 尚未执行
     * 5 无须执行
     * 6 已无法执行
     */
    @Column(name = "exe_desc")
    private String exeDesc;

    /**
     * 处罚类型 punishType
     * 1. 罚款
     * 2. 警告
     * 3. 责令停产整顿
     * 4. 责令停产、停业、关闭
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
    @Column(name = "closed_date")
    private Date closedDate;
    /**
     * 决定书处罚内容
     */
    @Column(name = "content")
    private String content;


    @Transient
    private String attachmentIds;

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

    public Date getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(Date closedDate) {
        this.closedDate = closedDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAttachmentIds() {
        return attachmentIds;
    }

    public void setAttachmentIds(String attachmentIds) {
        this.attachmentIds = attachmentIds;
    }

    public String getDispatchTaskId() {
        return dispatchTaskId;
    }

    public void setDispatchTaskId(String dispatchTaskId) {
        this.dispatchTaskId = dispatchTaskId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
}