package com.harmonywisdom.dshbcbp.alert.action;

import com.harmonywisdom.dshbcbp.alert.bean.Message;
import com.harmonywisdom.dshbcbp.alert.bean.MessageTrace;
import com.harmonywisdom.dshbcbp.alert.service.MessageService;
import com.harmonywisdom.dshbcbp.alert.service.MessageTraceService;
import com.harmonywisdom.dshbcbp.common.dict.util.DateUtil;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageTraceAction extends BaseAction<MessageTrace, MessageTraceService> {
    @AutoService
    private MessageTraceService messageTraceService;

    @AutoService
    private MessageService messageService;

    @Override
    protected MessageTraceService getService() {
        return messageTraceService;
    }

    /**
     * 获取用户未消息数量
     */
    public void getNewMsgCountByUserId(){
        String userId = request.getParameter("userId");
        if(StringUtils.isNotBlank(userId)){
            int msgCount = getService().getNewMsgCountByUserId(userId);
            write(msgCount);
        }else{
            write(false);
        }
    }

    /**
     * 获取用户消息列表
     */
    public void getUserMsgList(){
        String userId = request.getParameter("userId");
        if(StringUtils.isNotBlank(userId)){//查询最新消息
            List<MessageTrace> messageTraces = getService().getNewMessagesByUserId(userId);
            write(messageTraces);
        }else{
            write(false);
        }
    }

    /**
     * 获取用户历史消息列表
     */
    public void getHistoryByUserId(){
        String userId = request.getParameter("userId");
        String oldMsgCreateTimeStr = request.getParameter("oldMsgCreateTime");
        if(StringUtils.isNotBlank(oldMsgCreateTimeStr) && StringUtils.isNotBlank(userId)){//查询历史消息
            Date oldMsgCreateTime = DateUtil.strToDate(oldMsgCreateTimeStr, "yyyy-MM-dd hh:mm:ss");
            List<MessageTrace> messageTraces = getService().getHistoryByUserId(userId,oldMsgCreateTime);
            write(messageTraces);
        }
    }

    public void setStatusReceived(){
        String[] ids = request.getParameterValues("ids");
        if (ids != null && ids.length > 0) {
            int count = getService().setStatusReceived(ids);
            write(count);
        }else{
            write(false);
        }

    }

}