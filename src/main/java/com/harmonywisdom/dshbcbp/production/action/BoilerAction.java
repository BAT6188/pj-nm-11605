package com.harmonywisdom.dshbcbp.production.action;

import com.harmonywisdom.dshbcbp.production.bean.Boiler;
import com.harmonywisdom.dshbcbp.production.service.BoilerService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class BoilerAction extends BaseAction<Boiler, BoilerService> {
    @AutoService
    private BoilerService boilerService;

    @Override
    protected BoilerService getService() {
        return boilerService;
    }
}