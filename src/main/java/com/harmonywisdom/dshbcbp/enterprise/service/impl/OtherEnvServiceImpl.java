package com.harmonywisdom.dshbcbp.enterprise.service.impl;

import com.harmonywisdom.dshbcbp.enterprise.bean.OtherEnv;
import com.harmonywisdom.dshbcbp.enterprise.dao.OtherEnvDAO;
import com.harmonywisdom.dshbcbp.enterprise.service.OtherEnvService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("otherEnvService")
public class OtherEnvServiceImpl extends BaseService<OtherEnv, String> implements OtherEnvService {
    @Autowired
    private OtherEnvDAO otherEnvDAO;

    @Override
    protected BaseDAO<OtherEnv, String> getDAO() {
        return otherEnvDAO;
    }
}