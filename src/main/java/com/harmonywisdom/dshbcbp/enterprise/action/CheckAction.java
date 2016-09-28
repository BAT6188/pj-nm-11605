package com.harmonywisdom.dshbcbp.enterprise.action;

import com.harmonywisdom.dshbcbp.enterprise.bean.Check;
import com.harmonywisdom.dshbcbp.enterprise.service.CheckService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class CheckAction extends BaseAction<Check, CheckService> {
    @AutoService
    private CheckService checkService;

    @Override
    protected CheckService getService() {
        return checkService;
    }
}