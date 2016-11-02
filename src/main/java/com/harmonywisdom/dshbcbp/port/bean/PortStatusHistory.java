package com.harmonywisdom.dshbcbp.port.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 超标预警记录
 */
@Entity
@Table(name = "HW_DSHBCBP_PORT_STATUS_HISTORY")
public class PortStatusHistory implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    private String id;

    /**
     * 关联设备ID
     */
    @Column(name = "port_id")
    private String portId;

    /**
     * 关联设备ID
     */
    @Column(name = "port_number")
    private String portNumber;

    /**
     * 关联设备ID
     */
    @Column(name = "port_name")
    private String portName;

    /**
     * 关联企业ID
     */
    @Column(name = "enterprise_id")
    private String enterpriseId;

    /**
     * 关联企业名称
     */
    @Column(name = "enterprise_name")
    private String enterpriseName;

    /**
     * 污染物编码
     */
    @Column(name = "pollutant_code")
    private String pollutantCode;

    /**
     * 状态
     * 0:正常
     * 1:超标
     * 2:异常
     */
    @Column(name = "status")
    private String status;

    /**
     * 最小值
     */
    @Column(name = "min_value")
    private Double minValue;

    /**
     * 最大值
     */
    @Column(name = "max_value")
    private Double maxValue;

    /**
     * 状态开始时间
     */
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 状态结束时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 解决方案
     */
    @Column(name = "solution")
    private String solution;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPortId() {
        return portId;
    }

    public void setPortId(String portId) {
        this.portId = portId;
    }

    public String getPollutantCode() {
        return pollutantCode;
    }

    public void setPollutantCode(String pollutantCode) {
        this.pollutantCode = pollutantCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getMinValue() {
        return minValue;
    }

    public void setMinValue(Double minValue) {
        this.minValue = minValue;
    }

    public Double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Double maxValue) {
        this.maxValue = maxValue;
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

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
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

    public String getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(String portNumber) {
        this.portNumber = portNumber;
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }
}