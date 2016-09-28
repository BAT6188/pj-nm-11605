package com.harmonywisdom.dshbcbp.enterprise.action;

import com.harmonywisdom.dshbcbp.enterprise.bean.GasControlFacility;
import com.harmonywisdom.dshbcbp.enterprise.service.GasControlFacilityService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class GasControlFacilityAction extends BaseAction<GasControlFacility, GasControlFacilityService> {
    @AutoService
    private GasControlFacilityService gasControlFacilityService;

    @Override
    protected GasControlFacilityService getService() {
        return gasControlFacilityService;
    }
}