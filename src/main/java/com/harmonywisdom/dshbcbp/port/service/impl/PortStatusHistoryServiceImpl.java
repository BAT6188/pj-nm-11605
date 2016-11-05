package com.harmonywisdom.dshbcbp.port.service.impl;

import com.harmonywisdom.dshbcbp.port.bean.PortStatusHistory;
import com.harmonywisdom.dshbcbp.port.dao.PortStatusHistoryDAO;
import com.harmonywisdom.dshbcbp.port.service.PortStatusHistoryService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.vendor.OpenJpaDialect;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service("portStatusHistoryService")
public class PortStatusHistoryServiceImpl extends BaseService<PortStatusHistory, String> implements PortStatusHistoryService {
    @Autowired
    private PortStatusHistoryDAO portStatusHistoryDAO;

    @Override
    protected BaseDAO<PortStatusHistory, String> getDAO() {
        return portStatusHistoryDAO;
    }


    /**
     * 超标柱状图数据
     * @param firstTime
     * @param lastTime
     * @return
     */
    @Override
    public List<Object[]> findColumnData(String name,String firstTime, String lastTime) {
        String whereSql = " AND 1=1 ";
        whereSql += "AND STATUS='1'";
        if(name != null && !"".equals(name)){
            whereSql += "AND enterprise_name LIKE '%" + name + "%'";
        }
        whereSql += " GROUP BY MONTH";
        List<Object[]> list = getDAO().queryNativeSQL("SELECT DATE_FORMAT(start_time,'%Y-%m')AS MONTH,COUNT(*)  FROM `hw_dshbcbp_port_status_history` where DATE_FORMAT(start_time,'%Y-%m-%d') >='" + firstTime + "' AND DATE_FORMAT(start_time,'%Y-%m-%d') <= '" + lastTime + "'" + whereSql);
        return list;
    }

    /**
     * 超标同期对比分析
     * @param name
     * @param startXdate
     * @param lastXdate
     * @param startSdate
     * @param lastSdate
     * @return
     */
    @Override
    public List<Object[]> findColumnRatio(String name, String startXdate, String lastXdate, String startSdate, String lastSdate) {
        String whereSql = " where 1=1 ";
        whereSql += "AND STATUS='1'";
        if(name != null && !"".equals(name)){
            whereSql += "AND enterprise_name LIKE '%" + name + "%'";
        }else if( startXdate !=null && !"".equals(startXdate)){
            whereSql += " AND DATE_FORMAT(start_time,'%Y-%m-%d') >='" + startXdate + "' AND DATE_FORMAT(start_time,'%Y-%m-%d') <= '" + lastXdate + "'";
        } else if(lastXdate != null && !"".equals(lastXdate)) {
            whereSql += "AND DATE_FORMAT(start_time,'%Y-%m-%d') >= '" + startXdate + "'AND DATE_FORMAT(start_time,'%Y-%m-%d') <= '" + lastXdate + "'";
        }
        whereSql += "GROUP BY MONTH";
        List<Object[]> list = getDAO().queryNativeSQL("SELECT DATE_FORMAT(start_time,'%Y-%m')AS MONTH,COUNT(*)  FROM `hw_dshbcbp_port_status_history`" + whereSql);

        String whereSql2 = " where 1=1 ";
        whereSql2 += "AND STATUS='1'";
        if(name != null && !"".equals(name)){
            whereSql2 += "AND enterprise_name LIKE '%" + name + "%'";
        }else if( startSdate !=null && !"".equals(startSdate)){
            whereSql2 += " AND DATE_FORMAT(start_time,'%Y-%m-%d') >='" + startSdate + "' AND DATE_FORMAT(start_time,'%Y-%m-%d') <= '" + lastSdate + "'";
        } else if(lastSdate != null && !"".equals(lastSdate)) {
            whereSql2 += "AND DATE_FORMAT(start_time,'%Y-%m-%d') >= '" + startSdate + "'AND DATE_FORMAT(start_time,'%Y-%m-%d') <= '" + lastSdate + "'";
        }
        whereSql2 += "GROUP BY MONTH";
        List<Object[]> list2 = getDAO().queryNativeSQL("SELECT DATE_FORMAT(start_time,'%Y-%m')AS MONTH,COUNT(*)  FROM `hw_dshbcbp_port_status_history`" + whereSql2);

        List<Object[]> temp = new ArrayList<>();
        do{
            if (list2.size() < list.size()) {
                temp = list2;
                list2 = list;
                list = temp;
            }
            for (int i = 0; i < list2.size(); i++) {
                Object[] a = list2.get(i);
                String months = a[0].toString();
                String month = months.substring(5,7);
                boolean flag = false;
                String lackDate = "";
                for (int j = 0; j < list.size(); j++) {
                    Object[] b = list.get(j);
                    String months2 = b[0].toString();
                    String year = months2.substring(0,5);
                    String month2 = months2.substring(5,7);
                    if (month.equals(month2)) {
                        flag = true;
                        break;
                    }
                    lackDate = year+month;
                }
                if (!flag) {
                    list.add(i,new Object[]{lackDate,"0"});
                }
            }
        }while(list.size() != list2.size());

        List<Object[]> sList = new ArrayList<>();
        for (int i =0; i < list.size(); i++) {
            Object obj[] = list.get(i);
            Object obj2[] = list2.get(i);
            String x = "("+obj[0].toString()+")"+"-" + "("+obj2[0].toString()+")";
            Object [] cc = {x,obj[1],obj2[1]};
            sList.add(cc);
        }
        return sList;
    }

    @Override
    public List<PortStatusHistory> findByPortids(String... portids) {
        return getDAO().find("portId in ?1", Arrays.asList(portids));
    }

    @Override
    public List<PortStatusHistory> findByEnterpriseids(String... enterpriseIds) {
        return getDAO().find("enterpriseId in ?1", Arrays.asList(enterpriseIds));
    }


}