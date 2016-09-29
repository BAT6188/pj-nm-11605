package com.harmonywisdom.dshbcbp.exelaw.action;

import com.harmonywisdom.dshbcbp.exelaw.bean.LetterSue;
import com.harmonywisdom.dshbcbp.exelaw.service.LetterSueService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class LetterSueAction extends BaseAction<LetterSue, LetterSueService> {
    @AutoService
    private LetterSueService letterSueService;

    @Override
    protected LetterSueService getService() {
        return letterSueService;
    }
}