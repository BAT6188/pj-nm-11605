package com.harmonywisdom.dshbcbp.alert.service.impl;

import com.harmonywisdom.dshbcbp.alert.bean.Message;
import com.harmonywisdom.dshbcbp.alert.bean.MessageTrace;
import com.harmonywisdom.dshbcbp.alert.dao.MessageDAO;
import com.harmonywisdom.dshbcbp.alert.service.MessageService;
import com.harmonywisdom.dshbcbp.alert.service.MessageTraceService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("messageService")
public class MessageServiceImpl extends BaseService<Message, String> implements MessageService {
    @Autowired
    private MessageDAO messageDAO;

    @Autowired
    private MessageTraceService messageTraceService;

    @Override
    protected BaseDAO<Message, String> getDAO() {
        return messageDAO;
    }

    @Override
    public void sendMessage(Message message, List<MessageTrace> receivers) {
        if (receivers == null || receivers.size() <= 0) {
            return;
        }
        Date currentDate = new Date();
        //提醒时间默认为当前时间
        if (message.getAlertTime() == null) {
            message.setAlertTime(currentDate);
        }
        message.setCreateTime(currentDate);
        getDAO().save(message);
        Set<MessageTrace> msgTracees = new HashSet<>();
        for (MessageTrace receiver : receivers) {
            receiver.setMsgId(message.getId());
            receiver.setReceiveStatus(MessageTrace.RECEIVE_STATUS_UNRECEIVE);
            msgTracees.add(receiver);
        }
        messageTraceService.saveBatch(msgTracees);
    }


    @Override
    public Message getMessageByTraceId(String traceId){
        if (StringUtils.isNotBlank(traceId)) {
            List<Message> msges = getDAO().queryNativeSQL("select m.* from HW_MESSAGE_TRACE t left join HW_MESSAGE m on t.MSG_ID=m.id where t.id=?1",
                    Message.class,
                    traceId);
            if (msges != null && msges.size() > 0) {
                return msges.get(0);
            }
        }
        return null;
    }

    @Override
    public void deleteByBusinessId(String... ids) {
        if (ids == null || ids.length <= 0) {
            return;
        }
        getDAO().executeJPQL("delete from MessageTrace where msgId in (select id from Message where businessId in ?1)", Arrays.asList(ids));
        getDAO().executeJPQL("delete from Message where businessId in ?1", Arrays.asList(ids));
    }

}