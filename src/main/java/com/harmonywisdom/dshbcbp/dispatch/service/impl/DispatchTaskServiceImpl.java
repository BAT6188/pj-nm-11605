package com.harmonywisdom.dshbcbp.dispatch.service.impl;

import com.harmonywisdom.dshbcbp.dispatch.bean.DispatchTask;
import com.harmonywisdom.dshbcbp.dispatch.dao.DispatchTaskDAO;
import com.harmonywisdom.dshbcbp.dispatch.service.DispatchTaskService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("dispatchTaskService")
public class DispatchTaskServiceImpl extends BaseService<DispatchTask, String> implements DispatchTaskService {
    @Autowired
    private DispatchTaskDAO dispathTaskDAO;

    @Override
    protected BaseDAO<DispatchTask, String> getDAO() {
        return dispathTaskDAO;
    }


    /**
     * 执法统计
     * highchart获取数据
     * @param firstTime
     * @param lastTime
     * @return
     */
    @Override
    public List<Object[]> getByColumnData(String name, String lawType, String firstTime, String lastTime) {
        String whereSql = "AND 1=1 ";
        if(name != null && !"".equals(name)){
            whereSql += "AND enterprise_name LIKE '%" + name + "%'";
        }else if(lawType != null && !"".equals(lawType)) {
            whereSql += "AND source = '" + lawType + "' ";
        }
//        } else if( firstTime !=null && !"".equals(firstTime)){
//            whereSql += " AND DATE_FORMAT(event_time,'%Y-%m-%d') >='" + firstTime + "' AND DATE_FORMAT(event_time,'%Y-%m-%d') <= '" + lastTime + "'";
//        } else if(lastTime != null && !"".equals(lastTime)) {
//            whereSql += "AND DATE_FORMAT(event_time,'%Y-%m-%d') >= '" + firstTime + "' AND DATE_FORMAT(event_time,'%Y-%m-%d') <= '" + lastTime + "'";
//        }
        whereSql += "GROUP BY MONTH";
        List<Object[]> list = getDAO().queryNativeSQL("SELECT DATE_FORMAT(event_time,'%Y-%m')AS MONTH,COUNT(*) FROM `HW_DISPATH_TASK` where DATE_FORMAT(event_time,'%Y-%m-%d') >= '" + firstTime +"' AND DATE_FORMAT(event_time,'%Y-%m-%d') <= '" + lastTime + "'" + whereSql);
        return list;
    }

    /**
     * 执法同期对比分析获取数据
     * @param startXdate
     * @param lastXdate
     * @param startSdate
     * @param lastSdate
     * @param name
     * @param lawType
     * @return
     */
    @Override
    public List<Object[]> findByColumnRatio(String startXdate, String lastXdate, String startSdate, String lastSdate, String name, String lawType) {
        String whereSql = "AND 1=1 ";
        if(name != null && !"".equals(name)){
            whereSql += "AND enterprise_name LIKE '%" + name + "%'";
        }else if(lawType != null && !"".equals(lawType)) {
            whereSql += "AND source = '" + lawType + "' ";
        }
        whereSql += "GROUP BY MONTH";
        List<Object[]> list = getDAO().queryNativeSQL("SELECT DATE_FORMAT(event_time,'%Y-%m')AS MONTH,COUNT(*) FROM `HW_DISPATH_TASK` where DATE_FORMAT(event_time,'%Y-%m-%d') >='" +startXdate+ "' AND DATE_FORMAT(event_time,'%Y-%m-%d') <= '"+lastXdate + "'" + whereSql);

        List<Object[]> list2 = getDAO().queryNativeSQL("SELECT DATE_FORMAT(event_time,'%Y-%m')AS MONTH,COUNT(*)  FROM `HW_DISPATH_TASK` where DATE_FORMAT(event_time,'%Y-%m-%d') >='" + startSdate + "' AND DATE_FORMAT(event_time,'%Y-%m-%d') <= '" + lastSdate + "'" + whereSql);

        List<Object[]> temp = new ArrayList<>();
        if (list2.size() < list.size()) {
            temp = list2;
            list2 = list;
            list = temp;
        }
        for(int i =0; i<list2.size(); i++){
            Object[] a = list2.get(i);
            String months = a[0].toString();
            String month = months.substring(5,7);
            boolean flag = false;
            String lackDate = "";
            for(int j = 0; j < list.size();j++){
                Object[] b = list.get(j);
                String months2 = b[0].toString();
                String year = months2.substring(0,5);
                String month2 = months2.substring(5,7);
                if(month.equals(month2)){
                    flag = true;
                    break;
                }
                lackDate = year+month;
            }
            if(!flag){
                list.add(i,new Object[]{lackDate,"0"});
            }
        }
        List<Object[]> sList = new ArrayList<>();
        for (int i =0; i < list.size(); i++) {
            Object obj1[] = list.get(i);
            Object obj2[] = list2.get(i);
            String x = "("+obj1[0].toString()+")"+"-" + "("+obj2[0].toString()+")";
            Object [] cc = {x,obj1[1],obj2[1]};
            sList.add(cc);
        }
        return sList;
    }
}