package com.harmonywisdom.dshbcbp.detect.service.impl;

import com.harmonywisdom.dshbcbp.detect.bean.MonitorReport;
import com.harmonywisdom.dshbcbp.detect.dao.MonitorReportDAO;
import com.harmonywisdom.dshbcbp.detect.service.MonitorReportService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("monitorReportService")
public class MonitorReportServiceImpl extends BaseService<MonitorReport, String> implements MonitorReportService {
    @Autowired
    private MonitorReportDAO monitorReportDAO;

    @Override
    protected BaseDAO<MonitorReport, String> getDAO() {
        return monitorReportDAO;
    }
}