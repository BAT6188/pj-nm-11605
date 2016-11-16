package com.harmonywisdom.dshbcbp.dispatch.service;

import com.harmonywisdom.dshbcbp.dispatch.bean.MonitorCase;
import com.harmonywisdom.framework.service.IBaseService;

public interface MonitorCaseService extends IBaseService<MonitorCase, String> {

    /**
     * 增量更新
     * @param monitorCase
     * @return
     */
    String updateMonitorCase(MonitorCase monitorCase);
}