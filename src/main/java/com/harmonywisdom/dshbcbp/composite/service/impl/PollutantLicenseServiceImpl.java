package com.harmonywisdom.dshbcbp.composite.service.impl;

import com.harmonywisdom.dshbcbp.composite.bean.PollutantLicense;
import com.harmonywisdom.dshbcbp.composite.dao.PollutantLicenseDAO;
import com.harmonywisdom.dshbcbp.composite.service.PollutantLicenseService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("pollutantLicenseService")
public class PollutantLicenseServiceImpl extends BaseService<PollutantLicense, String> implements PollutantLicenseService {
    @Autowired
    private PollutantLicenseDAO pollutantLicenseDAO;

    @Override
    protected BaseDAO<PollutantLicense, String> getDAO() {
        return pollutantLicenseDAO;
    }
}