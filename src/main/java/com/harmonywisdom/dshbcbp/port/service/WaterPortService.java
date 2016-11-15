package com.harmonywisdom.dshbcbp.port.service;

import com.harmonywisdom.dshbcbp.port.bean.WaterPort;
import com.harmonywisdom.framework.service.IBaseService;

public interface WaterPortService extends IBaseService<WaterPort, String> {

    void delete(String portId);
}