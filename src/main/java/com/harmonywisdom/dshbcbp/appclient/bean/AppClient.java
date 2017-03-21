package com.harmonywisdom.dshbcbp.appclient.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "HW_APP_CLIENT")
public class AppClient implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id",length = 32)
    private String id;

    /**
     * 版本号
     */
    @Column(name="apk_version_num",length = 32)
    private Integer apkVersionNum;

    /**
     * 编码
     */
    @Column(name="apk_version_code", length=100)
    private String apkVersionCode;

    /**
     * 名称
     */
    @Column(name="apk_name", length=100)
    private String apkName;

    /**
     * APK类型(1代表协管员, 2代表领导)
     */
    @Column(name="apk_type", length=8)
    private String apkType;

    /**
     * APK上传状态(0代表未上传, 1代表已上传)
     */
    @Column(name="upload_status", length=8)
    private String uploadStatus;

    /**
     * APK访问路径
     */
    @Column(name="apk_url", length=200)
    private String apkUrl;

    /**
     * APK文件存放位置
     */
    @Column(name="file_path", length=300)
    private String filePath;

    /**
     * APK文件大小
     */
    @Column(name="apk_size")
    private String apkSize;

    /**
     * APK二进制大对象
     */
    //@Lob
    @Basic(fetch = FetchType.LAZY, optional = true)
    @Column(name = "apk_bytes",columnDefinition = "longtext")
    private byte[] apkBytes;

    /**
     * APK更新信息
     */
    @Column(name="update_info", columnDefinition="longtext")
    private String updateInfo;

    /**
     * 备注
     */
    @Column(name="apk_desc", length=200)
    private String apkDesc;

    @Column(name="up_time")
    private Date upTime;

    @Column(name="attachment_id",length = 32)
    private String attachmentId;

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

    public Integer getApkVersionNum() {
        return apkVersionNum;
    }

    public void setApkVersionNum(Integer apkVersionNum) {
        this.apkVersionNum = apkVersionNum;
    }

    public String getApkVersionCode() {
        return apkVersionCode;
    }

    public void setApkVersionCode(String apkVersionCode) {
        this.apkVersionCode = apkVersionCode;
    }

    public String getApkName() {
        return apkName;
    }

    public void setApkName(String apkName) {
        this.apkName = apkName;
    }

    public String getApkType() {
        return apkType;
    }

    public void setApkType(String apkType) {
        this.apkType = apkType;
    }

    public String getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(String uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    public String getApkUrl() {
        return apkUrl;
    }

    public void setApkUrl(String apkUrl) {
        this.apkUrl = apkUrl;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getApkSize() {
        return apkSize;
    }

    public void setApkSize(String apkSize) {
        this.apkSize = apkSize;
    }

    public byte[] getApkBytes() {
        return apkBytes;
    }

    public void setApkBytes(byte[] apkBytes) {
        this.apkBytes = apkBytes;
    }

    public String getUpdateInfo() {
        return updateInfo;
    }

    public void setUpdateInfo(String updateInfo) {
        this.updateInfo = updateInfo;
    }

    public String getApkDesc() {
        return apkDesc;
    }

    public void setApkDesc(String apkDesc) {
        this.apkDesc = apkDesc;
    }

    public Date getUpTime() {
        return upTime;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }

    public String getAttachmentIds() {
        return attachmentIds;
    }

    public void setAttachmentIds(String attachmentIds) {
        this.attachmentIds = attachmentIds;
    }

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }
}