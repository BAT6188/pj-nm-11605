package com.harmonywisdom.dshbcbp.sms.action;

import com.harmonywisdom.dshbcbp.sms.bean.SmsRecord;
import com.harmonywisdom.dshbcbp.sms.service.SmsRecordService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class SmsRecordAction extends BaseAction<SmsRecord, SmsRecordService> {
    @AutoService
    private SmsRecordService smsRecordService;

    @Override
    protected SmsRecordService getService() {
        return smsRecordService;
    }
}