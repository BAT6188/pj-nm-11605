package com.harmonywisdom.dshbcbp.exelaw.action;

import com.harmonywisdom.dshbcbp.exelaw.bean.Punish;
import com.harmonywisdom.dshbcbp.exelaw.service.PunishService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class PunishAction extends BaseAction<Punish, PunishService> {
    @AutoService
    private PunishService punishService;

    @Override
    protected PunishService getService() {
        return punishService;
    }
}