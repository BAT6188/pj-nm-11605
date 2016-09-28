package com.harmonywisdom.dshbcbp.port.service.impl;

import com.harmonywisdom.dshbcbp.port.bean.DustPort;
import com.harmonywisdom.dshbcbp.port.dao.DustPortDAO;
import com.harmonywisdom.dshbcbp.port.service.DustPortService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dustPortService")
public class DustPortServiceImpl extends BaseService<DustPort, String> implements DustPortService {
    @Autowired
    private DustPortDAO dustPortDAO;

    @Override
    protected BaseDAO<DustPort, String> getDAO() {
        return dustPortDAO;
    }
}