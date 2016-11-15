package com.harmonywisdom.dshbcbp.port.service.impl;

import com.harmonywisdom.dshbcbp.port.bean.FumesPort;
import com.harmonywisdom.dshbcbp.port.bean.FumesPortHistory;
import com.harmonywisdom.dshbcbp.port.dao.FumesPortDAO;
import com.harmonywisdom.dshbcbp.port.dao.FumesPortHistoryDAO;
import com.harmonywisdom.dshbcbp.port.service.FumesPortService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("fumesPortService")
public class FumesPortServiceImpl extends BaseService<FumesPort, String> implements FumesPortService {
    @Autowired
    private FumesPortDAO fumesPortDAO;
    @Autowired
    private FumesPortHistoryDAO fumesPortHistoryDAO;

    @Override
    protected BaseDAO<FumesPort, String> getDAO() {
        return fumesPortDAO;
    }

    @Override
    public void delete(String portId) {
        FumesPortHistory fumesPortHistory = new FumesPortHistory();
        fumesPortHistory.setPortId(portId);
        List<FumesPortHistory> fumesPortHistories = fumesPortHistoryDAO.findBySample(fumesPortHistory);
        if(fumesPortHistories.size()>0){
            for(FumesPortHistory fph:fumesPortHistories){
                fumesPortHistoryDAO.remove(fph);
            }
        }
        fumesPortDAO.remove(portId);
    }
}