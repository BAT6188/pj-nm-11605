package com.harmonywisdom.dshbcbp.dispatch.bean;

import javax.persistence.*;
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
     * hw_dispatch_task 表id
     */
    @Column(name = "dispatch_id", length = 32)
    private String dispatchId;

    /**
     * 事件原因
     */
    @Transient
    private String caseReason;


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

    @Transient
    private String attachmentIds;

    public String getAttachmentIds() {
        return attachmentIds;
    }

    public void setAttachmentIds(String attachmentIds) {
        this.attachmentIds = attachmentIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDispatchId() {
        return dispatchId;
    }

    public void setDispatchId(String dispathId) {
        this.dispatchId = dispathId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCaseReason() {
        return caseReason;
    }

    public void setCaseReason(String caseReason) {
        this.caseReason = caseReason;
    }
}