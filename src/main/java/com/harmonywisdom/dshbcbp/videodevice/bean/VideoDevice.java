package com.harmonywisdom.dshbcbp.videodevice.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 公安视频
 */
@Entity
@Table(name = "HW_VIDEO_DEVICE")
public class VideoDevice implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    private String id;

    /**
     * 设备id
     */
    @Column(name = "device_id")
    private String deviceId;

    /**
     * 通道id
     */
    @Column(name = "channal_id")
    private String channalId;

    /**
     * 视频安装地址
     */
    @Column(name = "addr")
    private String addr;

    /**
     * 摄像头类型：1普通，2高空
     */
    @Column(name = "type",length = 1)
    private String type;

    /**
     * 所属单位
     */
    @Column(name = "unit")
    private String unit;

    /**
     * 经度
     */
    @Column(name = "longitude")
    private String longitude;

    /**
     * 维度
     */
    @Column(name = "latitude")
    private String latitude;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getChannalId() {
        return channalId;
    }

    public void setChannalId(String channalId) {
        this.channalId = channalId;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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
}