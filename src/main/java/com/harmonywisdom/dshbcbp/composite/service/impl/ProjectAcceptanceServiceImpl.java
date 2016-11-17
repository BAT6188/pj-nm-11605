package com.harmonywisdom.dshbcbp.composite.service.impl;

import com.harmonywisdom.dshbcbp.composite.bean.BuildProject;
import com.harmonywisdom.dshbcbp.composite.bean.ProjectAcceptance;
import com.harmonywisdom.dshbcbp.composite.dao.ProjectAcceptanceDAO;
import com.harmonywisdom.dshbcbp.composite.service.BuildProjectService;
import com.harmonywisdom.dshbcbp.composite.service.ProjectAcceptanceService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("projectAcceptanceService")
public class ProjectAcceptanceServiceImpl extends BaseService<ProjectAcceptance, String> implements ProjectAcceptanceService {
    @Autowired
    private ProjectAcceptanceDAO projectAcceptanceDAO;
    @Autowired
    private BuildProjectService buildProjectService;
    @Override
    protected BaseDAO<ProjectAcceptance, String> getDAO() {
        return projectAcceptanceDAO;
    }

    @Override
    public ProjectAcceptance findByBuildProjectId(String buildProjectId) {
        BuildProject project=buildProjectService.findById(buildProjectId);
        List<ProjectAcceptance> projectAcceptance=getDAO().find("projectId=?1",buildProjectId);
        if (projectAcceptance != null && projectAcceptance.size() > 0) {
            ProjectAcceptance acceptance = projectAcceptance.get(0);
            acceptance.setBuildProject(project);
            return acceptance;
        }
        return null;
    }

    @Override
    public void deleteAcceptanceBuildProjectId(String projectId) {
        projectAcceptanceDAO.executeJPQL("delete from ProjectAcceptance entity where entity.projectId in ?1",projectId);

    }

    @Override
    public void updateBuildProject(Date acceptTime, String acceptOrg,Date replyAccTime,String projectId) {
        buildProjectService.executeJPQL("update BuildProject set acceptTime=?,acceptOrg=?,replyTime=? where id=?",acceptTime,acceptOrg,replyAccTime,projectId);
    }
}