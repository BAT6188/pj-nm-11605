package com.harmonywisdom.dshbcbp.port.action;


import com.harmonywisdom.dshbcbp.port.bean.AirQuality;
import com.harmonywisdom.dshbcbp.port.service.AirQualityService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

import java.util.List;

public class AirQualityAction extends BaseAction<AirQuality, AirQualityService> {
    @AutoService
    private AirQualityService airQualityService;

    @Override
    protected AirQualityService getService() {
        return airQualityService;
    }


}