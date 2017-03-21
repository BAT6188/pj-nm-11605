package com.harmonywisdom.dshbcbp.port.service.impl;

import com.harmonywisdom.dshbcbp.port.bean.CityDayAqiPublish;
import com.harmonywisdom.dshbcbp.port.dao.CityDayAqiPublishDAO;
import com.harmonywisdom.dshbcbp.port.service.CityDayAqiPublishService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.dao.Paging;
import com.harmonywisdom.framework.dao.QueryResult;
import com.harmonywisdom.framework.service.BaseService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("cityDayAqiPublishService")
public class CityDayAqiPublishServiceImpl extends BaseService<CityDayAqiPublish, String> implements CityDayAqiPublishService {
    @Autowired
    private CityDayAqiPublishDAO cityDayAqiPublishDAO;

    @Override
    protected BaseDAO<CityDayAqiPublish, String> getDAO() {
        return cityDayAqiPublishDAO;
    }

    @Override
    public QueryResult<CityDayAqiPublish> findAirRatio(Map<String, String> params, Paging paging) {
        QueryResult<CityDayAqiPublish> result = new QueryResult<>();
        List<CityDayAqiPublish> rows = new ArrayList<>();

        //分页条件
        int startIndex = paging.getStartIndex();
        int endIndex = paging.getPageSize();

        StringBuilder whereSql = new StringBuilder(" where 1=1 ");
        if(StringUtils.isNotBlank(params.get("minValue"))){
            whereSql.append("and t.AQI > ").append(params.get("minValue"));
        }
        if(StringUtils.isNotBlank(params.get("maxValue"))){
            whereSql.append(" and t.AQI <= ").append(params.get("maxValue"));
        }
        if (StringUtils.isNotBlank(params.get("startXdate")) || StringUtils.isNotBlank(params.get("lastXdate"))) {
            whereSql.append(" and ( t.TimePoint > '").append(params.get("startXdate")).append("' and t.TimePoint < '").append(params.get("lastXdate"));
        }
        if(StringUtils.isNotBlank(params.get("startSdate")) || StringUtils.isNotBlank(params.get("lastSdate"))){
            whereSql.append("' OR t.TimePoint > '").append(params.get("startSdate")).append("' and t.TimePoint <= '").append(params.get("lastSdate")+"')");
        }
        whereSql.append(" order by t.TimePoint desc ");


        String countSql = "select count(*) from HW_CITY_DAY_AQI_PUBLISH t" +whereSql.toString();
        String querySql = "select t.* from HW_CITY_DAY_AQI_PUBLISH t " +whereSql.toString()+"limit " + startIndex+","+endIndex;

        long total = cityDayAqiPublishDAO.getCount(countSql);
        List<CityDayAqiPublish> list = cityDayAqiPublishDAO.queryNativeSQL(querySql,CityDayAqiPublish.class);
        result.setRows(list);
        result.setTotal(total);
        return result;
    }
}