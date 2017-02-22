package com.harmonywisdom.dshbcbp.port.dao;


import com.harmonywisdom.dshbcbp.common.dict.util.DateUtil;
import com.harmonywisdom.dshbcbp.port.bean.AirEquipmentHistory;
import com.harmonywisdom.framework.dao.DefaultDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Repository
public class AirEquipmentHistoryDAO extends DefaultDAO<AirEquipmentHistory, String> {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean loadOuterData(){
        String sql="";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        List<AirEquipmentHistory> airEquipmentHistoryList=new ArrayList<>();
        for (Map<String, Object> map : list) {
            AirEquipmentHistory a=new AirEquipmentHistory();
            a.setAirMonitoringName(map.get("PositionName").toString());
            a.setMonitoringNumber(map.get("StationCode").toString());
            a.setMonitoringTime(DateUtil.strToDate(map.get("TimePoint").toString(),"yyyy-MM-dd HH:mm:ss"));
            a.setMonitoringNumber(map.get("StationCode").toString());
            a.setMonitoringNumber(map.get("StationCode").toString());
            a.setMonitoringNumber(map.get("StationCode").toString());
            a.setMonitoringNumber(map.get("StationCode").toString());
            a.setMonitoringNumber(map.get("StationCode").toString());
        }
        return true;
    }

}