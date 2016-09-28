package com.harmonywisdom.dshbcbp.port.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
     * 是否监测Leq(db)
     */
    @Column(name = "is_leqdb")
    private Double isLeqdb;

    /**
     * 是否监测sd
     */
    @Column(name = "is_sd")
    private Double isSd;

    /**
     * 是否监测Lmax(dB)
     */
    @Column(name = "is_lmax")
    private Double isLmax;

    /**
     * 是否监测Lmin(dB)
     */
    @Column(name = "is_lmin")
    private Double isLmin;

    /**
     * 是否监测L5(dB)
     */
    @Column(name = "is_l5")
    private Double isL5;

    /**
     * 是否监测L10(dB)
     */
    @Column(name = "is_l10")
    private Double isL10;

    /**
     * 是否监测L50(dB)
     */
    @Column(name = "is_l50")
    private Double isL50;

    /**
     * 是否监测L90(dB)
     */
    @Column(name = "is_l90")
    private Double isL90;

    /**
     * 是否监测L95(dB)
     */
    @Column(name = "is_l95")
    private Double isL95;

    /**
     * 是否监测Le
     */
    @Column(name = "is_le")
    private Double isLe;

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
     * 企业ID
     */
    @Column(name = "enterprise_id",length = 32)
    private String enterpriseId;

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

    public Double getIsLeqdb() {
        return isLeqdb;
    }

    public void setIsLeqdb(Double isLeqdb) {
        this.isLeqdb = isLeqdb;
    }

    public Double getIsSd() {
        return isSd;
    }

    public void setIsSd(Double isSd) {
        this.isSd = isSd;
    }

    public Double getIsLmax() {
        return isLmax;
    }

    public void setIsLmax(Double isLmax) {
        this.isLmax = isLmax;
    }

    public Double getIsLmin() {
        return isLmin;
    }

    public void setIsLmin(Double isLmin) {
        this.isLmin = isLmin;
    }

    public Double getIsL5() {
        return isL5;
    }

    public void setIsL5(Double isL5) {
        this.isL5 = isL5;
    }

    public Double getIsL10() {
        return isL10;
    }

    public void setIsL10(Double isL10) {
        this.isL10 = isL10;
    }

    public Double getIsL50() {
        return isL50;
    }

    public void setIsL50(Double isL50) {
        this.isL50 = isL50;
    }

    public Double getIsL90() {
        return isL90;
    }

    public void setIsL90(Double isL90) {
        this.isL90 = isL90;
    }

    public Double getIsL95() {
        return isL95;
    }

    public void setIsL95(Double isL95) {
        this.isL95 = isL95;
    }

    public Double getIsLe() {
        return isLe;
    }

    public void setIsLe(Double isLe) {
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

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
}