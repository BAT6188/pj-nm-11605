package com.harmonywisdom.dshbcbp.sms.service.impl;

import com.harmonywisdom.dshbcbp.sms.bean.SmsRecord;
import com.harmonywisdom.dshbcbp.sms.bean.SmsSendStatus;
import com.harmonywisdom.dshbcbp.sms.dao.SmsRecordDAO;
import com.harmonywisdom.dshbcbp.sms.service.SmsRecordService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("smsRecordService")
public class SmsRecordServiceImpl extends BaseService<SmsRecord, String> implements SmsRecordService {
    @Autowired
    private SmsRecordDAO smsRecordDAO;

    @Override
    protected BaseDAO<SmsRecord, String> getDAO() {
        return smsRecordDAO;
    }

    @Override
    public List<SmsSendStatus> sendSms(SmsRecord smsRsecord, List<SmsSendStatus> receivers) {
        smsRsecord.setSendTime(new Date());
        return null;
    }
}