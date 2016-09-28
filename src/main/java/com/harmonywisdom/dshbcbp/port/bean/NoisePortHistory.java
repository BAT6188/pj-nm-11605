package com.harmonywisdom.dshbcbp.port.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
    @Column(name = "l5")
    private Double l5;

    /**
     * L10(dB)
     */
    @Column(name = "l10")
    private Double l10;

    /**
     * L50(dB)
     */
    @Column(name = "l50")
    private Double l50;

    /**
     * L90(dB)
     */
    @Column(name = "l90")
    private Double l90;

    /**
     * L95(dB)
     */
    @Column(name = "l95")
    private Double l95;

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

    public Double getL5() {
        return l5;
    }

    public void setL5(Double l5) {
        this.l5 = l5;
    }

    public Double getL10() {
        return l10;
    }

    public void setL10(Double l10) {
        this.l10 = l10;
    }

    public Double getL50() {
        return l50;
    }

    public void setL50(Double l50) {
        this.l50 = l50;
    }

    public Double getL90() {
        return l90;
    }

    public void setL90(Double l90) {
        this.l90 = l90;
    }

    public Double getL95() {
        return l95;
    }

    public void setL95(Double l95) {
        this.l95 = l95;
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
}