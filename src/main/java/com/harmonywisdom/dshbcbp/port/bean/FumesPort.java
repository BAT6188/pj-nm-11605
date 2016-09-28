package com.harmonywisdom.dshbcbp.port.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
     * 站点名称
     */
    @Column(name = "name",length = 100)
    private String name;

    /**
     * 监测时间
     */
    @Column(name = "monitor_time")
    private Date monitorTime;

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
    @Column(name = "is_fumes")
    private Double isFumes;

    /**
     * 是否监测烟气温度
     */
    @Column(name = "is_temperature")
    private Double isTemperature;

    /**
     * 是否监测烟气湿度
     */
    @Column(name = "is_humidity")
    private Double isHumidity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}