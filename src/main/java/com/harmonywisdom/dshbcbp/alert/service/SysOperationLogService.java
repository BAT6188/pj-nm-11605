package com.harmonywisdom.dshbcbp.alert.service;

import com.harmonywisdom.dshbcbp.alert.bean.SysOperationLog;
import com.harmonywisdom.framework.service.IBaseService;

public interface SysOperationLogService extends IBaseService<SysOperationLog, String> {

    /**
     * 基本操作日志
     * @param userId 操作人Id
     * @param opType 操作类型 Constants.OPTYPE_ADD_**
     * @param model 操作模块
     * @param content 操作内容
     * @return
     */
    String insertBaseOpLog(String userId,String opType,String model,String content,String refTableId);

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
    String insertAllOpLog(String userId,String opType,String model,String content,String refTableName,String refTableId,String refUrl);
}