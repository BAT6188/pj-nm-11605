package com.harmonywisdom.dshbcbp.port.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 废气排口历史数据
 */
@Entity
@Table(name = "HW_DSHBCBP_GAS_PORT_HISTORY")
public class GasPortHistory implements Serializable {
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
     * 排口code
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
     * 排口名称
     */
    @Column(name = "name",length = 100)
    private String name;

    /**
     * 监测时间
     */
    @Column(name = "monitor_time")
    private Date monitorTime;

    //--------------------------------------------------------------------------------------------//

    /**
     * 氮氧化物（毫克/立方米）
     */
    @Column(name = "nitrogen_PAvgValue")
    private Double nitrogenPAvgValue;

    /**
     * 氮氧化物（毫克/立方米）
     */
    @Column(name = "nitrogen_StandardMaxValue")
    private Double nitrogenStandardMaxValue;

    /**
     * 氮氧化物（毫克/立方米）
     */
    @Column(name = "nitrogen_StandardMinValue")
    private Double nitrogenStandardMinValue;

    /**
     * 氮氧化物（毫克/立方米）
     */
    @Column(name = "nitrogen_ExceptionMaxValue")
    private Double nitrogenExceptionMaxValue;

    /**
     * 氮氧化物（毫克/立方米）
     */
    @Column(name = "nitrogen_ExceptionMinValue")
    private Double nitrogenExceptionMinValue;

    /**
     * 是否监测氮氧
     */
    @Column(name = "is_nitrogen",length = 1)
    private String isNitrogen;

    //--------------------------------------------------------------------------------------------//

    /**
     * 二氧化硫（毫克/立方米）
     */
    @Column(name = "sulfur_PAvgValue")
    private Double sulfurPAvgValue;

    /**
     * 二氧化硫（毫克/立方米）
     */
    @Column(name = "sulfur_StandardMaxValue")
    private Double sulfurStandardMaxValue;

    /**
     * 二氧化硫（毫克/立方米）
     */
    @Column(name = "sulfur_StandardMinValue")
    private Double sulfurStandardMinValue;

    /**
     * 二氧化硫（毫克/立方米）
     */
    @Column(name = "sulfur_ExceptionMaxValue")
    private Double sulfurExceptionMaxValue;

    /**
     * 二氧化硫（毫克/立方米）
     */
    @Column(name = "sulfur_ExceptionMinValue")
    private Double sulfurExceptionMinValue;


    /**
     * 是否监测二氧
     */
    @Column(name = "is_sulfur",length = 1)
    private String isSulfur;

    //--------------------------------------------------------------------------------------------//

    /**
     * 废气流量（立方米/小时）
     */
    @Column(name = "gas_flow_PAvgValue")
    private Double gasFlowPAvgValue;

    /**
     * 废气流量（立方米/小时）
     */
    @Column(name = "gas_flow_StandardMaxValue")
    private Double gasFlowStandardMaxValue;

    /**
     * 废气流量（立方米/小时）
     */
    @Column(name = "gas_flow_StandardMinValue")
    private Double gasFlowStandardMinValue;

    /**
     * 废气流量（立方米/小时）
     */
    @Column(name = "gas_flow_ExceptionMaxValue")
    private Double gasFlowExceptionMaxValue;

    /**
     * 废气流量（立方米/小时）
     */
    @Column(name = "gas_flow_ExceptionMinValue")
    private Double gasFlowExceptionMinValue;

    /**
     * 是否监测废气流量
     */
    @Column(name = "is_gas_flow",length = 1)
    private String isGasFlow;

    //--------------------------------------------------------------------------------------------//

    /**
     * 烟尘（毫克/立方米）
     */
    @Column(name = "dust_PAvgValue")
    private Double dustPAvgValue;

    /**
     * 烟尘（毫克/立方米）
     */
    @Column(name = "dust_StandardMaxValue")
    private Double dustStandardMaxValue;

    /**
     * 烟尘（毫克/立方米）
     */
    @Column(name = "dust_StandardMinValue")
    private Double dustStandardMinValue;

    /**
     * 烟尘（毫克/立方米）
     */
    @Column(name = "dust_ExceptionMaxValue")
    private Double dustExceptionMaxValue;

    /**
     * 烟尘（毫克/立方米）
     */
    @Column(name = "dust_ExceptionMinValue")
    private Double dustExceptionMinValue;

    /**
     * 是否监测烟尘
     */
    @Column(name = "is_dust",length = 1)
    private String isDust;

    //--------------------------------------------------------------------------------------------//

    /**
     * 氧含量（%）
     */
    @Column(name = "oxygen_PAvgValue")
    private Double oxygenPAvgValue;

    /**
     * 氧含量（%）
     */
    @Column(name = "oxygen_StandardMaxValue")
    private Double oxygenStandardMaxValue;

    /**
     * 氧含量（%）
     */
    @Column(name = "oxygen_StandardMinValue")
    private Double oxygenStandardMinValue;

    /**
     * 氧含量（%）
     */
    @Column(name = "oxygen_ExceptionMaxValue")
    private Double oxygenExceptionMaxValue;

    /**
     * 氧含量（%）
     */
    @Column(name = "oxygen_ExceptionMinValue")
    private Double oxygenExceptionMinValue;

    /**
     * 是否监测氧含量
     */
    @Column(name = "isO_oxygen",length = 1)
    private String isOxygen;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getIsNitrogen() {
        return isNitrogen;
    }

    public void setIsNitrogen(String isNitrogen) {
        this.isNitrogen = isNitrogen;
    }

    public Double getSulfurPAvgValue() {
        return sulfurPAvgValue;
    }

    public void setSulfurPAvgValue(Double sulfurPAvgValue) {
        this.sulfurPAvgValue = sulfurPAvgValue;
    }

    public Double getSulfurStandardMaxValue() {
        return sulfurStandardMaxValue;
    }

    public void setSulfurStandardMaxValue(Double sulfurStandardMaxValue) {
        this.sulfurStandardMaxValue = sulfurStandardMaxValue;
    }

    public Double getSulfurStandardMinValue() {
        return sulfurStandardMinValue;
    }

    public void setSulfurStandardMinValue(Double sulfurStandardMinValue) {
        this.sulfurStandardMinValue = sulfurStandardMinValue;
    }

    public Double getSulfurExceptionMaxValue() {
        return sulfurExceptionMaxValue;
    }

    public void setSulfurExceptionMaxValue(Double sulfurExceptionMaxValue) {
        this.sulfurExceptionMaxValue = sulfurExceptionMaxValue;
    }

    public Double getSulfurExceptionMinValue() {
        return sulfurExceptionMinValue;
    }

    public void setSulfurExceptionMinValue(Double sulfurExceptionMinValue) {
        this.sulfurExceptionMinValue = sulfurExceptionMinValue;
    }

    public String getIsSulfur() {
        return isSulfur;
    }

    public void setIsSulfur(String isSulfur) {
        this.isSulfur = isSulfur;
    }

    public Double getGasFlowPAvgValue() {
        return gasFlowPAvgValue;
    }

    public void setGasFlowPAvgValue(Double gasFlowPAvgValue) {
        this.gasFlowPAvgValue = gasFlowPAvgValue;
    }

    public Double getGasFlowStandardMaxValue() {
        return gasFlowStandardMaxValue;
    }

    public void setGasFlowStandardMaxValue(Double gasFlowStandardMaxValue) {
        this.gasFlowStandardMaxValue = gasFlowStandardMaxValue;
    }

    public Double getGasFlowStandardMinValue() {
        return gasFlowStandardMinValue;
    }

    public void setGasFlowStandardMinValue(Double gasFlowStandardMinValue) {
        this.gasFlowStandardMinValue = gasFlowStandardMinValue;
    }

    public Double getGasFlowExceptionMaxValue() {
        return gasFlowExceptionMaxValue;
    }

    public void setGasFlowExceptionMaxValue(Double gasFlowExceptionMaxValue) {
        this.gasFlowExceptionMaxValue = gasFlowExceptionMaxValue;
    }

    public Double getGasFlowExceptionMinValue() {
        return gasFlowExceptionMinValue;
    }

    public void setGasFlowExceptionMinValue(Double gasFlowExceptionMinValue) {
        this.gasFlowExceptionMinValue = gasFlowExceptionMinValue;
    }

    public String getIsGasFlow() {
        return isGasFlow;
    }

    public void setIsGasFlow(String isGasFlow) {
        this.isGasFlow = isGasFlow;
    }

    public Double getDustPAvgValue() {
        return dustPAvgValue;
    }

    public void setDustPAvgValue(Double dustPAvgValue) {
        this.dustPAvgValue = dustPAvgValue;
    }

    public Double getDustStandardMaxValue() {
        return dustStandardMaxValue;
    }

    public void setDustStandardMaxValue(Double dustStandardMaxValue) {
        this.dustStandardMaxValue = dustStandardMaxValue;
    }

    public Double getDustStandardMinValue() {
        return dustStandardMinValue;
    }

    public void setDustStandardMinValue(Double dustStandardMinValue) {
        this.dustStandardMinValue = dustStandardMinValue;
    }

    public Double getDustExceptionMaxValue() {
        return dustExceptionMaxValue;
    }

    public void setDustExceptionMaxValue(Double dustExceptionMaxValue) {
        this.dustExceptionMaxValue = dustExceptionMaxValue;
    }

    public Double getDustExceptionMinValue() {
        return dustExceptionMinValue;
    }

    public void setDustExceptionMinValue(Double dustExceptionMinValue) {
        this.dustExceptionMinValue = dustExceptionMinValue;
    }

    public String getIsDust() {
        return isDust;
    }

    public void setIsDust(String isDust) {
        this.isDust = isDust;
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

    public String getIsOxygen() {
        return isOxygen;
    }

    public void setIsOxygen(String isOxygen) {
        this.isOxygen = isOxygen;
    }

    public String getPortCode() {
        return portCode;
    }

    public void setPortCode(String portCode) {
        this.portCode = portCode;
    }
}