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
}
