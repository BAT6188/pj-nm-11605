package com.harmonywisdom.dshbcbp.office.action;

import com.harmonywisdom.dshbcbp.office.bean.Manual;
import com.harmonywisdom.dshbcbp.office.service.ManualService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class ManualAction extends BaseAction<Manual, ManualService> {
    @AutoService
    private ManualService manualService;

    @Override
    protected ManualService getService() {
        return manualService;
    }
}