package com.harmonywisdom.dshbcbp.port.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 空气质量时均值
 */
@Entity
@Table(name = "HW_CITY_AQI_PUBLISH")
public class CityAqiPublish implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    private String id;

    @Column(name = "TimePoint")
    private Date TimePoint;

    @Column(name = "CO")
    private String CO;

    @Column(name = "time_point")
    private String NO2;

    @Column(name = "O3")
    private String O3;

    @Column(name = "PM10")
    private String PM10;

    @Column(name = "PM2_5")
    private String PM2_5;

    @Column(name = "SO2")
    private String SO2;

    @Column(name = "AQI")
    private Integer AQI;

    @Column(name = "PrimaryPollutant")
    private String PrimaryPollutant;

    @Column(name = "Quality")
    private String Quality;

    @Column(name = "QualityLevel")
    private String QualityLevel;

    @Column(name = "Measure")
    private String Measure;

    @Column(name = "Unheathful")
    private String Unheathful;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "mobile_timestamp",columnDefinition = "CURRENT_TIMESTAMP")
    private Date mobileTimestamp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTimePoint() {
        return TimePoint;
    }

    public void setTimePoint(Date timePoint) {
        TimePoint = timePoint;
    }

    public String getCO() {
        return CO;
    }

    public void setCO(String CO) {
        this.CO = CO;
    }

    public String getNO2() {
        return NO2;
    }

    public void setNO2(String NO2) {
        this.NO2 = NO2;
    }

    public String getO3() {
        return O3;
    }

    public void setO3(String o3) {
        O3 = o3;
    }

    public String getPM10() {
        return PM10;
    }

    public void setPM10(String PM10) {
        this.PM10 = PM10;
    }

    public String getPM2_5() {
        return PM2_5;
    }

    public void setPM2_5(String PM2_5) {
        this.PM2_5 = PM2_5;
    }

    public String getSO2() {
        return SO2;
    }

    public void setSO2(String SO2) {
        this.SO2 = SO2;
    }

    public Integer getAQI() {
        return AQI;
    }

    public void setAQI(Integer AQI) {
        this.AQI = AQI;
    }

    public String getPrimaryPollutant() {
        return PrimaryPollutant;
    }

    public void setPrimaryPollutant(String primaryPollutant) {
        PrimaryPollutant = primaryPollutant;
    }

    public String getQuality() {
        return Quality;
    }

    public void setQuality(String quality) {
        Quality = quality;
    }

    public String getMeasure() {
        return Measure;
    }

    public void setMeasure(String measure) {
        Measure = measure;
    }

    public String getUnheathful() {
        return Unheathful;
    }

    public void setUnheathful(String unheathful) {
        Unheathful = unheathful;
    }

    public String getQualityLevel() {
        return QualityLevel;
    }

    public void setQualityLevel(String qualityLevel) {
        QualityLevel = qualityLevel;
    }

    public Date getMobileTimestamp() {
        return mobileTimestamp;
    }

    public void setMobileTimestamp(Date mobileTimestamp) {
        this.mobileTimestamp = mobileTimestamp;
    }
}