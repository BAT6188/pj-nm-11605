package com.harmonywisdom.dshbcbp.dispatch.action;

import com.harmonywisdom.dshbcbp.dispatch.bean.MonitorCase;
import com.harmonywisdom.dshbcbp.dispatch.service.MonitorCaseService;
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