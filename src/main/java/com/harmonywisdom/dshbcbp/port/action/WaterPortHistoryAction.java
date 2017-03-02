package com.harmonywisdom.dshbcbp.port.action;

import com.harmonywisdom.dshbcbp.port.bean.GasPort;
import com.harmonywisdom.dshbcbp.port.bean.GasPortHistory;
import com.harmonywisdom.dshbcbp.port.bean.WaterPort;
import com.harmonywisdom.dshbcbp.port.bean.WaterPortHistory;
import com.harmonywisdom.dshbcbp.port.service.GasPortHistoryService;
import com.harmonywisdom.dshbcbp.port.service.GasPortService;
import com.harmonywisdom.dshbcbp.port.service.WaterPortHistoryService;
import com.harmonywisdom.dshbcbp.port.service.WaterPortService;
import com.harmonywisdom.dshbcbp.utils.MyDateUtils;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.*;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class WaterPortHistoryAction extends BaseAction<WaterPortHistory, WaterPortHistoryService> {
    @AutoService
    private WaterPortHistoryService waterPortHistoryService;

    @AutoService
    private GasPortHistoryService gasPortHistoryService;

    @AutoService
    private WaterPortService waterPortService;

    @Override
    protected WaterPortHistoryService getService() {
        return waterPortHistoryService;
    }

    public void test(){
//        waterPortHistoryService.saveTestWaterPortHistoryData(request.getParameter("s"),request.getParameter("e"));
//        gasPortHistoryService.saveTestGasPortHistoryData(request.getParameter("s"),request.getParameter("e"));
        gasPortHistoryService.saveGasPortHistoryDataList();
        waterPortHistoryService.saveWaterPortHistoryDataList();

    }


    @Override
    protected QueryCondition getQueryCondition() {
        String mobileOperType = request.getParameter("mobileOperType");
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
        condition.setPaging(getPaging());
        condition.setOrderBy("monitorTime", Direction.DESC);
        if (StringUtils.isNotEmpty(mobileOperType)){
            condition.setOrderBy("mobileTimestamp", Direction.DESC);
        }
        return condition;
    }

    public void listForMobile(){
        List<WaterPort> portList = waterPortService.find(" enterpriseId = ? ", entity.getEnterpriseId());
        QueryResult<WaterPortHistory> waterPortHistoryQueryResult = waterPortHistoryService.find(getQueryCondition());
        for (WaterPortHistory waterPortHistory : waterPortHistoryQueryResult.getRows()) {
            for (WaterPort waterPort : portList) {
                if(waterPort.getId().equals(waterPortHistory.getPortId())){
                    waterPortHistory.setName(waterPort.getName());
                    break;
                }
            }
        }
        write(waterPortHistoryQueryResult);
    }
}