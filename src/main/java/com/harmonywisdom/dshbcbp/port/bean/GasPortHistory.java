package com.harmonywisdom.dshbcbp.port.bean;

import javax.persistence.*;
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
     * 本地排口表ID
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
     * 氮氧化物（毫克/立方米） 实时值
     */
    @Column(name = "nitrogen_LiveValue")
    private Double nitrogenLiveValue;

    /**
     * 指标状态
     * 0:正常
     * 1:超标
     * 2:异常
     */
    @Column(name = "nitrogen_StandardValue")
    private Double nitrogenStandardValue;

    /**
     * 氮氧化物（毫克/立方米）
     */
    @Column(name = "nitrogen_Status",length = 2)
    private String nitrogenStatus;

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
    @Column(name = "sulfur_LiveValue")
    private Double sulfurLiveValue;

    /**
     * 二氧化硫（毫克/立方米）
     */
    @Column(name = "sulfur_StandardValue")
    private Double sulfurStandardValue;

    /**
     * 二氧化硫（毫克/立方米）
     */
    @Column(name = "sulfur_Status",length = 2)
    private String sulfurStatus;

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
    @Column(name = "gas_flow_LiveValue")
    private Double gasFlowLiveValue;

    /**
     * 废气流量（立方米/小时）
     */
    @Column(name = "gas_flow_StandardValue")
    private Double gasFlowStandardValue;

    /**
     * 废气流量（立方米/小时）
     */
    @Column(name = "gas_flow_Status",length = 2)
    private String gasFlowStatus;

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
    @Column(name = "dust_LiveValue")
    private Double dustLiveValue;

    /**
     * 烟尘（毫克/立方米）
     */
    @Column(name = "dust_StandardValue")
    private Double dustStandardValue;

    /**
     * 烟尘（毫克/立方米）
     */
    @Column(name = "dust_Status",length = 2)
    private String dustStatus;

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
    @Column(name = "oxygen_LiveValue")
    private Double oxygenLiveValue;

    /**
     * 氧含量（%）
     */
    @Column(name = "oxygen_StandardValue")
    private Double oxygenStandardValue;

    /**
     * 氧含量（%）
     */
    @Column(name = "oxygen_Status",length = 2)
    private String oxygenStatus;

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
    @Column(name = "data_status",length = 2)
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

    public String getIsNitrogen() {
        return isNitrogen;
    }

    public void setIsNitrogen(String isNitrogen) {
        this.isNitrogen = isNitrogen;
    }

    public Double getSulfurLiveValue() {
        return sulfurLiveValue;
    }

    public void setSulfurLiveValue(Double sulfurLiveValue) {
        this.sulfurLiveValue = sulfurLiveValue;
    }

    public Double getSulfurStandardValue() {
        return sulfurStandardValue;
    }

    public void setSulfurStandardValue(Double sulfurStandardValue) {
        this.sulfurStandardValue = sulfurStandardValue;
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

    public Double getGasFlowLiveValue() {
        return gasFlowLiveValue;
    }

    public void setGasFlowLiveValue(Double gasFlowLiveValue) {
        this.gasFlowLiveValue = gasFlowLiveValue;
    }

    public Double getGasFlowStandardValue() {
        return gasFlowStandardValue;
    }

    public void setGasFlowStandardValue(Double gasFlowStandardValue) {
        this.gasFlowStandardValue = gasFlowStandardValue;
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

    public Double getDustLiveValue() {
        return dustLiveValue;
    }

    public void setDustLiveValue(Double dustLiveValue) {
        this.dustLiveValue = dustLiveValue;
    }

    public Double getDustStandardValue() {
        return dustStandardValue;
    }

    public void setDustStandardValue(Double dustStandardValue) {
        this.dustStandardValue = dustStandardValue;
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

    public String getNitrogenStatus() {
        return nitrogenStatus;
    }

    public void setNitrogenStatus(String nitrogenStatus) {
        this.nitrogenStatus = nitrogenStatus;
    }

    public String getSulfurStatus() {
        return sulfurStatus;
    }

    public void setSulfurStatus(String sulfurStatus) {
        this.sulfurStatus = sulfurStatus;
    }

    public String getGasFlowStatus() {
        return gasFlowStatus;
    }

    public void setGasFlowStatus(String gasFlowStatus) {
        this.gasFlowStatus = gasFlowStatus;
    }

    public String getDustStatus() {
        return dustStatus;
    }

    public void setDustStatus(String dustStatus) {
        this.dustStatus = dustStatus;
    }

    public String getOxygenStatus() {
        return oxygenStatus;
    }

    public void setOxygenStatus(String oxygenStatus) {
        this.oxygenStatus = oxygenStatus;
    }

}