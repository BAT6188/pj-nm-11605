package com.harmonywisdom.dshbcbp.detect.service;

import com.harmonywisdom.dshbcbp.detect.bean.MonitorReport;
import com.harmonywisdom.framework.service.IBaseService;

public interface MonitorReportService extends IBaseService<MonitorReport, String> {

    /**
     * 增量更新
     * @param monitorReport
     * @return
     */
    String updateMonitorReport(MonitorReport monitorReport);
}