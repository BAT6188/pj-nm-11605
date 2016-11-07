package com.harmonywisdom.dshbcbp.alert.action;

import com.harmonywisdom.dshbcbp.alert.bean.SysOperationLog;
import com.harmonywisdom.dshbcbp.alert.service.SysOperationLogService;
import com.harmonywisdom.dshbcbp.utils.MyDateUtils;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.Direction;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryOperator;
import com.harmonywisdom.framework.dao.QueryParam;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

public class SysOperationLogAction extends BaseAction<SysOperationLog, SysOperationLogService> {
    @AutoService
    private SysOperationLogService sysOperationLogService;

    @Override
    protected SysOperationLogService getService() {
        return sysOperationLogService;
    }

    @Override
    protected QueryCondition getQueryCondition() {
        QueryParam param = new QueryParam();
        if (StringUtils.isNotBlank(entity.getOpModule())) {
            param.andParam(new QueryParam("opModule", QueryOperator.LIKE,"%"+entity.getOpModule()+"%"));
        }
        if (StringUtils.isNotBlank(entity.getOpUser())) {
            param.andParam(new QueryParam("opUser", QueryOperator.LIKE,"%"+entity.getOpUser()+"%"));
        }
        if (StringUtils.isNotBlank(entity.getOpType())) {
            param.andParam(new QueryParam("opType", QueryOperator.EQ,entity.getOpType()));
        }
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        if(StringUtils.isNotBlank(startTime)){
            param.andParam(new QueryParam("opTime", QueryOperator.GE, MyDateUtils.getFullDate(startTime,true)));
        }
        if(StringUtils.isNotBlank(endTime)){
            param.andParam(new QueryParam("opTime", QueryOperator.LE,MyDateUtils.getFullDate(endTime,false)));
        }

        QueryCondition condition = new QueryCondition();
        if (param.getField() != null) {
            condition.setParam(param);
        }
        condition.setPaging(getPaging());
        condition.setOrderBy("opTime", Direction.DESC);
        return condition;
    }
}