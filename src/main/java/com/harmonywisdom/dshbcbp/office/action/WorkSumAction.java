package com.harmonywisdom.dshbcbp.office.action;

import com.harmonywisdom.dshbcbp.office.bean.WorkSum;
import com.harmonywisdom.dshbcbp.office.service.WorkSumService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class WorkSumAction extends BaseAction<WorkSum, WorkSumService> {
    @AutoService
    private WorkSumService workSumService;

    @Override
    protected WorkSumService getService() {
        return workSumService;
    }
}