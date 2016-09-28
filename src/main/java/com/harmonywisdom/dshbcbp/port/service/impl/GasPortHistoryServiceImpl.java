package com.harmonywisdom.dshbcbp.port.service.impl;

import com.harmonywisdom.dshbcbp.port.bean.GasPortHistory;
import com.harmonywisdom.dshbcbp.port.dao.GasPortHistoryDAO;
import com.harmonywisdom.dshbcbp.port.service.GasPortHistoryService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("gasPortHistoryService")
public class GasPortHistoryServiceImpl extends BaseService<GasPortHistory, String> implements GasPortHistoryService {
    @Autowired
    private GasPortHistoryDAO gasPortHistoryDAO;

    @Override
    protected BaseDAO<GasPortHistory, String> getDAO() {
        return gasPortHistoryDAO;
    }
}