package com.harmonywisdom.dshbcbp.composite.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 四级网格
 */
@Entity
@Table(name = "HW_BLOCK")
public class Block implements Serializable {
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
     *网格级别名称
     */
    @Column(name = "BLOCK_LEVEL_NAME",length = 100)
    private String blockLevelName;
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
    @Column(name = "AREA_POINTS")
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
     *下级网格id
     */
//    @Column(name = "CHILD_BLOCK_ID",length = 100)
    @Transient
    private String childBlockId;

    /**
     *createTime
     */
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * 网格长
     */
    @Column(name = "BLOCK_LEADER")
    private String  blockLeader;
    /**
     * 网格长联系方式
     */
    @Column(name = "block_Leader_Tel")
    private String  blockLeaderTel;

    /**
     *环保负责人
     */
    @Column(name="ENVIRONMENTAL_LEADER")
    private String environmentalLeader;
    /**
     * 联系电话
     */
    @Column(name = "ENVIRONMENTAL_PHONE")
    private String environmentalPhone;
    /**
     * 网格职责
     */
    @Column(name="BLOCK_DUTY",length=1000)
    private String blockDuty;
    /**
     *附件
     */
    @Transient
    private String attachmentIds;

    @Transient
    private List<Block> children;

    public Block(String id, String name) {
        this.id=id;
        this.orgName=name;
    }
    public Block(){}


    public List<Block> getChildren() {
        return children;
    }

    public void setChildren(List<Block> children) {
        this.children = children;
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

    public String getBlockDuty() {
        return blockDuty;
    }

    public void setBlockDuty(String blockDuty) {
        this.blockDuty = blockDuty;
    }

    public String getEnvironmentalLeader() {
        return environmentalLeader;
    }

    public void setEnvironmentalLeader(String environmentalLeader) {
        this.environmentalLeader = environmentalLeader;
    }

    public String getEnvironmentalPhone() {
        return environmentalPhone;
    }

    public void setEnvironmentalPhone(String environmentalPhone) {
        this.environmentalPhone = environmentalPhone;
    }

    public String getChildBlockId() {
        return childBlockId;
    }

    public void setChildBlockId(String childBlockId) {
        this.childBlockId = childBlockId;
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

    public String getBlockLevelName() {
        return blockLevelName;
    }

    public void setBlockLevelName(String blockLevelName) {
        this.blockLevelName = blockLevelName;
    }

    public String getBlockLeader() {
        return blockLeader;
    }

    public void setBlockLeader(String blockLeader) {
        this.blockLeader = blockLeader;
    }

    public String getBlockLeaderTel() {
        return blockLeaderTel;
    }

    public void setBlockLeaderTel(String blockLeaderTel) {
        this.blockLeaderTel = blockLeaderTel;
    }
}