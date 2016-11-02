package com.harmonywisdom.dshbcbp.port.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 空气质量监测表
 */
@Entity
@Table(name = "HW_DSHBCBP_AIR_QUALITY")
public class AirQuality implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    private String id;

    /**
     * 空气AQI值
     */
    @Column(name="AIR_VALUE",length=100)
    private String airValue;

    /**
     * 更新时间
     * @return
     */
    @Column(name="REC_TIME")
    private Date rec_Time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAirValue() {
        return airValue;
    }

    public void setAirValue(String airValue) {
        this.airValue = airValue;
    }

    public Date getRec_Time() {
        return rec_Time;
    }

    public void setRec_Time(Date rec_Time) {
        this.rec_Time = rec_Time;
    }
}