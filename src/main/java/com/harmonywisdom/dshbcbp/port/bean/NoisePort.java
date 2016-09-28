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
}