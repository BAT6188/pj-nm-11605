package com.harmonywisdom.dshbcbp.port.action;


import com.harmonywisdom.dshbcbp.port.bean.AirEquipment;
import com.harmonywisdom.dshbcbp.port.bean.AirEquipmentHistory;
import com.harmonywisdom.dshbcbp.port.service.AirEquipmentHistoryService;
import com.harmonywisdom.dshbcbp.port.service.AirEquipmentService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.Direction;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryOperator;
import com.harmonywisdom.framework.dao.QueryParam;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang3.StringUtils;



public class AirEquipmentHistoryAction extends BaseAction<AirEquipmentHistory, AirEquipmentHistoryService> {
    @AutoService
    private AirEquipmentHistoryService airEquipmentHistoryService;

    @AutoService
    private AirEquipmentService airEquipmentService;

    @Override
    protected AirEquipmentHistoryService getService() {
        return airEquipmentHistoryService;
    }

    @Override
    protected QueryCondition getQueryCondition() {
        QueryParam param = new QueryParam();

        if (StringUtils.isNotBlank(entity.getAirMonitoringName())) {
            param.andParam(new QueryParam("airMonitoringName", QueryOperator.LIKE,"%"+entity.getAirMonitoringName()+"%"));
        }
        QueryCondition condition = new QueryCondition();
        if (param.getField() != null) {
            condition.setParam(param);
        }
        condition.setPaging(getPaging());
        condition.setOrderBy("airMonitoringName", Direction.DESC);
        return condition;
    }

}