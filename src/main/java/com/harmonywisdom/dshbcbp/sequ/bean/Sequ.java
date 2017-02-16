package com.harmonywisdom.dshbcbp.sequ.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "SEQU")
public class Sequ implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 64)
    private String id;

    @Column(name = "vd_id")
    private String vd_id;

    @Column(name = "vt_id")
    private String vt_id;

    @Column(name = "lon")
    private String lon;

    @Column(name = "lat")
    private String lat;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVd_id() {
        return vd_id;
    }

    public void setVd_id(String vd_id) {
        this.vd_id = vd_id;
    }

    public String getVt_id() {
        return vt_id;
    }

    public void setVt_id(String vt_id) {
        this.vt_id = vt_id;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}