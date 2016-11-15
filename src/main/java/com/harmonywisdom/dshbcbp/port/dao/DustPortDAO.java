package com.harmonywisdom.dshbcbp.port.dao;

import com.harmonywisdom.dshbcbp.port.bean.DustPort;
import com.harmonywisdom.dshbcbp.port.bean.DustPortHistory;
import com.harmonywisdom.framework.dao.DefaultDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DustPortDAO extends DefaultDAO<DustPort, String> {
    @Autowired
    private DustPortHistoryDAO dustPortHistoryDAO;
    public void delete(DustPort dp) {
        DustPortHistory dustPortHistory = new DustPortHistory();
        dustPortHistory.setPortId(dp.getId());
        List<DustPortHistory> dustPortHistories = dustPortHistoryDAO.findBySample(dustPortHistory);
        if(dustPortHistories.size()>0){
            for(DustPortHistory dph:dustPortHistories){
                dustPortHistoryDAO.remove(dph);
            }
        }
        this.remove(dp);
    }
}