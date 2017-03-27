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

    public static final String TASK_TYPE_BIG = "0";
    public static final String TASK_TYPE_MIDDLE = "1";
    public static final String TASK_TYPE_LITTLE = "2";

    public static final String TASK_STATUS_NOPUB = "0";
    public static final String TASK_STATUS_NODONE = "1";
    public static final String TASK_STATUS_DONE = "2";
    public static final String TASK_STATUS_OVER = "3";

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
     * 调度责任领导ID
     */
    @Column(name = "dispatch_duty_leader_id",length = 128)
    private String dispatchDutyLeaderId;

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
     * 任务发布人
     */
    @Column(name = "task_pub_user_id")
    private String taskPubUserId;

    /**
     * 任务创建单位code
     */
    @Column(name = "task_create_department_code")
    private String taskCreateDepartmentCode;

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
     * 审核状态(0：未提交审核，1：已提交审核，2：审核通过，3：审核未通过)
     */
    @Column(name = "review_status",length = 2)
    private String reviewStatus;

    /**
     * 任务接收人
     */
    @Column(name = "task_recipient",columnDefinition = "mediumtext")
    private String taskRecipient;

    /**
     * 提醒频次
     */
    @Column(name = "warn_frequency",length = 8)
    private Integer warnFrequency;

    /**
     * 提醒状态(0:不需要提醒，1：需要提醒)
     */
    @Column(name = "warn_status",length = 2)
    private String warnStatus;

    /**
     * 提醒时间
     */
    @Column(name = "warn_time")
    private Date warnTime;

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

    public String getParentTaskName() {
        return parentTaskName;
    }

    public void setParentTaskName(String parentTaskName) {
        this.parentTaskName = parentTaskName;
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

    public String getDispatchDutyLeaderId() {
        return dispatchDutyLeaderId;
    }

    public void setDispatchDutyLeaderId(String dispatchDutyLeaderId) {
        this.dispatchDutyLeaderId = dispatchDutyLeaderId;
    }

    public String getDispatchDutyLeader() {
        return dispatchDutyLeader;
    }

    public void setDispatchDutyLeader(String dispatchDutyLeader) {
        this.dispatchDutyLeader = dispatchDutyLeader;
    }

    public String getDispatchDutyDepartmentCode() {
        return dispatchDutyDepartmentCode;
    }

    public void setDispatchDutyDepartmentCode(String dispatchDutyDepartmentCode) {
        this.dispatchDutyDepartmentCode = dispatchDutyDepartmentCode;
    }

    public String getDispatchDutyDepartment() {
        return dispatchDutyDepartment;
    }

    public void setDispatchDutyDepartment(String dispatchDutyDepartment) {
        this.dispatchDutyDepartment = dispatchDutyDepartment;
    }

    public Date getTaskDeadline() {
        return taskDeadline;
    }

    public void setTaskDeadline(Date taskDeadline) {
        this.taskDeadline = taskDeadline;
    }

    public String getTaskCreatorId() {
        return taskCreatorId;
    }

    public void setTaskCreatorId(String taskCreatorId) {
        this.taskCreatorId = taskCreatorId;
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

    public Date getTaskPubTime() {
        return taskPubTime;
    }

    public void setTaskPubTime(Date taskPubTime) {
        this.taskPubTime = taskPubTime;
    }

    public String getTaskPubUserId() {
        return taskPubUserId;
    }

    public void setTaskPubUserId(String taskPubUserId) {
        this.taskPubUserId = taskPubUserId;
    }

    public String getTaskCreateDepartmentCode() {
        return taskCreateDepartmentCode;
    }

    public void setTaskCreateDepartmentCode(String taskCreateDepartmentCode) {
        this.taskCreateDepartmentCode = taskCreateDepartmentCode;
    }

    public String getTaskCreateDepartment() {
        return taskCreateDepartment;
    }

    public void setTaskCreateDepartment(String taskCreateDepartment) {
        this.taskCreateDepartment = taskCreateDepartment;
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

    public String getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public String getTaskRecipient() {
        return taskRecipient;
    }

    public void setTaskRecipient(String taskRecipient) {
        this.taskRecipient = taskRecipient;
    }

    public Integer getWarnFrequency() {
        return warnFrequency;
    }

    public void setWarnFrequency(Integer warnFrequency) {
        this.warnFrequency = warnFrequency;
    }

    public String getWarnStatus() {
        return warnStatus;
    }

    public void setWarnStatus(String warnStatus) {
        this.warnStatus = warnStatus;
    }

    public Date getWarnTime() {
        return warnTime;
    }

    public void setWarnTime(Date warnTime) {
        this.warnTime = warnTime;
    }

    public Date getMobileTimestamp() {
        return mobileTimestamp;
    }

    public void setMobileTimestamp(Date mobileTimestamp) {
        this.mobileTimestamp = mobileTimestamp;
    }

    public String getIsHaveChild() {
        return isHaveChild;
    }

    public void setIsHaveChild(String isHaveChild) {
        this.isHaveChild = isHaveChild;
    }

    public String getAttachmentIds() {
        return attachmentIds;
    }

    public void setAttachmentIds(String attachmentIds) {
        this.attachmentIds = attachmentIds;
    }
}