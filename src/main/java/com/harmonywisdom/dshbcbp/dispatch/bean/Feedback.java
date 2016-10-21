package com.harmonywisdom.dshbcbp.dispatch.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 环保站执法反馈表
 */
@Entity
@Table(name = "T_FEEDBACK")
public class Feedback implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    private String id;

    /**
     * hw_dispath_task 表id
     */
    @Column(name = "dispath_id", length = 32)
    private String dispathId;

    /**
     * 现场执法人
     */
    @Column(name = "lawer_id", length = 32)
    private String lawerId;
    /**
     * 现场执法人姓名
     */
    @Column(name = "lawer_name", length = 20)
    private String lawerName;

    /**
     * 联系方式
     */
    @Column(name = "phone", length = 20)
    private String phone;

    /**
     * 执法时间
     */
    @Column(name = "exe_time")
    private Date exeTime;
    /**
     * 执法情况,执法详情
     */
    @Column(name = "exe_desc")
    private String exeDesc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLawerId() {
        return lawerId;
    }

    public void setLawerId(String lawerId) {
        this.lawerId = lawerId;
    }

    public String getLawerName() {
        return lawerName;
    }

    public void setLawerName(String lawerName) {
        this.lawerName = lawerName;
    }

    public Date getExeTime() {
        return exeTime;
    }

    public void setExeTime(Date exeTime) {
        this.exeTime = exeTime;
    }

    public String getExeDesc() {
        return exeDesc;
    }

    public void setExeDesc(String exeDesc) {
        this.exeDesc = exeDesc;
    }

    public String getDispathId() {
        return dispathId;
    }

    public void setDispathId(String dispathId) {
        this.dispathId = dispathId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}