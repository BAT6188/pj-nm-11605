package com.harmonywisdom.dshbcbp.detect.service.impl;

import com.harmonywisdom.dshbcbp.detect.bean.TrustMonitor;
import com.harmonywisdom.dshbcbp.detect.dao.TrustMonitorDAO;
import com.harmonywisdom.dshbcbp.detect.service.TrustMonitorService;
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