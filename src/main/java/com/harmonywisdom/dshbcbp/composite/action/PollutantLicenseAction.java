package com.harmonywisdom.dshbcbp.composite.action;

import com.harmonywisdom.dshbcbp.composite.bean.PollutantLicense;
import com.harmonywisdom.dshbcbp.composite.service.PollutantLicenseService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class PollutantLicenseAction extends BaseAction<PollutantLicense, PollutantLicenseService> {
    @AutoService
    private PollutantLicenseService pollutantLicenseService;

    @Override
    protected PollutantLicenseService getService() {
        return pollutantLicenseService;
    }
}