package com.harmonywisdom.dshbcbp.dispatch.service;

import com.harmonywisdom.dshbcbp.dispatch.bean.DispatchTask;
import com.harmonywisdom.framework.service.IBaseService;

import java.util.List;

public interface DispatchTaskService extends IBaseService<DispatchTask, String> {

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
     * @param startSdate
     * @param lastSdate
     * @param name
     * @param lawType
     * @return
     */
    List<Object[]> findByColumnRatio(String startSdate, String lastSdate, String name, String lawType);

    /**
     * 增量更新
     * @param dispatchTask
     * @return
     */
    String updateDispatchTask(DispatchTask dispatchTask);
}