package com.harmonywisdom.dshbcbp.production.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 锅炉
 */
@Entity
@Table(name = "HW_DSHBCBP_BOILER")
public class Boiler implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    private String id;

    /**
     * 锅炉型号
     */
    @Column(name = "model",length = 100)
    private String model;

    /**
     * 设备名称
     */
    @Column(name = "name",length = 100)
    private String name;

    /**
     * 使用单位
     */
    @Column(name = "user_name",length = 100)
    private String userName;

    /**
     * 使用单位地址
     */
    @Column(name = "user_address",length = 100)
    private String userAddress;

    /**
     * 建成时间
     */
    @Column(name = "build_time")
    private Date buildTime;

    /**
     * 锅炉用途
     */
    @Column(name = "purpose",length = 100)
    private String purpose;

    /**
     * 是否位于地级及以上城区
     */
    @Column(name = "is_import_position",length = 1)
    private String isImportPosition;

    /**
     * 锅炉规模
     */
    @Column(name = "scale",length = 100)
    private String scale;

    /**
     * 除尘方式
     */
    @Column(name = "dust_type",length = 100)
    private String dustType;

    /**
     * 锅炉脱硫方式
     */
    @Column(name = "sulfur_type",length = 100)
    private String sulfurType;

    /**
     * 脱硝方式
     */
    @Column(name = "saltpetreType",length = 100)
    private String saltpetreType;

    /**
     * 燃料种类
     */
    @Column(name = "fuel_type",length = 100)
    private String fuelType;

    /**
     * createTime
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 企业ID
     */
    @Column(name = "enterprise_id",length = 32)
    private String enterpriseId;

    /**
     * 附件
     */
    @Transient
    private String attachmentId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public Date getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(Date buildTime) {
        this.buildTime = buildTime;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getIsImportPosition() {
        return isImportPosition;
    }

    public void setIsImportPosition(String isImportPosition) {
        this.isImportPosition = isImportPosition;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getDustType() {
        return dustType;
    }

    public void setDustType(String dustType) {
        this.dustType = dustType;
    }

    public String getSulfurType() {
        return sulfurType;
    }

    public void setSulfurType(String sulfurType) {
        this.sulfurType = sulfurType;
    }

    public String getSaltpetreType() {
        return saltpetreType;
    }

    public void setSaltpetreType(String saltpetreType) {
        this.saltpetreType = saltpetreType;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}