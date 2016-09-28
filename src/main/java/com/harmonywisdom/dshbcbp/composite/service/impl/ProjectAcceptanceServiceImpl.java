package com.harmonywisdom.dshbcbp.composite.service.impl;

import com.harmonywisdom.dshbcbp.composite.bean.ProjectAcceptance;
import com.harmonywisdom.dshbcbp.composite.dao.ProjectAcceptanceDAO;
import com.harmonywisdom.dshbcbp.composite.service.ProjectAcceptanceService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("projectAcceptanceService")
public class ProjectAcceptanceServiceImpl extends BaseService<ProjectAcceptance, String> implements ProjectAcceptanceService {
    @Autowired
    private ProjectAcceptanceDAO projectAcceptanceDAO;

    @Override
    protected BaseDAO<ProjectAcceptance, String> getDAO() {
        return projectAcceptanceDAO;
    }
}