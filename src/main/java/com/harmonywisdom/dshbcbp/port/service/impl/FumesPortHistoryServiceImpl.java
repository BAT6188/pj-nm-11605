package com.harmonywisdom.dshbcbp.port.service.impl;

import com.harmonywisdom.dshbcbp.port.bean.FumesPortHistory;
import com.harmonywisdom.dshbcbp.port.dao.FumesPortHistoryDAO;
import com.harmonywisdom.dshbcbp.port.service.FumesPortHistoryService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service("fumesPortHistoryService")
public class FumesPortHistoryServiceImpl extends BaseService<FumesPortHistory, String> implements FumesPortHistoryService {
    @Autowired
    private FumesPortHistoryDAO fumesPortHistoryDAO;

    @Override
    protected BaseDAO<FumesPortHistory, String> getDAO() {
        return fumesPortHistoryDAO;
    }

    @Override
    public void deleteFumesPortHistoryDataOnTime() {
        Date curentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(curentDate);
        calendar.add(Calendar.DAY_OF_MONTH,-11);

        Date delDate = calendar.getTime();
        fumesPortHistoryDAO.executeJPQL("delete from FumesPortHistory where monitorTime<?",delDate);
    }
}