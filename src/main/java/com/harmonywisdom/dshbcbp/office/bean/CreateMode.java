package com.harmonywisdom.dshbcbp.office.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.DataInput;
import java.io.Serializable;
import java.util.Date;

/**
 * 创模建设
 */
@Entity
@Table(name = "T_CREATE_MODE")
public class CreateMode implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    private String id;

    /**
     * 任务名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 上报状态
     * 0未完成，1完成
     */
    @Column(name = "status")
    private String status;


    /**
     * 发布单位
     */
    @Column(name = "publish_org_id")
    private String publishOrgId;
    @Column(name = "publish_org_name")
    private String publishOrgName;

    /**
     * 发布时间
     */
    @Column(name = "publish_time")
    private Date publishTime;

    /**
     * 上报截止时间
     */
    @Column(name = "deadline")
    private Date deadline;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    public String getPublishOrgId() {
        return publishOrgId;
    }

    public void setPublishOrgId(String publishOrgId) {
        this.publishOrgId = publishOrgId;
    }

    public String getPublishOrgName() {
        return publishOrgName;
    }

    public void setPublishOrgName(String publishOrgName) {
        this.publishOrgName = publishOrgName;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}