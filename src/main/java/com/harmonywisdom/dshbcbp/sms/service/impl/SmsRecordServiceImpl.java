package com.harmonywisdom.dshbcbp.sms.service.impl;

import com.harmonywisdom.dshbcbp.sms.bean.SmsRecord;
import com.harmonywisdom.dshbcbp.sms.bean.SmsSendStatus;
import com.harmonywisdom.dshbcbp.sms.dao.SmsRecordDAO;
import com.harmonywisdom.dshbcbp.sms.service.SmsRecordService;
import com.harmonywisdom.dshbcbp.sms.service.SmsSendStatusService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("smsRecordService")
public class SmsRecordServiceImpl extends BaseService<SmsRecord, String> implements SmsRecordService {
    @Autowired
    private SmsRecordDAO smsRecordDAO;

    @Autowired
    private SmsSendStatusService smsSendStatusService;

    @Override
    protected BaseDAO<SmsRecord, String> getDAO() {
        return smsRecordDAO;
    }

    @Override
    public List<SmsSendStatus> sendSms(SmsRecord smsRsecord, List<SmsSendStatus> receivers) {
        if (receivers == null || receivers.size() <= 0) {
            return null;
        }
        smsRsecord.setSendTime(new Date());
        getDAO().save(smsRsecord);
        Set<SmsSendStatus> statuses = new HashSet<>();
        for (SmsSendStatus status : receivers) {
            status.setSmsRecordId(smsRsecord.getId());
            status.setStatus(SmsSendStatus.SEND_STATUS_SENT);
            statuses.add(status);
        }
        smsSendStatusService.saveBatch(statuses);
        return receivers;
    }
}