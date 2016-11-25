package com.harmonywisdom.dshbcbp.composite.service;

import com.harmonywisdom.dshbcbp.composite.bean.BuildProject;
import com.harmonywisdom.dshbcbp.composite.bean.ProjectAcceptance;
import com.harmonywisdom.dshbcbp.composite.bean.ProjectEIA;
import com.harmonywisdom.framework.dao.Paging;
import com.harmonywisdom.framework.dao.QueryResult;
import com.harmonywisdom.framework.service.IBaseService;

import java.util.List;
import java.util.Map;

public interface BuildProjectService extends IBaseService<BuildProject, String> {

    List<BuildProject> getAll();

    List<BuildProject> findByName(String name);

    List<ProjectEIA> findEIAById(String projectId);
    List<ProjectAcceptance> findAcceptanceById(String projectId);

    /**
     * 建项环评验收查询建设项目环评及验收信息表
     * @param params
     * @param paging
     * @return
     */
    QueryResult<BuildProject> findBulidProList(Map<String, String> params, Paging paging);
}