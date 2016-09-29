package com.harmonywisdom.dshbcbp.composite.service.impl;

import com.harmonywisdom.dshbcbp.composite.bean.CleanLicense;
import com.harmonywisdom.dshbcbp.composite.dao.CleanLicenseDAO;
import com.harmonywisdom.dshbcbp.composite.service.CleanLicenseService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("cleanLicenseService")
public class CleanLicenseServiceImpl extends BaseService<CleanLicense, String> implements CleanLicenseService {
    @Autowired
    private CleanLicenseDAO cleanLicenseDAO;

    @Override
    protected BaseDAO<CleanLicense, String> getDAO() {
        return cleanLicenseDAO;
    }
}