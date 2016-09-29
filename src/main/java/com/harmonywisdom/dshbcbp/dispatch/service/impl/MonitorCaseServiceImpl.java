package com.harmonywisdom.dshbcbp.dispatch.service.impl;

import com.harmonywisdom.dshbcbp.dispatch.bean.MonitorCase;
import com.harmonywisdom.dshbcbp.dispatch.dao.MonitorCaseDAO;
import com.harmonywisdom.dshbcbp.dispatch.service.MonitorCaseService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("monitorCaseService")
public class MonitorCaseServiceImpl extends BaseService<MonitorCase, String> implements MonitorCaseService {
    @Autowired
    private MonitorCaseDAO monitorCaseDAO;

    @Override
    protected BaseDAO<MonitorCase, String> getDAO() {
        return monitorCaseDAO;
    }
}