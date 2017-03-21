package com.harmonywisdom.dshbcbp.port.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 超标预警记录
 */
@Entity
@Table(name = "HW_DSHBCBP_PORT_STATUS_HISTORY")
public class PortStatusHistory implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String STATUS_NORMAL = "0";
    public static final String STATUS_OVER = "1";
    public static final String STATUS_WARNING = "2";

    @Id
    @Column(length = 32)
    private String id;

    /**
     * 超标值
     */
    @Column(name = "live_Value")
    private String liveValue;

    /**
     * 标准值
     */
    @Column(name = "standard_Value")
    private String standardValue;


    /**
     * 关联设备ID
     */
    @Column(name = "port_id")
    private String portId;

    /**
     * 关联设备number portCode
     */
    @Column(name = "port_number")
    private String portNumber;

    /**
     * 关联设备name
     */
    @Column(name = "port_name")
    private String portName;

    /**
     * 关联企业ID
     */
    @Column(name = "enterprise_id")
    private String enterpriseId;

    /**
     * 关联企业名称
     */
    @Column(name = "enterprise_name")
    private String enterpriseName;

    /**
     * 关联企业类型
     */
    @Column(name = "enterprise_type")
    private String enterpriseType;

    /**
     * 网格级别
     */
    @Column(name = "block_level_id")
    private String blockLevelId;

    /**
     * 所属网格
     */
    @Column(name = "block_id")
    private String blockId;

    /**
     * 污染物名称
     */
    @Column(name = "pollutant_code")
    private String pollutantCode;
    @Column(name = "pollutant_name")
    private String pollutantName;

    /**
     * 状态
     * 0:正常
     * 1:超标
     * 2:异常
     */
    @Column(name = "port_status")
    private String portStatus;

    /**
     * 最小值
     */
    @Column(name = "min_value")
    private Double minValue;

    /**
     * 最大值
     */
    @Column(name = "max_value")
    private Double maxValue;

    /**
     * 监测时间
     */
    @Column(name = "time")
    private Date time;


    /**
     * 状态开始时间
     */
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 状态结束时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 解决方案
     */
    @Column(name = "solution")
    private String solution;

    /**
     * 标题
     * @return
     */
    @Column(name = "RES_TITLE")
    private String res_title;

    /**
     * 发布单位
     * @return
     */
    @Column(name="PUBLISHING_UNIT")
    private String publishingUnit;

    /**
     * 发布时间
     * @return
     */
    @Column(name = "RELEASE_TIME")
    private Date release_time;

    /**
     * 发布人
     * @return
     */
    @Column(name = "RELEASE_PERSON")
    private String release_person;

    /**
     * 联系方式
     */
    @Column(name="CONTACT")
    private String contact;

    /**
     * 实时数据
     * @return
     */
    @Column(name = "REALTIME_DATA")
    private String realtimeData;

    /**
     * 是否反馈
     * 1：已反馈
     * 2：未反馈
     */
    @Column(name = "IS_NO_TICKLING")
    private String isNoTickling;

    /**
     * 设备位置
     */
    @Column(name="EQUIPMENT_POSITION")
    private String equipmentPosition;

    /**
     * 附件
     **/
    @Transient
    private String attachmentId;

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

    public String getPollutantCode() {
        return pollutantCode;
    }

    public void setPollutantCode(String pollutantCode) {
        this.pollutantCode = pollutantCode;
    }

    public String getPortStatus() {
        return portStatus;
    }

    public void setPortStatus(String portStatus) {
        this.portStatus = portStatus;
    }

    public Double getMinValue() {
        return minValue;
    }

    public void setMinValue(Double minValue) {
        this.minValue = minValue;
    }

    public Double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Double maxValue) {
        this.maxValue = maxValue;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(String portNumber) {
        this.portNumber = portNumber;
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

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

    public String getEnterpriseType() {
        return enterpriseType;
    }

    public void setEnterpriseType(String enterpriseType) {
        this.enterpriseType = enterpriseType;
    }

    public String getRes_title() {
        return res_title;
    }

    public void setRes_title(String res_title) {
        this.res_title = res_title;
    }

    public String getPublishingUnit() {
        return publishingUnit;
    }

    public void setPublishingUnit(String publishingUnit) {
        this.publishingUnit = publishingUnit;
    }

    public Date getRelease_time() {
        return release_time;
    }

    public void setRelease_time(Date release_time) {
        this.release_time = release_time;
    }

    public String getRelease_person() {
        return release_person;
    }

    public void setRelease_person(String release_person) {
        this.release_person = release_person;
    }

    public String getRealtimeData() {
        return realtimeData;
    }

    public void setRealtimeData(String realtimeData) {
        this.realtimeData = realtimeData;
    }

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getIsNoTickling() {
        return isNoTickling;
    }

    public void setIsNoTickling(String isNoTickling) {
        this.isNoTickling = isNoTickling;
    }

    public String getEquipmentPosition() {
        return equipmentPosition;
    }

    public void setEquipmentPosition(String equipmentPosition) {
        this.equipmentPosition = equipmentPosition;
    }

    public String getPollutantName() {
        return pollutantName;
    }

    public void setPollutantName(String pollutantName) {
        this.pollutantName = pollutantName;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getLiveValue() {
        return liveValue;
    }

    public void setLiveValue(String liveValue) {
        this.liveValue = liveValue;
    }

    public String getStandardValue() {
        return standardValue;
    }

    public void setStandardValue(String standardValue) {
        this.standardValue = standardValue;
    }
}