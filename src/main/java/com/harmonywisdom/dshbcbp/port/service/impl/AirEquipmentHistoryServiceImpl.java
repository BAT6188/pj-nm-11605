package com.harmonywisdom.dshbcbp.port.service.impl;


import com.harmonywisdom.dshbcbp.common.dict.util.DateUtil;
import com.harmonywisdom.dshbcbp.port.bean.AirEquipment;
import com.harmonywisdom.dshbcbp.port.bean.AirEquipmentHistory;
import com.harmonywisdom.dshbcbp.port.bean.AirQuality;
import com.harmonywisdom.dshbcbp.port.dao.AirEquipmentDAO;
import com.harmonywisdom.dshbcbp.port.dao.AirEquipmentHistoryDAO;
import com.harmonywisdom.dshbcbp.port.dao.AirQualityDAO;
import com.harmonywisdom.dshbcbp.port.service.AirEquipmentHistoryService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("airEquipmentHistoryService")
public class AirEquipmentHistoryServiceImpl extends BaseService<AirEquipmentHistory, String> implements AirEquipmentHistoryService {
    @Autowired
    private AirEquipmentDAO airEquipmentDAO;
    @Autowired
    private AirEquipmentHistoryDAO airEquipmentHistoryDAO;
    @Autowired
    private AirQualityDAO airQualityDAO;
    @Autowired
    private JdbcTemplate airQualityJdbcTemplate;

    @Override
    protected BaseDAO<AirEquipmentHistory, String> getDAO() {
        return airEquipmentHistoryDAO;
    }


    /**
     * 获取数据接口
     */
    @Override
    public void saveAirEquipmentHistoryFromOuter() {
        List<AirEquipmentHistory> airEquipmentHistories = airEquipmentHistoryDAO.queryJPQL("from AirEquipmentHistory ORDER BY mobileTimestamp DESC");
        String currentMaxTimeString = "2016-01-01 00:00:00";
        Date currentMaxTime;
        if(airEquipmentHistories.size()>0){
            currentMaxTime = airEquipmentHistories.get(0).getMobileTimestamp();
            currentMaxTimeString = DateUtil.dateToStr(currentMaxTime,"yyyy-MM-dd HH:mm:ss");
        }

        List<AirEquipment> airEquipments = airEquipmentDAO.findAll();
        for (AirEquipment airEquipment:airEquipments){
            String currentSql="select * from AQIDataPublishLive WHERE TimePoint > \'"+currentMaxTimeString+"\' AND StationCode = '"+airEquipment.getId()+"\' ORDER BY TimePoint DESC";
            String oldSql="select * from AQIDataPublishHistory WHERE TimePoint > \'"+currentMaxTimeString+"\' AND StationCode = '"+airEquipment.getId()+"\' ORDER BY TimePoint DESC";
            List<Map<String, Object>> list = airQualityJdbcTemplate.queryForList(oldSql);
            if(list.size()>0){
                saveAirEquipmentData(list.get(0));
                saveAirEquipmentHistoryData(list);
            }else{
                list = airQualityJdbcTemplate.queryForList(currentSql);
                if(list.size()>0){
                    saveAirEquipmentData(list.get(0));
                }
            }
        }
    }

    public boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++){
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }


    public void saveAirEquipmentData(Map<String, Object> map){
        String PositionName = map.get("PositionName").toString();
        String StationCode = map.get("StationCode").toString();
        Date TimePoint = DateUtil.strToDate(map.get("TimePoint").toString(),"yyyy-MM-dd HH:mm:ss");
        String Area = map.get("Area").toString();
        String aqiStringValue = map.get("AQI").toString();
        Integer aqiValue = 0;
        if(StringUtils.isNotBlank(aqiStringValue) && isNumeric(aqiStringValue)) {
            aqiValue = Integer.parseInt(aqiStringValue);
        }
        String Quality = map.get("Quality").toString();
        String PrimaryPollutant = map.get("PrimaryPollutant").toString();

        AirEquipment a = airEquipmentDAO.findById(StationCode);
        if(a!=null){
            a.setPrimaryPollutant(PrimaryPollutant);
            a.setAirQualityGrade(Quality);
            a.setAirIndex(aqiValue);
            a.setAirMonitoringName(PositionName);
            a.setMonitoringNumber(StationCode);
            a.setMonitoringPosition(PositionName);
            a.setMonitoringTime(TimePoint);
            airEquipmentDAO.update(a);
        }
    }

    public void saveAirEquipmentHistoryData(List<Map<String, Object>> list){
        for (Map<String, Object> map : list) {
            String PositionName = map.get("PositionName").toString();
            String StationCode = map.get("StationCode").toString();
            Date TimePoint = DateUtil.strToDate(map.get("TimePoint").toString(),"yyyy-MM-dd HH:mm:ss");
            String Area = map.get("Area").toString();
            String aqiStringValue = map.get("AQI").toString();
            Integer aqiValue = 0;
            if(StringUtils.isNotBlank(aqiStringValue) && isNumeric(aqiStringValue)) {
                aqiValue = Integer.parseInt(aqiStringValue);
            }
            String Quality = map.get("Quality").toString();
            String PrimaryPollutant = map.get("PrimaryPollutant").toString();

            AirEquipmentHistory airEquipmentHistory=new AirEquipmentHistory();
            airEquipmentHistory.setAirMonitoringName(PositionName);
            airEquipmentHistory.setMonitoringNumber(StationCode);
            airEquipmentHistory.setMonitoringTime(TimePoint);
            airEquipmentHistory.setMonitoringPosition(PositionName);
            airEquipmentHistory.setAirIndex(aqiValue);
            airEquipmentHistory.setAirQualityGrade(Quality);
            airEquipmentHistory.setPrimaryPollutant(PrimaryPollutant);
            //airEquipmentHistory.setLatitude(Double.parseDouble(map.get("Latitude").toString()));
            //airEquipmentHistory.setLongitude(Double.parseDouble(map.get("Longitude").toString()));
            airEquipmentHistory.setMobileTimestamp(TimePoint);
            airEquipmentHistoryDAO.saveOrUpdate(airEquipmentHistory);
            //airEquipmentHistoryList.add(airEquipmentHistory);

            AirQuality airQuality = new AirQuality();
            airQuality.setRec_Time(TimePoint);
            airQuality.setAirValue(aqiValue);

            airQualityDAO.saveOrUpdate(airQuality);
            //airQualityList.add(airQuality);
        }
    }
}
