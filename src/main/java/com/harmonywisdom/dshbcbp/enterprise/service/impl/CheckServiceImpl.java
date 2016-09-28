package com.harmonywisdom.dshbcbp.enterprise.service.impl;

import com.harmonywisdom.dshbcbp.enterprise.bean.Check;
import com.harmonywisdom.dshbcbp.enterprise.dao.CheckDAO;
import com.harmonywisdom.dshbcbp.enterprise.service.CheckService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("checkService")
public class CheckServiceImpl extends BaseService<Check, String> implements CheckService {
    @Autowired
    private CheckDAO checkDAO;

    @Override
    protected BaseDAO<Check, String> getDAO() {
        return checkDAO;
    }
}