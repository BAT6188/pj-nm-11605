package com.harmonywisdom.dshbcbp.port.action;

import com.harmonywisdom.dshbcbp.port.bean.GasPortHistory;
import com.harmonywisdom.dshbcbp.port.service.GasPortHistoryService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class GasPortHistoryAction extends BaseAction<GasPortHistory, GasPortHistoryService> {
    @AutoService
    private GasPortHistoryService gasPortHistoryService;

    @Override
    protected GasPortHistoryService getService() {
        return gasPortHistoryService;
    }
}