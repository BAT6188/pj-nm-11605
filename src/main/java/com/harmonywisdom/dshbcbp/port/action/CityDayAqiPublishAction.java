package com.harmonywisdom.dshbcbp.port.action;

import com.harmonywisdom.dshbcbp.common.dict.util.DateUtil;
import com.harmonywisdom.dshbcbp.port.bean.CityDayAqiPublish;
import com.harmonywisdom.dshbcbp.port.service.CityDayAqiPublishService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.*;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class CityDayAqiPublishAction extends BaseAction<CityDayAqiPublish, CityDayAqiPublishService> {
    @AutoService
    private CityDayAqiPublishService cityDayAqiPublishService;

    @Override
    protected CityDayAqiPublishService getService() {
        return cityDayAqiPublishService;
    }

    @Override
    public QueryCondition getQueryCondition(){
        QueryParam param = new QueryParam();

        String firstTime = request.getParameter("firstTime");
        String lastTime = request.getParameter("lastTime");
        String minValue = request.getParameter("minValue");
        String maxValue = request.getParameter("maxValue");
        Integer minValues = Integer.parseInt(minValue);
        Integer maxValues = Integer.parseInt(maxValue);

        if(StringUtils.isNotBlank(firstTime)){
            param.andParam(new QueryParam("TimePoint", QueryOperator.GE, DateUtil.strToDate(firstTime,"yyyy-MM-dd")));
        }
        if(StringUtils.isNotBlank(lastTime)){
            param.andParam(new QueryParam("TimePoint",QueryOperator.LE,DateUtil.strToDate(lastTime,"yyyy-MM-dd")));
        }
        if(StringUtils.isNotBlank(minValue)){
            param.andParam(new QueryParam("AQI",QueryOperator.GT,minValues));
        }
        if(StringUtils.isNotBlank(maxValue)){
            param.andParam(new QueryParam("AQI",QueryOperator.LE,maxValues));
        }

        QueryCondition condition = new QueryCondition();
        condition.setPaging(getPaging());
        condition.setOrderBy("TimePoint", Direction.DESC);
        if (param.getField() != null) {
            condition.setParam(param);
        }
        return condition;
    }

    /**
     * 同期对比查询空气质量表
     */
    public void airRatiolist(){

        Map<String,String> params = new HashMap<>();

        String startXdate = request.getParameter("startXdate");
        if(StringUtils.isNotBlank(startXdate)){
            params.put("startXdate",startXdate);
        }

        String lastXdate = request.getParameter("lastXdate");
        if(StringUtils.isNotBlank(lastXdate)){
            params.put("lastXdate",lastXdate);
        }

        String startSdate = request.getParameter("startSdate");
        if(StringUtils.isNotBlank(startSdate)){
            params.put("startSdate",startSdate);
        }

        String lastSdate = request.getParameter("lastSdate");
        if(StringUtils.isNotBlank(lastSdate)){
            params.put("lastSdate",lastSdate);
        }

        String minValue = request.getParameter("minValue");
        if(StringUtils.isNotBlank(minValue)){
            params.put("minValue",minValue);
        }

        String maxValue = request.getParameter("maxValue");
        if(StringUtils.isNotBlank(maxValue)){
            params.put("maxValue",maxValue);
        }

        QueryResult<CityDayAqiPublish> result = null;

        result = cityDayAqiPublishService.findAirRatio(params,getPaging());

        write(result);

    }

}