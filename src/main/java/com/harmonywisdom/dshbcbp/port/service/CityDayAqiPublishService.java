package com.harmonywisdom.dshbcbp.port.service;

import com.harmonywisdom.dshbcbp.port.bean.CityDayAqiPublish;
import com.harmonywisdom.framework.dao.Paging;
import com.harmonywisdom.framework.dao.QueryResult;
import com.harmonywisdom.framework.service.IBaseService;

import java.util.Map;

public interface CityDayAqiPublishService extends IBaseService<CityDayAqiPublish, String> {

    /**
     * 同期对比查询空气质量表
     * @param params
     * @param paging
     * @return
     */
    QueryResult<CityDayAqiPublish> findAirRatio(Map<String, String> params, Paging paging);
}