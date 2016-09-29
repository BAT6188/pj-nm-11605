package com.harmonywisdom.dshbcbp.composite.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 一级网格
 */
@Entity
@Table(name = "HW_BLOCK_FIRST")
public class BlockFirst implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    private String id;
    /**
     *网格级别id
     */
    @Column(name = "BLOCK_LEVEL_ID",length = 100)
    private String blockLevelId;
    /**
     *单位名称
     */
    @Column(name = "ORG_NAME",length = 100)
    private String orgName;
    /**
     *单位地址
     */
    @Column(name = "ORG_ADDRESS",length = 100)
    private String orgAddress;
    /**
     *管辖区域描述
     */
    @Column(name = "AREA_DESC",length = 100)
    private String areaDesc;
    /**
     *管辖区域标绘
     */
    @Column(name = "AREA_POINTS",length = 100)
    private String areaPoints;
    /**
     *网格负责人
     */
    @Column(name = "PRINCIPAL",length = 100)
    private String principal;
    /**
     *联系方式
     */
    @Column(name = "PRINCIPAL_PHONE",length = 100)
    private String principalPhone;
    /**
     *职务
     */
    @Column(name = "POSITION",length = 100)
    private String position;
    /**
     *附件Id
     */
    @Transient
    private String attachmentId;
    /**
     *上级网格id
     */
    @Column(name = "parent_Block_Id",length = 100)
    private String parentBlockId;
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

    public String getBlockLevelId() {
        return blockLevelId;
    }

    public void setBlockLevelId(String blockLevelId) {
        this.blockLevelId = blockLevelId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
    }

    public String getAreaDesc() {
        return areaDesc;
    }

    public void setAreaDesc(String areaDesc) {
        this.areaDesc = areaDesc;
    }

    public String getAreaPoints() {
        return areaPoints;
    }

    public void setAreaPoints(String areaPoints) {
        this.areaPoints = areaPoints;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getParentBlockId() {
        return parentBlockId;
    }

    public void setParentBlockId(String parentBlockId) {
        this.parentBlockId = parentBlockId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}