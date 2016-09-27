package com.harmonywisdom.dshbcbp.demo.action;

import com.harmonywisdom.dshbcbp.demo.bean.Demo;
import com.harmonywisdom.dshbcbp.demo.service.DemoService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

/**
 * Created by hotleave on 14-9-16.
 */
public class DemoAction extends BaseAction<Demo, DemoService> {
    @AutoService
    private DemoService demoService;

    @Override
    protected DemoService getService() {
        return demoService;
    }
}
