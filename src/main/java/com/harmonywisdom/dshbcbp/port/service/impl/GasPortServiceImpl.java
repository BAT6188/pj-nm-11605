package com.harmonywisdom.dshbcbp.port.service.impl;

import com.harmonywisdom.dshbcbp.port.bean.GasPort;
import com.harmonywisdom.dshbcbp.port.dao.GasPortDAO;
import com.harmonywisdom.dshbcbp.port.service.GasPortService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("gasPortService")
public class GasPortServiceImpl extends BaseService<GasPort, String> implements GasPortService {
    @Autowired
    private GasPortDAO gasPortDAO;

    @Override
    protected BaseDAO<GasPort, String> getDAO() {
        return gasPortDAO;
    }
}