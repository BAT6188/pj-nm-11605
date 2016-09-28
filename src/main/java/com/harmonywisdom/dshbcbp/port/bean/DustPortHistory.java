package com.harmonywisdom.dshbcbp.port.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 沙尘排口历史数据
 */
@Entity
@Table(name = "HW_DSHBCBP_DUST_PORT_HISTORY")
public class DustPortHistory implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    private String id;

    /**
     * 监测时间
     */
    @Column(name = "monitor_time")
    private Date monitorTime;

    /**
     * 能见度(km)
     */
    @Column(name = "visibility")
    private Double visibility;

    /**
     * PM(mg/m3)
     */
    @Column(name = "pm")
    private Double pm;

    /**
     * TSP(mg/m3)
     */
    @Column(name = "tsp")
    private Double tsp;

    /**
     * 温度(.C)
     */
    @Column(name = "temperature")
    private Double temperature;

    /**
     * 湿度(%)
     */
    @Column(name = "humidity")
    private Double humidity;

    /**
     * 气压(hpa)
     */
    @Column(name = "air_pressure")
    private Double airPressure;

    /**
     * 风向(度)
     */
    @Column(name = "wind_direction")
    private Double windDirection;

    /**
     * 风速(m/s)
     */
    @Column(name = "wind_speed")
    private Double windSpeed;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getMonitorTime() {
        return monitorTime;
    }

    public void setMonitorTime(Date monitorTime) {
        this.monitorTime = monitorTime;
    }

    public Double getVisibility() {
        return visibility;
    }

    public void setVisibility(Double visibility) {
        this.visibility = visibility;
    }

    public Double getPm() {
        return pm;
    }

    public void setPm(Double pm) {
        this.pm = pm;
    }

    public Double getTsp() {
        return tsp;
    }

    public void setTsp(Double tsp) {
        this.tsp = tsp;
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

    public Double getAirPressure() {
        return airPressure;
    }

    public void setAirPressure(Double airPressure) {
        this.airPressure = airPressure;
    }

    public Double getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(Double windDirection) {
        this.windDirection = windDirection;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }
}