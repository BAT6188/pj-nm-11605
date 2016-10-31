package com.harmonywisdom.dshbcbp.dispatch.service;

import com.harmonywisdom.dshbcbp.dispatch.bean.DispathTask;
import com.harmonywisdom.framework.service.IBaseService;

import java.util.Date;
import java.util.List;

public interface DispathTaskService extends IBaseService<DispathTask, String> {

    /**
     * 执法统计
     * highchart获取柱状图数据
     * @param firstTime
     * @param lastTime
     * @return
     */
    List<Object[]> getByColumnData(String name,String lawType,String firstTime, String lastTime);

    /**
     * 执法同期对比分析获取数据
     * @param startXdate
     * @param lastXdate
     * @param startSdate
     * @param lastSdate
     * @param name
     * @param lawType
     * @return
     */
    List<Object[]> findByColumnRatio(String startXdate, String lastXdate, String startSdate, String lastSdate, String name, String lawType);
}