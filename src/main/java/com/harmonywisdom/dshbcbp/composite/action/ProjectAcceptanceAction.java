package com.harmonywisdom.dshbcbp.composite.action;

import com.harmonywisdom.dshbcbp.composite.bean.ProjectAcceptance;
import com.harmonywisdom.dshbcbp.composite.service.ProjectAcceptanceService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class ProjectAcceptanceAction extends BaseAction<ProjectAcceptance, ProjectAcceptanceService> {
    @AutoService
    private ProjectAcceptanceService projectAcceptanceService;

    @Override
    protected ProjectAcceptanceService getService() {
        return projectAcceptanceService;
    }
}