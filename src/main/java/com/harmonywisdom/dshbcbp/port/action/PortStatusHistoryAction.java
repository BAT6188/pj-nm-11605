package com.harmonywisdom.dshbcbp.port.action;

import com.harmonywisdom.dshbcbp.port.bean.PortStatusHistory;
import com.harmonywisdom.dshbcbp.port.service.PortStatusHistoryService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PortStatusHistoryAction extends BaseAction<PortStatusHistory, PortStatusHistoryService> {
    @AutoService
    private PortStatusHistoryService portStatusHistoryService;

    @Override
    protected PortStatusHistoryService getService() {
        return portStatusHistoryService;
    }

    /**
     * highchart获取超标数据
     */
    public void getColumnHighChart() throws ParseException {
        Map<String,Object> result = new HashMap<String,Object>();
        String startYdate = request.getParameter("startYdate");
        String lastYdate = request.getParameter("lastYdate");
        String name = request.getParameter("name");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date firstTime = sdf.parse(startYdate);
        Date lastTime = sdf.parse(lastYdate);

        List<Object[]> list = portStatusHistoryService.findColumnData(firstTime,lastTime);
        if(list != null && list.size()>0){
            Object[] xlist = new Object[list.size()];
            Object[] ylist = new Object[list.size()];
            for(int i =0 ;i<list.size();i++){
                Object[] oo = list.get(i);
                xlist[i] = String.valueOf(oo[0]);
                ylist[i] = String.valueOf(oo[1]);
            }
            result.put("x",xlist);
            result.put("y",ylist);
        }
        write(result);

//        List<Object[]> result = new ArrayList<Object[]>();
//
//        String[] categories = new String[] {"1月","2月","3月","4月","5月","6月"};
//        Double[] serie1 = new Double[] {1d,2d,3d,3d,4d,3.55};
//
//
//        result.add(categories);
//        result.add(serie1);
//
//        write(result);
    }

}