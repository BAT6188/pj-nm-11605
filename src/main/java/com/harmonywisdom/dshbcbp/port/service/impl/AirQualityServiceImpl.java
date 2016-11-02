package com.harmonywisdom.dshbcbp.port.service.impl;


import com.harmonywisdom.dshbcbp.port.bean.AirQuality;
import com.harmonywisdom.dshbcbp.port.dao.AirQualityDAO;
import com.harmonywisdom.dshbcbp.port.service.AirQualityService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("airQualityService")
public class AirQualityServiceImpl extends BaseService<AirQuality, String> implements AirQualityService {
    @Autowired
    private AirQualityDAO airQualityDAO;

    @Override
    protected BaseDAO<AirQuality, String> getDAO() {
        return airQualityDAO;
    }


    /**
     * 空气质量获取后台数据
     * @param startYdate
     * @param lastYdate
     * @param airType
     * @return
     */
    @Override
    public List<Object[]> findByAirData(String startYdate,String lastYdate,String airType) {
        List<Object[]> list = getDAO().queryNativeSQL("SELECT" +
                "(SELECT COUNT(*) FROM hw_dshbcbp_air_quality t0 WHERE t0.air_value >0 AND t0.air_value <= 50 AND DATE_FORMAT(t0.`rec_time`,'%Y-%m')>='"+startYdate+"' AND DATE_FORMAT(t0.`rec_time`,'%Y-%m') <= '"+lastYdate+"')," +
                "(SELECT COUNT(*) FROM hw_dshbcbp_air_quality t0 WHERE t0.air_value >50 AND t0.air_value <= 100 AND DATE_FORMAT(t0.`rec_time`,'%Y-%m')>='"+startYdate+"' AND DATE_FORMAT(t0.`rec_time`,'%Y-%m') <= '"+lastYdate+"')," +
                "(SELECT COUNT(*) FROM hw_dshbcbp_air_quality t0 WHERE t0.air_value >100 AND t0.air_value <= 150 AND DATE_FORMAT(t0.`rec_time`,'%Y-%m')>='"+startYdate+"' AND DATE_FORMAT(t0.`rec_time`,'%Y-%m') <= '"+lastYdate+"')," +
                "(SELECT COUNT(*) FROM hw_dshbcbp_air_quality t0 WHERE t0.air_value >150 AND t0.air_value <= 200 AND DATE_FORMAT(t0.`rec_time`,'%Y-%m')>='"+startYdate+"' AND DATE_FORMAT(t0.`rec_time`,'%Y-%m') <= '"+lastYdate+"')," +
                "(SELECT COUNT(*) FROM hw_dshbcbp_air_quality t0 WHERE t0.air_value >200 AND t0.air_value <= 300 AND DATE_FORMAT(t0.`rec_time`,'%Y-%m')>='"+startYdate+"' AND DATE_FORMAT(t0.`rec_time`,'%Y-%m') <= '"+lastYdate+"')," +
                "(SELECT COUNT(*) FROM hw_dshbcbp_air_quality t0 WHERE t0.air_value >300 AND DATE_FORMAT(t0.`rec_time`,'%Y-%m')>='"+startYdate+"' AND DATE_FORMAT(t0.`rec_time`,'%Y-%m') <= '"+lastYdate+"')" +
                "FROM hw_dshbcbp_air_quality t GROUP BY DATE_FORMAT(t.`rec_time`,'%Y');");

        String[] title = new String[]{"优", "良", "轻度污染", "中度污染", "重度污染", "严重污染"};

        List<Object[]> sList = new ArrayList<>();
        Object[] result = list.get(0);
        if("1".equals(airType)){
            Object[] you = {title[0],result[0]};
            sList.add(you);
            return sList;
        }else if("2".equals(airType)) {
            Object[] liang = {title[1], result[1]};
            sList.add(liang);
            return sList;
        }else if("3".equals(airType)) {
            Object[] qing = {title[2], result[2]};
            sList.add(qing);
            return sList;
        }else if("4".equals(airType)) {
            Object[] zhong = {title[3], result[3]};
            sList.add(zhong);
            return sList;
        }else if("5".equals(airType)) {
            Object[] zd = {title[4], result[4]};
            sList.add(zd);
            return sList;
        }else if("6".equals(airType)){
            Object[] yz = {title[5],result[5]};
            sList.add(yz);
            return sList;
        }else{
            Object[] you = {title[0],result[0]};
            Object[] liang = {title[1],result[1]};
            Object[] qing = {title[2],result[2]};
            Object[] zhong = {title[3],result[3]};
            Object[] zd = {title[4],result[4]};
            Object[] yz = {title[5],result[5]};
            sList.add(you);
            sList.add(liang);
            sList.add(qing);
            sList.add(zhong);
            sList.add(zd);
            sList.add(yz);
            return sList;
        }


    }
}