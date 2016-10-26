package com.harmonywisdom.dshbcbp.exelaw.service;

import com.harmonywisdom.dshbcbp.exelaw.bean.PollutantPayment;
import com.harmonywisdom.framework.service.IBaseService;

import java.util.Date;
import java.util.List;

public interface PollutantPaymentService extends IBaseService<PollutantPayment, String> {

    /**
     * 排污统计highCahrt
     * 获取柱状图数据
     * @param name
     * @param payType
     * @param firstTime
     * @param lastTime
     * @return
     */
    List<Object[]>  findByColumnChart(String name,String payType,String firstTime, String lastTime);

    /**
     * 排污统计highchart
     * 获取饼状图数据
     * @param name
     * @param payType
     * @param firstTime
     * @param lastTime
     * @return
     */
    List<Object[]> findByPieChart(String name,String payType,String firstTime, String lastTime);
}