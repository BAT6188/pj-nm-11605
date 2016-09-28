package com.harmonywisdom.dshbcbp.enterprise.service.impl;

import com.harmonywisdom.dshbcbp.enterprise.bean.Enterprise;
import com.harmonywisdom.dshbcbp.enterprise.dao.EnterpriseDAO;
import com.harmonywisdom.dshbcbp.enterprise.service.EnterpriseService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("enterpriseService")
public class EnterpriseServiceImpl extends BaseService<Enterprise, String> implements EnterpriseService {
    @Autowired
    private EnterpriseDAO enterpriseDAO;

    @Override
    protected BaseDAO<Enterprise, String> getDAO() {
        return enterpriseDAO;
    }
}