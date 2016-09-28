package com.harmonywisdom.dshbcbp.port.service.impl;

import com.harmonywisdom.dshbcbp.port.bean.NoisePortHistory;
import com.harmonywisdom.dshbcbp.port.dao.NoisePortHistoryDAO;
import com.harmonywisdom.dshbcbp.port.service.NoisePortHistoryService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("noisePortHistoryService")
public class NoisePortHistoryServiceImpl extends BaseService<NoisePortHistory, String> implements NoisePortHistoryService {
    @Autowired
    private NoisePortHistoryDAO noisePortHistoryDAO;

    @Override
    protected BaseDAO<NoisePortHistory, String> getDAO() {
        return noisePortHistoryDAO;
    }
}