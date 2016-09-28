package com.harmonywisdom.dshbcbp.enterprise.service.impl;

import com.harmonywisdom.dshbcbp.enterprise.bean.SolidControlFacility;
import com.harmonywisdom.dshbcbp.enterprise.dao.SolidControlFacilityDAO;
import com.harmonywisdom.dshbcbp.enterprise.service.SolidControlFacilityService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("solidControlFacilityService")
public class SolidControlFacilityServiceImpl extends BaseService<SolidControlFacility, String> implements SolidControlFacilityService {
    @Autowired
    private SolidControlFacilityDAO solidControlFacilityDAO;

    @Override
    protected BaseDAO<SolidControlFacility, String> getDAO() {
        return solidControlFacilityDAO;
    }
}