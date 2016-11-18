package com.harmonywisdom.dshbcbp.office.service.impl;

import com.harmonywisdom.dshbcbp.office.bean.CreateModeDetail;
import com.harmonywisdom.dshbcbp.office.dao.CreateModeDetailDAO;
import com.harmonywisdom.dshbcbp.office.service.CreateModeDetailService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("createModeDetailService")
public class CreateModeDetailServiceImpl extends BaseService<CreateModeDetail, String> implements CreateModeDetailService {
    @Autowired
    private CreateModeDetailDAO createModeDetailDAO;

    @Override
    protected BaseDAO<CreateModeDetail, String> getDAO() {
        return createModeDetailDAO;
    }
}