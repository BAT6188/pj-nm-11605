package com.harmonywisdom.dshbcbp.enterprise.action;

import com.harmonywisdom.dshbcbp.enterprise.bean.WaterControlFacility;
import com.harmonywisdom.dshbcbp.enterprise.service.WaterControlFacilityService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class WaterControlFacilityAction extends BaseAction<WaterControlFacility, WaterControlFacilityService> {
    @AutoService
    private WaterControlFacilityService waterControlFacilityService;

    @Override
    protected WaterControlFacilityService getService() {
        return waterControlFacilityService;
    }
}