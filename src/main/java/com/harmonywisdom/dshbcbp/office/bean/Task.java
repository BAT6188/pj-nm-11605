package com.harmonywisdom.dshbcbp.office.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 任务管理
 */
@Entity
@Table(name = "HW_TASK")
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    private String id;

    /**
     * 任务类型(0:大任务，1：中任务,2小任务)
     */
    @Column(name = "task_type",length = 1)
    private String taskType;

    /**
     * 父任务ID
     */
    @Column(name = "parent_task_id",length = 32)
    private String parentTaskId;

    /**
     * 父任务名称
     */
    @Column(name = "parent_task_name")
    private String parentTaskName;

    /**
     * 任务名称
     */
    @Column(name = "task_name",length = 1024)
    private String taskName;

    /**
     * 任务内容
     */
    @Column(name = "task_content",columnDefinition = "mediumtext")
    private String taskContent;

    /**
     * 任务备注
     */
    @Column(name = "task_remark",columnDefinition = "mediumtext")
    private String taskRemark;

    /**
     * 责任领导
     */
    @Column(name = "duty_leader",length = 128)
    private String dutyLeader;

    /**
     * 责任部门
     */
    @Column(name = "duty_department",length = 128)
    private String dutyDepartment;

    /**
     * 调度责任领导
     */
    @Column(name = "dispatch_duty_leader",length = 128)
    private String dispatchDutyLeader;

    /**
     * 调度责任部门ORG_CODE
     */
    @Column(name = "dispatch_department_code",length = 128)
    private String dispatchDutyDepartmentCode;

    /**
     * 调度责任部门
     */
    @Column(name = "dispatch_department",length = 128)
    private String dispatchDutyDepartment;

    /**
     * 任务截止时间
     */
    @Column(name = "task_deadline")
    private Date taskDeadline;

    /**
     * 任务创建人ID
     */
    @Column(name = "task_creator_id",length = 32)
    private String taskCreatorId;

    /**
     * 任务创建人
     */
    @Column(name = "task_creator",length = 128)
    private String taskCreator;

    /**
     * 任务创建时间
     */
    @Column(name = "task_create_time")
    private Date taskCreateTime;

    /**
     * 任务发布时间
     */
    @Column(name = "task_pub_time")
    private Date taskPubTime;

    /**
     * 任务创建单位
     */
    @Column(name = "task_create_department")
    private String taskCreateDepartment;

    /**
     * 任务状态(0:初态,未发布;1:未完成；2：已完成；3：已办结)
     */
    @Column(name = "task_status",length = 2)
    private String taskStatus;

    /**
     * 任务接收人ID
     */
    @Column(name = "task_recipientId",length = 32)
    private String taskRecipientId;

    /**
     * 审核状态
     */
    @Column(name = "review_status",length = 2)
    private String reviewStatus;

    /**
     * 任务接收人
     */
    @Column(name = "task_recipient",columnDefinition = "mediumtext")
    private String taskRecipient;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "mobile_timestamp",columnDefinition = "CURRENT_TIMESTAMP")
    private Date mobileTimestamp;

    @Column(name = "is_have_child",length = 2)
    private String isHaveChild;

    /**
     *附件
     */
    @Transient
    private String attachmentIds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getParentTaskId() {
        return parentTaskId;
    }

    public void setParentTaskId(String parentTaskId) {
        this.parentTaskId = parentTaskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    public String getTaskRemark() {
        return taskRemark;
    }

    public void setTaskRemark(String taskRemark) {
        this.taskRemark = taskRemark;
    }

    public Date getTaskDeadline() {
        return taskDeadline;
    }

    public void setTaskDeadline(Date taskDeadline) {
        this.taskDeadline = taskDeadline;
    }

    public String getTaskCreator() {
        return taskCreator;
    }

    public void setTaskCreator(String taskCreator) {
        this.taskCreator = taskCreator;
    }

    public Date getTaskCreateTime() {
        return taskCreateTime;
    }

    public void setTaskCreateTime(Date taskCreateTime) {
        this.taskCreateTime = taskCreateTime;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskRecipientId() {
        return taskRecipientId;
    }

    public void setTaskRecipientId(String taskRecipientId) {
        this.taskRecipientId = taskRecipientId;
    }

    public String getTaskRecipient() {
        return taskRecipient;
    }

    public void setTaskRecipient(String taskRecipient) {
        this.taskRecipient = taskRecipient;
    }

    public Date getMobileTimestamp() {
        return mobileTimestamp;
    }

    public void setMobileTimestamp(Date mobileTimestamp) {
        this.mobileTimestamp = mobileTimestamp;
    }

    public String getAttachmentIds() {
        return attachmentIds;
    }

    public void setAttachmentIds(String attachmentIds) {
        this.attachmentIds = attachmentIds;
    }

    public String getDutyLeader() {
        return dutyLeader;
    }

    public void setDutyLeader(String dutyLeader) {
        this.dutyLeader = dutyLeader;
    }

    public String getDutyDepartment() {
        return dutyDepartment;
    }

    public void setDutyDepartment(String dutyDepartment) {
        this.dutyDepartment = dutyDepartment;
    }

    public String getDispatchDutyLeader() {
        return dispatchDutyLeader;
    }

    public void setDispatchDutyLeader(String dispatchDutyLeader) {
        this.dispatchDutyLeader = dispatchDutyLeader;
    }

    public String getDispatchDutyDepartment() {
        return dispatchDutyDepartment;
    }

    public void setDispatchDutyDepartment(String dispatchDutyDepartment) {
        this.dispatchDutyDepartment = dispatchDutyDepartment;
    }

    public String getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public String getTaskCreateDepartment() {
        return taskCreateDepartment;
    }

    public void setTaskCreateDepartment(String taskCreateDepartment) {
        this.taskCreateDepartment = taskCreateDepartment;
    }

    public Date getTaskPubTime() {
        return taskPubTime;
    }

    public void setTaskPubTime(Date taskPubTime) {
        this.taskPubTime = taskPubTime;
    }

    public String getIsHaveChild() {
        return isHaveChild;
    }

    public void setIsHaveChild(String isHaveChild) {
        this.isHaveChild = isHaveChild;
    }

    public String getTaskCreatorId() {
        return taskCreatorId;
    }

    public void setTaskCreatorId(String taskCreatorId) {
        this.taskCreatorId = taskCreatorId;
    }

    public String getDispatchDutyDepartmentCode() {
        return dispatchDutyDepartmentCode;
    }

    public void setDispatchDutyDepartmentCode(String dispatchDutyDepartmentCode) {
        this.dispatchDutyDepartmentCode = dispatchDutyDepartmentCode;
    }

    public String getParentTaskName() {
        return parentTaskName;
    }

    public void setParentTaskName(String parentTaskName) {
        this.parentTaskName = parentTaskName;
    }
}