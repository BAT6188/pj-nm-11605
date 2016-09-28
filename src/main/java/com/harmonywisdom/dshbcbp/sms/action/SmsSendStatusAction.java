package com.harmonywisdom.dshbcbp.sms.action;

import com.harmonywisdom.dshbcbp.sms.bean.SmsSendStatus;
import com.harmonywisdom.dshbcbp.sms.service.SmsSendStatusService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class SmsSendStatusAction extends BaseAction<SmsSendStatus, SmsSendStatusService> {
    @AutoService
    private SmsSendStatusService smsSendStatusService;

    @Override
    protected SmsSendStatusService getService() {
        return smsSendStatusService;
    }
}