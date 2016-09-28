package com.harmonywisdom.dshbcbp.port.action;

import com.harmonywisdom.dshbcbp.port.bean.NoisePort;
import com.harmonywisdom.dshbcbp.port.service.NoisePortService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class NoisePortAction extends BaseAction<NoisePort, NoisePortService> {
    @AutoService
    private NoisePortService noisePortService;

    @Override
    protected NoisePortService getService() {
        return noisePortService;
    }
}