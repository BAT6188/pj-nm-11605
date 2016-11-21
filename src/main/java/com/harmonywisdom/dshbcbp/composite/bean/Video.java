package com.harmonywisdom.dshbcbp.composite.bean;

import javax.persistence.*;
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
    /**
     *摄像头编号
     */
    @Column(name = "NUMBER",length = 100)
    private String number;
    /**
     * 摄像头位置
     */
    @Column(name = "POSITION",length = 100)
    private String position;
    /**
     * 摄像头类型 1：企业摄像头；0：单位摄像头
     */
    @Column(name = "VIDEO_TYPE",length = 1)
    private String videoType;
    /**
     * 平面图标绘
     */
    @Column(name = "plane_map_mark",length = 1024)
    private String planeMapMark;
    /**
     * 网格级别
     */
    @Column(name = "BLOCK_LEVEL_ID")
    private String blockLevelId;

    /**
     *所属网格id
     *
     */
    @Column(name = "BLOCK_ID",length = 100)
    private String blockId;
    /**
     *附件
     */
    @Transient
    private String attachmentIds;


    public String getBlockLevelId() {
        return blockLevelId;
    }

    public void setBlockLevelId(String blockLevelId) {
        this.blockLevelId = blockLevelId;
    }

    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
    }

    public String getAttachmentIds() {
        return attachmentIds;
    }

    public void setAttachmentIds(String attachmentIds) {
        this.attachmentIds = attachmentIds;
    }

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    public String getPlaneMapMark() {
        return planeMapMark;
    }

    public void setPlaneMapMark(String planeMapMark) {
        this.planeMapMark = planeMapMark;
    }
}