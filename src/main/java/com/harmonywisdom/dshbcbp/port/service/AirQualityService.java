package com.harmonywisdom.dshbcbp.port.service;


import com.harmonywisdom.dshbcbp.port.bean.AirQuality;
import com.harmonywisdom.framework.dao.Paging;
import com.harmonywisdom.framework.dao.QueryResult;
import com.harmonywisdom.framework.service.IBaseService;

import java.util.List;
import java.util.Map;

public interface AirQualityService extends IBaseService<AirQuality, String> {

    /**
     * 空气质量获取后台数据
     * @param startYdate
     * @param lastYdate
     * @param airType
     * @return
     */
    List<Object[]> findByAirData(String startYdate, String lastYdate, String airType);

    /**
     * 空气质量同期对比分析获取后台数据
     * @param startXdate
     * @param lastXdate
     * @param startSdate
     * @param lastSdate
     * @param airType
     * @return
     */
    List<Object[]> findByAirRadioData(String startXdate, String lastXdate, String startSdate, String lastSdate, String airType);

    /**
     * 通过接口每15天查询空气质量指数 并保存记录到数据库
     */
    public void save15DayAQI();

    /**
     * 同期对比查询空气质量表
     * @param params
     * @param paging
     * @return
     */
    QueryResult<AirQuality> findAirRatio(Map<String, String> params, Paging paging);

    /**
     * 获取最新空气质量信息
     * @return
     */
    AirQuality realTimeAir();


}