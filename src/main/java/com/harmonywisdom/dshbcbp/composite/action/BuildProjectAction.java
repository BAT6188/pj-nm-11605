package com.harmonywisdom.dshbcbp.composite.action;

import com.harmonywisdom.dshbcbp.composite.bean.BuildProject;
import com.harmonywisdom.dshbcbp.composite.service.BuildProjectService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class BuildProjectAction extends BaseAction<BuildProject, BuildProjectService> {
    @AutoService
    private BuildProjectService buildProjectService;

    @Override
    protected BuildProjectService getService() {
        return buildProjectService;
    }
}