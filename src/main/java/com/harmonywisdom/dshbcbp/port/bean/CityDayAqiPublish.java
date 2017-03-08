package com.harmonywisdom.dshbcbp.port.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "HW_CITY_DAY_AQI_PUBLISH")
public class CityDayAqiPublish implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    private String id;

    @Column(name = "TimePoint")
    private Date TimePoint;

    @Column(name = "SO2_24h")
    private String SO2_24h;

    @Column(name = "CO_24h")
    private String CO_24h;

    @Column(name = "NO2_24h")
    private String NO2_24h;

    @Column(name = "O3_8h_24h")
    private String O3_8h_24h;

    @Column(name = "PM10_24h")
    private String PM10_24h;

    @Column(name = "PM2_5_24h")
    private String PM2_5_24h;

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

    public String getSO2_24h() {
        return SO2_24h;
    }

    public void setSO2_24h(String SO2_24h) {
        this.SO2_24h = SO2_24h;
    }

    public String getCO_24h() {
        return CO_24h;
    }

    public void setCO_24h(String CO_24h) {
        this.CO_24h = CO_24h;
    }

    public String getNO2_24h() {
        return NO2_24h;
    }

    public void setNO2_24h(String NO2_24h) {
        this.NO2_24h = NO2_24h;
    }

    public String getO3_8h_24h() {
        return O3_8h_24h;
    }

    public void setO3_8h_24h(String o3_8h_24h) {
        O3_8h_24h = o3_8h_24h;
    }

    public String getPM10_24h() {
        return PM10_24h;
    }

    public void setPM10_24h(String PM10_24h) {
        this.PM10_24h = PM10_24h;
    }

    public String getPM2_5_24h() {
        return PM2_5_24h;
    }

    public void setPM2_5_24h(String PM2_5_24h) {
        this.PM2_5_24h = PM2_5_24h;
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