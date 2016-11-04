package com.harmonywisdom.dshbcbp.port.service.impl;

import com.harmonywisdom.dshbcbp.port.bean.TransportEfficient;
import com.harmonywisdom.dshbcbp.port.dao.TransportEfficientDAO;
import com.harmonywisdom.dshbcbp.port.service.TransportEfficientService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("transportEfficientService")
public class TransportEfficientServiceImpl extends BaseService<TransportEfficient, String> implements TransportEfficientService {
    @Autowired
    private TransportEfficientDAO transportEfficientDAO;

    @Override
    protected BaseDAO<TransportEfficient, String> getDAO() {
        return transportEfficientDAO;
    }


    /**
     * highChart获取柱状图数据
     * @param startYdate
     * @param lastYdate
     * @return
     */
    @Override
    public List<Object[]> findPortChart(String name,String startYdate, String lastYdate) {
        String whereSql = " where 1=1 ";
        if(name != null && !"".equals(name)){
            whereSql += "AND enterprise_name LIKE '%" + name + "%'";
        }else if( startYdate !=null && !"".equals(startYdate)){
            whereSql += " AND DATE_FORMAT(start_time,'%Y-%m-%d') >='" + startYdate + "' AND DATE_FORMAT(start_time,'%Y-%m-%d') <= '" + lastYdate + "'";
        } else if(lastYdate != null && !"".equals(lastYdate)) {
            whereSql += "AND DATE_FORMAT(start_time,'%Y-%m-%d') >= '" + lastYdate + "'AND DATE_FORMAT(start_time,'%Y-%m-%d') <= '" + lastYdate + "'";
        }
        whereSql += " GROUP BY MONTH";
        List<Object[]> list = getDAO().queryNativeSQL("SELECT DATE_FORMAT(start_time,'%Y-%m')AS MONTH," +
                "(SELECT AVG(RATIO) FROM `hw_dshbcbp_transport_efficient` t0 WHERE DATE_FORMAT(t0.start_time,'%m') = DATE_FORMAT(t.start_time,'%m'))" +
                "FROM `hw_dshbcbp_transport_efficient` t " + whereSql);
        return list;
    }
}