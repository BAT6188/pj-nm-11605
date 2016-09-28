package com.harmonywisdom.dshbcbp.office.service.impl;

import com.harmonywisdom.dshbcbp.office.bean.CreationMode;
import com.harmonywisdom.dshbcbp.office.dao.CreationModeDAO;
import com.harmonywisdom.dshbcbp.office.service.CreationModeService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("creationModeService")
public class CreationModeServiceImpl extends BaseService<CreationMode, String> implements CreationModeService {
    @Autowired
    private CreationModeDAO creationModeDAO;

    @Override
    protected BaseDAO<CreationMode, String> getDAO() {
        return creationModeDAO;
    }
}