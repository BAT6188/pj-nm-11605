package com.harmonywisdom.dshbcbp.port.action;

import com.harmonywisdom.dshbcbp.port.bean.DustPortHistory;
import com.harmonywisdom.dshbcbp.port.service.DustPortHistoryService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class DustPortHistoryAction extends BaseAction<DustPortHistory, DustPortHistoryService> {
    @AutoService
    private DustPortHistoryService dustPortHistoryService;

    @Override
    protected DustPortHistoryService getService() {
        return dustPortHistoryService;
    }
}