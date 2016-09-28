package com.harmonywisdom.dshbcbp.enterprise.action;

import com.harmonywisdom.dshbcbp.enterprise.bean.LetterSue;
import com.harmonywisdom.dshbcbp.enterprise.service.LetterSueService;
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