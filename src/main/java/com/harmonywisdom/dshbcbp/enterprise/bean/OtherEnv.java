package com.harmonywisdom.dshbcbp.enterprise.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

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
    private String EnvBuildDesc;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnvBuildDesc() {
        return EnvBuildDesc;
    }

    public void setEnvBuildDesc(String envBuildDesc) {
        EnvBuildDesc = envBuildDesc;
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

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    private String enterpriseId;
}