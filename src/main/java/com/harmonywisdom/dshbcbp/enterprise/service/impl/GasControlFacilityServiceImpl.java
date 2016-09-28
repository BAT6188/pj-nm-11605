package com.harmonywisdom.dshbcbp.enterprise.service.impl;

import com.harmonywisdom.dshbcbp.enterprise.bean.GasControlFacility;
import com.harmonywisdom.dshbcbp.enterprise.dao.GasControlFacilityDAO;
import com.harmonywisdom.dshbcbp.enterprise.service.GasControlFacilityService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("gasControlFacilityService")
public class GasControlFacilityServiceImpl extends BaseService<GasControlFacility, String> implements GasControlFacilityService {
    @Autowired
    private GasControlFacilityDAO gasControlFacilityDAO;

    @Override
    protected BaseDAO<GasControlFacility, String> getDAO() {
        return gasControlFacilityDAO;
    }
}