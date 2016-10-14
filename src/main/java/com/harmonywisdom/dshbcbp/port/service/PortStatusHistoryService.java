package com.harmonywisdom.dshbcbp.port.service;

import com.harmonywisdom.dshbcbp.port.bean.PortStatusHistory;
import com.harmonywisdom.framework.service.IBaseService;

import java.util.Date;
import java.util.List;

public interface PortStatusHistoryService extends IBaseService<PortStatusHistory, String> {
    /**
     * high获取超标统计数据
     * @param firstTime
     * @param lastTime
     * @return
     */
    List<Object[]> findColumnData(Date firstTime, Date lastTime);
}