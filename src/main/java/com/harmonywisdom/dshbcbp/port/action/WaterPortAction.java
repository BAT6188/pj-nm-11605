package com.harmonywisdom.dshbcbp.port.action;

import com.harmonywisdom.dshbcbp.port.bean.WaterPort;
import com.harmonywisdom.dshbcbp.port.service.WaterPortService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class WaterPortAction extends BaseAction<WaterPort, WaterPortService> {
    @AutoService
    private WaterPortService waterPortService;

    @Override
    protected WaterPortService getService() {
        return waterPortService;
    }
}