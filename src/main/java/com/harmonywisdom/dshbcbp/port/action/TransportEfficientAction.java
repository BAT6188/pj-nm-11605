package com.harmonywisdom.dshbcbp.port.action;

import com.harmonywisdom.dshbcbp.port.bean.DustPort;
import com.harmonywisdom.dshbcbp.port.bean.TransportEfficient;
import com.harmonywisdom.dshbcbp.port.service.DustPortService;
import com.harmonywisdom.dshbcbp.port.service.TransportEfficientService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.Direction;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryOperator;
import com.harmonywisdom.framework.dao.QueryParam;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransportEfficientAction extends BaseAction<TransportEfficient, TransportEfficientService> {
    @AutoService
    private TransportEfficientService transportEfficientService;

    @Override
    protected TransportEfficientService getService() {
        return transportEfficientService;
    }

    @Override
    protected QueryCondition getQueryCondition() {
        QueryParam param = new QueryParam();
        String startTime = request.getParameter("startYdate");
        String lastTime = request.getParameter("lastYdate");
        if (StringUtils.isNotBlank(entity.getEnterpriseName())) {
            param.andParam(new QueryParam("enterpriseName", QueryOperator.LIKE,entity.getEnterpriseName()));
        }
        if(org.apache.commons.lang3.StringUtils.isNotBlank(startTime) && org.apache.commons.lang3.StringUtils.isNotBlank(lastTime)){
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date starttime   = sdf.parse(startTime);
                Date   endtime = sdf.parse(lastTime);

                param.andParam(new QueryParam("startTime",QueryOperator.GE,starttime));
                param.andParam(new QueryParam("startTime",QueryOperator.LE,endtime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        QueryCondition condition = new QueryCondition();
        if (param.getField() != null) {
            condition.setParam(param);
        }
        condition.setPaging(getPaging());
        return condition;
    }

    /**
     * highChart获取柱状图数据
     */
    public void getPortChart(){
        String name = request.getParameter("name");
        String startYdate = request.getParameter("startYdate");
        String lastYdate = request.getParameter("lastYdate");

        Map<String,Object> result = new HashMap<String ,Object>();

        List<Object[]> list = transportEfficientService.findPortChart(name,startYdate,lastYdate);
        if(list != null && list.size()>0){
            Object[] xlist = new Object[list.size()];
            Object[] ylist = new Object[list.size()];
            for(int i = 0; i<list.size();i++){
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