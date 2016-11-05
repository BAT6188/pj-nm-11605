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
    List<Object[]> findColumnData(String name,String firstTime, String lastTime);

    /**
     * 超标同期对比分析获取后台数据
     * @param name
     * @param startXdate
     * @param lastXdate
     * @param startSdate
     * @param lastSdate
     * @return
     */
    List<Object[]> findColumnRatio(String name, String startXdate, String lastXdate, String startSdate, String lastSdate);

    List<PortStatusHistory> findByPortids(String... portids);

    List<PortStatusHistory> findByEnterpriseids(String... enterpriseIds);
}