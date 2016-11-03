package com.harmonywisdom.dshbcbp.port.action;


import com.harmonywisdom.dshbcbp.port.bean.AirQuality;
import com.harmonywisdom.dshbcbp.port.service.AirQualityService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

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


}