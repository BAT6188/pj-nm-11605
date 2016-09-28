package com.harmonywisdom.dshbcbp.enterprise.action;

import com.harmonywisdom.dshbcbp.enterprise.bean.PollutantPayment;
import com.harmonywisdom.dshbcbp.enterprise.service.PollutantPaymentService;
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