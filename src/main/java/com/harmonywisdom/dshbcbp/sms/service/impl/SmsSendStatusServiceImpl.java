package com.harmonywisdom.dshbcbp.sms.service.impl;

import com.harmonywisdom.dshbcbp.sms.bean.SmsSendStatus;
import com.harmonywisdom.dshbcbp.sms.dao.SmsSendStatusDAO;
import com.harmonywisdom.dshbcbp.sms.service.SmsSendStatusService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("smsSendStatusService")
public class SmsSendStatusServiceImpl extends BaseService<SmsSendStatus, String> implements SmsSendStatusService {
    @Autowired
    private SmsSendStatusDAO smsSendStatusDAO;

    @Override
    protected BaseDAO<SmsSendStatus, String> getDAO() {
        return smsSendStatusDAO;
    }
}