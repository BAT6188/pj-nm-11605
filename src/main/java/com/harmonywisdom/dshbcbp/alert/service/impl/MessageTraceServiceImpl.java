package com.harmonywisdom.dshbcbp.alert.service.impl;

import com.harmonywisdom.dshbcbp.alert.bean.MessageTrace;
import com.harmonywisdom.dshbcbp.alert.dao.MessageTraceDAO;
import com.harmonywisdom.dshbcbp.alert.service.MessageTraceService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("messageTraceService")
public class MessageTraceServiceImpl extends BaseService<MessageTrace, String> implements MessageTraceService {
    @Autowired
    private MessageTraceDAO messageTraceDAO;

    @Override
    protected BaseDAO<MessageTrace, String> getDAO() {
        return messageTraceDAO;
    }
}