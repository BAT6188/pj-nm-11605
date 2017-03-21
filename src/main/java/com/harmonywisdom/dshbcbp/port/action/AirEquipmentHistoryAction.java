package com.harmonywisdom.dshbcbp.port.action;


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

import java.util.LinkedHashMap;


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

        String mobileOperType = request.getParameter("mobileOperType");

        if (StringUtils.isNotBlank(entity.getAirMonitoringName())) {
            param.andParam(new QueryParam("airMonitoringName", QueryOperator.LIKE,"%"+entity.getAirMonitoringName()+"%"));
        }
        if(StringUtils.isNotBlank(entity.getMonitoringNumber())){
            param.andParam(new QueryParam("monitoringNumber", QueryOperator.EQ,entity.getMonitoringNumber()));
        }
        if(entity.getMonitoringTime()!=null){
            param.andParam(new QueryParam("monitoringTime", QueryOperator.EQ,entity.getMonitoringTime()));
        }

        if("1".equals(mobileOperType)){//下拉
//            log.debug("下拉："+DateUtil.dateToStr(entity.getMobileTimestamp(),"yyyy-MM-dd HH:mm:ss"));
            if (null!=entity.getMobileTimestamp()){
                param.andParam(new QueryParam("mobileTimestamp",QueryOperator.GT, entity.getMobileTimestamp()));
            }
        }else if("2".equals(mobileOperType)){//上拉
//            log.debug("上拉："+DateUtil.dateToStr(entity.getMobileTimestamp(),"yyyy-MM-dd HH:mm:ss"));
            if (null!=entity.getMobileTimestamp()){
                param.andParam(new QueryParam("mobileTimestamp",QueryOperator.LT, entity.getMobileTimestamp()));
            }
        }


        QueryCondition condition = new QueryCondition();
        if (param.getField() != null) {
            condition.setParam(param);
        }
        LinkedHashMap<String, Direction> orders = new LinkedHashMap<>();
        condition.setPaging(getPaging());
        if (StringUtils.isNotEmpty(mobileOperType)){
            condition.setOrderBy("mobileTimestamp", Direction.DESC);
        }else{
            orders.put("monitoringTime",Direction.DESC);
            //orders.put("",Direction.DESC);
            condition.setOrderBys(orders);
        }
        return condition;
    }

    public void findBySimple(){
        write(airEquipmentHistoryService.findBySample(entity));
    }
}
