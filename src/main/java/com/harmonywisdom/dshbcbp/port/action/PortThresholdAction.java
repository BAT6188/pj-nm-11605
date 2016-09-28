package com.harmonywisdom.dshbcbp.port.action;

import com.harmonywisdom.dshbcbp.port.bean.PortThreshold;
import com.harmonywisdom.dshbcbp.port.service.PortThresholdService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class PortThresholdAction extends BaseAction<PortThreshold, PortThresholdService> {
    @AutoService
    private PortThresholdService portThresholdService;

    @Override
    protected PortThresholdService getService() {
        return portThresholdService;
    }
}