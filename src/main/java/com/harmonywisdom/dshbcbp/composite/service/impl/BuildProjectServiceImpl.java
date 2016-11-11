package com.harmonywisdom.dshbcbp.composite.service.impl;

import com.harmonywisdom.dshbcbp.composite.bean.BuildProject;
import com.harmonywisdom.dshbcbp.composite.bean.ProjectAcceptance;
import com.harmonywisdom.dshbcbp.composite.bean.ProjectEIA;
import com.harmonywisdom.dshbcbp.composite.dao.BuildProjectDAO;
import com.harmonywisdom.dshbcbp.composite.service.BuildProjectService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("buildProjectService")
public class BuildProjectServiceImpl extends BaseService<BuildProject, String> implements BuildProjectService {
    @Autowired
    private BuildProjectDAO buildProjectDAO;

    @Override
    protected BaseDAO<BuildProject, String> getDAO() {
        return buildProjectDAO;
    }

    @Override
    public List<BuildProject> getAll() {
        String sql="SELECT * FROM HW_BUILD_PROJECT a INNER JOIN  HW_PROJECT_EIA t ON a.id=t.PROJECT_ID";
        List<BuildProject> priList=getDAO().queryNativeSQL(sql);
        return priList;
    }

    @Override
    public List<BuildProject> findByName(String name) {
        List<BuildProject> list=getDAO().queryJPQL("from BuildProject where  name LIKE '%" + name + "%'");
        return list;
    }

    @Override
    public List<ProjectEIA> findEIAById(String projectId) {
        List<ProjectEIA> list=getDAO().queryJPQL("from ProjectEIA where projectId=? ",projectId);
        return list;
    }

    @Override
    public List<ProjectAcceptance> findAcceptanceById(String projectId) {
        List<ProjectAcceptance> list=getDAO().queryJPQL("from ProjectAcceptance where projectId=? ",projectId);
        return list;
    }
}