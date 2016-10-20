package com.harmonywisdom.dshbcbp.port.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 阀值
 */
@Entity
@Table(name = "HW_DSHBCBP_PORT_THRESHOLD")
public class PortThreshold implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    private String id;

    /**
     * 阀值类型
     */
    @Column(name = "type")
    private String type; //废水阈值管理、火电厂废气阈值管理、锅炉废气阈值管理、油烟阈值管理

    /**
     * 污染物编码
     */
    @Column(name = "pollutant_code")
    private String pollutantCode;

    /**
     * 超标值
     */
    @Column(name = "over_value")
    private Double overValue;

    /**
     * 上限
     */
    @Column(name = "max_value")
    private Double maxValue;

    /**
     * 下限
     */
    @Column(name = "min_value")
    private Double minValue;

    /**
     * createTime
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 企业ID
     */
    @Column(name = "enterprise_id",length = 32)
    private String enterpriseId;

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

    public String getPollutantCode() {
        return pollutantCode;
    }

    public void setPollutantCode(String pollutantCode) {
        this.pollutantCode = pollutantCode;
    }

    public Double getOverValue() {
        return overValue;
    }

    public void setOverValue(Double overValue) {
        this.overValue = overValue;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Double maxValue) {
        this.maxValue = maxValue;
    }

    public Double getMinValue() {
        return minValue;
    }

    public void setMinValue(Double minValue) {
        this.minValue = minValue;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
}