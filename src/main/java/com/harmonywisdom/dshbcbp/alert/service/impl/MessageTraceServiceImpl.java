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
            List result = getDAO().queryJPQL("select count(*) from MessageTrace where receiverId=?1 and receiveStatus=?2",userId,MessageTrace.RECEIVE_STATUS_UNRECEIVE);
            if (result != null && result.size() > 0) {
                int msgCount = Integer.valueOf(result.get(0).toString());
                return msgCount;
            }
        }

        return 0;
    }

    @Override
    public List<MessageTrace> getNewMessagesByUserId(String userId) {
        if (StringUtils.isNotBlank(userId)) {
            List<MessageTrace> traces = getDAO().queryNativeSQL("select t.* from HW_MESSAGE_TRACE t left join HW_MESSAGE m " +
                            "on t.MSG_ID=m.id where t.RECEIVER_ID=?1 and t.RECEIVE_STATUS=?2 order by m.CREATE_TIME desc",
                    MessageTrace.class,
                    userId, MessageTrace.RECEIVE_STATUS_UNRECEIVE);
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
    public List<MessageTrace> getHistoryByUserId(String userId, Date oldMsgCreateTime) {
        if (StringUtils.isNotBlank(userId)) {
            List<MessageTrace> traces = getDAO().queryNativeSQL("select t.* from HW_MESSAGE_TRACE t left join HW_MESSAGE m " +
                            "on t.MSG_ID=m.id where t.RECEIVER_ID=?1 and m.CREATE_TIME < ?2 order by m.CREATE_TIME desc limit 5",
                    MessageTrace.class,
                    userId,oldMsgCreateTime);
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
            return getDAO().executeJPQL("update MessageTrace set receiveStatus=?1 where id in ?2", MessageTrace.RECEIVE_STATUS_RECEIVED, Arrays.asList(ids));
        }else {
            return 0;
        }


    }

}