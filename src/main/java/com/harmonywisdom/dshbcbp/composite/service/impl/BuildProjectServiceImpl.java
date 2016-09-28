package com.harmonywisdom.dshbcbp.composite.service.impl;

import com.harmonywisdom.dshbcbp.composite.bean.BuildProject;
import com.harmonywisdom.dshbcbp.composite.dao.BuildProjectDAO;
import com.harmonywisdom.dshbcbp.composite.service.BuildProjectService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("buildProjectService")
public class BuildProjectServiceImpl extends BaseService<BuildProject, String> implements BuildProjectService {
    @Autowired
    private BuildProjectDAO buildProjectDAO;

    @Override
    protected BaseDAO<BuildProject, String> getDAO() {
        return buildProjectDAO;
    }
}