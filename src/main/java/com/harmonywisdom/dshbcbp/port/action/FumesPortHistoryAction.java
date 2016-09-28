package com.harmonywisdom.dshbcbp.port.action;

import com.harmonywisdom.dshbcbp.port.bean.FumesPortHistory;
import com.harmonywisdom.dshbcbp.port.service.FumesPortHistoryService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class FumesPortHistoryAction extends BaseAction<FumesPortHistory, FumesPortHistoryService> {
    @AutoService
    private FumesPortHistoryService fumesPortHistoryService;

    @Override
    protected FumesPortHistoryService getService() {
        return fumesPortHistoryService;
    }
}