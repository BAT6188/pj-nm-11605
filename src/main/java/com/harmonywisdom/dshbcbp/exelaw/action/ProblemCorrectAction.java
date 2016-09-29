package com.harmonywisdom.dshbcbp.exelaw.action;

import com.harmonywisdom.dshbcbp.exelaw.bean.ProblemCorrect;
import com.harmonywisdom.dshbcbp.exelaw.service.ProblemCorrectService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class ProblemCorrectAction extends BaseAction<ProblemCorrect, ProblemCorrectService> {
    @AutoService
    private ProblemCorrectService problemCorrectService;

    @Override
    protected ProblemCorrectService getService() {
        return problemCorrectService;
    }
}