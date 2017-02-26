package com.harmonywisdom.dshbcbp.port.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 噪音排扣历史数据
 */
@Entity
@Table(name = "HW_DSHBCBP_NOISE_PORT_HISTORY")
public class NoisePortHistory implements Serializable {
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
     * 数据状态
     * 0:正常
     * 1:超标
     * 2:异常
     */
    @Column(name = "data_status",length = 1)
    private String dataStatus;

    /**
     * `mobile_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "mobile_timestamp",columnDefinition = "CURRENT_TIMESTAMP")
    private Date mobileTimestamp;

    public String getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
    }

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

    public Double getLFive() {
        return lFive;
    }

    public void setLFive(Double lFive) {
        this.lFive = lFive;
    }

    public Double getLTen() {
        return lTen;
    }

    public void setLTen(Double lTen) {
        this.lTen = lTen;
    }

    public Double getLFifty() {
        return lFifty;
    }

    public void setLFifty(Double lFifty) {
        this.lFifty = lFifty;
    }

    public Double getLNinety() {
        return lNinety;
    }

    public void setLNinety(Double lNinety) {
        this.lNinety = lNinety;
    }

    public Double getLNinetyFive() {
        return lNinetyFive;
    }

    public void setLNinetyFive(Double lNinetyFive) {
        this.lNinetyFive = lNinetyFive;
    }

    public Double getLe() {
        return le;
    }

    public void setLe(Double le) {
        this.le = le;
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

    public Date getMobileTimestamp() {
        return mobileTimestamp;
    }

    public void setMobileTimestamp(Date mobileTimestamp) {
        this.mobileTimestamp = mobileTimestamp;
    }
}