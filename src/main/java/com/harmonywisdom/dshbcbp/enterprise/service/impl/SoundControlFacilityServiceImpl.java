package com.harmonywisdom.dshbcbp.enterprise.service.impl;

import com.harmonywisdom.dshbcbp.enterprise.bean.SoundControlFacility;
import com.harmonywisdom.dshbcbp.enterprise.dao.SoundControlFacilityDAO;
import com.harmonywisdom.dshbcbp.enterprise.service.SoundControlFacilityService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("soundControlFacilityService")
public class SoundControlFacilityServiceImpl extends BaseService<SoundControlFacility, String> implements SoundControlFacilityService {
    @Autowired
    private SoundControlFacilityDAO soundControlFacilityDAO;

    @Override
    protected BaseDAO<SoundControlFacility, String> getDAO() {
        return soundControlFacilityDAO;
    }
}