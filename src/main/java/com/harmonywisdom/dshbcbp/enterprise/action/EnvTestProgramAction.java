package com.harmonywisdom.dshbcbp.enterprise.action;

import com.harmonywisdom.dshbcbp.enterprise.bean.EnvTestProgram;
import com.harmonywisdom.dshbcbp.enterprise.service.EnvTestProgramService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class EnvTestProgramAction extends BaseAction<EnvTestProgram, EnvTestProgramService> {
    @AutoService
    private EnvTestProgramService envTestProgramService;

    @Override
    protected EnvTestProgramService getService() {
        return envTestProgramService;
    }
}