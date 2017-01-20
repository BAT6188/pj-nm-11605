package com.harmonywisdom.dshbcbp.alert.bean;

import java.io.Serializable;

/**
 * Desc：基本用户信息
 * User: ZhouJing
 * Date: 2017/1/17 15:30
 */
public class User implements Serializable {

    /**
     * 用户账号
     */
    private String userId;
    /**
     * 用户昵称
     */
    private String userName;
    /**
     * 用户类型
     */
    private String userType;
    /**
     * 用户状态
     */
    private String userStat;
    /**
     * 用户上级ID
     */
    private String personBaseId;
    /**
     * 登录用户ID
     */
    private String personId;
    /**
     * 组织机构ID
     */
    private String orgId;
    /**
     * 组织机构名称
     */
    private String orgName;
    /**
     * 用户电话
     */
    private String mobile;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserStat() {
        return userStat;
    }

    public void setUserStat(String userStat) {
        this.userStat = userStat;
    }

    public String getPersonBaseId() {
        return personBaseId;
    }

    public void setPersonBaseId(String personBaseId) {
        this.personBaseId = personBaseId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
