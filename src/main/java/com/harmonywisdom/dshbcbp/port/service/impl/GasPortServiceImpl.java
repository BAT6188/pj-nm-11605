package com.harmonywisdom.dshbcbp.port.service.impl;

import com.harmonywisdom.dshbcbp.port.bean.GasPort;
import com.harmonywisdom.dshbcbp.port.bean.GasPortHistory;
import com.harmonywisdom.dshbcbp.port.dao.GasPortDAO;
import com.harmonywisdom.dshbcbp.port.dao.GasPortHistoryDAO;
import com.harmonywisdom.dshbcbp.port.service.GasPortService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("gasPortService")
public class GasPortServiceImpl extends BaseService<GasPort, String> implements GasPortService {
    @Autowired
    private GasPortDAO gasPortDAO;
    @Autowired
    private GasPortHistoryDAO gasPortHistoryDAO;

    @Override
    protected BaseDAO<GasPort, String> getDAO() {
        return gasPortDAO;
    }

    @Override
    public void delete(String portId) {
        GasPortHistory gasPortHistory = new GasPortHistory();
        gasPortHistory.setPortId(portId);
        List<GasPortHistory> gasPortHistories = gasPortHistoryDAO.findBySample(gasPortHistory);
        if(gasPortHistories.size()>0){
            for(GasPortHistory gph:gasPortHistories){
                gasPortHistoryDAO.remove(gph);
            }
        }
        gasPortDAO.remove(portId);
    }
}