package com.harmonywisdom.dshbcbp.exelaw.service.impl;

import com.harmonywisdom.dshbcbp.exelaw.bean.PollutantPayment;
import com.harmonywisdom.dshbcbp.exelaw.dao.PollutantPaymentDAO;
import com.harmonywisdom.dshbcbp.exelaw.service.PollutantPaymentService;
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


@Service("pollutantPaymentService")
public class PollutantPaymentServiceImpl extends BaseService<PollutantPayment, String> implements PollutantPaymentService {
    @Autowired
    private PollutantPaymentDAO pollutantPaymentDAO;

    @Override
    protected BaseDAO<PollutantPayment, String> getDAO() {
        return pollutantPaymentDAO;
    }

    /**
     * 排污统计highCahrt
     * 获取柱状图数据
     * @param name
     * @param firstTime
     * @param lastTime
     * @return
     */
    @Override
    public List<Object[]> findByColumnChart(String name,String firstTime, String lastTime) {
        String whereSql = " where 1=1 ";
        if (name != null && !"".equals(name)) {
            whereSql += "AND enterprise_name LIKE '%" + name + "%'";
        }else if( firstTime !=null && !"".equals(firstTime)){
            whereSql += "AND DATE_FORMAT(pay_date,'%Y-%m-%d') >='" + firstTime + "' AND DATE_FORMAT(pay_date,'%Y-%m-%d') <= '" + lastTime + "'";
        } else if(lastTime != null && !"".equals(lastTime)) {
            whereSql += "AND DATE_FORMAT(pay_date,'%Y-%m-%d') >= '" + firstTime + "'AND DATE_FORMAT(pay_date,'%Y-%m-%d') <= '" + lastTime + "'";
        }
        whereSql += " GROUP BY MONTH";
        List<Object[]> list = getDAO().queryNativeSQL("SELECT DATE_FORMAT(pay_date,'%Y-%m')AS MONTH," +
                "(SELECT COUNT(*) FROM `hw_pollutant_payment` t0 WHERE t0.PAYMENT_STATUS='1' AND DATE_FORMAT(t0.pay_date,'%Y-%m-%d') = DATE_FORMAT(t.pay_date,'%Y-%m-%d'))," +
                "(SELECT COUNT(*) FROM `hw_pollutant_payment` t0 WHERE t0.PAYMENT_STATUS='0' AND DATE_FORMAT(t0.pay_date,'%Y-%m-%d') = DATE_FORMAT(t.pay_date,'%Y-%m-%d'))" +
                "FROM hw_pollutant_payment t" + whereSql);
       return list;
    }


    /**
     * 排污统计highChart
     * 获取饼状图数据
     * @param name
     * @param firstTime
     * @param lastTime
     * @return
     */
    @Override
    public List<Object[]> findByPieChart(String name,String firstTime, String lastTime) {
        String whereSql = " where 1=1 ";
        if (name != null && !"".equals(name)) {
            whereSql += "AND enterprise_name LIKE '%" + name + "%'";
        }else if( firstTime !=null && !"".equals(firstTime)){
            whereSql += " AND DATE_FORMAT(pay_date,'%Y-%m-%d') >='" + firstTime + "' AND DATE_FORMAT(pay_date,'%Y-%m-%d') <= '" + lastTime + "'";
        } else if(lastTime != null && !"".equals(lastTime)) {
            whereSql += "AND DATE_FORMAT(pay_date,'%Y-%m-%d') >= '" + firstTime + "'AND DATE_FORMAT(pay_date,'%Y-%m-%d') <= '" + lastTime + "'";
        }
        whereSql += " GROUP BY MONTH ";
        List<Object[]> list = getDAO().queryNativeSQL("SELECT DATE_FORMAT(pay_date,'%Y-%m')AS MONTH," +
                "(SELECT COUNT(*) FROM `hw_pollutant_payment` t0 WHERE t0.PAYMENT_STATUS='1' AND DATE_FORMAT(t0.pay_date,'%Y-%m-%d') = DATE_FORMAT(t.pay_date,'%Y-%m-%d'))" +
                "FROM `hw_pollutant_payment` t" + whereSql);
        return list;
    }

    /**
     * 排污统计查询列表
     * @param params
     * @param paging
     * @return
     */
    @Override
    public QueryResult<PollutantPayment> findSewagelist(Map<String, String> params, Paging paging) {

        QueryResult<PollutantPayment> result = new QueryResult<>();

        //分页条件
        int startIndex = paging.getStartIndex();
        int endIndex = paging.getPageSize();

        StringBuilder whereSql = new StringBuilder(" where 1=1 ");
//        if(StringUtils.isNotBlank(params.get("paymentStatus"))){
//            whereSql.append("and t.PAYMENT_STATUS ='").append(params.get("paymentStatus"));
//        }
        String firstTime = params.get("firstTime");
        firstTime=firstTime.substring(0,7);
        if (StringUtils.isNotBlank(firstTime)) {
            whereSql.append(" and DATE_FORMAT(t.pay_date, '%Y-%m') = '").append(firstTime).append("'");
        }

        String countSql = "select count(*) from hw_pollutant_payment t"  +whereSql.toString();
        String querySql = "select t.* from hw_pollutant_payment t " +whereSql.toString()+"order by t.pay_date desc "+"limit " + startIndex+","+endIndex;

        long total = pollutantPaymentDAO.getCount(countSql);
        List<PollutantPayment> list = pollutantPaymentDAO.queryNativeSQL(querySql,PollutantPayment.class,null);

        result.setRows(list);
        result.setTotal(total);
        return result;
    }
}