package com.harmonywisdom.dshbcbp.detect.action;

import com.harmonywisdom.dshbcbp.detect.bean.EntMonitorTask;
import com.harmonywisdom.dshbcbp.detect.service.EntMonitorTaskService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class EntMonitorTaskAction extends BaseAction<EntMonitorTask, EntMonitorTaskService> {
    @AutoService
    private EntMonitorTaskService entMonitorTaskService;

    @Override
    protected EntMonitorTaskService getService() {
        return entMonitorTaskService;
    }
}