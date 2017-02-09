package com.harmonywisdom.dshbcbp.alert.service.impl;

import com.harmonywisdom.dshbcbp.alert.bean.Message;
import com.harmonywisdom.dshbcbp.alert.bean.MessageTrace;
import com.harmonywisdom.dshbcbp.alert.dao.MessageTraceDAO;
import com.harmonywisdom.dshbcbp.alert.service.MessageService;
import com.harmonywisdom.dshbcbp.alert.service.MessageTraceService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service("messageTraceService")
public class MessageTraceServiceImpl extends BaseService<MessageTrace, String> implements MessageTraceService {
    @Autowired
    private MessageTraceDAO messageTraceDAO;

    @Autowired
    private MessageService messageService;

    @Override
    protected BaseDAO<MessageTrace, String> getDAO() {
        return messageTraceDAO;
    }

    @Override
    public int getNewMsgCountByUserId(String userId) {
        if (StringUtils.isNotBlank(userId)) {
            List result = getDAO().queryNativeSQL("SELECT count(*) FROM HW_MESSAGE_TRACE t left join HW_MESSAGE m on t.MSG_ID = m.ID " +
                    "where RECEIVER_ID=?1 and RECEIVE_STATUS=?2 and m.ALERT_TIME < ?3",userId,MessageTrace.RECEIVE_STATUS_UNRECEIVE, new Date());
            if (result != null && result.size() > 0) {
                int msgCount = Integer.valueOf(result.get(0).toString());
                return msgCount;
            }
        }

        return 0;
    }

    @Override
    public List<MessageTrace> getNewMessagesByUserId(String userId,String isMobile) {
        if (StringUtils.isNotBlank(userId)) {
            List<MessageTrace> traces =new ArrayList<>();
            if ("1".equals(isMobile)){
                traces = getDAO().queryNativeSQL("select t.* from HW_MESSAGE_TRACE t left join HW_MESSAGE m " +
                                "on t.MSG_ID=m.id where t.RECEIVER_ID=?1 and t.MOBILE_RECEIVE_STATUS=?2 and m.ALERT_TIME < ?3 order by m.ALERT_TIME desc",
                        MessageTrace.class,
                        userId, MessageTrace.RECEIVE_STATUS_UNRECEIVE,new Date());
            }else {
                traces = getDAO().queryNativeSQL("select t.* from HW_MESSAGE_TRACE t left join HW_MESSAGE m " +
                                "on t.MSG_ID=m.id where t.RECEIVER_ID=?1 and t.RECEIVE_STATUS=?2 and m.ALERT_TIME < ?3 order by m.ALERT_TIME desc",
                        MessageTrace.class,
                        userId, MessageTrace.RECEIVE_STATUS_UNRECEIVE,new Date());
            }

            //查询消息
            for (MessageTrace trace : traces) {
                Message message = messageService.getMessageByTraceId(trace.getId());
                trace.setMessage(message);
            }
            return traces;
        }else{
            return null;
        }

    }

    @Override
    public List<MessageTrace> getHistoryByUserId(String userId, Date oldMsgAlertTime) {
        if (StringUtils.isNotBlank(userId)) {
            List<MessageTrace> traces = getDAO().queryNativeSQL("select t.* from HW_MESSAGE_TRACE t left join HW_MESSAGE m " +
                            "on t.MSG_ID=m.id where t.RECEIVER_ID=?1 and m.ALERT_TIME < ?2 order by m.ALERT_TIME desc limit 5",
                    MessageTrace.class,
                    userId,oldMsgAlertTime);
            //查询消息
            for (MessageTrace trace : traces) {
                Message message = messageService.getMessageByTraceId(trace.getId());
                trace.setMessage(message);
            }
            return traces;
        }else{
            return null;
        }
    }

    @Override
    public int setStatusReceived(String... ids) {
        if (ids != null && ids.length > 0) {
            return getDAO().executeJPQL("update MessageTrace set receiveStatus=?1,receiveTime=?2 where id in ?3", MessageTrace.RECEIVE_STATUS_RECEIVED,new Date(), Arrays.asList(ids));
        }else {
            return 0;
        }
    }

    @Override
    public int setMobileStatusReceived(String... ids) {
        if (ids != null && ids.length > 0) {
            return getDAO().executeJPQL("update MessageTrace set mobileReceiveStatus=?1,receiveStatus=?2,receiveTime=?3 where id in ?4", MessageTrace.RECEIVE_STATUS_RECEIVED,MessageTrace.RECEIVE_STATUS_RECEIVED,new Date(), Arrays.asList(ids));
        }else {
            return 0;
        }
    }



    @Override
    public boolean msgHasUnReceivedByBusinessId(String businessId) {
        Message msg = messageService.findByBusinessId(businessId);
        int unReceivedCount = 0;
        if (msg != null) {
            List list = getDAO().queryJPQL("select count(mt) from MessageTrace as mt where mt.msgId=?1 and mt.receiveStatus=?2",
                    msg.getId(), MessageTrace.RECEIVE_STATUS_UNRECEIVE);
            unReceivedCount = Integer.valueOf(list.get(0).toString());

        }
        boolean hasUnReceived = unReceivedCount > 0;
        return hasUnReceived;
    }

}