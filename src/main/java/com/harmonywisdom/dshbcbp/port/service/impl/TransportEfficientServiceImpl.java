package com.harmonywisdom.dshbcbp.port.service.impl;

import com.harmonywisdom.dshbcbp.port.bean.TransportEfficient;
import com.harmonywisdom.dshbcbp.port.dao.TransportEfficientDAO;
import com.harmonywisdom.dshbcbp.port.service.TransportEfficientService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("transportEfficientService")
public class TransportEfficientServiceImpl extends BaseService<TransportEfficient, String> implements TransportEfficientService {
    @Autowired
    private TransportEfficientDAO transportEfficientDAO;

    @Override
    protected BaseDAO<TransportEfficient, String> getDAO() {
        return transportEfficientDAO;
    }
}