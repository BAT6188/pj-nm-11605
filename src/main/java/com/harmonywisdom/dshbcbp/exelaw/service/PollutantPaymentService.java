package com.harmonywisdom.dshbcbp.exelaw.service;

import com.harmonywisdom.dshbcbp.exelaw.bean.PollutantPayment;
import com.harmonywisdom.framework.dao.Paging;
import com.harmonywisdom.framework.dao.QueryResult;
import com.harmonywisdom.framework.service.IBaseService;

import java.util.List;
import java.util.Map;

public interface PollutantPaymentService extends IBaseService<PollutantPayment, String> {

    /**
     * 排污统计highCahrt
     * 获取柱状图数据
     * @param name
     * @param firstTime
     * @param lastTime
     * @return
     */
    Map<String, Object[]>  findByColumnChart(String name,String firstTime, String lastTime);

    /**
     * 排污统计highchart
     * 获取饼状图数据
     * @param name
     * @param firstTime
     * @param lastTime
     * @return
     */
    List<Object[]> findByPieChart(String name,String firstTime, String lastTime);

    /**
     * 排污统计查询列表
     * @param params
     * @param paging
     * @return
     */
    QueryResult<PollutantPayment> findSewagelist(Map<String, String> params, Paging paging);
}