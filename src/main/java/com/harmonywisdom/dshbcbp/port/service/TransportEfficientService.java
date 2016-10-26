package com.harmonywisdom.dshbcbp.port.service;

import com.harmonywisdom.dshbcbp.port.bean.TransportEfficient;
import com.harmonywisdom.framework.service.IBaseService;

import java.util.List;

public interface TransportEfficientService extends IBaseService<TransportEfficient, String> {
    /**
     * highChart获取柱状图数据
     * @param name
     * @param startYdate
     * @param lastYdate
     * @return
     */
    List<Object[]> findPortChart(String name,String startYdate, String lastYdate);
}