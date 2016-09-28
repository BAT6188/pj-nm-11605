package com.harmonywisdom.dshbcbp.port.action;

import com.harmonywisdom.dshbcbp.port.bean.DustPort;
import com.harmonywisdom.dshbcbp.port.service.DustPortService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class DustPortAction extends BaseAction<DustPort, DustPortService> {
    @AutoService
    private DustPortService dustPortService;

    @Override
    protected DustPortService getService() {
        return dustPortService;
    }
}