package com.harmonywisdom.dshbcbp.port.action;

import com.harmonywisdom.dshbcbp.port.bean.FumesPort;
import com.harmonywisdom.dshbcbp.port.service.FumesPortService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class FumesPortAction extends BaseAction<FumesPort, FumesPortService> {
    @AutoService
    private FumesPortService fumesPortService;

    @Override
    protected FumesPortService getService() {
        return fumesPortService;
    }
}