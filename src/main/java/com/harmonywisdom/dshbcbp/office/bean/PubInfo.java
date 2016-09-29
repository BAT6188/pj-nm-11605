package com.harmonywisdom.dshbcbp.office.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *信息公告
 */
@Entity
@Table(name = "HW_PUB_INFO")
public class PubInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    private String id;
    /**
     *标题
     */
    @Column(name = "TITLE",length = 100)
    private String title;
    /**
     *发布时间
     */
    @Column(name = "PUB_TIME")
    private Date pubTime;
    /**
     *发布单位id
     */
    @Column(name = "PUB_ORG_ID",length = 100)
    private String pubOrgId;
    /**
     *发布单位名称
     */
    @Column(name = "PUB_ORG_NAME",length = 100)
    private String pubOrgName;
    /**
     *公告类型
     */
    @Column(name = "TYPE",length = 100)
    private String type;
    /**
     * 查看权限:
     * 1:环保局
     * 2:局机关
     * 3:监查大队
     * 4:监测站
     * 5:企业
      */
    @Column(name = "GRADE",length = 100)
    private String grade;
    /**
     *公告详情
     */
    @Column(name = "CONTENT",length = 255)
    private String content;
    /**
     *附件
     */
    @Transient
    private String attachmentId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPubTime() {
        return pubTime;
    }

    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
    }

    public String getPubOrgId() {
        return pubOrgId;
    }

    public void setPubOrgId(String pubOrgId) {
        this.pubOrgId = pubOrgId;
    }

    public String getPubOrgName() {
        return pubOrgName;
    }

    public void setPubOrgName(String pubOrgName) {
        this.pubOrgName = pubOrgName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }
}