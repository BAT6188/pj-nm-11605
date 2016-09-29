package com.harmonywisdom.dshbcbp.office.action;

import com.harmonywisdom.dshbcbp.office.bean.ManualCatalog;
import com.harmonywisdom.dshbcbp.office.service.ManualCatalogService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class ManualCatalogAction extends BaseAction<ManualCatalog, ManualCatalogService> {
    @AutoService
    private ManualCatalogService manualCatalogService;

    @Override
    protected ManualCatalogService getService() {
        return manualCatalogService;
    }
}