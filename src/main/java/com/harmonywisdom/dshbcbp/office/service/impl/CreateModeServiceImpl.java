package com.harmonywisdom.dshbcbp.office.service.impl;

import com.harmonywisdom.dshbcbp.office.bean.CreateMode;
import com.harmonywisdom.dshbcbp.office.dao.CreateModeDAO;
import com.harmonywisdom.dshbcbp.office.service.CreateModeService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("createModeService")
public class CreateModeServiceImpl extends BaseService<CreateMode, String> implements CreateModeService {
    @Autowired
    private CreateModeDAO createModeDAO;

    @Override
    protected BaseDAO<CreateMode, String> getDAO() {
        return createModeDAO;
    }
}