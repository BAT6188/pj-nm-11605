package com.harmonywisdom.dshbcbp.composite.service;

import com.harmonywisdom.dshbcbp.composite.bean.BuildProject;
import com.harmonywisdom.dshbcbp.composite.bean.ProjectAcceptance;
import com.harmonywisdom.dshbcbp.composite.bean.ProjectEIA;
import com.harmonywisdom.framework.service.IBaseService;

import java.util.List;

public interface BuildProjectService extends IBaseService<BuildProject, String> {

    List<BuildProject> getAll();

    List<BuildProject> findByName(String name);

    List<ProjectEIA> findEIAById(String projectId);
    List<ProjectAcceptance> findAcceptanceById(String projectId);

}