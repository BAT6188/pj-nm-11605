package com.harmonywisdom.dshbcbp.utils;

import com.harmonywisdom.apportal.sdk.person.IPerson;
import com.harmonywisdom.apportal.sdk.person.PersonServiceUtil;
import com.harmonywisdom.dshbcbp.alert.bean.SysOperationLog;
import com.harmonywisdom.dshbcbp.alert.service.SysOperationLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/7.
 */
@Service
public class CommonUtil {
    private static SysOperationLogService sysOperationLogService;

    @Resource
    public void setDataService(SysOperationLogService sysOperationLogService) {
        CommonUtil.sysOperationLogService = sysOperationLogService;
    }

    /**
     * 操作日志
     * @param request
     * @param opType 操作类型 Constants.OPTYPE_ADD_**
     * @param model 操作模块
     * @param content 操作内容
     * @return
     */
    public static String commonOperationLog(HttpServletRequest request, String opType, String model, String content,String refTableId){
        IPerson iPerson = ApportalUtil.getPerson(request);
        return insertBaseOpLog(iPerson.getPersonId(),opType,model,content,refTableId);
    }

    /**
     * 基本操作日志
     * @param userId 操作人Id
     * @param opType 操作类型 Constants.OPTYPE_ADD_**
     * @param model 操作模块
     * @param content 操作内容
     * @return
     */
    public static String insertBaseOpLog(String userId, String opType, String model, String content,String refTableId) {
        SysOperationLog sol = new SysOperationLog();
        IPerson iPerson = PersonServiceUtil.getPerson(userId);
        sol.setOpUser(iPerson.getUserName());
        sol.setOpUserId(userId);
        sol.setOpContent(content);
        sol.setOpType(opType);
        sol.setOpModule(model);
        sol.setRefTableId(refTableId);
        sol.setOpTime(new Date());
        return sysOperationLogService.save(sol);
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
    public static String insertAllOpLog(String userId, String opType, String model, String content, String refTableName, String refTableId, String refUrl) {
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
        return sysOperationLogService.save(sol);
    }
}
