package com.harmonywisdom.dshbcbp.enterprise.action;

import com.harmonywisdom.dshbcbp.enterprise.bean.SelfCheckReport;
import com.harmonywisdom.dshbcbp.enterprise.service.SelfCheckReportService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class SelfCheckReportAction extends BaseAction<SelfCheckReport, SelfCheckReportService> {
    @AutoService
    private SelfCheckReportService selfCheckReportService;

    @Override
    protected SelfCheckReportService getService() {
        return selfCheckReportService;
    }
}