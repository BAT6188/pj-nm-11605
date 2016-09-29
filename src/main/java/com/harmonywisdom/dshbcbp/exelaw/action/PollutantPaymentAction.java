package com.harmonywisdom.dshbcbp.exelaw.action;

import com.harmonywisdom.dshbcbp.exelaw.bean.PollutantPayment;
import com.harmonywisdom.dshbcbp.exelaw.service.PollutantPaymentService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class PollutantPaymentAction extends BaseAction<PollutantPayment, PollutantPaymentService> {
    @AutoService
    private PollutantPaymentService pollutantPaymentService;

    @Override
    protected PollutantPaymentService getService() {
        return pollutantPaymentService;
    }
}