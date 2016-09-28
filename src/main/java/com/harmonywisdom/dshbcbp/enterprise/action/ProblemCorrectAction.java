package com.harmonywisdom.dshbcbp.enterprise.action;

import com.harmonywisdom.dshbcbp.enterprise.bean.ProblemCorrect;
import com.harmonywisdom.dshbcbp.enterprise.service.ProblemCorrectService;
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