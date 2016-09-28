package com.harmonywisdom.dshbcbp.production.service.impl;

import com.harmonywisdom.dshbcbp.production.bean.Boiler;
import com.harmonywisdom.dshbcbp.production.dao.BoilerDAO;
import com.harmonywisdom.dshbcbp.production.service.BoilerService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("boilerService")
public class BoilerServiceImpl extends BaseService<Boiler, String> implements BoilerService {
    @Autowired
    private BoilerDAO boilerDAO;

    @Override
    protected BaseDAO<Boiler, String> getDAO() {
        return boilerDAO;
    }
}