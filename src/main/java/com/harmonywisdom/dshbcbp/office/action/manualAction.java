package com.harmonywisdom.dshbcbp.office.action;

import com.harmonywisdom.dshbcbp.office.bean.manual;
import com.harmonywisdom.dshbcbp.office.service.manualService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class manualAction extends BaseAction<manual, manualService> {
    @AutoService
    private manualService manualService;

    @Override
    protected manualService getService() {
        return manualService;
    }
}