package com.harmonywisdom.dshbcbp.port.service.impl;

import com.harmonywisdom.dshbcbp.port.bean.FumesPort;
import com.harmonywisdom.dshbcbp.port.dao.FumesPortDAO;
import com.harmonywisdom.dshbcbp.port.service.FumesPortService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("fumesPortService")
public class FumesPortServiceImpl extends BaseService<FumesPort, String> implements FumesPortService {
    @Autowired
    private FumesPortDAO fumesPortDAO;

    @Override
    protected BaseDAO<FumesPort, String> getDAO() {
        return fumesPortDAO;
    }
}