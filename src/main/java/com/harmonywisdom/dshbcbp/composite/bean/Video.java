package com.harmonywisdom.dshbcbp.composite.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 视频头
 */
@Entity
@Table(name = "HW_VIDEO")
public class Video implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    private String id;
    /**
     *名称
     */
    @Column(name = "NAME",length = 100)
    private String name;
    /**
     *所属单位Id
     */
    @Column(name = "UNIT_ID",length = 100)
    private String unitId;
    /**
     *所属单位名称
     */
    @Column(name = "UNIT_NAME",length = 100)
    private String unitName;
    /**
     *longitude
     */
    @Column(name = "LONGITUDE",length = 100)
    private String longitude;
    /**
     *latitude
     */
    @Column(name = "LATITUDE",length = 100)
    private String latitude;
    /**
     *设备id
     */
    @Column(name = "EQUIPMENT_ID",length = 100)
    private String equipmentId;
    /**
     *createTime
     */
    @Column(name = "CREATE_TIME")
    private Date createTime;


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

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}