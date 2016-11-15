package com.harmonywisdom.dshbcbp.port.service.impl;

import com.harmonywisdom.dshbcbp.port.bean.WaterPort;
import com.harmonywisdom.dshbcbp.port.bean.WaterPortHistory;
import com.harmonywisdom.dshbcbp.port.dao.WaterPortDAO;
import com.harmonywisdom.dshbcbp.port.dao.WaterPortHistoryDAO;
import com.harmonywisdom.dshbcbp.port.service.WaterPortService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("waterPortService")
public class WaterPortServiceImpl extends BaseService<WaterPort, String> implements WaterPortService {
    @Autowired
    private WaterPortDAO waterPortDAO;
    @Autowired
    private WaterPortHistoryDAO waterPortHistoryDAO;

    @Override
    protected BaseDAO<WaterPort, String> getDAO() {
        return waterPortDAO;
    }

    @Override
    public void delete(String portId){
        WaterPortHistory waterPortHistory = new WaterPortHistory();
        waterPortHistory.setPortId(portId);
        List<WaterPortHistory> waterPortHistories = waterPortHistoryDAO.findBySample(waterPortHistory);
        if(waterPortHistories.size()>0){
            for(WaterPortHistory wph:waterPortHistories){
                waterPortHistoryDAO.remove(wph);
            }
        }
        waterPortDAO.remove(portId);
    }
}