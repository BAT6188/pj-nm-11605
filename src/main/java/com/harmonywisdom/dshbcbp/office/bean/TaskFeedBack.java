package com.harmonywisdom.dshbcbp.office.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "HW_TASK_FEED_BACK")
public class TaskFeedBack implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String REVIEW_STATUS_NOPUB = "0";
    public static final String REVIEW_STATUS_NEEDCHECK = "1";
    public static final String REVIEW_STATUS_PASS = "2";
    public static final String REVIEW_STATUS_NOPASS = "3";

    @Id
    @Column(length = 32)
    private String id;

    /**
     * 关联任务ID
     */
    @Column(name = "task_id",length = 32)
    private String taskId;

    /**
     * 任务进展情况
     */
    @Column(name = "feedback_content",columnDefinition = "mediumtext")
    private String feedbackContent;

    /**
     * 反馈时间
     */
    @Column(name = "feedback_time")
    private Date feedbackTime;

    /**
     * 反馈人ID
     */
    @Column(name = "feedbacker_id",length = 32)
    private String feedbackerId;

    /**
     * 反馈人
     */
    @Column(name = "feedbacker",length = 128)
    private String feedbacker;

    /**
     * 审核状态(0：未提交审核，1：已提交审核，2：审核通过，3：审核未通过)
     */
    @Column(name = "review_status",length = 2)
    private String reviewStatus;

    /**
     * 审核意见
     */
    @Column(name = "review_opinion",columnDefinition = "mediumtext")
    private String reviewOpinion;

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

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }

    public Date getFeedbackTime() {
        return feedbackTime;
    }

    public void setFeedbackTime(Date feedbackTime) {
        this.feedbackTime = feedbackTime;
    }

    public String getFeedbackerId() {
        return feedbackerId;
    }

    public void setFeedbackerId(String feedbackerId) {
        this.feedbackerId = feedbackerId;
    }

    public String getFeedbacker() {
        return feedbacker;
    }

    public void setFeedbacker(String feedbacker) {
        this.feedbacker = feedbacker;
    }

    public String getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public String getReviewOpinion() {
        return reviewOpinion;
    }

    public void setReviewOpinion(String reviewOpinion) {
        this.reviewOpinion = reviewOpinion;
    }

    public String getAttachmentIds() {
        return attachmentIds;
    }

    public void setAttachmentIds(String attachmentIds) {
        this.attachmentIds = attachmentIds;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}