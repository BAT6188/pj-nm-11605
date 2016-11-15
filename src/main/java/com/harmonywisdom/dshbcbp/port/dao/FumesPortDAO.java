package com.harmonywisdom.dshbcbp.port.dao;

import com.harmonywisdom.dshbcbp.port.bean.FumesPort;
import com.harmonywisdom.dshbcbp.port.bean.FumesPortHistory;
import com.harmonywisdom.framework.dao.DefaultDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FumesPortDAO extends DefaultDAO<FumesPort, String> {

    @Autowired
    private FumesPortHistoryDAO fumesPortHistoryDAO;

    public void delete(FumesPort fp) {
        FumesPortHistory fumesPortHistory = new FumesPortHistory();
        fumesPortHistory.setPortId(fp.getId());
        List<FumesPortHistory> fumesPortHistories = fumesPortHistoryDAO.findBySample(fumesPortHistory);
        if(fumesPortHistories.size()>0){
            for(FumesPortHistory fph:fumesPortHistories){
                fumesPortHistoryDAO.remove(fph);
            }
        }
        this.remove(fp);
    }
}