package com.harmonywisdom.dshbcbp.enterprise.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 排污申报
 */
@Entity
@Table(name = "HW_POLLUTANT_PAYMENT")
public class PollutantPayment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    private String id;

    private String enterpriseName;
    /**
     * 企业法人
     */
    private String enterpriseAP;
    /**
     * 联系方式
     */
    private String apPhone;
    /**
     * 登记日期
     */
    private Date registDate;
    /**
     * 缴费日期
     */
    private Date payDate;
    /**
     * 缴费金额
     */
    private Double payMoney;
    /**
     * 提醒日期
     */
    private Date alertDate;
    /**
     * 再次提醒日期
     */
    private Date realertDate;
    /**
     * 备注
     */
    private String remark;
    private String attachmentId;
    private String enterpriseId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getEnterpriseAP() {
        return enterpriseAP;
    }

    public void setEnterpriseAP(String enterpriseAP) {
        this.enterpriseAP = enterpriseAP;
    }

    public String getApPhone() {
        return apPhone;
    }

    public void setApPhone(String apPhone) {
        this.apPhone = apPhone;
    }

    public Date getRegistDate() {
        return registDate;
    }

    public void setRegistDate(Date registDate) {
        this.registDate = registDate;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Double payMoney) {
        this.payMoney = payMoney;
    }

    public Date getAlertDate() {
        return alertDate;
    }

    public void setAlertDate(Date alertDate) {
        this.alertDate = alertDate;
    }

    public Date getRealertDate() {
        return realertDate;
    }

    public void setRealertDate(Date realertDate) {
        this.realertDate = realertDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
}