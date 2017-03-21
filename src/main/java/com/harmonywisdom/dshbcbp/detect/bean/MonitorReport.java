package com.harmonywisdom.dshbcbp.detect.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 监督性监测报告
 */
@Entity
@Table(name = "T_MONITOR_REPORT")
public class MonitorReport implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    private String id;

    /**
     * 类型：1水源地  2大气污染防治  3水污染防治
     * 4:噪声 5:土壤污染防治
     */
    @Column(length = 2)
    private String type;

    /**
     * 发送状态：0未发送，1已发送
     */
    @Column(length = 2)
    private String status;

    /**
     * 查看状态：0未查看，1已查看
     */
    @Column(name = "look_status",length = 1)
    private String lookStatus;

    /**
     * 选择发送的人员
     */
    @Column(name = "send_person_id")
    private String sendPersonId;
    @Column(name = "send_person_name")
    private String sendPersonName;


    /**
     * 监测名称
     */
    @Column(name = "monitor_name")
    private String monitorName;

    /**
     * 监测时间
     */
    @Column(name = "monitor_time")
    private String monitorTime;

    /**
     * 监测人员
     */
    @Column(name = "monitor_person_name")
    private String monitorPersonName;

    /**
     * 联系方式
     */
    @Column(name = "monitor_phone")
    private String monitorPhone;

    /**
     * 网格级别，
     */
    @Column(name = "block_level_id")
    private String blockLevelId;
    @Column(name = "block_level_name")
    private String blockLevelName;

    /**
     * 所属网格，
     */
    @Column(name = "block_id", length = 32)
    private String blockId;
    @Column(name = "block_name")
    private String blockName;


    /**
     * 网格负责人
     */
    @Column(name = "block_person_name")
    private String blockPersonName;

    /**
     * 联系方式
     */
    @Column(name = "block_person_phone")
    private String blockPersonPhone;

    /**
     * 监测类型
     */
    @Column(name="monitoringType")
    private String monitoringType;


    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;
    /**
     * 报告编号
     */
    @Column(name = "reportNumber")
    private String reportNumber;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "mobile_timestamp",columnDefinition = "CURRENT_TIMESTAMP")
    private Date mobileTimestamp;

    @Transient
    private String attachmentIds;

    public String getReportNumber() {
        return reportNumber;
    }

    public void setReportNumber(String reportNumber) {
        this.reportNumber = reportNumber;
    }

    public String getSendPersonId() {
        return sendPersonId;
    }

    public void setSendPersonId(String sendPersonId) {
        this.sendPersonId = sendPersonId;
    }

    public String getSendPersonName() {
        return sendPersonName;
    }

    public void setSendPersonName(String sendPersonName) {
        this.sendPersonName = sendPersonName;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMonitorName() {
        return monitorName;
    }

    public void setMonitorName(String monitorName) {
        this.monitorName = monitorName;
    }

    public String getMonitorTime() {
        return monitorTime;
    }

    public void setMonitorTime(String monitorTime) {
        this.monitorTime = monitorTime;
    }

    public String getMonitorPersonName() {
        return monitorPersonName;
    }

    public void setMonitorPersonName(String monitorPersonName) {
        this.monitorPersonName = monitorPersonName;
    }

    public String getMonitorPhone() {
        return monitorPhone;
    }

    public void setMonitorPhone(String monitorPhone) {
        this.monitorPhone = monitorPhone;
    }

    public String getBlockLevelId() {
        return blockLevelId;
    }

    public void setBlockLevelId(String blockLevelId) {
        this.blockLevelId = blockLevelId;
    }

    public String getBlockLevelName() {
        return blockLevelName;
    }

    public void setBlockLevelName(String blockLevelName) {
        this.blockLevelName = blockLevelName;
    }

    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public String getBlockPersonName() {
        return blockPersonName;
    }

    public void setBlockPersonName(String blockPersonName) {
        this.blockPersonName = blockPersonName;
    }

    public String getBlockPersonPhone() {
        return blockPersonPhone;
    }

    public void setBlockPersonPhone(String blockPersonPhone) {
        this.blockPersonPhone = blockPersonPhone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAttachmentIds() {
        return attachmentIds;
    }

    public void setAttachmentIds(String attachmentIds) {
        this.attachmentIds = attachmentIds;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLookStatus() {
        return lookStatus;
    }

    public void setLookStatus(String lookStatus) {
        this.lookStatus = lookStatus;
    }

    public String getMonitoringType() {
        return monitoringType;
    }

    public void setMonitoringType(String monitoringType) {
        this.monitoringType = monitoringType;
    }

    public Date getMobileTimestamp() {
        return mobileTimestamp;
    }

    public void setMobileTimestamp(Date mobileTimestamp) {
        this.mobileTimestamp = mobileTimestamp;
    }
}