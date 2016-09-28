package com.harmonywisdom.dshbcbp.composite.action;

import com.harmonywisdom.dshbcbp.composite.bean.CleanLicense;
import com.harmonywisdom.dshbcbp.composite.service.CleanLicenseService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class CleanLicenseAction extends BaseAction<CleanLicense, CleanLicenseService> {
    @AutoService
    private CleanLicenseService cleanLicenseService;

    @Override
    protected CleanLicenseService getService() {
        return cleanLicenseService;
    }
}