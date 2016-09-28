package com.harmonywisdom.dshbcbp.composite.service.impl;

import com.harmonywisdom.dshbcbp.composite.bean.ProjectEIA;
import com.harmonywisdom.dshbcbp.composite.dao.ProjectEIADAO;
import com.harmonywisdom.dshbcbp.composite.service.ProjectEIAService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("projectEIAService")
public class ProjectEIAServiceImpl extends BaseService<ProjectEIA, String> implements ProjectEIAService {
    @Autowired
    private ProjectEIADAO projectEIADAO;

    @Override
    protected BaseDAO<ProjectEIA, String> getDAO() {
        return projectEIADAO;
    }
}