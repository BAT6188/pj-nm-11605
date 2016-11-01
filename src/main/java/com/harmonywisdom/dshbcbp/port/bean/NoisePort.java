package com.harmonywisdom.dshbcbp.port.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 噪音排口
 */
@Entity
@Table(name = "HW_DSHBCBP_NOISE_PORT")
public class NoisePort implements Serializable {
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
     * 监测点类型
     */
    @Column(name = "type",length = 1)
    private String type; //0:企业噪声排口；1:政府监测点噪声排口

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
     * 排口位置
     */
    @Column(name = "position",length = 100)
    private String position;

    /**
     * 噪声源类型
     */
    @Column(name = "noise_type",length = 100)
    private String noiseType;

    /**
     * 排放标准
     */
    @Column(name = "discharge_standard",length = 100)
    private String dischargeStandard;

    /**
     * 功能区类别
     */
    @Column(name = "fn_type",length = 100)
    private String fnType;

    /**
     * 监测类型
     */
    @Column(name = "monitor_type",length = 100)
    private String monitorType;

    /**
     * 监测频次
     */
    @Column(name = "monitor_frequency",length = 100)
    private String monitorFrequency;

    /**
     * 监测时间
     */
    @Column(name = "monitor_time")
    private Date monitorTime;

    /**
     * Leq(db)
     */
    @Column(name = "leqdb")
    private Double leqdb;

    /**
     * sd
     */
    @Column(name = "sd")
    private Double sd;

    /**
     * Lmax(dB)
     */
    @Column(name = "lmax")
    private Double lmax;

    /**
     * Lmin(dB)
     */
    @Column(name = "lmin")
    private Double lmin;

    /**
     * L5(dB)
     */
    @Column(name = "lFive")
    private Double lFive;

    /**
     * L10(dB)
     */
    @Column(name = "lTen")
    private Double lTen;

    /**
     * L50(dB)
     */
    @Column(name = "lFifty")
    private Double lFifty;

    /**
     * L90(dB)
     */
    @Column(name = "lNinety")
    private Double lNinety;

    /**
     * L95(dB)
     */
    @Column(name = "lNinetyFive")
    private Double lNinetyFive;

    /**
     * Le
     */
    @Column(name = "le")
    private Double le;

    /**
     * 是否监测Leq(db)
     */
    @Column(name = "is_leqdb",length = 1)
    private String isLeqdb;

    /**
     * 是否监测sd
     */
    @Column(name = "is_sd",length = 1)
    private String isSd;

    /**
     * 是否监测Lmax(dB)
     */
    @Column(name = "is_lmax",length = 1)
    private String isLmax;

    /**
     * 是否监测Lmin(dB)
     */
    @Column(name = "is_lmin",length = 1)
    private String isLmin;

    /**
     * 是否监测L5(dB)
     */
    @Column(name = "is_lFive",length = 1)
    private String isLFive;

    /**
     * 是否监测L10(dB)
     */
    @Column(name = "is_lTen",length = 1)
    private String isLTen;

    /**
     * 是否监测L50(dB)
     */
    @Column(name = "is_lFifty",length = 1)
    private String isLFifty;

    /**
     * 是否监测L90(dB)
     */
    @Column(name = "is_lNinety",length = 1)
    private String isLNinety;

    /**
     * 是否监测L95(dB)
     */
    @Column(name = "is_lNinetyFive",length = 1)
    private String isLNinetyFive;

    /**
     * 是否监测Le
     */
    @Column(name = "is_le",length = 1)
    private String isLe;

    /**
     * 昼间上限(dB)
     */
    @Column(name = "day_max")
    private Double dayMax;

    /**
     * 夜间上限(dB)
     */
    @Column(name = "night_max")
    private Double nightMax;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getNoiseType() {
        return noiseType;
    }

    public void setNoiseType(String noiseType) {
        this.noiseType = noiseType;
    }

    public String getDischargeStandard() {
        return dischargeStandard;
    }

    public void setDischargeStandard(String dischargeStandard) {
        this.dischargeStandard = dischargeStandard;
    }

    public String getFnType() {
        return fnType;
    }

    public void setFnType(String fnType) {
        this.fnType = fnType;
    }

    public String getMonitorType() {
        return monitorType;
    }

    public void setMonitorType(String monitorType) {
        this.monitorType = monitorType;
    }

    public String getMonitorFrequency() {
        return monitorFrequency;
    }

    public void setMonitorFrequency(String monitorFrequency) {
        this.monitorFrequency = monitorFrequency;
    }

    public Date getMonitorTime() {
        return monitorTime;
    }

    public void setMonitorTime(Date monitorTime) {
        this.monitorTime = monitorTime;
    }

    public Double getLeqdb() {
        return leqdb;
    }

    public void setLeqdb(Double leqdb) {
        this.leqdb = leqdb;
    }

    public Double getSd() {
        return sd;
    }

    public void setSd(Double sd) {
        this.sd = sd;
    }

    public Double getLmax() {
        return lmax;
    }

    public void setLmax(Double lmax) {
        this.lmax = lmax;
    }

    public Double getLmin() {
        return lmin;
    }

    public void setLmin(Double lmin) {
        this.lmin = lmin;
    }

    public Double getlFive() {
        return lFive;
    }

    public void setlFive(Double lFive) {
        this.lFive = lFive;
    }

    public Double getlTen() {
        return lTen;
    }

    public void setlTen(Double lTen) {
        this.lTen = lTen;
    }

    public Double getlFifty() {
        return lFifty;
    }

    public void setlFifty(Double lFifty) {
        this.lFifty = lFifty;
    }

    public Double getlNinety() {
        return lNinety;
    }

    public void setlNinety(Double lNinety) {
        this.lNinety = lNinety;
    }

    public Double getlNinetyFive() {
        return lNinetyFive;
    }

    public void setlNinetyFive(Double lNinetyFive) {
        this.lNinetyFive = lNinetyFive;
    }

    public Double getLe() {
        return le;
    }

    public void setLe(Double le) {
        this.le = le;
    }

    public String getIsLeqdb() {
        return isLeqdb;
    }

    public void setIsLeqdb(String isLeqdb) {
        this.isLeqdb = isLeqdb;
    }

    public String getIsSd() {
        return isSd;
    }

    public void setIsSd(String isSd) {
        this.isSd = isSd;
    }

    public String getIsLmax() {
        return isLmax;
    }

    public void setIsLmax(String isLmax) {
        this.isLmax = isLmax;
    }

    public String getIsLmin() {
        return isLmin;
    }

    public void setIsLmin(String isLmin) {
        this.isLmin = isLmin;
    }

    public String getIsLFive() {
        return isLFive;
    }

    public void setIsLFive(String isLFive) {
        this.isLFive = isLFive;
    }

    public String getIsLTen() {
        return isLTen;
    }

    public void setIsLTen(String isLTen) {
        this.isLTen = isLTen;
    }

    public String getIsLFifty() {
        return isLFifty;
    }

    public void setIsLFifty(String isLFifty) {
        this.isLFifty = isLFifty;
    }

    public String getIsLNinety() {
        return isLNinety;
    }

    public void setIsLNinety(String isLNinety) {
        this.isLNinety = isLNinety;
    }

    public String getIsLNinetyFive() {
        return isLNinetyFive;
    }

    public void setIsLNinetyFive(String isLNinetyFive) {
        this.isLNinetyFive = isLNinetyFive;
    }

    public String getIsLe() {
        return isLe;
    }

    public void setIsLe(String isLe) {
        this.isLe = isLe;
    }

    public Double getDayMax() {
        return dayMax;
    }

    public void setDayMax(Double dayMax) {
        this.dayMax = dayMax;
    }

    public Double getNightMax() {
        return nightMax;
    }

    public void setNightMax(Double nightMax) {
        this.nightMax = nightMax;
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
}