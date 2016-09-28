package com.harmonywisdom.dshbcbp.enterprise.action;

import com.harmonywisdom.dshbcbp.enterprise.bean.Punish;
import com.harmonywisdom.dshbcbp.enterprise.service.PunishService;
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