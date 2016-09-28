package com.harmonywisdom.dshbcbp.port.service.impl;

import com.harmonywisdom.dshbcbp.port.bean.NoisePort;
import com.harmonywisdom.dshbcbp.port.dao.NoisePortDAO;
import com.harmonywisdom.dshbcbp.port.service.NoisePortService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("noisePortService")
public class NoisePortServiceImpl extends BaseService<NoisePort, String> implements NoisePortService {
    @Autowired
    private NoisePortDAO noisePortDAO;

    @Override
    protected BaseDAO<NoisePort, String> getDAO() {
        return noisePortDAO;
    }
}