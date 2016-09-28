package com.harmonywisdom.dshbcbp.composite.service.impl;

import com.harmonywisdom.dshbcbp.composite.bean.EnterprisePlan;
import com.harmonywisdom.dshbcbp.composite.dao.EnterprisePlanDAO;
import com.harmonywisdom.dshbcbp.composite.service.EnterprisePlanService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("enterprisePlanService")
public class EnterprisePlanServiceImpl extends BaseService<EnterprisePlan, String> implements EnterprisePlanService {
    @Autowired
    private EnterprisePlanDAO enterprisePlanDAO;

    @Override
    protected BaseDAO<EnterprisePlan, String> getDAO() {
        return enterprisePlanDAO;
    }
}