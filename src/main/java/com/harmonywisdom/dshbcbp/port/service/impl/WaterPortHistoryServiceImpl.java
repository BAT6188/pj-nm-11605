package com.harmonywisdom.dshbcbp.port.service.impl;

import com.harmonywisdom.dshbcbp.port.bean.WaterPortHistory;
import com.harmonywisdom.dshbcbp.port.dao.WaterPortHistoryDAO;
import com.harmonywisdom.dshbcbp.port.service.WaterPortHistoryService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("waterPortHistoryService")
public class WaterPortHistoryServiceImpl extends BaseService<WaterPortHistory, String> implements WaterPortHistoryService {
    @Autowired
    private WaterPortHistoryDAO waterPortHistoryDAO;

    @Override
    protected BaseDAO<WaterPortHistory, String> getDAO() {
        return waterPortHistoryDAO;
    }
}