package com.harmonywisdom.dshbcbp.dispatch.service.impl;

import com.harmonywisdom.dshbcbp.dispatch.bean.DispathTask;
import com.harmonywisdom.dshbcbp.dispatch.dao.DispathTaskDAO;
import com.harmonywisdom.dshbcbp.dispatch.service.DispathTaskService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            whereSql += "DATE_FORMAT(event_time,'%Y-%m-%d') >= '" + firstTime + "'DATE_FORMAT(event_time,'%Y-%m-%d') <= '" + lastTime + "'";
        }
        whereSql += " GROUP BY MONTH";
        List<Object[]> list = getDAO().queryNativeSQL("SELECT DATE_FORMAT(event_time,'%m')AS MONTH,COUNT(*)  FROM `HW_DISPATH_TASK`" + whereSql);
        return list;
    }
}