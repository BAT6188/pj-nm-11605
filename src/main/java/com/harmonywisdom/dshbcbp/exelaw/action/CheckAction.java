package com.harmonywisdom.dshbcbp.exelaw.action;

import com.harmonywisdom.dshbcbp.exelaw.bean.Check;
import com.harmonywisdom.dshbcbp.exelaw.service.CheckService;
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