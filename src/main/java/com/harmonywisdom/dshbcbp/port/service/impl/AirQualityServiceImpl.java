package com.harmonywisdom.dshbcbp.port.service.impl;


import com.harmonywisdom.dshbcbp.port.bean.AirQuality;
import com.harmonywisdom.dshbcbp.port.dao.AirQualityDAO;
import com.harmonywisdom.dshbcbp.port.service.AirQualityService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("airQualityService")
public class AirQualityServiceImpl extends BaseService<AirQuality, String> implements AirQualityService {
    @Autowired
    private AirQualityDAO airQualityDAO;

    @Override
    protected BaseDAO<AirQuality, String> getDAO() {
        return airQualityDAO;
    }


}