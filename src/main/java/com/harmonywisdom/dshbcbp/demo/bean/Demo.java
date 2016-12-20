package com.harmonywisdom.dshbcbp.demo.bean;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by hotleave on 14-9-16.
 */
@Entity
@Table(name = "T_DEMO")
public class Demo implements Serializable {
    @Id
    @Column(length = 64)
    private String id;

    @Column(unique = true, nullable = false, length = 128)
    private String name;

    @Column
    private Integer age;

    /**
     * 性格
     */
    @Column(length = 1)
    private String xg;

    /**
     * 备注
     */
    //@Lob
    @Column(name = "remark",columnDefinition = "longtext")
    private String remark;

    /**
     * x坐标
     */
    @Column
    private Double longitude;

    /**
     * y坐标
     */
    @Column
    private Double latitude;


    @Transient
    private String attachmentIds;

    /**
     * 导出word时使用
     */
    @Transient
    private String demoList;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAttachmentIds() {
        return attachmentIds;
    }

    public void setAttachmentIds(String attachmentIds) {
        this.attachmentIds = attachmentIds;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getDemoList() {
        return demoList;
    }

    public void setDemoList(String demoList) {
        this.demoList = demoList;
    }

    public String getXg() {
        return xg;
    }

    public void setXg(String xg) {
        this.xg = xg;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
