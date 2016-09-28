package com.harmonywisdom.dshbcbp.enterprise.action;

import com.harmonywisdom.dshbcbp.enterprise.bean.OtherEnv;
import com.harmonywisdom.dshbcbp.enterprise.service.OtherEnvService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class OtherEnvAction extends BaseAction<OtherEnv, OtherEnvService> {
    @AutoService
    private OtherEnvService otherEnvService;

    @Override
    protected OtherEnvService getService() {
        return otherEnvService;
    }
}