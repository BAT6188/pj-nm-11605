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
        List<Object[]> list = getDAO().queryNativeSQL("SELECT DATE_FORMAT(start_time,'%m')AS MONTH,COUNT(*)  FROM `hw_dshbcbp_port_status_history` where DATE_FORMAT(start_time,'%Y-%m-%d') >='" + firstTime + "' AND DATE_FORMAT(start_time,'%Y-%m-%d') <= '" + lastTime + "'" + whereSql);
        return list;
    }

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
        List<Object[]> list = getDAO().queryNativeSQL("SELECT DATE_FORMAT(start_time,'%m')AS MONTH,COUNT(*)  FROM `hw_dshbcbp_port_status_history`" + whereSql);


        List<Object[]> list2 = getDAO().queryNativeSQL("SELECT DATE_FORMAT(start_time,'%m')AS MONTH,COUNT(*)  FROM `hw_dshbcbp_port_status_history`" + whereSql);

        List<Object[]> temp = new ArrayList<>();
        if (list2.size() < list.size()) {
            temp = list2;
            list2 = list;
            list = temp;
        }
        for (int i = 0; i < list2.size(); i++) {
            Object[] a = list2.get(i);
            String month = a[0].toString();
            boolean flag = false;
            for (int j = 0; j < list.size(); j++) {
                Object[] b = list.get(j);
                String month2 = b[0].toString();
                if (month.equals(month2)) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                list.add(i,new Object[]{month,"0"});
            }
        }
        List<Object[]> sList = new ArrayList<>();
        for (int i =0; i < list.size(); i++) {
            Object Obj[] = list.get(i);
            Object Obj2[] = list2.get(i);
            Object [] cc = {Obj[0],Obj[1],Obj2[1]};
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