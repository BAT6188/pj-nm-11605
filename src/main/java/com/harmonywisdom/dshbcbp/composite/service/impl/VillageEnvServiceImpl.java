package com.harmonywisdom.dshbcbp.composite.service.impl;

import com.harmonywisdom.dshbcbp.composite.bean.VillageEnv;
import com.harmonywisdom.dshbcbp.composite.dao.VillageEnvDAO;
import com.harmonywisdom.dshbcbp.composite.service.VillageEnvService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("villageEnvService")
public class VillageEnvServiceImpl extends BaseService<VillageEnv, String> implements VillageEnvService {
    @Autowired
    private VillageEnvDAO villageEnvDAO;

    @Override
    protected BaseDAO<VillageEnv, String> getDAO() {
        return villageEnvDAO;
    }
}