package com.harmonywisdom.dshbcbp.port.dao;


import com.harmonywisdom.dshbcbp.common.dict.util.DateUtil;
import com.harmonywisdom.dshbcbp.port.bean.AirEquipmentHistory;
import com.harmonywisdom.dshbcbp.port.bean.AirQuality;
import com.harmonywisdom.framework.dao.DefaultDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Repository
public class AirEquipmentHistoryDAO extends DefaultDAO<AirEquipmentHistory, String> {
    @Autowired
    private JdbcTemplate airQualityJdbcTemplate;

    public boolean loadOuterData(){
        List<AirEquipmentHistory> airEquipmentHistories = find("from AirEquipmentHistory ORDER BY mobile_timestamp DESC LIMIT 0,1");
        String currentMaxTimeString = "2016-01-01 00:00:00";
        if(airEquipmentHistories.size()>0){
            Date currentMaxTime = airEquipmentHistories.get(0).getMobileTimestamp();
            currentMaxTimeString = DateUtil.dateToStr(currentMaxTime,"yyyy-MM-dd HH:mm:ss");
        }

        String sql="select * from AQIDataPublishLive WHERE TimePoint > "+currentMaxTimeString;
        List<Map<String, Object>> list = airQualityJdbcTemplate.queryForList(sql);
        List<AirEquipmentHistory> airEquipmentHistoryList=new ArrayList<>();
        List<AirQuality> airQualityList = new ArrayList<>();
        for (Map<String, Object> map : list) {
            AirEquipmentHistory airEquipmentHistory=new AirEquipmentHistory();
            airEquipmentHistory.setAirMonitoringName(map.get("PositionName").toString());
            airEquipmentHistory.setMonitoringNumber(map.get("StationCode").toString());
            airEquipmentHistory.setMonitoringTime(DateUtil.strToDate(map.get("TimePoint").toString(),"yyyy-MM-dd HH:mm:ss"));
            airEquipmentHistory.setMonitoringPosition(map.get("PositionName").toString());
            airEquipmentHistory.setAirIndex(Integer.parseInt(map.get("AQI").toString()));
            airEquipmentHistory.setAirQualityGrade(map.get("Quality").toString());
            airEquipmentHistory.setPrimaryPollutant(map.get("PrimaryPollutant").toString());
            airEquipmentHistory.setLatitude(Double.parseDouble(map.get("Latitude").toString()));
            airEquipmentHistory.setLongitude(Double.parseDouble(map.get("Longitude").toString()));
            airEquipmentHistory.setMobileTimestamp(airEquipmentHistory.getMonitoringTime());
            airEquipmentHistoryList.add(airEquipmentHistory);

            AirQuality airQuality = new AirQuality();
            airQuality.setRec_Time(DateUtil.strToDate(map.get("TimePoint").toString(),"yyyy-MM-dd HH:mm:ss"));
            airQuality.setAirValue(Integer.parseInt(map.get("AQI").toString()));
            airQualityList.add(airQuality);
        }
        return true;
    }

}