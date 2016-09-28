package com.harmonywisdom.dshbcbp.office.service.impl;

import com.harmonywisdom.dshbcbp.office.bean.ManualCatalog;
import com.harmonywisdom.dshbcbp.office.dao.ManualCatalogDAO;
import com.harmonywisdom.dshbcbp.office.service.ManualCatalogService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("manualCatalogService")
public class ManualCatalogServiceImpl extends BaseService<ManualCatalog, String> implements ManualCatalogService {
    @Autowired
    private ManualCatalogDAO manualCatalogDAO;

    @Override
    protected BaseDAO<ManualCatalog, String> getDAO() {
        return manualCatalogDAO;
    }
}