package com.harmonywisdom.dshbcbp.port.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.harmonywisdom.dshbcbp.common.dict.util.DateUtil;
import com.harmonywisdom.dshbcbp.port.bean.AirQuality;
import com.harmonywisdom.dshbcbp.port.dao.AirQualityDAO;
import com.harmonywisdom.dshbcbp.port.service.AirQualityService;
import com.harmonywisdom.dshbcbp.utils.HttpClientUtil;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.dao.Paging;
import com.harmonywisdom.framework.dao.QueryResult;
import com.harmonywisdom.framework.service.BaseService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("airQualityService")
public class AirQualityServiceImpl extends BaseService<AirQuality, String> implements AirQualityService {
    @Autowired
    private AirQualityDAO airQualityDAO;

    @Override
    protected BaseDAO<AirQuality, String> getDAO() {
        return airQualityDAO;
    }

    @Override
    public void save15DayAQI() {
        String url="http://110.19.109.61:9875/15DayAirQualityChangeCity.aspx?action=GetData";
        try {
            String result = HttpClientUtil.httpOrHttpsGet(url);
            List<Map<String, String>> ls = JSON.parseObject(result, new TypeReference<List<Map<String, String>>>() {});
            for (Map<String, String> map : ls) {
                String rec_time = map.get("Rec_Time");
                String aqi_24H = map.get("AQI_24H");
                AirQuality airQuality=new AirQuality();
                airQuality.setAirValue(Integer.valueOf(aqi_24H));
                List<AirQuality> sample = findBySample(airQuality);
                if (sample==null || sample.size()==0){
                    airQuality.setRec_Time(DateUtil.strToDate(rec_time,"yyyy/MM/dd HH:mm:ss"));
                    save(airQuality);
                }
            }

        } catch (Exception e) {
            log.error("通过接口每15天查询空气质量指数get15DayAQI方法报错",e);
        }

    }


    public static void main(String[] args) {
        AirQualityServiceImpl a=new AirQualityServiceImpl();
        a.save15DayAQI();
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

    /**
     * 空气质量同期对比分析获取数据
     * @param startXdate
     * @param lastXdate
     * @param startSdate
     * @param lastSdate
     * @param airType
     * @return
     */
    @Override
    public List<Object[]> findByAirRadioData(String startXdate, String lastXdate, String startSdate, String lastSdate, String airType) {
        List<Object[]> list = getDAO().queryNativeSQL("SELECT" +
                "(SELECT COUNT(*) FROM hw_dshbcbp_air_quality t0 WHERE t0.air_value >0 AND t0.air_value <= 50 AND DATE_FORMAT(t0.`rec_time`,'%Y-%m')>='"+startXdate+"' AND DATE_FORMAT(t0.`rec_time`,'%Y-%m') <= '"+lastXdate+"')," +
                "(SELECT COUNT(*) FROM hw_dshbcbp_air_quality t0 WHERE t0.air_value >50 AND t0.air_value <= 100 AND DATE_FORMAT(t0.`rec_time`,'%Y-%m')>='"+startXdate+"' AND DATE_FORMAT(t0.`rec_time`,'%Y-%m') <= '"+lastXdate+"')," +
                "(SELECT COUNT(*) FROM hw_dshbcbp_air_quality t0 WHERE t0.air_value >100 AND t0.air_value <= 150 AND DATE_FORMAT(t0.`rec_time`,'%Y-%m')>='"+startXdate+"' AND DATE_FORMAT(t0.`rec_time`,'%Y-%m') <= '"+lastXdate+"')," +
                "(SELECT COUNT(*) FROM hw_dshbcbp_air_quality t0 WHERE t0.air_value >150 AND t0.air_value <= 200 AND DATE_FORMAT(t0.`rec_time`,'%Y-%m')>='"+startXdate+"' AND DATE_FORMAT(t0.`rec_time`,'%Y-%m') <= '"+lastXdate+"')," +
                "(SELECT COUNT(*) FROM hw_dshbcbp_air_quality t0 WHERE t0.air_value >200 AND t0.air_value <= 300 AND DATE_FORMAT(t0.`rec_time`,'%Y-%m')>='"+startXdate+"' AND DATE_FORMAT(t0.`rec_time`,'%Y-%m') <= '"+lastXdate+"')," +
                "(SELECT COUNT(*) FROM hw_dshbcbp_air_quality t0 WHERE t0.air_value >300 AND DATE_FORMAT(t0.`rec_time`,'%Y-%m')>='"+startXdate+"' AND DATE_FORMAT(t0.`rec_time`,'%Y-%m') <= '"+lastXdate+"')" +
                "FROM hw_dshbcbp_air_quality t GROUP BY DATE_FORMAT(t.`rec_time`,'%Y');");

        List<Object[]> list2 = getDAO().queryNativeSQL("SELECT" +
                "(SELECT COUNT(*) FROM hw_dshbcbp_air_quality t0 WHERE t0.air_value >0 AND t0.air_value <= 50 AND DATE_FORMAT(t0.`rec_time`,'%Y-%m')>='"+startSdate+"' AND DATE_FORMAT(t0.`rec_time`,'%Y-%m') <= '"+lastSdate+"')," +
                "(SELECT COUNT(*) FROM hw_dshbcbp_air_quality t0 WHERE t0.air_value >50 AND t0.air_value <= 100 AND DATE_FORMAT(t0.`rec_time`,'%Y-%m')>='"+startSdate+"' AND DATE_FORMAT(t0.`rec_time`,'%Y-%m') <= '"+lastSdate+"')," +
                "(SELECT COUNT(*) FROM hw_dshbcbp_air_quality t0 WHERE t0.air_value >100 AND t0.air_value <= 150 AND DATE_FORMAT(t0.`rec_time`,'%Y-%m')>='"+startSdate+"' AND DATE_FORMAT(t0.`rec_time`,'%Y-%m') <= '"+lastSdate+"')," +
                "(SELECT COUNT(*) FROM hw_dshbcbp_air_quality t0 WHERE t0.air_value >150 AND t0.air_value <= 200 AND DATE_FORMAT(t0.`rec_time`,'%Y-%m')>='"+startSdate+"' AND DATE_FORMAT(t0.`rec_time`,'%Y-%m') <= '"+lastSdate+"')," +
                "(SELECT COUNT(*) FROM hw_dshbcbp_air_quality t0 WHERE t0.air_value >200 AND t0.air_value <= 300 AND DATE_FORMAT(t0.`rec_time`,'%Y-%m')>='"+startSdate+"' AND DATE_FORMAT(t0.`rec_time`,'%Y-%m') <= '"+lastSdate+"')," +
                "(SELECT COUNT(*) FROM hw_dshbcbp_air_quality t0 WHERE t0.air_value >300 AND DATE_FORMAT(t0.`rec_time`,'%Y-%m')>='"+startSdate+"' AND DATE_FORMAT(t0.`rec_time`,'%Y-%m') <= '"+lastSdate+"')" +
                "FROM hw_dshbcbp_air_quality t GROUP BY DATE_FORMAT(t.`rec_time`,'%Y');");

        String[] strAir = new String[]{"优", "良", "轻度污染", "中度污染", "重度污染", "严重污染"};

        List<Object[]> aList = new ArrayList<>();
        Object[] aResult = list.get(0);
        Object[] bResult = list2.get(0);

        if("1".equals(airType)){
            Object[] you = {strAir[0],aResult[0],bResult[0]};
            aList.add(you);
            return aList;
        }else if("2".equals(airType)){
            Object[] liang = {strAir[1],aResult[1] ,bResult[1]};
            aList.add(liang);
            return aList;
        }
        else if("3".equals(airType)){
            Object[] qing = {strAir[2],aResult[2],bResult[2]};
            aList.add(qing);
            return aList;
        }
        else if("4".equals(airType)){
            Object[] zhong = {strAir[3],aResult[3],bResult[3]};
            aList.add(zhong);
            return aList;
        }
        else if("5".equals(airType)){
            Object[] zd = {strAir[4],aResult[4],bResult[4]};
            aList.add(zd);
            return aList;
        }
        else if("6".equals(airType)) {
            Object[] yz = {strAir[4], aResult[4], bResult[4]};
            aList.add(yz);
            return aList;
        }else{
            Object[] you = {strAir[0],aResult[0],bResult[0]};
            Object[] liang = {strAir[1],aResult[1] ,bResult[1]};
            Object[] qing = {strAir[2],aResult[2],bResult[2]};
            Object[] zhong = {strAir[3],aResult[3],bResult[3]};
            Object[] zd = {strAir[4],aResult[4],bResult[4]};
            Object[] yz = {strAir[5],aResult[5],bResult[5]};
            aList.add(you);
            aList.add(liang);
            aList.add(qing);
            aList.add(zhong);
            aList.add(zd);
            aList.add(yz);
            return aList;
        }

    }

    /**
     * 同期对比查询空气质量表
     * @param params
     * @param paging
     * @return
     */
    @Override
    public QueryResult<AirQuality> findAirRatio(Map<String, String> params, Paging paging) {
        QueryResult<AirQuality> result = new QueryResult<>();
        List<AirQuality> rows = new ArrayList<>();

        //分页条件
        int startIndex = paging.getStartIndex();
        int endIndex = paging.getStartIndex() + paging.getPageSize();

        StringBuilder whereSql = new StringBuilder(" where 1=1 ");
        if(StringUtils.isNotBlank(params.get("minValue"))){
            whereSql.append("and t.air_value > ").append(params.get("minValue"));
        }
        if(StringUtils.isNotBlank(params.get("maxValue"))){
            whereSql.append(" and t.air_value <= ").append(params.get("maxValue"));
        }
        if (StringUtils.isNotBlank(params.get("startXdate")) || StringUtils.isNotBlank(params.get("lastXdate"))) {
            whereSql.append(" and ( t.rec_time > '").append(params.get("startXdate")).append("' and t.rec_time < '").append(params.get("lastXdate"));
        }
        if(StringUtils.isNotBlank(params.get("startSdate")) || StringUtils.isNotBlank(params.get("lastSdate"))){
            whereSql.append("' OR t.rec_time > '").append(params.get("startSdate")).append("' and t.rec_time <= '").append(params.get("lastSdate")+"')");
        }


        String countSql = "select count(*) from hw_dshbcbp_air_quality t" +whereSql.toString();
        String querySql = "select t.* from hw_dshbcbp_air_quality t " +whereSql.toString()+"limit " + startIndex+","+endIndex;

        long total = airQualityDAO.getCount(countSql);
        List<Object[]> list = airQualityDAO.queryNativeSQL(querySql);

        if(list != null && list.size()>0){
            AirQuality airs = null;
            for(Object[] obj : list){
                airs = new AirQuality();
                airs.setId(String.valueOf(obj[0]));
                airs.setAirValue(Integer.parseInt(String.valueOf(obj[1])));
                Date date = null;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

                try {
                    date = sdf.parse(obj[2]==null ? "" :String.valueOf(obj[2]));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                airs.setRec_Time(date);
                rows.add(airs);

            }
        }
        result.setRows(rows);
        result.setTotal(total);
        return result;
    }

}