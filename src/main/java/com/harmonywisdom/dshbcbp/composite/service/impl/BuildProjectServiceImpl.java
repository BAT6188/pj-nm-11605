package com.harmonywisdom.dshbcbp.composite.service.impl;

import com.harmonywisdom.dshbcbp.composite.bean.BuildProject;
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
        String sql="SELECT * FROM 'HW_BUILD_PROJECT' a LEFT JOIN  'HW_PROJECT_EIA' b ON a.'id'=b.'PROJECT_ID' LEFT JOIN 'HW_PROJECT_ACCEPTANCE' c ON a.'id'= c.'PROJECT_ID'";
        List<BuildProject> priList=getDAO().queryNativeSQL(sql);
        return priList;
    }
}