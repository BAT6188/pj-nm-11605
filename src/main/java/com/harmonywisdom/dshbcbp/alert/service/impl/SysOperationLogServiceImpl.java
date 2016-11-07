package com.harmonywisdom.dshbcbp.alert.service.impl;

import com.harmonywisdom.apportal.sdk.person.IPerson;
import com.harmonywisdom.apportal.sdk.person.PersonServiceUtil;
import com.harmonywisdom.dshbcbp.alert.bean.SysOperationLog;
import com.harmonywisdom.dshbcbp.alert.dao.SysOperationLogDAO;
import com.harmonywisdom.dshbcbp.alert.service.SysOperationLogService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("sysOperationLogService")
public class SysOperationLogServiceImpl extends BaseService<SysOperationLog, String> implements SysOperationLogService {
    @Autowired
    private SysOperationLogDAO sysOperationLogDAO;

    @Override
    protected BaseDAO<SysOperationLog, String> getDAO() {
        return sysOperationLogDAO;
    }

    @Override
    public String insertBaseOpLog(String userId, String opType, String model, String content,String refTableId) {
        SysOperationLog sol = new SysOperationLog();
        IPerson iPerson = PersonServiceUtil.getPerson(userId);
        sol.setOpUser(iPerson.getUserName());
        sol.setOpUserId(userId);
        sol.setOpContent(content);
        sol.setOpType(opType);
        sol.setOpModule(model);
        sol.setRefTableId(refTableId);
        sol.setOpTime(new Date());
        return sysOperationLogDAO.save(sol);
    }

    @Override
    public String insertAllOpLog(String userId, String opType, String model, String content, String refTableName, String refTableId, String refUrl) {
        SysOperationLog sol = new SysOperationLog();
        IPerson iPerson = PersonServiceUtil.getPerson(userId);
        sol.setOpUser(iPerson.getUserName());
        sol.setOpUserId(userId);
        sol.setOpContent(content);
        sol.setOpType(opType);
        sol.setOpModule(model);
        sol.setOpTime(new Date());

        sol.setRefTableName(refTableName);
        sol.setRefTableId(refTableId);
        sol.setRefUrl(refUrl);
        return sysOperationLogDAO.save(sol);
    }
}