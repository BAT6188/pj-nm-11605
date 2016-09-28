package com.harmonywisdom.dshbcbp.port.action;

import com.harmonywisdom.dshbcbp.port.bean.WaterPortHistory;
import com.harmonywisdom.dshbcbp.port.service.WaterPortHistoryService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class WaterPortHistoryAction extends BaseAction<WaterPortHistory, WaterPortHistoryService> {
    @AutoService
    private WaterPortHistoryService waterPortHistoryService;

    @Override
    protected WaterPortHistoryService getService() {
        return waterPortHistoryService;
    }
}