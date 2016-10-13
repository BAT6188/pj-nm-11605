package com.harmonywisdom.dshbcbp.port.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 废水排口
 */
@Entity
@Table(name = "HW_DSHBCBP_WATER_PORT")
public class WaterPort implements Serializable {
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
     * 排口名称
     */
    @Column(name = "name",length = 100)
    private String name;

    /**
     * 排口位置
     */
    @Column(name = "position",length = 100)
    private String position;

    /**
     * 排放方式
     */
    @Column(name = "discharge_mode",length = 100)
    private String dischargeMode;

    /**
     * 排放去向
     */
    @Column(name = "discharge_direction",length = 100)
    private String dischargeDirection ;

    /**
     * 排放标准
     */
    @Column(name = "discharge_standard",length = 100)
    private String dischargeStandard;

    /**
     * 监测类型
     */
    @Column(name = "monitor_type",length = 100)
    private String monitorType;

    /**
     * 监测时间
     */
    @Column(name = "monitor_time")
    private Date monitorTime;

    /**
     * 流量
     */
    @Column(name = "flow")
    private Double flow;

    /**
     * 是否监测流量
     */
    @Column(name = "is_flow")
    private String isFlow;

    /**
     * 化学需氧量
     */
    @Column(name = "oxygen")
    private Double oxygen;

    /**
     * 是否监测化学需氧量
     */
    @Column(name = "is_oxygen")
    private String isOxygen;

    /**
     * 氨氮
     */
    @Column(name = "nitrogen")
    private Double nitrogen;

    /**
     * 是否监测氨氮
     */
    @Column(name = "is_nitrogen")
    private String isNitrogen;

    /**
     * ph值
     */
    @Column(name = "ph")
    private Double ph;

    /**
     * 是否监测ph值
     */
    @Column(name = "is_ph")
    private String isPh;

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

    public Double getFlow() {
        return flow;
    }

    public void setFlow(Double flow) {
        this.flow = flow;
    }

    public String getIsFlow() {
        return isFlow;
    }

    public void setIsFlow(String isFlow) {
        this.isFlow = isFlow;
    }

    public Double getOxygen() {
        return oxygen;
    }

    public void setOxygen(Double oxygen) {
        this.oxygen = oxygen;
    }

    public String getIsOxygen() {
        return isOxygen;
    }

    public void setIsOxygen(String isOxygen) {
        this.isOxygen = isOxygen;
    }

    public Double getNitrogen() {
        return nitrogen;
    }

    public void setNitrogen(Double nitrogen) {
        this.nitrogen = nitrogen;
    }

    public String getIsNitrogen() {
        return isNitrogen;
    }

    public void setIsNitrogen(String isNitrogen) {
        this.isNitrogen = isNitrogen;
    }

    public Double getPh() {
        return ph;
    }

    public void setPh(Double ph) {
        this.ph = ph;
    }

    public String getIsPh() {
        return isPh;
    }

    public void setIsPh(String isPh) {
        this.isPh = isPh;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
}