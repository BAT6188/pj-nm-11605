package com.harmonywisdom.dshbcbp.alert.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 系统操作日志
 */
@Entity
@Table(name = "HW_DSHBCBP_SYS_OPERATION_LOG")
public class SysOperationLog implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    private String id;

    /**
     * 操作时间
     */
    @Column(name = "op_time")
    private Date opTime;

    /**
     * 操作人ID
     */
    @Column(name = "op_user_id",length = 64)
    private String opUserId;

    /**
     * 操作人名称
     */
    @Column(name = "op_user",length = 100)
    private String opUser;

    /**
     * 操作类型
     */
    @Column(name = "op_type",length = 10)
    private String opType;

    /**
     * 操作模块
     */
    @Column(name = "op_module",length = 100)
    private String opModule;

    /**
     * 操作内容
     */
    @Column(name = "op_content",length = 1024)
    private String opContent;

    /**
     * 操作关联表名
     */
    @Column(name = "ref_table_name",length = 100)
    private String refTableName;

    /**
     * 关联表主键ID
     */
    @Column(name = "ref_table_id",length = 64)
    private String refTableId;

    /**
     * 关联URL
     */
    @Column(name = "ref_url",length = 512)
    private String refUrl;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getOpTime() {
        return opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    public String getOpUserId() {
        return opUserId;
    }

    public void setOpUserId(String opUserId) {
        this.opUserId = opUserId;
    }

    public String getOpUser() {
        return opUser;
    }

    public void setOpUser(String opUser) {
        this.opUser = opUser;
    }

    public String getOpType() {
        return opType;
    }

    public void setOpType(String opType) {
        this.opType = opType;
    }

    public String getOpModule() {
        return opModule;
    }

    public void setOpModule(String opModule) {
        this.opModule = opModule;
    }

    public String getOpContent() {
        return opContent;
    }

    public void setOpContent(String opContent) {
        this.opContent = opContent;
    }

    public String getRefTableName() {
        return refTableName;
    }

    public void setRefTableName(String refTableName) {
        this.refTableName = refTableName;
    }

    public String getRefTableId() {
        return refTableId;
    }

    public void setRefTableId(String refTableId) {
        this.refTableId = refTableId;
    }

    public String getRefUrl() {
        return refUrl;
    }

    public void setRefUrl(String refUrl) {
        this.refUrl = refUrl;
    }
}