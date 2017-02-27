package com.harmonywisdom.dshbcbp.port.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
    @Column(name = "flow_PAvgValue")
    private Double flowPAvgValue;

    /**
     * 流量标准值上限: 用来判断数据超标
     */
    @Column(name = "flow_StandardMaxValue")
    private Double flowStandardMaxValue;

    /**
     * 流量标准值下限: 用来判断数据超标
     */
    @Column(name = "flow_StandardMinValue")
    private Double flowStandardMinValue;

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
    @Column(name = "oxygen_PAvgValue")
    private Double oxygenPAvgValue;

    /**
     * 化学需氧量
     */
    @Column(name = "oxygen_StandardMaxValue")
    private Double oxygenStandardMaxValue;

    /**
     * 化学需氧量
     */
    @Column(name = "oxygen_StandardMinValue")
    private Double oxygenStandardMinValue;

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
    @Column(name = "nitrogen_PAvgValue")
    private Double nitrogenPAvgValue;

    /**
     * 氨氮
     */
    @Column(name = "nitrogen_StandardMaxValue")
    private Double nitrogenStandardMaxValue;

    /**
     * 氨氮
     */
    @Column(name = "nitrogen_StandardMinValue")
    private Double nitrogenStandardMinValue;

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
    @Column(name = "ph_PAvgValue")
    private Double phPAvgValue;

    /**
     * ph值
     */
    @Column(name = "ph_StandardMaxValue")
    private Double phStandardMaxValue;

    /**
     * ph值
     */
    @Column(name = "ph_StandardMinValue")
    private Double phStandardMinValue;

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

    public Double getFlowPAvgValue() {
        return flowPAvgValue;
    }

    public void setFlowPAvgValue(Double flowPAvgValue) {
        this.flowPAvgValue = flowPAvgValue;
    }

    public Double getFlowStandardMaxValue() {
        return flowStandardMaxValue;
    }

    public void setFlowStandardMaxValue(Double flowStandardMaxValue) {
        this.flowStandardMaxValue = flowStandardMaxValue;
    }

    public Double getFlowStandardMinValue() {
        return flowStandardMinValue;
    }

    public void setFlowStandardMinValue(Double flowStandardMinValue) {
        this.flowStandardMinValue = flowStandardMinValue;
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

    public Double getOxygenPAvgValue() {
        return oxygenPAvgValue;
    }

    public void setOxygenPAvgValue(Double oxygenPAvgValue) {
        this.oxygenPAvgValue = oxygenPAvgValue;
    }

    public Double getOxygenStandardMaxValue() {
        return oxygenStandardMaxValue;
    }

    public void setOxygenStandardMaxValue(Double oxygenStandardMaxValue) {
        this.oxygenStandardMaxValue = oxygenStandardMaxValue;
    }

    public Double getOxygenStandardMinValue() {
        return oxygenStandardMinValue;
    }

    public void setOxygenStandardMinValue(Double oxygenStandardMinValue) {
        this.oxygenStandardMinValue = oxygenStandardMinValue;
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

    public Double getNitrogenPAvgValue() {
        return nitrogenPAvgValue;
    }

    public void setNitrogenPAvgValue(Double nitrogenPAvgValue) {
        this.nitrogenPAvgValue = nitrogenPAvgValue;
    }

    public Double getNitrogenStandardMaxValue() {
        return nitrogenStandardMaxValue;
    }

    public void setNitrogenStandardMaxValue(Double nitrogenStandardMaxValue) {
        this.nitrogenStandardMaxValue = nitrogenStandardMaxValue;
    }

    public Double getNitrogenStandardMinValue() {
        return nitrogenStandardMinValue;
    }

    public void setNitrogenStandardMinValue(Double nitrogenStandardMinValue) {
        this.nitrogenStandardMinValue = nitrogenStandardMinValue;
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

    public Double getPhPAvgValue() {
        return phPAvgValue;
    }

    public void setPhPAvgValue(Double phPAvgValue) {
        this.phPAvgValue = phPAvgValue;
    }

    public Double getPhStandardMaxValue() {
        return phStandardMaxValue;
    }

    public void setPhStandardMaxValue(Double phStandardMaxValue) {
        this.phStandardMaxValue = phStandardMaxValue;
    }

    public Double getPhStandardMinValue() {
        return phStandardMinValue;
    }

    public void setPhStandardMinValue(Double phStandardMinValue) {
        this.phStandardMinValue = phStandardMinValue;
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
}