package com.harmonywisdom.dshbcbp.production.action;

import com.harmonywisdom.dshbcbp.production.bean.MainProduct;
import com.harmonywisdom.dshbcbp.production.service.MainProductService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class MainProductAction extends BaseAction<MainProduct, MainProductService> {
    @AutoService
    private MainProductService mainProductService;

    @Override
    protected MainProductService getService() {
        return mainProductService;
    }
}