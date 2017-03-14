package com.harmonywisdom.dshbcbp.composite.service;

import com.harmonywisdom.dshbcbp.composite.bean.ProjectEIA;
import com.harmonywisdom.framework.service.IBaseService;

import java.util.Date;
import java.util.Map;

public interface ProjectEIAService extends IBaseService<ProjectEIA, String> {

    /**
     * highchart
     * 环评验收获取后台数据
     * @param startdate
     * @param lastdate
     * @param enterpriseId
     * @return
     */
    Map<Object,String[]> findByRatio(String startdate, String lastdate, String enterpriseId);

    ProjectEIA findByBuildProjectId(String buildProjectId);

    void deleteProjectEIABuildProjectId(String projectId);

    void updateBuildProject(Date replyTime,Date replyEIATime, String projectId);
}