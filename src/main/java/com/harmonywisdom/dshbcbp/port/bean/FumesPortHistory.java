package com.harmonywisdom.dshbcbp.port.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 油烟排口历史数据
 */
@Entity
@Table(name = "HW_DSHBCBP_FUMES_PORT_HISTORY")
public class FumesPortHistory implements Serializable {
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