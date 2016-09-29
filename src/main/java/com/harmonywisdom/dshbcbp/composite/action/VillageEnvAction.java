package com.harmonywisdom.dshbcbp.composite.action;

import com.harmonywisdom.dshbcbp.composite.bean.VillageEnv;
import com.harmonywisdom.dshbcbp.composite.service.VillageEnvService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class VillageEnvAction extends BaseAction<VillageEnv, VillageEnvService> {
    @AutoService
    private VillageEnvService villageEnvService;

    @Override
    protected VillageEnvService getService() {
        return villageEnvService;
    }
}