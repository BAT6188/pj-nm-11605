package com.harmonywisdom.dshbcbp.alert.dao;

import com.harmonywisdom.apportal.sdk.person.IPerson;
import com.harmonywisdom.apportal.sdk.person.PersonServiceUtil;
import com.harmonywisdom.dshbcbp.alert.bean.SysOperationLog;
import com.harmonywisdom.framework.dao.DefaultDAO;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class SysOperationLogDAO extends DefaultDAO<SysOperationLog, String> {

    /**
     * 基本操作日志
     * @param userId 操作人Id
     * @param opType 操作类型 Constants.OPTYPE_ADD_**
     * @param model 操作模块
     * @param content 操作内容
     * @return
     */
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
        return save(sol);
    }

    /**
     * 操作日志(表相关)
     * @param userId 操作人Id
     * @param opType 操作类型 Constants.OPTYPE_ADD_**
     * @param model 操作模块
     * @param content 操作内容
     * @param refTableName 操作关联表名
     * @param refTableId 操作数据ID
     * @param refUrl 扩展字段
     * @return
     */
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
        return save(sol);
    }
}