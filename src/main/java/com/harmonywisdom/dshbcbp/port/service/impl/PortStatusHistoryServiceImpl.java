package com.harmonywisdom.dshbcbp.port.service.impl;

import com.harmonywisdom.dshbcbp.port.bean.PortStatusHistory;
import com.harmonywisdom.dshbcbp.port.dao.PortStatusHistoryDAO;
import com.harmonywisdom.dshbcbp.port.service.PortStatusHistoryService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        String whereSql = " where 1=1 ";
        whereSql += "AND STATUS='1'";
        if(name != null && !"".equals(name)){
            whereSql += "AND enterprise_name LIKE '%" + name + "%'";
        }else if( firstTime !=null && !"".equals(firstTime)){
            whereSql += " AND DATE_FORMAT(start_time,'%Y-%m-%d') >='" + firstTime + "' AND DATE_FORMAT(start_time,'%Y-%m-%d') <= '" + lastTime + "'";
        } else if(lastTime != null && !"".equals(lastTime)) {
            whereSql += "AND DATE_FORMAT(start_time,'%Y-%m-%d') >= '" + firstTime + "'AND DATE_FORMAT(start_time,'%Y-%m-%d') <= '" + lastTime + "'";
        }
        whereSql += " GROUP BY MONTH";
        List<Object[]> list = getDAO().queryNativeSQL("SELECT DATE_FORMAT(start_time,'%m')AS MONTH,COUNT(*)  FROM `hw_dshbcbp_port_status_history`" + whereSql);
        return list;
    }
}