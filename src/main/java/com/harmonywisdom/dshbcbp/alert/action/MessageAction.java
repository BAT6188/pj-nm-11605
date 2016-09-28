package com.harmonywisdom.dshbcbp.alert.action;

import com.harmonywisdom.dshbcbp.alert.bean.Message;
import com.harmonywisdom.dshbcbp.alert.service.MessageService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class MessageAction extends BaseAction<Message, MessageService> {
    @AutoService
    private MessageService messageService;

    @Override
    protected MessageService getService() {
        return messageService;
    }
}