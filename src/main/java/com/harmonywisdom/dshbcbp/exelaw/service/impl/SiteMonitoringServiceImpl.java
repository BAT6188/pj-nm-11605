package com.harmonywisdom.dshbcbp.exelaw.service.impl;

import com.harmonywisdom.dshbcbp.exelaw.bean.SiteMonitoring;
import com.harmonywisdom.dshbcbp.exelaw.dao.SiteMonitoringDAO;
import com.harmonywisdom.dshbcbp.exelaw.service.SiteMonitoringService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("siteMonitoringService")
public class SiteMonitoringServiceImpl extends BaseService<SiteMonitoring, String> implements SiteMonitoringService {
    @Autowired
    private SiteMonitoringDAO siteMonitoringDAO;

    @Override
    protected BaseDAO<SiteMonitoring, String> getDAO() {
        return siteMonitoringDAO;
    }
}