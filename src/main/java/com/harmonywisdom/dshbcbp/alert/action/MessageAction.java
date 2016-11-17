package com.harmonywisdom.dshbcbp.alert.action;

import com.alibaba.fastjson.JSONArray;
import com.harmonywisdom.dshbcbp.alert.bean.Message;
import com.harmonywisdom.dshbcbp.alert.bean.MessageTrace;
import com.harmonywisdom.dshbcbp.alert.service.MessageService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class MessageAction extends BaseAction<Message, MessageService> {
    @AutoService
    private MessageService messageService;

    @Override
    protected MessageService getService() {
        return messageService;
    }

    /**
     * 发送系统消息
     */
    public void sendMessage(){
        String receiversStr = request.getParameter("receivers");
        if (entity != null && StringUtils.isNotBlank(receiversStr)) {
            List<MessageTrace> receivers = JSONArray.parseArray(receiversStr, MessageTrace.class);
            getService().sendMessage(entity, receivers);
            write(true);
        }else{
            write(false);
        }
    }

    /**
     * 设置当前session 是否消息提醒
     */
    public void setIsAlert(){
        String isAlert = request.getParameter("isAlert");
        request.getSession().setAttribute("msgIsAlert", isAlert);
        write(true);
    }
}