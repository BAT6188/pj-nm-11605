package com.harmonywisdom.dshbcbp.port.service;

import com.harmonywisdom.dshbcbp.port.bean.PortStatusHistory;
import com.harmonywisdom.framework.dao.Paging;
import com.harmonywisdom.framework.dao.QueryResult;
import com.harmonywisdom.framework.service.IBaseService;

import java.util.List;
import java.util.Map;

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
     * @param startSdate
     * @param lastSdate
     * @return
     */
    List<Object[]> findColumnRatio(String name,String startSdate, String lastSdate);

    List<PortStatusHistory> findByPortids(String... portids);

    List<PortStatusHistory> findByEnterpriseids(String... enterpriseIds);

    /**
     * 企业更新反馈状态
     * @param id
     */
    int updateStatus(String id);

    /**
     * 企业超标异常信息
     * @return
     */
    List<PortStatusHistory> companyByExcessive(String enterpriseId);

    /**
     * 超标同期对比查询列表
     * @param params
     * @param paging
     * @return
     */
    QueryResult<PortStatusHistory> excessiveRatiolist(Map<String, String> params, Paging paging);
}