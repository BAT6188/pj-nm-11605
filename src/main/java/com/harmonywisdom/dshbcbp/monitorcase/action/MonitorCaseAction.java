package com.harmonywisdom.dshbcbp.monitorcase.action;

import com.harmonywisdom.dshbcbp.monitorcase.bean.MonitorCase;
import com.harmonywisdom.dshbcbp.monitorcase.service.MonitorCaseService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class MonitorCaseAction extends BaseAction<MonitorCase, MonitorCaseService> {
    @AutoService
    private MonitorCaseService monitorCaseService;

    @Override
    protected MonitorCaseService getService() {
        return monitorCaseService;
    }
}