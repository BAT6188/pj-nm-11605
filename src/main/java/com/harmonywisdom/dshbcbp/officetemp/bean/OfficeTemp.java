package com.harmonywisdom.dshbcbp.officetemp.bean;

import javax.persistence.*;
import java.io.Serializable;

/**
 * office模板类
 * Created by sunzuoquan on 15-7-20.
 */
@Entity
@Table(name = "HW_OFFICE_TEMPLATE")
public class OfficeTemp implements Serializable {
    @Id
    @Column(name = "ID",length = 36)
    private String id;

    /**
     * 名称
     */
    @Column(name = "NAME",length = 256)
    private String name;

    /**
     * 存储路径
     */
    @Column(name = "FILE_PATH",length = 256)
    private String filePath;

    /**
     * 模板文件名
     */
    @Column(name = "FILE_NAME",length = 256)
    private String fileName;

    /**
     * 模板数据路径 json格式
     */
    @Column(name = "DATA_FILE_NAME",length = 256)
    private String dataFileName;

    /**
     * 附件ID
     */
    @Column(name = "attachment_Id",length = 256)
    private String attachmentId;

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

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDataFileName() {
        return dataFileName;
    }

    public void setDataFileName(String dataFileName) {
        this.dataFileName = dataFileName;
    }

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }
}
