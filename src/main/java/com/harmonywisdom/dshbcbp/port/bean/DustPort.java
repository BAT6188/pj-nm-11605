package com.harmonywisdom.dshbcbp.port.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 沙尘排口
 */
@Entity
@Table(name = "HW_DSHBCBP_DUST_PORT")
public class DustPort implements Serializable {
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

    /**
     * 是否监测PM(mg/m3)
     */
    @Column(name = "is_pm")
    private Double isPm;

    /**
     * 是否监测TSP(mg/m3)
     */
    @Column(name = "is_tsp")
    private Double isTsp;

    /**
     * 是否监测温度(.C)
     */
    @Column(name = "is_temperature")
    private Double isTemperature;

    /**
     * 是否监测湿度(%)
     */
    @Column(name = "is_humidity")
    private Double isHumidity;

    /**
     * 是否监测气压(hpa)
     */
    @Column(name = "is_air_pressure")
    private Double isAirPressure;

    /**
     * 是否监测风向(度)
     */
    @Column(name = "is_wind_direction")
    private Double isWindDirection;

    /**
     * 是否监测风速(m/s)
     */
    @Column(name = "is_wind_speed")
    private Double isWindSpeed;

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

    public Double getVisibility() {
        return visibility;
    }

    public void setVisibility(Double visibility) {
        this.visibility = visibility;
    }

    public Date getMonitorTime() {
        return monitorTime;
    }

    public void setMonitorTime(Date monitorTime) {
        this.monitorTime = monitorTime;
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

    public Double getIsPm() {
        return isPm;
    }

    public void setIsPm(Double isPm) {
        this.isPm = isPm;
    }

    public Double getIsTsp() {
        return isTsp;
    }

    public void setIsTsp(Double isTsp) {
        this.isTsp = isTsp;
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

    public Double getIsAirPressure() {
        return isAirPressure;
    }

    public void setIsAirPressure(Double isAirPressure) {
        this.isAirPressure = isAirPressure;
    }

    public Double getIsWindDirection() {
        return isWindDirection;
    }

    public void setIsWindDirection(Double isWindDirection) {
        this.isWindDirection = isWindDirection;
    }

    public Double getIsWindSpeed() {
        return isWindSpeed;
    }

    public void setIsWindSpeed(Double isWindSpeed) {
        this.isWindSpeed = isWindSpeed;
    }

}