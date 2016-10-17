package com.harmonywisdom.dshbcbp.sms.action;

import com.harmonywisdom.dshbcbp.sms.bean.SmsSendStatus;
import com.harmonywisdom.dshbcbp.sms.service.SmsSendStatusService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryOperator;
import com.harmonywisdom.framework.dao.QueryParam;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

public class SmsSendStatusAction extends BaseAction<SmsSendStatus, SmsSendStatusService> {
    @AutoService
    private SmsSendStatusService smsSendStatusService;

    @Override
    protected SmsSendStatusService getService() {
        return smsSendStatusService;
    }

    @Override
    protected QueryCondition getQueryCondition() {
        QueryParam param = new QueryParam();
        if (StringUtils.isNotBlank(entity.getSmsRecordId())) {
            param.andParam(new QueryParam("smsRecordId", QueryOperator.EQ, entity.getSmsRecordId()));
        }
        QueryCondition condition = new QueryCondition();
        condition.setPaging(getPaging());
        if (param.getField() != null) {
            condition.setParam(param);
        }
        return condition;
    }
}