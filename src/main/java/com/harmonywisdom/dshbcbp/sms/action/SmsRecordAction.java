package com.harmonywisdom.dshbcbp.sms.action;

import com.harmonywisdom.dshbcbp.sms.bean.SmsRecord;
import com.harmonywisdom.dshbcbp.sms.service.SmsRecordService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.Direction;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryOperator;
import com.harmonywisdom.framework.dao.QueryParam;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

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
}