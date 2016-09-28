package com.harmonywisdom.dshbcbp.port.service.impl;

import com.harmonywisdom.dshbcbp.port.bean.WaterPort;
import com.harmonywisdom.dshbcbp.port.dao.WaterPortDAO;
import com.harmonywisdom.dshbcbp.port.service.WaterPortService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("waterPortService")
public class WaterPortServiceImpl extends BaseService<WaterPort, String> implements WaterPortService {
    @Autowired
    private WaterPortDAO waterPortDAO;

    @Override
    protected BaseDAO<WaterPort, String> getDAO() {
        return waterPortDAO;
    }
}