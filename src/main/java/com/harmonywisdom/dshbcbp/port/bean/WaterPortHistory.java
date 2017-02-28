package com.harmonywisdom.dshbcbp.port.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 废水排口历史数据
 */
@Entity
@Table(name = "HW_DSHBCBP_WATER_PORT_HISTORY")
public class WaterPortHistory implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    private String id;

    /**
     * `mobile_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "mobile_timestamp",columnDefinition = "CURRENT_TIMESTAMP")
    private Date mobileTimestamp;


    /**
     * 排口ID
     */
    @Column(name = "port_id",length = 32)
    private String portId;

    /**
     * 排口ID
     */
    @Column(name = "port_code",length = 32)
    private String portCode;


    /**
     * 企业ID
     */
    @Column(name = "enterprise_id",length = 32)
    private String enterpriseId;

    /**
     * 企业code
     */
    @Column(name = "enterprise_code",length = 32)
    private String enterpriseCode;
    /**
     * 监测时间
     */
    @Column(name = "monitor_time")
    private Date monitorTime;

    //--------------------------------------------------------------------------------------------//

    /**
     * 流量平均值
     */
    @Column(name = "flow_LiveValue")
    private Double flowLiveValue;

    /**
     * 流量标准值上限: 用来判断数据超标
     */
    @Column(name = "flow_StandardValue")
    private Double flowStandardValue;

    /**
     * 流量标准值下限: 用来判断数据超标
     */
    @Column(name = "flow_Status")
    private String flowStatus;

    /**
     * 流量异常值上限: 用来判断数据异常
     */
    @Column(name = "flow_ExceptionMaxValue")
    private Double flowExceptionMaxValue;

    /**
     * 流量异常值下限: 用来判断数据异常
     */
    @Column(name = "flow_ExceptionMinValue")
    private Double flowExceptionMinValue;

    //--------------------------------------------------------------------------------------------//


    /**
     * 化学需氧量
     */
    @Column(name = "oxygen_LiveValue")
    private Double oxygenLiveValue;

    /**
     * 化学需氧量
     */
    @Column(name = "oxygen_StandardValue")
    private Double oxygenStandardValue;

    /**
     * 化学需氧量
     */
    @Column(name = "oxygen_Status")
    private String oxygenStatus;

    /**
     * 化学需氧量
     */
    @Column(name = "oxygen_ExceptionMaxValue")
    private Double oxygenExceptionMaxValue;

    /**
     * 化学需氧量
     */
    @Column(name = "oxygen_ExceptionMinValue")
    private Double oxygenExceptionMinValue;

    //--------------------------------------------------------------------------------------------//

    /**
     * 氨氮
     */
    @Column(name = "nitrogen_LiveValue")
    private Double nitrogenLiveValue;

    /**
     * 氨氮
     */
    @Column(name = "nitrogen_StandardValue")
    private Double nitrogenStandardValue;

    /**
     * 氨氮
     */
    @Column(name = "nitrogen_Status")
    private String nitrogenStatus;

    /**
     * 氨氮
     */
    @Column(name = "nitrogen_ExceptionMaxValue")
    private Double nitrogenExceptionMaxValue;

    /**
     * 氨氮
     */
    @Column(name = "nitrogen_ExceptionMinValue")
    private Double nitrogenExceptionMinValue;

    //--------------------------------------------------------------------------------------------//

    /**
     * ph值
     */
    @Column(name = "ph_LiveValue")
    private Double phLiveValue;

    /**
     * ph值
     */
    @Column(name = "ph_StandardValue")
    private Double phStandardValue;

    /**
     * ph值
     */
    @Column(name = "ph_Status")
    private String phStatus;

    /**
     * ph值
     */
    @Column(name = "ph_ExceptionMaxValue")
    private Double phExceptionMaxValue;

    /**
     * ph值
     */
    @Column(name = "ph_ExceptionMinValue")
    private Double phExceptionMinValue;

    //--------------------------------------------------------------------------------------------//

    /**
     * 数据状态
     * 0:正常
     * 1:超标
     * 2:异常
     */
    @Column(name = "data_status",length = 1)
    private String dataStatus;

    public String getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
    }

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

    public Date getMonitorTime() {
        return monitorTime;
    }

    public void setMonitorTime(Date monitorTime) {
        this.monitorTime = monitorTime;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Double getFlowLiveValue() {
        return flowLiveValue;
    }

    public void setFlowLiveValue(Double flowLiveValue) {
        this.flowLiveValue = flowLiveValue;
    }

    public Double getFlowStandardValue() {
        return flowStandardValue;
    }

    public void setFlowStandardValue(Double flowStandardValue) {
        this.flowStandardValue = flowStandardValue;
    }

    public String getFlowStatus() {
        return flowStatus;
    }

    public void setFlowStatus(String flowStatus) {
        this.flowStatus = flowStatus;
    }

    public Double getFlowExceptionMaxValue() {
        return flowExceptionMaxValue;
    }

    public void setFlowExceptionMaxValue(Double flowExceptionMaxValue) {
        this.flowExceptionMaxValue = flowExceptionMaxValue;
    }

    public Double getFlowExceptionMinValue() {
        return flowExceptionMinValue;
    }

    public void setFlowExceptionMinValue(Double flowExceptionMinValue) {
        this.flowExceptionMinValue = flowExceptionMinValue;
    }

    public Double getOxygenLiveValue() {
        return oxygenLiveValue;
    }

    public void setOxygenLiveValue(Double oxygenLiveValue) {
        this.oxygenLiveValue = oxygenLiveValue;
    }

    public Double getOxygenStandardValue() {
        return oxygenStandardValue;
    }

    public void setOxygenStandardValue(Double oxygenStandardValue) {
        this.oxygenStandardValue = oxygenStandardValue;
    }


    public Double getOxygenExceptionMaxValue() {
        return oxygenExceptionMaxValue;
    }

    public void setOxygenExceptionMaxValue(Double oxygenExceptionMaxValue) {
        this.oxygenExceptionMaxValue = oxygenExceptionMaxValue;
    }

    public Double getOxygenExceptionMinValue() {
        return oxygenExceptionMinValue;
    }

    public void setOxygenExceptionMinValue(Double oxygenExceptionMinValue) {
        this.oxygenExceptionMinValue = oxygenExceptionMinValue;
    }

    public Double getNitrogenLiveValue() {
        return nitrogenLiveValue;
    }

    public void setNitrogenLiveValue(Double nitrogenLiveValue) {
        this.nitrogenLiveValue = nitrogenLiveValue;
    }

    public Double getNitrogenStandardValue() {
        return nitrogenStandardValue;
    }

    public void setNitrogenStandardValue(Double nitrogenStandardValue) {
        this.nitrogenStandardValue = nitrogenStandardValue;
    }


    public Double getNitrogenExceptionMaxValue() {
        return nitrogenExceptionMaxValue;
    }

    public void setNitrogenExceptionMaxValue(Double nitrogenExceptionMaxValue) {
        this.nitrogenExceptionMaxValue = nitrogenExceptionMaxValue;
    }

    public Double getNitrogenExceptionMinValue() {
        return nitrogenExceptionMinValue;
    }

    public void setNitrogenExceptionMinValue(Double nitrogenExceptionMinValue) {
        this.nitrogenExceptionMinValue = nitrogenExceptionMinValue;
    }

    public Double getPhLiveValue() {
        return phLiveValue;
    }

    public void setPhLiveValue(Double phLiveValue) {
        this.phLiveValue = phLiveValue;
    }

    public Double getPhStandardValue() {
        return phStandardValue;
    }

    public void setPhStandardValue(Double phStandardValue) {
        this.phStandardValue = phStandardValue;
    }

    public String getOxygenStatus() {
        return oxygenStatus;
    }

    public void setOxygenStatus(String oxygenStatus) {
        this.oxygenStatus = oxygenStatus;
    }

    public String getNitrogenStatus() {
        return nitrogenStatus;
    }

    public void setNitrogenStatus(String nitrogenStatus) {
        this.nitrogenStatus = nitrogenStatus;
    }

    public String getPhStatus() {
        return phStatus;
    }

    public void setPhStatus(String phStatus) {
        this.phStatus = phStatus;
    }

    public Double getPhExceptionMaxValue() {
        return phExceptionMaxValue;
    }

    public void setPhExceptionMaxValue(Double phExceptionMaxValue) {
        this.phExceptionMaxValue = phExceptionMaxValue;
    }

    public Double getPhExceptionMinValue() {
        return phExceptionMinValue;
    }

    public void setPhExceptionMinValue(Double phExceptionMinValue) {
        this.phExceptionMinValue = phExceptionMinValue;
    }

    public String getPortCode() {
        return portCode;
    }

    public void setPortCode(String portCode) {
        this.portCode = portCode;
    }

    public Date getMobileTimestamp() {
        return mobileTimestamp;
    }

    public void setMobileTimestamp(Date mobileTimestamp) {
        this.mobileTimestamp = mobileTimestamp;
    }
}