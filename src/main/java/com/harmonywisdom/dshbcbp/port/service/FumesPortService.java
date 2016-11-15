package com.harmonywisdom.dshbcbp.port.service;

import com.harmonywisdom.dshbcbp.port.bean.FumesPort;
import com.harmonywisdom.framework.service.IBaseService;

public interface FumesPortService extends IBaseService<FumesPort, String> {

    void delete(String portId);
}