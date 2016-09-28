package com.harmonywisdom.dshbcbp.alert.service.impl;

import com.harmonywisdom.dshbcbp.alert.bean.Message;
import com.harmonywisdom.dshbcbp.alert.dao.MessageDAO;
import com.harmonywisdom.dshbcbp.alert.service.MessageService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("messageService")
public class MessageServiceImpl extends BaseService<Message, String> implements MessageService {
    @Autowired
    private MessageDAO messageDAO;

    @Override
    protected BaseDAO<Message, String> getDAO() {
        return messageDAO;
    }
}