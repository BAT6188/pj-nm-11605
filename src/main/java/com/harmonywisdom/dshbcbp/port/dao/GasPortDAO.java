package com.harmonywisdom.dshbcbp.port.dao;

import com.harmonywisdom.dshbcbp.port.bean.GasPort;
import com.harmonywisdom.dshbcbp.port.bean.GasPortHistory;
import com.harmonywisdom.framework.dao.DefaultDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GasPortDAO extends DefaultDAO<GasPort, String> {
    @Autowired
    private GasPortHistoryDAO gasPortHistoryDAO;

    public void delete(GasPort gp) {
        GasPortHistory gasPortHistory = new GasPortHistory();
        gasPortHistory.setPortId(gp.getId());
        List<GasPortHistory> gasPortHistories = gasPortHistoryDAO.findBySample(gasPortHistory);
        if(gasPortHistories.size()>0){
            for(GasPortHistory gph:gasPortHistories){
                gasPortHistoryDAO.remove(gph);
            }
        }
        this.remove(gp);
    }
}