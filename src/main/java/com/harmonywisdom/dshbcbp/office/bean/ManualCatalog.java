package com.harmonywisdom.dshbcbp.office.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 环保手册目录
 */
@Entity
@Table(name = "HW_MANUAL_CATALOG")
public class ManualCatalog implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    private String id;
    /**
     *父目录id
     */
    @Column(name = "MANUAL_ID",length = 100)
    private String manualId ;
    /**
     *名称
     */
    @Column(name = "name",length = 100)
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getManualId() {
        return manualId;
    }

    public void setManualId(String manualId) {
        this.manualId = manualId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}