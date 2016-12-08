package com.harmonywisdom.dshbcbp.port.bean;

import javax.persistence.*;
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
     * 排口编号
     */
    @Column(name = "number",length = 100)
    private String number;

    /**
     * 站点名称
     */
    @Column(name = "name",length = 100)
    private String name;

    /**
     * 经度
     */
    @Column(name = "longitude")
    private Double longitude;

    /**
     * 纬度
     */
    @Column(name = "latitude")
    private Double latitude;

    /**
     * 位置
     */
    @Column(name = "position")
    private String position;

    /**
     * 监测时间
     */
    @Column(name = "monitor_time")
    private Date monitorTime;

    /**
     * 状态
     * 0:正常
     * 1:超标
     * 2:异常
     */
    @Column(name = "port_status")
    private String portStatus;

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
    @Column(name = "is_pm",length = 1)
    private String isPm;

    /**
     * 是否监测TSP(mg/m3)
     */
    @Column(name = "is_tsp",length = 1)
    private String isTsp;

    /**
     * 是否监测温度(.C)
     */
    @Column(name = "is_temperature",length = 1)
    private String isTemperature;

    /**
     * 是否监测湿度(%)
     */
    @Column(name = "is_humidity",length = 1)
    private String isHumidity;

    /**
     * 是否监测气压(hpa)
     */
    @Column(name = "is_air_pressure",length = 1)
    private String isAirPressure;

    /**
     * 是否监测风向(度)
     */
    @Column(name = "is_wind_direction",length = 1)
    private String isWindDirection;

    /**
     * 是否监测风速(m/s)
     */
    @Column(name = "is_wind_speed",length = 1)
    private String isWindSpeed;

    /**
     * createTime
     */
    @Column(name = "create_time")
    private Date createTime;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public Date getMonitorTime() {
        return monitorTime;
    }

    public void setMonitorTime(Date monitorTime) {
        this.monitorTime = monitorTime;
    }

    public String getPortStatus() {
        return portStatus;
    }

    public void setPortStatus(String portStatus) {
        this.portStatus = portStatus;
    }

    public String getIsPm() {
        return isPm;
    }

    public void setIsPm(String isPm) {
        this.isPm = isPm;
    }

    public String getIsTsp() {
        return isTsp;
    }

    public void setIsTsp(String isTsp) {
        this.isTsp = isTsp;
    }

    public String getIsTemperature() {
        return isTemperature;
    }

    public void setIsTemperature(String isTemperature) {
        this.isTemperature = isTemperature;
    }

    public String getIsHumidity() {
        return isHumidity;
    }

    public void setIsHumidity(String isHumidity) {
        this.isHumidity = isHumidity;
    }

    public String getIsAirPressure() {
        return isAirPressure;
    }

    public void setIsAirPressure(String isAirPressure) {
        this.isAirPressure = isAirPressure;
    }

    public String getIsWindDirection() {
        return isWindDirection;
    }

    public void setIsWindDirection(String isWindDirection) {
        this.isWindDirection = isWindDirection;
    }

    public String getIsWindSpeed() {
        return isWindSpeed;
    }

    public void setIsWindSpeed(String isWindSpeed) {
        this.isWindSpeed = isWindSpeed;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}