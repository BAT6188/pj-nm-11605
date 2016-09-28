package com.harmonywisdom.dshbcbp.trustmonitor.service.impl;

import com.harmonywisdom.dshbcbp.trustmonitor.bean.TrustMonitor;
import com.harmonywisdom.dshbcbp.trustmonitor.dao.TrustMonitorDAO;
import com.harmonywisdom.dshbcbp.trustmonitor.service.TrustMonitorService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("trustMonitorService")
public class TrustMonitorServiceImpl extends BaseService<TrustMonitor, String> implements TrustMonitorService {
    @Autowired
    private TrustMonitorDAO trustMonitorDAO;

    @Override
    protected BaseDAO<TrustMonitor, String> getDAO() {
        return trustMonitorDAO;
    }
}