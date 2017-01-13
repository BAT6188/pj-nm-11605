package com.harmonywisdom.dshbcbp.port.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 空气质量监测设备历史表
 */
@Entity
@Table(name = "HW_AIR_EQUIPMENTHISTORY")
public class AirEquipmentHistory implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    private String id;

    /**
     * 空气质量监测设备ID
     */
    @Column(name="EQUIPMENTID",length=100)
    private String equipmentId;

    /**
     * 空气质量监测点
     */
    @Column(name="AIR_MONITORING_NAME",length=100)
    private String airMonitoringName;

    /**
     * 监测点编号
     */
    @Column(name="MONITORING_NUMBER")
    private String monitoringNumber;

    /**
     * 经度
     */
    @Column(name="LONGITUDE")
    private Double longitude;

    /**
     * 纬度
     */
    @Column(name="latitude")
    private Double latitude;

    /**
     * 监测时间
     */
    @Column(name="MONITORING_TIME")
    private Date monitoringTime;

    /**
     * 监测点位置
     */
    @Column(name="MONITORING_POSITION")
    private String monitoringPosition;

    /**
     * 空气质量指数
     */
    @Column(name="AIR_INDEX")
    private Integer airIndex;

    /**
     * 企业ID
     */
    @Column(name = "enterprise_id",length = 32)
    private String enterpriseId;

    /**
     * 企业名称
     * @return
     */
    @Column(name="ENTERPRISE_NAME",length = 100)
    private String enterpriseName;

    /**
     * 空气质量等级
     * @return
     */
    @Column(name="AIRQUALITY_GRADE")
    private String airQualityGrade;

    /**
     * 首要污染物
     * @return
     */
    @Column(name="PRIMARY_POLLUTANT")
    private String primaryPollutant;

    /**
     * `mobile_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
     */
    @Column(name = "mobile_timestamp")
    private Date mobileTimestamp;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAirMonitoringName() {
        return airMonitoringName;
    }

    public void setAirMonitoringName(String airMonitoringName) {
        this.airMonitoringName = airMonitoringName;
    }

    public String getMonitoringNumber() {
        return monitoringNumber;
    }

    public void setMonitoringNumber(String monitoringNumber) {
        this.monitoringNumber = monitoringNumber;
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

    public Date getMonitoringTime() {
        return monitoringTime;
    }

    public void setMonitoringTime(Date monitoringTime) {
        this.monitoringTime = monitoringTime;
    }

    public String getMonitoringPosition() {
        return monitoringPosition;
    }

    public void setMonitoringPosition(String monitoringPosition) {
        this.monitoringPosition = monitoringPosition;
    }

    public Integer getAirIndex() {
        return airIndex;
    }

    public void setAirIndex(Integer airIndex) {
        this.airIndex = airIndex;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getAirQualityGrade() {
        return airQualityGrade;
    }

    public void setAirQualityGrade(String airQualityGrade) {
        this.airQualityGrade = airQualityGrade;
    }

    public String getPrimaryPollutant() {
        return primaryPollutant;
    }

    public void setPrimaryPollutant(String primaryPollutant) {
        this.primaryPollutant = primaryPollutant;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public Date getMobileTimestamp() {
        return mobileTimestamp;
    }

    public void setMobileTimestamp(Date mobileTimestamp) {
        this.mobileTimestamp = mobileTimestamp;
    }
}