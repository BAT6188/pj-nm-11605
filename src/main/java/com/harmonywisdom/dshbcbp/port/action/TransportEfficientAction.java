package com.harmonywisdom.dshbcbp.port.action;

import com.harmonywisdom.dshbcbp.port.bean.DustPort;
import com.harmonywisdom.dshbcbp.port.bean.TransportEfficient;
import com.harmonywisdom.dshbcbp.port.service.DustPortService;
import com.harmonywisdom.dshbcbp.port.service.TransportEfficientService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class TransportEfficientAction extends BaseAction<TransportEfficient, TransportEfficientService> {
    @AutoService
    private TransportEfficientService transportEfficientService;

    @Override
    protected TransportEfficientService getService() {
        return transportEfficientService;
    }
}