package com.harmonywisdom.dshbcbp.port.action;

import com.harmonywisdom.dshbcbp.port.bean.GasPort;
import com.harmonywisdom.dshbcbp.port.service.GasPortService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class GasPortAction extends BaseAction<GasPort, GasPortService> {
    @AutoService
    private GasPortService gasPortService;

    @Override
    protected GasPortService getService() {
        return gasPortService;
    }
}