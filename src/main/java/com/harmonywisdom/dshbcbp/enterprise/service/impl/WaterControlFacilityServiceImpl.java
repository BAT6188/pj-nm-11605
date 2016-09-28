package com.harmonywisdom.dshbcbp.enterprise.service.impl;

import com.harmonywisdom.dshbcbp.enterprise.bean.WaterControlFacility;
import com.harmonywisdom.dshbcbp.enterprise.dao.WaterControlFacilityDAO;
import com.harmonywisdom.dshbcbp.enterprise.service.WaterControlFacilityService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("waterControlFacilityService")
public class WaterControlFacilityServiceImpl extends BaseService<WaterControlFacility, String> implements WaterControlFacilityService {
    @Autowired
    private WaterControlFacilityDAO waterControlFacilityDAO;

    @Override
    protected BaseDAO<WaterControlFacility, String> getDAO() {
        return waterControlFacilityDAO;
    }
}