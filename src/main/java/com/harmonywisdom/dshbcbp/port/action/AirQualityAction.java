package com.harmonywisdom.dshbcbp.port.action;


import com.harmonywisdom.dshbcbp.common.dict.util.DateUtil;
import com.harmonywisdom.dshbcbp.port.bean.AirQuality;
import com.harmonywisdom.dshbcbp.port.service.AirQualityService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.*;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AirQualityAction extends BaseAction<AirQuality, AirQualityService> {
    @AutoService
    private AirQualityService airQualityService;

    @Override
    protected AirQualityService getService() {
        return airQualityService;
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
            param.andParam(new QueryParam("rec_Time", QueryOperator.GE, DateUtil.strToDate(firstTime,"yyyy-MM-dd")));
        }
        if(StringUtils.isNotBlank(lastTime)){
            param.andParam(new QueryParam("rec_Time",QueryOperator.LE,DateUtil.strToDate(lastTime,"yyyy-MM-dd")));
        }
        if(StringUtils.isNotBlank(minValue)){
            param.andParam(new QueryParam("airValue",QueryOperator.GT,minValues));
        }
        if(StringUtils.isNotBlank(maxValue)){
            param.andParam(new QueryParam("airValue",QueryOperator.LE,maxValues));
        }

        QueryCondition condition = new QueryCondition();
        condition.setPaging(getPaging());
        condition.setOrderBy("rec_Time", Direction.DESC);
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

        QueryResult<AirQuality> result = null;

        result = airQualityService.findAirRatio(params,getPaging());

        write(result);



    }


    /**
     * highchart
     * 空气质量获取后台数据
     */
    public void getColumnHighChart(){
        String startYdate = request.getParameter("startYdate");
        String lastYdate = request.getParameter("lastYdate");
        String airType = request.getParameter("airType");

        Map<String,Object> result = new HashMap<>();

        List<Object[]> list = airQualityService.findByAirData(startYdate,lastYdate,airType);
        if(list != null && list.size()>0){
            Object[] xlist = new Object[list.size()];
            Object[] ylist = new Object[list.size()];
            for(int i = 0; i < list.size(); i++ ){
                Object[] oo = list.get(i);
                xlist[i] = String.valueOf(oo[0]);
                ylist[i] = String.valueOf(oo[1]);
            }
            result.put("x",xlist);
            result.put("y",ylist);
        }
        write(result);

    }

    /**
     * highchart
     * 空气质量同期对比分析获取后台数据
     */
    public void getColumnAirRatio(){
        String startXdate= request.getParameter("startXdate");
        String lastXdate= request.getParameter("lastXdate");
        String startSdate= request.getParameter("startSdate");
        String lastSdate= request.getParameter("lastSdate");
        String airType= request.getParameter("airType");

        Map<String,Object> result = new HashMap<>();

        List<Object[]> list = airQualityService.findByAirRadioData(startXdate,lastXdate,startSdate,lastSdate,airType);
        if(list != null && list.size()>0){
            Object[] xList = new Object[list.size()];
            Object[] aList = new Object[list.size()];
            Object[] bList = new Object[list.size()];
            for(int i = 0;i < list.size();i++){
                Object[] cc = list.get(i);
                xList[i] = String.valueOf(cc[0]);
                aList[i] = String.valueOf(cc[1]);
                bList[i] = String.valueOf(cc[2]);
            }
            result.put("x",xList);
            result.put("y1",aList);
            result.put("y2",bList);
        }
        write(result);

    }

    /**
     * 首页获取最新天气信息
     */
    public void realTimeAirIndex(){
        AirQuality airQuality = airQualityService.realTimeAir();
        write(airQuality);
    }



}