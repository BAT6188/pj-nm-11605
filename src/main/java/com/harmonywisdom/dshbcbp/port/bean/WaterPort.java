package com.harmonywisdom.dshbcbp.port.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 废水排口
 */
@Entity
@Table(name = "HW_DSHBCBP_WATER_PORT")
public class WaterPort implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    private String id;

    /**
     * 企业ID
     */
    @Column(name = "enterprise_id",length = 32)
    private String enterpriseId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
}