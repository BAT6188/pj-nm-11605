package com.harmonywisdom.dshbcbp.port.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 油烟排口
 */
@Entity
@Table(name = "HW_DSHBCBP_FUMES_PORT")
public class FumesPort implements Serializable {
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
     * 监测点
     */
    @Column(name = "name",length = 100)
    private String name;

    /**
     * 监测类型
     */
    @Column(name = "monitor_type",length = 100)
    private String monitorType;

    /**
     * 监测时间
     */
    //@Column(name = "monitor_time")
    //private Date monitorTime;

    /**
     * 状态
     * 0:正常
     * 1:超标
     * 2:异常
     */
    @Column(name = "port_status")
    private String portStatus;

    /**
     * 油烟
     */
    @Column(name = "fumes")
    private Double fumes;

    /**
     * 烟气温度
     */
    @Column(name = "temperature")
    private Double temperature;

    /**
     * 烟气湿度
     */
    @Column(name = "humidity")
    private Double humidity;

    /**
     * 是否监测油烟
     */
    @Column(name = "is_fumes",length = 1)
    private Double isFumes;

    /**
     * 是否监测烟气温度
     */
    @Column(name = "is_temperature",length = 1)
    private Double isTemperature;

    /**
     * 是否监测烟气湿度
     */
    @Column(name = "is_humidity",length = 1)
    private Double isHumidity;

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
     * 平面图标绘
     */
    @Column(name = "plane_map_mark",length = 1024)
    private String planeMapMark;

    /**
     * 附件
     */
    @Transient
    private String attachmentId;

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

    public String getMonitorType() {
        return monitorType;
    }

    public void setMonitorType(String monitorType) {
        this.monitorType = monitorType;
    }

    public String getPortStatus() {
        return portStatus;
    }

    public void setPortStatus(String portStatus) {
        this.portStatus = portStatus;
    }

    public Double getIsFumes() {
        return isFumes;
    }

    public void setIsFumes(Double isFumes) {
        this.isFumes = isFumes;
    }

    public Double getIsTemperature() {
        return isTemperature;
    }

    public void setIsTemperature(Double isTemperature) {
        this.isTemperature = isTemperature;
    }

    public Double getIsHumidity() {
        return isHumidity;
    }

    public void setIsHumidity(Double isHumidity) {
        this.isHumidity = isHumidity;
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

    public String getPlaneMapMark() {
        return planeMapMark;
    }

    public void setPlaneMapMark(String planeMapMark) {
        this.planeMapMark = planeMapMark;
    }

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    public Double getFumes() {
        return fumes;
    }

    public void setFumes(Double fumes) {
        this.fumes = fumes;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }
}