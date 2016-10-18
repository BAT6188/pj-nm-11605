package com.harmonywisdom.dshbcbp.port.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 废气排口
 */
@Entity
@Table(name = "HW_DSHBCBP_GAS_PORT")
public class GasPort implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    private String id;

    /**
     * 排口编号
     */
    @Column(name = "number",length = 100)
    private String number;

    /**
     * 排口名称
     */
    @Column(name = "name",length = 100)
    private String name;

    /**
     * 排口位置
     */
    @Column(name = "position",length = 100)
    private String position;

    /**
     * 排放方式
     */
    @Column(name = "discharge_mode",length = 100)
    private String dischargeMode;

    /**
     * 排放去向
     */
    @Column(name = "discharge_direction",length = 100)
    private String dischargeDirection ;
    /**
     * 排放设备
     */
    @Column(name = "discharge_device",length = 100)
    private String dischargeDevice ;
    /**
     * 排放标准
     */
    @Column(name = "discharge_standard",length = 100)
    private String dischargeStandard;

    /**
     * 监测类型
     */
    @Column(name = "monitor_type",length = 100)
    private String monitorType ;

    /**
     * 监测时间
     */
    @Column(name = "monitor_time")
    private Date monitorTime;

    /**
     * 氮氧化物（毫克/立方米）
     */
    @Column(name = "nitrogen")
    private Double nitrogen;

    /**
     * 是否监测氮氧
     */
    @Column(name = "is_nitrogen",length = 1)
    private String isNitrogen;

    /**
     * 二氧化硫（毫克/立方米）
     */
    @Column(name = "sulfur")
    private Double sulfur;

    /**
     * 是否监测二氧
     */
    @Column(name = "is_sulfur",length = 1)
    private String isSulfur;

    /**
     * 废气流量（立方米/小时）
     */
    @Column(name = "gas_flow")
    private Double gasFlow;

    /**
     * 是否监测废气流量
     */
    @Column(name = "is_gas_flow",length = 1)
    private String isGasFlow;

    /**
     * 烟尘（毫克/立方米）
     */
    @Column(name = "dust")
    private Double dust;

    /**
     * 是否监测烟尘
     */
    @Column(name = "is_dust",length = 1)
    private String isDust;

    /**
     * 氧含量（%）
     */
    @Column(name = "oxygen")
    private Double oxygen;

    /**
     * 是否监测氧含量
     */
    @Column(name = "isO_oxygen",length = 1)
    private String isOxygen;

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDischargeMode() {
        return dischargeMode;
    }

    public void setDischargeMode(String dischargeMode) {
        this.dischargeMode = dischargeMode;
    }

    public String getDischargeDirection() {
        return dischargeDirection;
    }

    public void setDischargeDirection(String dischargeDirection) {
        this.dischargeDirection = dischargeDirection;
    }

    public String getDischargeStandard() {
        return dischargeStandard;
    }

    public void setDischargeStandard(String dischargeStandard) {
        this.dischargeStandard = dischargeStandard;
    }

    public String getMonitorType() {
        return monitorType;
    }

    public void setMonitorType(String monitorType) {
        this.monitorType = monitorType;
    }

    public Date getMonitorTime() {
        return monitorTime;
    }

    public void setMonitorTime(Date monitorTime) {
        this.monitorTime = monitorTime;
    }

    public Double getNitrogen() {
        return nitrogen;
    }

    public void setNitrogen(Double nitrogen) {
        this.nitrogen = nitrogen;
    }

    public String getIsNitrogen() {
        return isNitrogen;
    }

    public void setIsNitrogen(String isNitrogen) {
        this.isNitrogen = isNitrogen;
    }

    public Double getSulfur() {
        return sulfur;
    }

    public void setSulfur(Double sulfur) {
        this.sulfur = sulfur;
    }

    public String getIsSulfur() {
        return isSulfur;
    }

    public void setIsSulfur(String isSulfur) {
        this.isSulfur = isSulfur;
    }

    public Double getGasFlow() {
        return gasFlow;
    }

    public void setGasFlow(Double gasFlow) {
        this.gasFlow = gasFlow;
    }

    public String getIsGasFlow() {
        return isGasFlow;
    }

    public void setIsGasFlow(String isGasFlow) {
        this.isGasFlow = isGasFlow;
    }

    public Double getDust() {
        return dust;
    }

    public void setDust(Double dust) {
        this.dust = dust;
    }

    public String getIsDust() {
        return isDust;
    }

    public void setIsDust(String isDust) {
        this.isDust = isDust;
    }

    public Double getOxygen() {
        return oxygen;
    }

    public void setOxygen(Double oxygen) {
        this.oxygen = oxygen;
    }

    public String getIsOxygen() {
        return isOxygen;
    }

    public void setIsOxygen(String isOxygen) {
        this.isOxygen = isOxygen;
    }

    public String getDischargeDevice() {
        return dischargeDevice;
    }

    public void setDischargeDevice(String dischargeDevice) {
        this.dischargeDevice = dischargeDevice;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
}