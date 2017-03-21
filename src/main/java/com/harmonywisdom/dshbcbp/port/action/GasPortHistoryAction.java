package com.harmonywisdom.dshbcbp.port.action;

import com.harmonywisdom.dshbcbp.port.bean.GasPort;
import com.harmonywisdom.dshbcbp.port.bean.GasPortHistory;
import com.harmonywisdom.dshbcbp.port.service.GasPortHistoryService;
import com.harmonywisdom.dshbcbp.port.service.GasPortService;
import com.harmonywisdom.dshbcbp.utils.MyDateUtils;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.*;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class GasPortHistoryAction extends BaseAction<GasPortHistory, GasPortHistoryService> {
    @AutoService
    private GasPortHistoryService gasPortHistoryService;
    @AutoService
    private GasPortService gasPortService;

    @Override
    protected GasPortHistoryService getService() {
        return gasPortHistoryService;
    }

    @Override
    protected QueryCondition getQueryCondition() {
        QueryParam param = new QueryParam();
        if (StringUtils.isNotBlank(entity.getPortId())) {
            param.andParam(new QueryParam("portId", QueryOperator.EQ,entity.getPortId()));
        }
        if (StringUtils.isNotBlank(entity.getEnterpriseId())) {
            param.andParam(new QueryParam("enterpriseId", QueryOperator.EQ,entity.getEnterpriseId()));
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

    public void listForMobile(){
        List<GasPort> portList = gasPortService.find(" enterpriseId = ? ", entity.getEnterpriseId());
        QueryResult<GasPortHistory> gasPortHistoryQueryResult = gasPortHistoryService.find(getQueryCondition());
        for (GasPortHistory gasPortHistory : gasPortHistoryQueryResult.getRows()) {
            for (GasPort gasPort : portList) {
                if(gasPort.getId().equals(gasPortHistory.getPortId())){
                    gasPortHistory.setName(gasPort.getName());
                    break;
                }
            }
        }
        write(gasPortHistoryQueryResult);
    }
}