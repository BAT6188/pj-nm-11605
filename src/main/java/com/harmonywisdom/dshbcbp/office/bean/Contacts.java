package com.harmonywisdom.dshbcbp.office.bean;

import javax.persistence.*;
import java.io.Serializable;

/**
 *人员管理
 */
@Entity
@Table(name = "HW_CONTACTS")
public class Contacts implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(length = 32)
    private String id;
    /**
     *姓名
     */
    @Column(name = "NAME",length = 100)
    private String name;
    /**
     *部门
     */
    @Column(name = "DEPARTMENT",length = 100)
    private String department;
    /**
     *职务
     */
    @Column(name = "POSITION",length = 100)
    private String position;
    /**
     *单位地址
     */
    @Column(name = "ADDRESS",length = 100)
    private String address;
    /**
     *座机号码
     */
    @Column(name = "TEL",length = 100)
    private String tel;
    /**
     *手机号码
     */
    @Column(name = "PHONE",length = 100)
    private String phone;
    /**
     *所属组织机构id
     */
    @Column(name = "ORG_ID",length = 100)
    private String orgId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getAttachmentIds() {
        return attachmentIds;
    }

    public void setAttachmentIds(String attachmentIds) {
        this.attachmentIds = attachmentIds;
    }
}