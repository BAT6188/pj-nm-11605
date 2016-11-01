package com.harmonywisdom.dshbcbp.port.action;

import com.harmonywisdom.dshbcbp.port.bean.DustPortHistory;
import com.harmonywisdom.dshbcbp.port.service.DustPortHistoryService;
import com.harmonywisdom.dshbcbp.utils.MyDateUtils;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.Direction;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryOperator;
import com.harmonywisdom.framework.dao.QueryParam;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

public class DustPortHistoryAction extends BaseAction<DustPortHistory, DustPortHistoryService> {
    @AutoService
    private DustPortHistoryService dustPortHistoryService;

    @Override
    protected DustPortHistoryService getService() {
        return dustPortHistoryService;
    }

    @Override
    protected QueryCondition getQueryCondition() {
        QueryParam param = new QueryParam();
        if (StringUtils.isNotBlank(entity.getPortId())) {
            param.andParam(new QueryParam("portId", QueryOperator.EQ,entity.getPortId()));
        }
        if (StringUtils.isNotBlank(entity.getName())) {
            param.andParam(new QueryParam("name", QueryOperator.EQ,entity.getName()));
        }
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        if(StringUtils.isNotBlank(startTime)){
            param.andParam(new QueryParam("monitorTime", QueryOperator.GE, MyDateUtils.getFullDate(startTime,true)));
        }
        if(StringUtils.isNotBlank(endTime)){
            param.andParam(new QueryParam("monitorTime", QueryOperator.LE,MyDateUtils.getFullDate(endTime,false)));
        }

        QueryCondition condition = new QueryCondition();
        if (param.getField() != null) {
            condition.setParam(param);
        }
        condition.setPaging(getPaging());
        condition.setOrderBy("monitorTime", Direction.DESC);
        return condition;
    }
}