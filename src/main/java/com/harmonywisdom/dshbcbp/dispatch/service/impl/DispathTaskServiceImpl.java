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
     * highchart获取柱状图数据
     * @param firstTime
     * @param lastTime
     * @return
     */
    @Override
    public List<Object[]> getByColumnData(Date firstTime, Date lastTime) {
        List<Object[]> list = getDAO().queryNativeSQL("SELECT DATE_FORMAT(event_time,'%m')AS MONTH,COUNT(*)  FROM `HW_DISPATH_TASK`" +
                "WHERE DATE_FORMAT(event_time,'%Y-%m-%d')> ? AND DATE_FORMAT(event_time,'%Y-%m-%d')<= ? GROUP BY MONTH",firstTime,lastTime);
        return list;
    }
}