package com.harmonywisdom.dshbcbp.enterprise.action;

import com.harmonywisdom.dshbcbp.enterprise.bean.SolidControlFacility;
import com.harmonywisdom.dshbcbp.enterprise.service.SolidControlFacilityService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class SolidControlFacilityAction extends BaseAction<SolidControlFacility, SolidControlFacilityService> {
    @AutoService
    private SolidControlFacilityService solidControlFacilityService;

    @Override
    protected SolidControlFacilityService getService() {
        return solidControlFacilityService;
    }
}