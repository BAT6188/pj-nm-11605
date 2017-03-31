package com.harmonywisdom.dshbcbp.alert.dao;

import com.harmonywisdom.dshbcbp.alert.bean.Message;
import com.harmonywisdom.dshbcbp.alert.bean.MessageTrace;
import com.harmonywisdom.framework.dao.DefaultDAO;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class MessageDAO extends DefaultDAO<Message, String> {

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
        this.save(message);
        Set<MessageTrace> msgTracees = new HashSet<>();
        for (MessageTrace receiver : receivers) {
            receiver.setMsgId(message.getId());
            receiver.setReceiveStatus(MessageTrace.RECEIVE_STATUS_UNRECEIVE);
            msgTracees.add(receiver);
        }
    }
}