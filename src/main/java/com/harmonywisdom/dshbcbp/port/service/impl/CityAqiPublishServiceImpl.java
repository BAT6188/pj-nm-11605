package com.harmonywisdom.dshbcbp.port.service.impl;

import com.harmonywisdom.dshbcbp.port.bean.CityAqiPublish;
import com.harmonywisdom.dshbcbp.port.dao.CityAqiPublishDAO;
import com.harmonywisdom.dshbcbp.port.service.CityAqiPublishService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("cityAqiPublishService")
public class CityAqiPublishServiceImpl extends BaseService<CityAqiPublish, String> implements CityAqiPublishService {
    @Autowired
    private CityAqiPublishDAO cityAqiPublishDAO;

    @Override
    protected BaseDAO<CityAqiPublish, String> getDAO() {
        return cityAqiPublishDAO;
    }
}