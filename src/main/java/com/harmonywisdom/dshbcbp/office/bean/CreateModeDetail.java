package com.harmonywisdom.dshbcbp.office.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 创模建设详表
 */
@Entity
@Table(name = "T_CREATE_MODE_DETAIL")
public class CreateModeDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    private String id;

    @Column(name = "create_mode_id",length = 32)
    private String createModeId;

    /**
     * 上报完成状态：点上报按钮即视为完成
     * 0未完成，1完成
     * 说明：详细表的每个completeStatus状态为完成，则修改父表status为完成状态
     */
    @Column(name = "complete_status",length = 2)
    private String completeStatus;

    /**
     * 1指标任务
     * 2重点工程
     */
    @Column(length = 2)
    private String type;

    /**
     * 发布单位
     */
    @Column(name = "publish_org_id")
    private String publishOrgId;
    @Column(name = "publish_org_name")
    private String publishOrgName;

    /**
     * 责任部门
     */
    @Column(name = "responsible_department_id")
    private String responsibleDepartmentId;
    @Column(name = "responsible_department_name")
    private String responsibleDepartmentName;

    /**
     * 上报截止时间
     */
    @Column(name = "deadline")
    private Date deadline;

    /**
     * 指标内容
     */
    @Column(name = "content")
    private String content;

    @Transient
    private String attachmentIds;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateModeId() {
        return createModeId;
    }

    public void setCreateModeId(String createModeId) {
        this.createModeId = createModeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getResponsibleDepartmentId() {
        return responsibleDepartmentId;
    }

    public void setResponsibleDepartmentId(String responsibleDepartmentId) {
        this.responsibleDepartmentId = responsibleDepartmentId;
    }

    public String getResponsibleDepartmentName() {
        return responsibleDepartmentName;
    }

    public void setResponsibleDepartmentName(String responsibleDepartmentName) {
        this.responsibleDepartmentName = responsibleDepartmentName;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAttachmentIds() {
        return attachmentIds;
    }

    public void setAttachmentIds(String attachmentIds) {
        this.attachmentIds = attachmentIds;
    }

    public String getCompleteStatus() {
        return completeStatus;
    }

    public void setCompleteStatus(String completeStatus) {
        this.completeStatus = completeStatus;
    }
}