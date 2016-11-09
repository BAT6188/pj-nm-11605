package com.harmonywisdom.dshbcbp.sms.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.harmonywisdom.dshbcbp.sms.bean.SmsRecord;
import com.harmonywisdom.dshbcbp.sms.bean.SmsSendStatus;
import com.harmonywisdom.dshbcbp.sms.service.SmsRecordService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.Direction;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryOperator;
import com.harmonywisdom.framework.dao.QueryParam;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class SmsRecordAction extends BaseAction<SmsRecord, SmsRecordService> {
    @AutoService
    private SmsRecordService smsRecordService;

    @Override
    protected SmsRecordService getService() {
        return smsRecordService;
    }

    @Override
    protected QueryCondition getQueryCondition() {
        QueryParam params = new QueryParam();
        if (StringUtils.isNotBlank(entity.getSenderId())) {
            params.andParam(new QueryParam("senderId", QueryOperator.LIKE, entity.getSenderId()));
        }

        if (entity.getSenderName() != null) {
            params.andParam(new QueryParam("senderName", QueryOperator.LIKE, entity.getSenderName()));
        }

        QueryCondition condition = new QueryCondition();
        if (params.getField() != null) {
            condition.setParam(params);
        }
        condition.setPaging(getPaging());
        condition.setOrderBy("sendTime", Direction.DESC);
        return condition;
    }

    /**
     * 发送短信
     */
    public void sendSms(){
        String receiversStr = request.getParameter("receivers");
        if (entity != null && StringUtils.isNotBlank(receiversStr)) {
            List<SmsSendStatus> receivers =JSONArray.parseArray(receiversStr, SmsSendStatus.class);
            List<SmsSendStatus> sendStatuses =getService().sendSms(entity, receivers);
            write(sendStatuses);
        }else{
            write(false);
        }

    }

}