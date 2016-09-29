package com.harmonywisdom.dshbcbp.composite.action;

import com.harmonywisdom.dshbcbp.composite.bean.EnterprisePlan;
import com.harmonywisdom.dshbcbp.composite.service.EnterprisePlanService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class EnterprisePlanAction extends BaseAction<EnterprisePlan, EnterprisePlanService> {
    @AutoService
    private EnterprisePlanService enterprisePlanService;

    @Override
    protected EnterprisePlanService getService() {
        return enterprisePlanService;
    }
}