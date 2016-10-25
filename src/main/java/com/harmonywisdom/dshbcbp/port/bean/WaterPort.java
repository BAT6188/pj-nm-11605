package com.harmonywisdom.dshbcbp.port.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 废水排口
 */
@Entity
@Table(name = "HW_DSHBCBP_WATER_PORT")
public class WaterPort implements Serializable {
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
     * 排口经度
     */
    @Column(name = "longitude")
    private Double longitude;

    /**
     * 排口纬度
     */
    @Column(name = "latitude")
    private Double latitude;

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
     * 排放标准
     */
    @Column(name = "discharge_standard",length = 100)
    private String dischargeStandard;

    /**
     * 受纳水体
     */
    @Column(name = "valley")
    private String valley;

    /**
     * 受纳水体功能区类别
     */
    @Column(name = "valley_fn_type")
    private String valleyFnType;

    /**
     * 监测类型
     */
    @Column(name = "monitor_type",length = 100)
    private String monitorType;

    /**
     * 标志牌安装形式
     */
    @Column(name = "single_install_type",length = 100)
    private String singleInstallType;

    /**
     * 状态
     */
    @Column(name = "status",length = 1)
    private String status;

    /**
     * 是否进水口
     */
    @Column(name = "is_water_intake",length = 1)
    private String isWaterIntake;

    /**
     * 备注
     */
    @Column(name = "remark",length = 512)
    private String remark;

    /**
     * 监测时间
     */
    @Column(name = "monitor_time")
    private Date monitorTime;

    /**
     * 流量
     */
    @Column(name = "flow")
    private Double flow;

    /**
     * 是否监测流量
     */
    @Column(name = "is_flow")
    private String isFlow;

    /**
     * 化学需氧量
     */
    @Column(name = "oxygen")
    private Double oxygen;

    /**
     * 是否监测化学需氧量
     */
    @Column(name = "is_oxygen")
    private String isOxygen;

    /**
     * 氨氮
     */
    @Column(name = "nitrogen")
    private Double nitrogen;

    /**
     * 是否监测氨氮
     */
    @Column(name = "is_nitrogen")
    private String isNitrogen;

    /**
     * ph值
     */
    @Column(name = "ph")
    private Double ph;

    /**
     * 是否监测ph值
     */
    @Column(name = "is_ph")
    private String isPh;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getFlow() {
        return flow;
    }

    public void setFlow(Double flow) {
        this.flow = flow;
    }

    public String getIsFlow() {
        return isFlow;
    }

    public void setIsFlow(String isFlow) {
        this.isFlow = isFlow;
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

    public Double getPh() {
        return ph;
    }

    public void setPh(Double ph) {
        this.ph = ph;
    }

    public String getIsPh() {
        return isPh;
    }

    public void setIsPh(String isPh) {
        this.isPh = isPh;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getValley() {
        return valley;
    }

    public void setValley(String valley) {
        this.valley = valley;
    }

    public String getValleyFnType() {
        return valleyFnType;
    }

    public void setValleyFnType(String valleyFnType) {
        this.valleyFnType = valleyFnType;
    }

    public String getSingleInstallType() {
        return singleInstallType;
    }

    public void setSingleInstallType(String singleInstallType) {
        this.singleInstallType = singleInstallType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsWaterIntake() {
        return isWaterIntake;
    }

    public void setIsWaterIntake(String isWaterIntake) {
        this.isWaterIntake = isWaterIntake;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}