package com.harmonywisdom.dshbcbp.port.service;

import com.harmonywisdom.dshbcbp.port.bean.GasPortHistory;
import com.harmonywisdom.framework.service.IBaseService;

public interface GasPortHistoryService extends IBaseService<GasPortHistory, String> {
    public void saveGasPortHistoryDataList();
}