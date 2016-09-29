package com.harmonywisdom.dshbcbp.exelaw.service.impl;

import com.harmonywisdom.dshbcbp.exelaw.bean.Check;
import com.harmonywisdom.dshbcbp.exelaw.dao.CheckDAO;
import com.harmonywisdom.dshbcbp.exelaw.service.CheckService;
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