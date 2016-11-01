package com.harmonywisdom.dshbcbp.dispatch.service.impl;

import com.harmonywisdom.dshbcbp.dispatch.bean.DispathTask;
import com.harmonywisdom.dshbcbp.dispatch.dao.DispathTaskDAO;
import com.harmonywisdom.dshbcbp.dispatch.service.DispathTaskService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("dispathTaskService")
public class DispathTaskServiceImpl extends BaseService<DispathTask, String> implements DispathTaskService {
    @Autowired
    private DispathTaskDAO dispathTaskDAO;

    @Override
    protected BaseDAO<DispathTask, String> getDAO() {
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
        String whereSql = " where 1=1 ";
        if(name !=null && !"".equals("name")){
            whereSql += "AND enterprise_name LIKE '%" + name + "%'";
        }else if(lawType != null && !"".equals("lawType")){
            whereSql += "AND source = '" + lawType + "' ";
        } else if( firstTime !=null && !"".equals(firstTime)){
            whereSql += " AND DATE_FORMAT(event_time,'%Y-%m-%d') >='" + firstTime + "' DATE_FORMAT(event_time,'%Y-%m-%d') <= '" + lastTime + "'";
        } else if(lastTime != null && !"".equals(lastTime)) {
            whereSql += "AND DATE_FORMAT(event_time,'%Y-%m-%d') >= '" + firstTime + "'DATE_FORMAT(event_time,'%Y-%m-%d') <= '" + lastTime + "'";
        }
        whereSql += " GROUP BY MONTH";
        List<Object[]> list = getDAO().queryNativeSQL("SELECT DATE_FORMAT(event_time,'%m')AS MONTH,COUNT(*) FROM `HW_DISPATH_TASK`" + whereSql);
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
        String whereSql = " AND 1=1 ";
        if(name != null && "".equals("name")){
            whereSql += "AND enterprise_name LIKE '%" + name + "%'";
        }else if(lawType != null && "".equals("lawType")){
            whereSql += "AND source = '" + lawType + "' ";
        }
        whereSql += "GROUP BY MONTH";
        List<Object[]> list = getDAO().queryNativeSQL("SELECT DATE_FORMAT(event_time,'%m')AS MONTH,COUNT(*) FROM `HW_DISPATH_TASK` where DATE_FORMAT(event_time,'%Y-%m-%d') >='" +startXdate+ "' AND DATE_FORMAT(event_time,'%Y-%m-%d') <= '"+lastXdate + "'" + whereSql);

        List<Object[]> list2 = getDAO().queryNativeSQL("SELECT DATE_FORMAT(event_time,'%m')AS MONTH,COUNT(*)  FROM `HW_DISPATH_TASK` where DATE_FORMAT(event_time,'%Y-%m-%d') >='" + startSdate + "' AND DATE_FORMAT(event_time,'%Y-%m-%d') <= '" + lastSdate + "'" + whereSql);

        List<Object[]> obj = new ArrayList<>();
        if(list2.size() < list.size()){
            obj = list2;
            list2 = list;
            list = obj;
        }
        for(int i =0; i<list2.size(); i++){
            Object[] a = list2.get(i);
            String month = a[0].toString();
            boolean flag = false;
            for(int j = 0; j < list.size();j++){
                Object[] b = list.get(j);
                String month2 = b[0].toString();
                if(month.equals(month2)){
                    flag = true;
                    break;
                }
            }
            if(!flag){
                list.add(i,new Object[]{month,"0"});
            }
        }
        List<Object[]> gList = new ArrayList<>();
        for(int i =0; i < list.size();i++){
            Object str[] = list.get(i);
            Object col[] = list2.get(i);
            Object[] listArrry = {str[0],str[1],col[1]};
            gList.add(listArrry);
        }
        return gList;
    }
}