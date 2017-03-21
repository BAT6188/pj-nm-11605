package com.harmonywisdom.dshbcbp.port.dao;

import com.harmonywisdom.dshbcbp.common.dict.util.DateUtil;
import com.harmonywisdom.dshbcbp.port.bean.WaterPort;
import com.harmonywisdom.dshbcbp.port.bean.WaterPortHistory;
import com.harmonywisdom.framework.dao.DefaultDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Repository
public class WaterPortDAO extends DefaultDAO<WaterPort, String> {
    @Autowired
    private WaterPortHistoryDAO waterPortHistoryDAO;



    public void delete(WaterPort wp){
        WaterPortHistory waterPortHistory = new WaterPortHistory();
        waterPortHistory.setPortId(wp.getId());
        List<WaterPortHistory> waterPortHistories = waterPortHistoryDAO.findBySample(waterPortHistory);
        if(waterPortHistories.size()>0){
            for(WaterPortHistory wph:waterPortHistories){
                waterPortHistoryDAO.remove(wph);
            }
        }
        this.remove(wp);
    }
}