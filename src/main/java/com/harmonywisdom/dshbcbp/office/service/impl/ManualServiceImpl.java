package com.harmonywisdom.dshbcbp.office.service.impl;

import com.harmonywisdom.dshbcbp.office.bean.Manual;
import com.harmonywisdom.dshbcbp.office.dao.ManualDAO;
import com.harmonywisdom.dshbcbp.office.service.ManualService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("manualService")
public class ManualServiceImpl extends BaseService<Manual, String> implements ManualService {
    @Autowired
    private ManualDAO manualDAO;

    @Override
    protected BaseDAO<Manual, String> getDAO() {
        return manualDAO;
    }
}