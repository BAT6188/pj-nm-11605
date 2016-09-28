package com.harmonywisdom.dshbcbp.dispathtask.action;

import com.harmonywisdom.dshbcbp.dispathtask.bean.DispathTask;
import com.harmonywisdom.dshbcbp.dispathtask.service.DispathTaskService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class DispathTaskAction extends BaseAction<DispathTask, DispathTaskService> {
    @AutoService
    private DispathTaskService dispathTaskService;

    @Override
    protected DispathTaskService getService() {
        return dispathTaskService;
    }
}