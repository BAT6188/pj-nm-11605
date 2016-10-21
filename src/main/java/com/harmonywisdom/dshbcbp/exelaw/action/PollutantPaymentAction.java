package com.harmonywisdom.dshbcbp.exelaw.action;

import com.harmonywisdom.dshbcbp.exelaw.bean.PollutantPayment;
import com.harmonywisdom.dshbcbp.exelaw.service.PollutantPaymentService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PollutantPaymentAction extends BaseAction<PollutantPayment, PollutantPaymentService> {
    @AutoService
    private PollutantPaymentService pollutantPaymentService;

    @Override
    protected PollutantPaymentService getService() {
        return pollutantPaymentService;
    }

    /**
     * 排污统计
     * highchart获取柱状图数据,折线图数据
     */
    public void getSewageColumn() throws ParseException {
        String startYdate = request.getParameter("startYdate");
        String lastYdate = request.getParameter("lastYdate");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date firstTime = sdf.parse(startYdate);
        Date lastTime = sdf.parse(lastYdate);
        Map<String, Object> result = new HashMap<String, Object>();


        List<Object[]> list = pollutantPaymentService.findByColumnChart(firstTime,lastTime);
        if (list != null && list.size() > 0) {
            Object[] xlist = new Object[list.size()];
            Object[] y1list = new Object[list.size()];
            Object[] y21list = new Object[list.size()];
            for (int i = 0; i < list.size(); i++) {
                Object[] oo = list.get(i);
                xlist[i] = String.valueOf(oo[0]);
                y1list[i] = String.valueOf(oo[1]);
                y21list[i] = String.valueOf(oo[2]);

            }
            result.put("x", xlist);
            result.put("y1", y1list);
            result.put("y2", y21list);
        }
        write(result);

    }

    /**
     * 排污统计
     * highchart获取饼状图数据
     */
    public void getSewagePie() throws ParseException {
        String startYdate = request.getParameter("startYdate");
        String lastYdate = request.getParameter("lastYdate");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date firstTime = sdf.parse(startYdate);
        Date endTime = sdf.parse(lastYdate);
        Map<String,Object> result = new HashMap<String,Object>();

        List<Object[]> list = pollutantPaymentService.findByPieChart(firstTime,endTime);
        if(list !=null && list.size()>0){
            Object[] xlist = new Object[list.size()];
            Object[] ylist = new Object[list.size()];
            for(int i=0;i<list.size();i++){
                Object[] oo = list.get(i);
                xlist[i] = String.valueOf(oo[0]);
                ylist[i] = String.valueOf(oo[1]);
            }
            result.put("x",xlist);
            result.put("y",ylist);
        }
        write(result);

    }




}