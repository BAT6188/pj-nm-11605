package com.harmonywisdom.dshbcbp.dispatch.action;

import com.harmonywisdom.dshbcbp.dispatch.bean.DispathTask;
import com.harmonywisdom.dshbcbp.dispatch.service.DispathTaskService;
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