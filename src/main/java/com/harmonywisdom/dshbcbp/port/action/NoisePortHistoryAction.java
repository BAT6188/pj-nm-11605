package com.harmonywisdom.dshbcbp.port.action;

import com.harmonywisdom.dshbcbp.port.bean.NoisePortHistory;
import com.harmonywisdom.dshbcbp.port.service.NoisePortHistoryService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class NoisePortHistoryAction extends BaseAction<NoisePortHistory, NoisePortHistoryService> {
    @AutoService
    private NoisePortHistoryService noisePortHistoryService;

    @Override
    protected NoisePortHistoryService getService() {
        return noisePortHistoryService;
    }
}