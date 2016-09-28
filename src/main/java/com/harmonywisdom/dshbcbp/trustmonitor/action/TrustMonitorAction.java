package com.harmonywisdom.dshbcbp.trustmonitor.action;

import com.harmonywisdom.dshbcbp.trustmonitor.bean.TrustMonitor;
import com.harmonywisdom.dshbcbp.trustmonitor.service.TrustMonitorService;
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