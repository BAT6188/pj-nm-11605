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
    public List<Object[]> findColumnData(Date firstTime, Date lastTime) {
        List<Object[]> list = getDAO().queryNativeSQL("SELECT DATE_FORMAT(start_time,'%m')AS MONTH,COUNT(*)  FROM `hw_dshbcbp_port_status_history`" +
                "WHERE STATUS='1' AND DATE_FORMAT(start_time,'%Y-%m-%d')> '2016-01-01' AND DATE_FORMAT(start_time,'%Y-%m-%d')<='2016-12-04' GROUP BY MONTH");

        return list;
    }
}