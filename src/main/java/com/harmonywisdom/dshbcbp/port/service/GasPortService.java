package com.harmonywisdom.dshbcbp.port.service;

import com.harmonywisdom.dshbcbp.port.bean.GasPort;
import com.harmonywisdom.framework.service.IBaseService;

import java.util.List;

public interface GasPortService extends IBaseService<GasPort, String> {

    void delete(String portId);

    /**
     * 获取实时废气排口数据
     * @return
     */
    List<GasPort> fincdRealTimePort();
}