package com.harmonywisdom.dshbcbp.composite.service;

import com.harmonywisdom.dshbcbp.composite.bean.ProjectAcceptance;
import com.harmonywisdom.framework.service.IBaseService;

public interface ProjectAcceptanceService extends IBaseService<ProjectAcceptance, String> {

    //验收查询
    ProjectAcceptance findByBuildProjectId(String buildProjectId);
    void deleteAcceptanceBuildProjectId(String projectId);
}