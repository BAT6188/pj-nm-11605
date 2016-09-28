package com.harmonywisdom.dshbcbp.production.action;

import com.harmonywisdom.dshbcbp.production.bean.Kiln;
import com.harmonywisdom.dshbcbp.production.service.KilnService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class KilnAction extends BaseAction<Kiln, KilnService> {
    @AutoService
    private KilnService kilnService;

    @Override
    protected KilnService getService() {
        return kilnService;
    }
}