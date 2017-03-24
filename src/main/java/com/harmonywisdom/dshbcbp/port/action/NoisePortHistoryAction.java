package com.harmonywisdom.dshbcbp.port.action;

import com.harmonywisdom.dshbcbp.port.bean.NoisePortHistory;
import com.harmonywisdom.dshbcbp.port.service.NoisePortHistoryService;
import com.harmonywisdom.dshbcbp.utils.MyDateUtils;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.Direction;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryOperator;
import com.harmonywisdom.framework.dao.QueryParam;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

public class NoisePortHistoryAction extends BaseAction<NoisePortHistory, NoisePortHistoryService> {
    @AutoService
    private NoisePortHistoryService noisePortHistoryService;

    @Override
    protected NoisePortHistoryService getService() {
        return noisePortHistoryService;
    }

    @Override
    protected QueryCondition getQueryCondition() {
        QueryParam param = new QueryParam();

        String mobileOperType = request.getParameter("mobileOperType");

        if (StringUtils.isNotBlank(entity.getPortId())) {
            param.andParam(new QueryParam("portId", QueryOperator.EQ,entity.getPortId()));
        }
        if (StringUtils.isNotBlank(entity.getType())) {
            param.andParam(new QueryParam("type", QueryOperator.EQ,entity.getType()));
        }
        if (StringUtils.isNotBlank(entity.getName())) {
            param.andParam(new QueryParam("name", QueryOperator.LIKE,entity.getName()));
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
}