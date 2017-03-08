package com.harmonywisdom.dshbcbp.port.service.impl;


import com.harmonywisdom.dshbcbp.common.dict.util.DateUtil;
import com.harmonywisdom.dshbcbp.port.bean.AirEquipment;
import com.harmonywisdom.dshbcbp.port.bean.AirEquipmentHistory;
import com.harmonywisdom.dshbcbp.port.bean.CityAqiPublish;
import com.harmonywisdom.dshbcbp.port.bean.CityDayAqiPublish;
import com.harmonywisdom.dshbcbp.port.dao.*;
import com.harmonywisdom.dshbcbp.port.service.AirEquipmentHistoryService;
import com.harmonywisdom.dshbcbp.utils.MyDateUtils;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("airEquipmentHistoryService")
public class AirEquipmentHistoryServiceImpl extends BaseService<AirEquipmentHistory, String> implements AirEquipmentHistoryService {
    @Autowired
    private AirEquipmentDAO airEquipmentDAO;
    @Autowired
    private AirEquipmentHistoryDAO airEquipmentHistoryDAO;
    @Autowired
    private CityAqiPublishDAO cityAqiPublishDAO;
    @Autowired
    private CityDayAqiPublishDAO cityDayAqiPublishDAO;
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
        String currentMaxTimeString = "2016-01-01 00:00:00";
        Date currentMaxTime = gtHistoryMaxDate();
        if(currentMaxTime!=null){
            currentMaxTimeString = DateUtil.dateToStr(currentMaxTime,"yyyy-MM-dd HH:mm:ss");
        }

        List<AirEquipment> airEquipments = airEquipmentDAO.findAll();
        int airEquipmentNum = 0;
        for (AirEquipment airEquipment:airEquipments){
            String currentSql="select * from AQIDataPublishLive WHERE TimePoint > \'"+currentMaxTimeString+"\' AND StationCode = '"+airEquipment.getMonitoringNumber()+"\' ORDER BY TimePoint DESC";
            String oldSql="select * from AQIDataPublishHistory WHERE TimePoint > \'"+currentMaxTimeString+"\' AND StationCode = '"+airEquipment.getMonitoringNumber()+"\' ORDER BY TimePoint DESC";
            List<Map<String, Object>> list = airQualityJdbcTemplate.queryForList(oldSql);
            if(list.size()>0){
                saveAirEquipmentData(list.get(0));
                saveAirEquipmentHistoryData(list,airEquipmentNum);
                airEquipmentNum +=1;
            }else{
                list = airQualityJdbcTemplate.queryForList(currentSql);
                if(list.size()>0){
                    saveAirEquipmentData(list.get(0));
                }
            }
        }
        //saveCityAqiPublish();
        saveCityDayAqiPublish();
        saveStatisticalAnalysisCityHourAirQuality();
    }

    public Date gtHistoryMaxDate(){
        List<AirEquipmentHistory> airEquipmentHistories = airEquipmentHistoryDAO.queryJPQL("from AirEquipmentHistory ORDER BY mobileTimestamp DESC");
        if(airEquipmentHistories.size()>0){
            Date currentMaxTime = airEquipmentHistories.get(0).getMonitoringTime();
            return currentMaxTime;
        }else{
            return null;
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


    /**
     * 保存实时数据
     * @param map
     */
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

    /**
     * 保存历史数据
     * @param list
     * @param airEquipmentNum
     */
    public void saveAirEquipmentHistoryData(List<Map<String, Object>> list,int airEquipmentNum){
        int num=1;
        for (Map<String, Object> map : list) {
            String Id = map.get("Id").toString();
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
            airEquipmentHistory.setId(Id);
            airEquipmentHistory.setAirMonitoringName(PositionName);
            airEquipmentHistory.setMonitoringNumber(StationCode);
            airEquipmentHistory.setMonitoringTime(TimePoint);
            airEquipmentHistory.setMonitoringPosition(PositionName);
            airEquipmentHistory.setAirIndex(aqiValue);
            airEquipmentHistory.setAirQualityGrade(Quality);
            airEquipmentHistory.setPrimaryPollutant(PrimaryPollutant);
            //airEquipmentHistory.setLatitude(Double.parseDouble(map.get("Latitude").toString()));
            //airEquipmentHistory.setLongitude(Double.parseDouble(map.get("Longitude").toString()));
            airEquipmentHistory.setMobileTimestamp(new Date(TimePoint.getTime()- airEquipmentNum*1000 - num*1000));
            num +=1;
            airEquipmentHistoryDAO.saveOrUpdate(airEquipmentHistory);
            //airEquipmentHistoryList.add(airEquipmentHistory);

            //AirQuality airQuality = new AirQuality();
            //airQuality.setId(Id);
            //airQuality.setRec_Time(TimePoint);
            //airQuality.setAirValue(aqiValue);

            //airQualityDAO.saveOrUpdate(airQuality);
            //airQualityList.add(airQuality);
        }
    }

    /**
     * 保存地区时均值
     */
    public void saveCityAqiPublish(){
        List<CityAqiPublish> airEquipmentHistories = cityAqiPublishDAO.queryJPQL("from CityAqiPublish ORDER BY TimePoint DESC");
        String currentMaxTimeString = "2016-01-01 00:00:00";
        Date currentMaxTime;
        if(airEquipmentHistories.size()>0){
            currentMaxTime = airEquipmentHistories.get(0).getTimePoint();
            currentMaxTimeString = DateUtil.dateToStr(currentMaxTime,"yyyy-MM-dd HH:mm:ss");
        }

        String currentSql="select * from CityAqiPublish WHERE TimePoint > \'"+currentMaxTimeString+"\' ORDER BY TimePoint DESC";
        List<Map<String, Object>> list = airQualityJdbcTemplate.queryForList(currentSql);
        if(list.size()>0){
            for(Map<String, Object> map:list){
                String Id = map.get("Id").toString();
                Date TimePoint = DateUtil.strToDate(map.get("TimePoint").toString(),"yyyy-MM-dd HH:mm:ss");
                String aqiStringValue = map.get("AQI").toString();
                Integer aqiValue = 0;
                if(StringUtils.isNotBlank(aqiStringValue) && isNumeric(aqiStringValue)) {
                    aqiValue = Integer.parseInt(aqiStringValue);
                }
                String Quality = map.get("Quality").toString();
                String PrimaryPollutant = map.get("PrimaryPollutant").toString();
                Map<String,String> m = getAirEquiment(aqiValue);

                CityAqiPublish c = new CityAqiPublish();
                c.setId(Id);
                c.setQuality(Quality);
                c.setTimePoint(TimePoint);
                c.setAQI(aqiValue);
                c.setPrimaryPollutant(PrimaryPollutant);
                c.setQualityLevel( m.get("aqi"));

                cityAqiPublishDAO.saveOrUpdate(c);
            }
        }
    }

    /**
     * 保存地区日均值
     */
    public void saveCityDayAqiPublish(){
        List<CityDayAqiPublish> airEquipmentHistories = cityDayAqiPublishDAO.queryJPQL("from CityDayAqiPublish ORDER BY TimePoint DESC");
        String currentMaxTimeString = "2016-01-01 00:00:00";
        Date currentMaxTime;
        if(airEquipmentHistories.size()>0){
            currentMaxTime = airEquipmentHistories.get(0).getTimePoint();
            currentMaxTimeString = DateUtil.dateToStr(currentMaxTime,"yyyy-MM-dd HH:mm:ss");
        }

        String currentSql="select * from CityDayAqiPublish WHERE TimePoint > \'"+currentMaxTimeString+"\' ORDER BY TimePoint DESC";
        List<Map<String, Object>> list = airQualityJdbcTemplate.queryForList(currentSql);
        if(list.size()>0){
            for(Map<String, Object> map:list){
                String Id = map.get("Id").toString();
                Date TimePoint = DateUtil.strToDate(map.get("TimePoint").toString(),"yyyy-MM-dd HH:mm:ss");
                String aqiStringValue = map.get("AQI").toString();
                Integer aqiValue = 0;
                if(StringUtils.isNotBlank(aqiStringValue) && isNumeric(aqiStringValue)) {
                    aqiValue = Integer.parseInt(aqiStringValue);
                }
                String Quality = map.get("Quality").toString();
                String PrimaryPollutant = map.get("PrimaryPollutant").toString();
                Map<String,String> m = getAirEquiment(aqiValue);

                CityDayAqiPublish c = new CityDayAqiPublish();
                c.setId(Id);
                c.setQuality(Quality);
                c.setTimePoint(TimePoint);
                c.setAQI(aqiValue);
                c.setPrimaryPollutant(PrimaryPollutant);
                c.setQualityLevel(m.get("aqi"));

                cityDayAqiPublishDAO.saveOrUpdate(c);
            }
        }
    }

    /*****************************************************************************************************************************************/

    //计算时均值
    public void saveStatisticalAnalysisCityHourAirQuality(){
        Date maxHistoryDate = gtHistoryMaxDate();
        if(maxHistoryDate!=null){
            List<CityAqiPublish> cityAqiPublishs = cityAqiPublishDAO.queryJPQL("from CityAqiPublish ORDER BY TimePoint DESC");
            String currentStartTimeString = "2016-01-01 00:00:00";
            Date currentStartTime;
            if(cityAqiPublishs.size()>0){
                currentStartTime = cityAqiPublishs.get(0).getTimePoint();
                currentStartTimeString = DateUtil.dateToStr(currentStartTime,"yyyy-MM-dd HH:mm:ss");
            }

            Date startDate = MyDateUtils.parseDateFormat(currentStartTimeString,"yyyy-MM-dd hh:mm:ss");
            while (startDate.getTime()<=maxHistoryDate.getTime()){
                List<AirEquipmentHistory> thisHourHisList = airEquipmentHistoryDAO.queryJPQL("from AirEquipmentHistory where monitoringTime=? ",
                        startDate);
                int aqiValue = 0;
                for(AirEquipmentHistory aeh:thisHourHisList){
                    aqiValue +=aeh.getAirIndex();
                }
                if(aqiValue>0){
                    int avgValue = aqiValue/thisHourHisList.size();
                    Map<String,String> pubMap = getAirEquiment(avgValue);
                    CityAqiPublish cdap = new CityAqiPublish();
                    cdap.setId(String.valueOf(startDate.getTime()));
                    cdap.setTimePoint(startDate);
                    cdap.setAQI(avgValue);
                    cdap.setQualityLevel(pubMap.get("aqi"));
                    cdap.setQuality(pubMap.get("aqiMsg"));
                    cdap.setMobileTimestamp(startDate);
                    cityAqiPublishDAO.saveOrUpdate(cdap);
                }

                Calendar ca=Calendar.getInstance();
                ca.setTime(startDate);
                ca.add(Calendar.HOUR_OF_DAY,1);
                startDate = ca.getTime();
            }
        }
    }

    //计算日均值
    public void saveStatisticalAnalysisCityDayAirQuality(){
        List<CityDayAqiPublish> cityDayAqiPublishs = cityDayAqiPublishDAO.queryJPQL("from CityDayAqiPublish ORDER BY TimePoint DESC");
        String currentStartTimeString = "2016-01-01 00:00:00";
        Date currentStartTime;
        String currentDateStr = MyDateUtils.getDateFormat(new Date(),"yyyyMMdd");
        if(cityDayAqiPublishs.size()>0){
            currentStartTime = cityDayAqiPublishs.get(0).getTimePoint();
            currentStartTimeString = DateUtil.dateToStr(currentStartTime,"yyyy-MM-dd HH:mm:ss");
        }

        Date startDate = MyDateUtils.parseDateFormat(currentStartTimeString,"yyyy-MM-dd hh:mm:ss");
        for(int i=Integer.parseInt(currentStartTimeString);i<Integer.parseInt(currentDateStr);i++){
            String thisDay = MyDateUtils.getDateFormat(startDate,"yyyy-MM-dd");
            String startDateStr = thisDay + " 00:00:00";
            Date sDate = MyDateUtils.parseDateFormat(startDateStr);
            String endDateStr = thisDay + " 23:59:59";
            Date eDate = MyDateUtils.parseDateFormat(endDateStr);
            List<AirEquipmentHistory> thisDayHisList = airEquipmentHistoryDAO.queryJPQL("from AirEquipmentHistory where monitoringTime>=? and  monitoringTime<=? ORDER BY mobileTimestamp DESC",
                    sDate,eDate);
            int aqiValue = 0;
            for(AirEquipmentHistory aeh:thisDayHisList){
                aqiValue +=aeh.getAirIndex();
            }
            if(aqiValue>0){
                int avgValue = aqiValue/thisDayHisList.size();
                Map<String,String> pubMap = getAirEquiment(avgValue);
                CityDayAqiPublish cdap = new CityDayAqiPublish();
                cdap.setId(String.valueOf(startDate.getTime()));
                cdap.setTimePoint(startDate);
                cdap.setAQI(avgValue);
                cdap.setQualityLevel(pubMap.get("aqi"));
                cdap.setQuality(pubMap.get("aqiMsg"));
                cdap.setMobileTimestamp(startDate);
                cityDayAqiPublishDAO.saveOrUpdate(cdap);
            }
            GregorianCalendar gc=new GregorianCalendar();
            gc.setTime(startDate);
            gc.add(5,1);
            startDate = gc.getTime();
        }
    }

    public Map<String,String> getAirEquiment(int aqi){
        Map<String,String> rMap = new HashMap<>();
        if(aqi>300){
            rMap.put("aqi","6");
            rMap.put("aqiMsg","严重");
        }else if(aqi>200){
            rMap.put("aqi","5");
            rMap.put("aqiMsg","重度");
        }else if(aqi>200){
            rMap.put("aqi","4");
            rMap.put("aqiMsg","中度");
        }else if(aqi>100){
            rMap.put("aqi","3");
            rMap.put("aqiMsg","轻度");
        }else if(aqi>50){
            rMap.put("aqi","2");
            rMap.put("aqiMsg","良");
        }else{
            rMap.put("aqi","1");
            rMap.put("aqiMsg","优");
        }
        return rMap;
    }
}
