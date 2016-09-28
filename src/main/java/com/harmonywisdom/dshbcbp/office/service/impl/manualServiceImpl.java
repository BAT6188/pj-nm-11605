package com.harmonywisdom.dshbcbp.office.service.impl;

import com.harmonywisdom.dshbcbp.office.bean.manual;
import com.harmonywisdom.dshbcbp.office.dao.manualDAO;
import com.harmonywisdom.dshbcbp.office.service.manualService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("manualService")
public class manualServiceImpl extends BaseService<manual, String> implements manualService {
    @Autowired
    private manualDAO manualDAO;

    @Override
    protected BaseDAO<manual, String> getDAO() {
        return manualDAO;
    }
}