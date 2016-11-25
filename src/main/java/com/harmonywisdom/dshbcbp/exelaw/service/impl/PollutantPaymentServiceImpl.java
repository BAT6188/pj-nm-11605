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
                "(SELECT COUNT(*) FROM `hw_pollutant_payment` t0 WHERE t0.PAYMENT_STATUS='1' AND DATE_FORMAT(t0.pay_date,'%m') = DATE_FORMAT(t.pay_date,'%m'))," +
                "(SELECT COUNT(*) FROM `hw_pollutant_payment` t0 WHERE t0.PAYMENT_STATUS='0' AND DATE_FORMAT(t0.pay_date,'%m') = DATE_FORMAT(t.pay_date,'%m'))" +
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
                "(SELECT COUNT(*) FROM `hw_pollutant_payment` t0 WHERE t0.PAYMENT_STATUS='1' AND DATE_FORMAT(t0.pay_date,'%m') = DATE_FORMAT(t.pay_date,'%m'))" +
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
        List<PollutantPayment> rows = new ArrayList<>();

        //分页条件
        int startIndex = paging.getStartIndex();
        int endIndex = paging.getStartIndex() + paging.getPageSize();

        StringBuilder whereSql = new StringBuilder(" where 1=1 ");
        if(StringUtils.isNotBlank(params.get("paymentStatus"))){
            whereSql.append("and (t.PAYMENT_STATUS ='").append(params.get("paymentStatus"));
        }
        if(StringUtils.isNotBlank(params.get("unpaidStatus"))){
            whereSql.append("' OR t.PAYMENT_STATUS = '").append(params.get("unpaidStatus"));
        }

        if (StringUtils.isNotBlank(params.get("firstTime")) || StringUtils.isNotBlank(params.get("lastTime"))) {
            whereSql.append("') and ( t.pay_date > '").append(params.get("firstTime")).append("' and t.pay_date < '").append(params.get("lastTime")+"')");
        }

        String countSql = "select count(*) from hw_pollutant_payment t"  +whereSql.toString();
        String querySql = "select t.* from hw_pollutant_payment t " +whereSql.toString()+"limit " + startIndex+","+endIndex;

        long total = pollutantPaymentDAO.getCount(countSql);
        List<Object[]> list = pollutantPaymentDAO.queryNativeSQL(querySql);

        if(list != null && list.size()>0){
            PollutantPayment pol = null;
            for(Object[] obj : list){
                pol = new PollutantPayment();
                pol.setId(String.valueOf(obj[0]));
                pol.setEnterpriseName(obj[5]==null ? "" : String.valueOf(obj[5]));
                pol.setEnterpriseAP(obj[3]==null ? "" : String.valueOf(obj[3]));
                pol.setApPhone(String.valueOf(obj[2]));
                pol.setPayMoney(Double.parseDouble(obj[7]==null ? "" : String.valueOf(obj[7])));
                //registDate
                Date registDate = null;
                Date payDate = null;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                try {
                    registDate = sdf.parse(obj[8]==null ? "" : String.valueOf(obj[8]));
                    payDate = sdf.parse(obj[6]==null ? "" : String.valueOf(obj[6]));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                pol.setRegistDate(registDate);
                pol.setPayDate(payDate);
                pol.setPaymentStatus(obj[11]==null ? "" : String.valueOf(obj[11]));
                rows.add(pol);

            }
        }
        result.setRows(rows);
        result.setTotal(total);
        return result;
    }
}