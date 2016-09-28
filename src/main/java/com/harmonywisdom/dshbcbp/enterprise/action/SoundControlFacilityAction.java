package com.harmonywisdom.dshbcbp.enterprise.action;

import com.harmonywisdom.dshbcbp.enterprise.bean.SoundControlFacility;
import com.harmonywisdom.dshbcbp.enterprise.service.SoundControlFacilityService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class SoundControlFacilityAction extends BaseAction<SoundControlFacility, SoundControlFacilityService> {
    @AutoService
    private SoundControlFacilityService soundControlFacilityService;

    @Override
    protected SoundControlFacilityService getService() {
        return soundControlFacilityService;
    }
}