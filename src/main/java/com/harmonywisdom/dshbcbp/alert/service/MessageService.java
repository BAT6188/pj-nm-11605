package com.harmonywisdom.dshbcbp.alert.service;

import com.harmonywisdom.dshbcbp.alert.bean.Message;
import com.harmonywisdom.dshbcbp.alert.bean.MessageTrace;
import com.harmonywisdom.framework.service.IBaseService;

import java.util.List;

public interface MessageService extends IBaseService<Message, String> {

    /**
     * 发送系统消息
     * @param message 消息
     * @param receivers 接收人列表
     */
    void sendMessage(Message message, List<MessageTrace> receivers);

    /**
     * 根据消息跟踪id 得到消息
     * @param traceId
     * @return
     */
    Message getMessageByTraceId(String traceId);

    /**
     * 根据BusinessId删除消息
     * @param ids
     */
    void deleteByBusinessId(String ...ids);

    Message findByBusinessId(String businessId);


}