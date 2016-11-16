package com.harmonywisdom.dshbcbp.detect.service.impl;

import com.harmonywisdom.dshbcbp.detect.bean.MonitorReport;
import com.harmonywisdom.dshbcbp.detect.dao.MonitorReportDAO;
import com.harmonywisdom.dshbcbp.detect.service.MonitorReportService;
import com.harmonywisdom.dshbcbp.utils.EntityUtil;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("monitorReportService")
public class MonitorReportServiceImpl extends BaseService<MonitorReport, String> implements MonitorReportService {
    @Autowired
    private MonitorReportDAO monitorReportDAO;

    @Override
    protected BaseDAO<MonitorReport, String> getDAO() {
        return monitorReportDAO;
    }

    @Override
    public String updateMonitorReport(MonitorReport monitorReport) {
        Map<String,Object> map = EntityUtil.getUpdateMap(monitorReport);
        return String.valueOf(monitorReportDAO.executeJPQL(String.valueOf(map.get("upStr")),(Map<String, Object>)map.get("valMap")));
    }
}