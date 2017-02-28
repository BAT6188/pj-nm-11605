package com.harmonywisdom.dshbcbp.port.service;

import com.harmonywisdom.dshbcbp.port.bean.GasPortHistory;
import com.harmonywisdom.framework.service.IBaseService;

public interface GasPortHistoryService extends IBaseService<GasPortHistory, String> {
    public void saveGasPortHistoryDataList();
    public void saveGasPortHistoryData();
    public void saveTestGasPortHistoryData(String startTime,String endTime);
}