package com.harmonywisdom.dshbcbp.alert.action;

import com.harmonywisdom.dshbcbp.alert.bean.MessageTrace;
import com.harmonywisdom.dshbcbp.alert.service.MessageTraceService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class MessageTraceAction extends BaseAction<MessageTrace, MessageTraceService> {
    @AutoService
    private MessageTraceService messageTraceService;

    @Override
    protected MessageTraceService getService() {
        return messageTraceService;
    }
}