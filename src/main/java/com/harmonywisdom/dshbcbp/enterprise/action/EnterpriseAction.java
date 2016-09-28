package com.harmonywisdom.dshbcbp.enterprise.action;

import com.harmonywisdom.dshbcbp.enterprise.bean.Enterprise;
import com.harmonywisdom.dshbcbp.enterprise.service.EnterpriseService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class EnterpriseAction extends BaseAction<Enterprise, EnterpriseService> {
    @AutoService
    private EnterpriseService enterpriseService;

    @Override
    protected EnterpriseService getService() {
        return enterpriseService;
    }
}