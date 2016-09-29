package com.harmonywisdom.dshbcbp.detect.service.impl;

import com.harmonywisdom.dshbcbp.detect.bean.EntMonitorTask;
import com.harmonywisdom.dshbcbp.detect.dao.EntMonitorTaskDAO;
import com.harmonywisdom.dshbcbp.detect.service.EntMonitorTaskService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("entMonitorTaskService")
public class EntMonitorTaskServiceImpl extends BaseService<EntMonitorTask, String> implements EntMonitorTaskService {
    @Autowired
    private EntMonitorTaskDAO entMonitorTaskDAO;

    @Override
    protected BaseDAO<EntMonitorTask, String> getDAO() {
        return entMonitorTaskDAO;
    }
}