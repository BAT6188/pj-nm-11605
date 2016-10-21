package com.harmonywisdom.dshbcbp.composite.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 农村生态环境
 */
@Entity
@Table(name = "HW_VILLAGE_ENV")
public class VillageEnv implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    private String id;
    /**
     *乡镇名称
     */
    @Column(name = "NAME",length = 100)
    private String name;
    /**
     *所属网格id
     */
    @Column(name = "BLOCK_ID",length = 100)
    private String blockId;
    /**
     *所属网格名称
     */
    @Column(name = "BLOCK_NAME",length = 100)
    private String blockName;
    /**
     *网格负责人
     */
    @Column(name = "PRINCIPAL",length = 100)
    private String principal;
    /**
     *负责人联系方式
     */
    @Column(name = "PRINCIPAL_PHONE",length = 100)
    private String principalPhone;
    /**
     *位置坐标
     */
    @Column(name = "POINTS",length = 100)
    private String points;
    /**
     *环境详情
     */
    @Column(name = "DESCRIPTION",length = 500)
    private String description;
    /**
     *附件
     */
    @Transient
    private String attachmentIds;
    /**
     *createTime
     */
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     *所属区域
     */
    @Column(name = "ADDRESS")
    private Date address;

    public Date getAddress() {
        return address;
    }

    public void setAddress(Date address) {
        this.address = address;
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

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getPrincipalPhone() {
        return principalPhone;
    }

    public void setPrincipalPhone(String principalPhone) {
        this.principalPhone = principalPhone;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAttachmentIds() {
        return attachmentIds;
    }

    public void setAttachmentIds(String attachmentIds) {
        this.attachmentIds = attachmentIds;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}