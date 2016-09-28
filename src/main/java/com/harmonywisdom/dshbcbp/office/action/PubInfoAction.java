package com.harmonywisdom.dshbcbp.office.action;

import com.harmonywisdom.dshbcbp.office.bean.PubInfo;
import com.harmonywisdom.dshbcbp.office.service.PubInfoService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class PubInfoAction extends BaseAction<PubInfo, PubInfoService> {
    @AutoService
    private PubInfoService pubInfoService;

    @Override
    protected PubInfoService getService() {
        return pubInfoService;
    }
}