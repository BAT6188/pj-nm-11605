package com.harmonywisdom.dshbcbp.port.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 传输有效率表
 */
@Entity
@Table(name = "HW_DSHBCBP_TRANSPORT_EFFICIENT")
public class TransportEfficient implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    private String id;

    /**
     * 企业id
     */
    @Column(name="ENTERPRISE_ID")
    private String enterpriseId;

    /**
     * 企业名称
     */
    @Column(name="ENTERPRISE_NAME",length=100)
    private String enterpriseName;

    /**
     * 网格级别
     */
    @Column(name="BLOCK_LEVEL")
    private String blockLevel;

    /**
     * 所属网格
     */
    @Column(name="BLOCKID")
    private String blockId;

    /**
     * 所属网格名称
     */
    @Column(name="BLOCK_NAME")
    private String blockName;

    /**
     * 开始时间
     */
    @Column(name="START_TIME")
    private Date startTime;

    /**
     * 结束时间
     */
    @Column(name="END_TIME")
    private Date endTime;

    /**
     * 传输率
     */
    @Column(name="TRANSPOR")
    private Double transpor;

    /**
     * 有效率
     */
    @Column(name="EFFICIENT")
    private Double efficient;

    /**
     * 传输有效率
     */
    @Column(name="RATIO")
    private Double ratio;

    /**
     * 状态
     * 0:停运 1：整改 2：正常
     */
    @Column(name="STATUS")
    private String status;

    /**
     * 备注
     */
    @Column(name="REMARK")
    private String remark;

    /**
     * `mobile_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "mobile_timestamp",columnDefinition = "CURRENT_TIMESTAMP")
    private Date mobileTimestamp;


    /**
     * 附件
     */
    @Transient
    private String attachmentId;

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getBlockLevel() {
        return blockLevel;
    }

    public void setBlockLevel(String blockLevel) {
        this.blockLevel = blockLevel;
    }

    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Double getTranspor() {
        return transpor;
    }

    public void setTranspor(Double transpor) {
        this.transpor = transpor;
    }

    public Double getEfficient() {
        return efficient;
    }

    public void setEfficient(Double efficient) {
        this.efficient = efficient;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getMobileTimestamp() {
        return mobileTimestamp;
    }

    public void setMobileTimestamp(Date mobileTimestamp) {
        this.mobileTimestamp = mobileTimestamp;
    }
}