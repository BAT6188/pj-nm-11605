package com.harmonywisdom.dshbcbp.dispatch.service;

import com.harmonywisdom.dshbcbp.dispatch.bean.DispathTask;
import com.harmonywisdom.framework.service.IBaseService;

import java.util.Date;
import java.util.List;

public interface DispathTaskService extends IBaseService<DispathTask, String> {

    /**
     * highchart获取柱状图数据
     * @param firstTime
     * @param lastTime
     * @return
     */
    List<Object[]> getByColumnData(String name,String lawType,String firstTime, String lastTime);
}