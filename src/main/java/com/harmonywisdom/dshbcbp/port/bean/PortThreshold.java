package com.harmonywisdom.dshbcbp.port.bean;

import javax.persistence.*;
import java.io.Serializable;

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
    @Column(name = "max")
    private Double max;

    /**
     * 下限
     */
    @Column(name = "min")
    private Double min;

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

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }
}