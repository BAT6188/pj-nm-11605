package com.harmonywisdom.dshbcbp.office.action;

import com.harmonywisdom.dshbcbp.office.bean.ShareMeans;
import com.harmonywisdom.dshbcbp.office.service.ShareMeansService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class ShareMeansAction extends BaseAction<ShareMeans, ShareMeansService> {
    @AutoService
    private ShareMeansService shareMeansService;

    @Override
    protected ShareMeansService getService() {
        return shareMeansService;
    }
}