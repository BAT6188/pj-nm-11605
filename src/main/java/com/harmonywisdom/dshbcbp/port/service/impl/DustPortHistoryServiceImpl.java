package com.harmonywisdom.dshbcbp.port.service.impl;

import com.harmonywisdom.dshbcbp.port.bean.DustPortHistory;
import com.harmonywisdom.dshbcbp.port.dao.DustPortHistoryDAO;
import com.harmonywisdom.dshbcbp.port.service.DustPortHistoryService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dustPortHistoryService")
public class DustPortHistoryServiceImpl extends BaseService<DustPortHistory, String> implements DustPortHistoryService {
    @Autowired
    private DustPortHistoryDAO dustPortHistoryDAO;

    @Override
    protected BaseDAO<DustPortHistory, String> getDAO() {
        return dustPortHistoryDAO;
    }
}