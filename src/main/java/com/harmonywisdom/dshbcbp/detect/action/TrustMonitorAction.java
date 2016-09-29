package com.harmonywisdom.dshbcbp.detect.action;

import com.harmonywisdom.dshbcbp.detect.bean.TrustMonitor;
import com.harmonywisdom.dshbcbp.detect.service.TrustMonitorService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class TrustMonitorAction extends BaseAction<TrustMonitor, TrustMonitorService> {
    @AutoService
    private TrustMonitorService trustMonitorService;

    @Override
    protected TrustMonitorService getService() {
        return trustMonitorService;
    }
}