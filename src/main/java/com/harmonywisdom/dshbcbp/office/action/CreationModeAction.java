package com.harmonywisdom.dshbcbp.office.action;

import com.harmonywisdom.dshbcbp.office.bean.CreationMode;
import com.harmonywisdom.dshbcbp.office.service.CreationModeService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class CreationModeAction extends BaseAction<CreationMode, CreationModeService> {
    @AutoService
    private CreationModeService creationModeService;

    @Override
    protected CreationModeService getService() {
        return creationModeService;
    }
}