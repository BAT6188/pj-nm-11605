package com.harmonywisdom.dshbcbp.sms.service;

import com.harmonywisdom.dshbcbp.sms.bean.SmsRecord;
import com.harmonywisdom.dshbcbp.sms.bean.SmsSendStatus;
import com.harmonywisdom.framework.service.IBaseService;

import java.util.List;
import java.util.Map;

public interface SmsRecordService extends IBaseService<SmsRecord, String> {

    /**
     * 发送短信
     * @param smsRsecord 短信记录信息
     * @param receivers 接收人list
     * @return 短信接收状态
     */
    List<SmsSendStatus> sendSms(SmsRecord smsRsecord, List<SmsSendStatus> receivers);
}