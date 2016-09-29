package com.harmonywisdom.dshbcbp.composite.action;

import com.harmonywisdom.dshbcbp.composite.bean.ProjectEIA;
import com.harmonywisdom.dshbcbp.composite.service.ProjectEIAService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class ProjectEIAAction extends BaseAction<ProjectEIA, ProjectEIAService> {
    @AutoService
    private ProjectEIAService projectEIAService;

    @Override
    protected ProjectEIAService getService() {
        return projectEIAService;
    }
}