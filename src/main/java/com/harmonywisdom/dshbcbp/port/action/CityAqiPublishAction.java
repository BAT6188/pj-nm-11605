package com.harmonywisdom.dshbcbp.port.action;

import com.harmonywisdom.dshbcbp.port.bean.CityAqiPublish;
import com.harmonywisdom.dshbcbp.port.service.CityAqiPublishService;
import com.harmonywisdom.dshbcbp.utils.MyDateUtils;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.Direction;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryOperator;
import com.harmonywisdom.framework.dao.QueryParam;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedHashMap;

public class CityAqiPublishAction extends BaseAction<CityAqiPublish, CityAqiPublishService> {
    @AutoService
    private CityAqiPublishService cityAqiPublishService;

    @Override
    protected CityAqiPublishService getService() {
        return cityAqiPublishService;
    }

    @Override
    protected QueryCondition getQueryCondition() {
        QueryParam param = new QueryParam();
        String mobileOperType = request.getParameter("mobileOperType");

        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        if(StringUtils.isNotBlank(startTime)){
            param.andParam(new QueryParam("TimePoint", QueryOperator.GE, MyDateUtils.getFullDate(startTime,true)));
        }
        if(StringUtils.isNotBlank(endTime)){
            param.andParam(new QueryParam("TimePoint", QueryOperator.LE,MyDateUtils.getFullDate(endTime,false)));
        }
        if(StringUtils.isNotBlank(entity.getQuality())){
            param.andParam(new QueryParam("quality", QueryOperator.EQ,entity.getQuality()));
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
            orders.put("TimePoint",Direction.DESC);
            //orders.put("",Direction.DESC);
            condition.setOrderBys(orders);
        }
        return condition;
    }
}