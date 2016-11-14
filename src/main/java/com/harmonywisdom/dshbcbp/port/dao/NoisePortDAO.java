package com.harmonywisdom.dshbcbp.port.dao;

import com.harmonywisdom.dshbcbp.port.bean.NoisePort;
import com.harmonywisdom.dshbcbp.port.bean.NoisePortHistory;
import com.harmonywisdom.framework.dao.DefaultDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NoisePortDAO extends DefaultDAO<NoisePort, String> {
    @Autowired
    private NoisePortHistoryDAO noisePortHistoryDAO;

    public void delete(NoisePort np) {
        NoisePortHistory noisePortHistory = new NoisePortHistory();
        noisePortHistory.setPortId(np.getId());
        List<NoisePortHistory> waterPortHistories = noisePortHistoryDAO.findBySample(noisePortHistory);
        if(waterPortHistories.size()>0){
            for(NoisePortHistory wph:waterPortHistories){
                noisePortHistoryDAO.remove(wph);
            }
        }
        this.remove(np);
    }

}