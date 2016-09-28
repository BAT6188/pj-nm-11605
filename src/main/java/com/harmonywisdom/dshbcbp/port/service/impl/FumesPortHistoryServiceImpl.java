package com.harmonywisdom.dshbcbp.port.service.impl;

import com.harmonywisdom.dshbcbp.port.bean.FumesPortHistory;
import com.harmonywisdom.dshbcbp.port.dao.FumesPortHistoryDAO;
import com.harmonywisdom.dshbcbp.port.service.FumesPortHistoryService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("fumesPortHistoryService")
public class FumesPortHistoryServiceImpl extends BaseService<FumesPortHistory, String> implements FumesPortHistoryService {
    @Autowired
    private FumesPortHistoryDAO fumesPortHistoryDAO;

    @Override
    protected BaseDAO<FumesPortHistory, String> getDAO() {
        return fumesPortHistoryDAO;
    }
}