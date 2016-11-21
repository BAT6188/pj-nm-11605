package com.harmonywisdom.dshbcbp.enterprise.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 企业其他环境信息
 */
@Entity
@Table(name = "HW_OTHER_ENV")
public class OtherEnv implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    private String id;

    /**
     * 环境制度建设情况
     */
    @Column(name = "env_build_desc")
    private String envBuildDesc;
    /**
     * 是否通过ISO管理体系认证
     */
    @Column(name = "is_iso", length = 2)
    private String isISO;
    /**
     * 其他环境信息
     */
    @Column(name = "other_info")
    private String otherInfo;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
    /**
     * 附件
     */
    @Transient
    private String attachmentId;
    /**
     * 企业id
     */
    @Column(name = "enterprise_id", length = 32)
    private String enterpriseId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnvBuildDesc() {
        return envBuildDesc;
    }

    public void setEnvBuildDesc(String envBuildDesc) {
        this.envBuildDesc = envBuildDesc;
    }

    public String getIsISO() {
        return isISO;
    }

    public void setIsISO(String isISO) {
        this.isISO = isISO;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
}