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
            whereSql += "AND source = '" + lawType + "'";
        }
        whereSql += "GROUP BY MONTH";
        List<Object[]> list = getDAO().queryNativeSQL("SELECT DATE_FORMAT(event_time,'%Y-%m')AS MONTH,\n" +
                "(SELECT COUNT(*) FROM `hw_dispatch_task` t0 WHERE DATE_FORMAT(t0.event_time,'%m') = DATE_FORMAT(t.event_time,'%m')) AS yjf\n" +
                "FROM `hw_dispatch_task` t WHERE DATE_FORMAT(event_time,'%Y-%m-%d')> '"+firstTime+"' AND DATE_FORMAT(event_time,'%Y-%m-%d')<= '"+lastTime+"'" + whereSql);
        return list;
    }

    /**
     * 执法同期对比分析获取数据
     * @param startSdate
     * @param lastSdate
     * @param name
     * @param lawType
     * @return
     */
    @Override
    public List<Object[]> findByColumnRatio(String startSdate, String lastSdate, String name, String lawType) {
        String strsStart = startSdate.substring(5,7);
        String currentYear = startSdate.substring(0,4);
        String lastYear = String.valueOf((Integer.parseInt(currentYear) -1));
        String strEnd = lastSdate.substring(5,7);
        String whereSql = "AND 1=1 ";
        if(name != null && !"".equals(name)){
            whereSql += "AND enterprise_name LIKE '%" + name + "%'";
        }else if(lawType != null && !"".equals(lawType)) {
            whereSql += "AND source = '" + lawType + "' ";
        }
        whereSql += "GROUP BY DATE_FORMAT(t.`event_time`,'%m')";
        List<Object[]> list = getDAO().queryNativeSQL("SELECT DATE_FORMAT(t.`event_time`,'%m')AS MONTH," +
                "IFNULL((SELECT COUNT(*) AS b\n" +
                "FROM hw_dispatch_task t2 \n" +
                "WHERE DATE_FORMAT(t2.event_time,'%Y') = '"+lastYear+"' \n" +
                "AND DATE_FORMAT(t2.event_time,'%m') = DATE_FORMAT(t.`event_time`,'%m') \n" +
                "GROUP BY DATE_FORMAT(t.`event_time`,'%Y-%m')),0) AS s,\n" +
                "IFNULL((SELECT COUNT(*) AS b\n" +
                "FROM hw_dispatch_task t2 \n" +
                "WHERE DATE_FORMAT(t2.event_time,'%Y') = '"+currentYear+"' \n" +
                "AND DATE_FORMAT(t2.event_time,'%m') = DATE_FORMAT(t.`event_time`,'%m') \n" +
                "GROUP BY DATE_FORMAT(t.`event_time`,'%Y-%m')),0) AS c\n" +
                "FROM hw_dispatch_task t\n" +
                " WHERE DATE_FORMAT(t.`event_time`,'%m')>= '"+strsStart+"' AND DATE_FORMAT(t.`event_time`,'%m')<= '"+strEnd+"'" + whereSql);

        return list;
    }
}