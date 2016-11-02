package com.harmonywisdom.dshbcbp.port.service;


import com.harmonywisdom.dshbcbp.port.bean.AirQuality;
import com.harmonywisdom.framework.service.IBaseService;

import java.util.List;

public interface AirQualityService extends IBaseService<AirQuality, String> {

    /**
     * 空气质量获取后台数据
     * @param startYdate
     * @param lastYdate
     * @param airType
     * @return
     */
    List<Object[]> findByAirData(String startYdate, String lastYdate, String airType);

}