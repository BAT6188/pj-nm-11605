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