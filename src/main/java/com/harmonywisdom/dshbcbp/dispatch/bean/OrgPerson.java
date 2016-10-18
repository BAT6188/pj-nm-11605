package com.harmonywisdom.dshbcbp.dispatch.bean;

import com.harmonywisdom.apportal.sdk.person.domain.Person;

import java.util.List;

/**
 * Created by Administrator on 2016/10/18.
 * 组织机构和人员实体（发送 选择人员）
 */
public class OrgPerson {
    private String id;
    private String name;
    private String job;
    private boolean isParent;

    private List<OrgPerson> children;

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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public boolean isParent() {
        return isParent;
    }

    public void setParent(boolean parent) {
        isParent = parent;
    }

    public List<OrgPerson> getChildren() {
        return children;
    }

    public void setChildren(List<OrgPerson> children) {
        this.children = children;
    }
}
