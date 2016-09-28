package com.harmonywisdom.dshbcbp.production.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 其他生产设备
 */
@Entity
@Table(name = "HW_DSHBCBP_OTHER_EQUIPMENT")
public class OtherEquipment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    private String id;

    /**
     * 设备名称
     */
    @Column(name = "name",length = 100)
    private String name;

    /**
     * 设备编码
     */
    @Column(name = "code",length = 100)
    private String code;

    /**
     * 设备型号
     */
    @Column(name = "model",length = 100)
    private String model;

    /**
     * 状态
     * 0:停用
     * 1:在用
     */
    @Column(name = "status",length = 100)
    private String status;

    /**
     * 数量
     */
    @Column(name = "quantity",length = 100)
    private String quantity;

    /**
     * 计量单位
     */
    @Column(name = "unit",length = 100)
    private String unit;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
}