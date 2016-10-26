package com.harmonywisdom.dshbcbp.exelaw.service.impl;

import com.harmonywisdom.dshbcbp.exelaw.bean.PollutantPayment;
import com.harmonywisdom.dshbcbp.exelaw.dao.PollutantPaymentDAO;
import com.harmonywisdom.dshbcbp.exelaw.service.PollutantPaymentService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
     * @param payType
     * @param firstTime
     * @param lastTime
     * @return
     */
    @Override
    public List<Object[]> findByColumnChart(String name,String payType,String firstTime, String lastTime) {
        String whereSql = " where 1=1 ";
        if (name != null && !"".equals(name)) {
            whereSql += "AND enterprise_name LIKE '%" + name + "%'";
        }else if(payType != null && !"".equals(payType)) {
            if(payType == "0"){
                whereSql += "AND PAYMENT_STATUS = '" + payType + "' AND AND PAYMENT_STATUS = '" + 2 + "' ";
            }else{
                whereSql += "AND PAYMENT_STATUS = '" + payType + "'";
            }
        }else if( firstTime !=null && !"".equals(firstTime)){
            whereSql += "AND DATE_FORMAT(pay_date,'%Y-%m-%d') >='" + firstTime + "' AND DATE_FORMAT(pay_date,'%Y-%m-%d') <= '" + lastTime + "'";
        } else if(lastTime != null && !"".equals(lastTime)) {
            whereSql += "AND DATE_FORMAT(pay_date,'%Y-%m-%d') >= '" + firstTime + "'AND DATE_FORMAT(pay_date,'%Y-%m-%d') <= '" + lastTime + "'";
        }
        whereSql += " GROUP BY MONTH";
        List<Object[]> list = getDAO().queryNativeSQL("SELECT DATE_FORMAT(pay_date,'%m')AS MONTH," +
                "(SELECT COUNT(*) FROM `hw_pollutant_payment` t0 WHERE t0.PAYMENT_STATUS='1'AND DATE_FORMAT(t0.pay_date,'%m') = DATE_FORMAT(t.pay_date,'%m'))," +
                "(SELECT COUNT(*) FROM `hw_pollutant_payment` t0 WHERE t0.PAYMENT_STATUS='2'AND DATE_FORMAT(t0.pay_date,'%m') = DATE_FORMAT(t.pay_date,'%m'))" +
                "FROM hw_pollutant_payment t" + whereSql);
       return list;
    }

    /**
     * 排污统计highChart
     * 获取饼状图数据
     * @param name
     * @param payType
     * @param firstTime
     * @param lastTime
     * @return
     */
    @Override
    public List<Object[]> findByPieChart(String name,String payType,String firstTime, String lastTime) {
        String whereSql = " where 1=1 ";
        if (name != null && !"".equals(name)) {
            whereSql += "AND enterprise_name LIKE '%" + name + "%'";
        }else if(payType != null && !"".equals(payType)) {
            if(payType == "0"){
                whereSql += "AND PAYMENT_STATUS = '" + payType + "' AND AND PAYMENT_STATUS = '" + 2 + "' ";
            }else{
                whereSql += "AND PAYMENT_STATUS = '" + payType + "'";
            }
        }else if( firstTime !=null && !"".equals(firstTime)){
            whereSql += " AND DATE_FORMAT(pay_date,'%Y-%m-%d') >='" + firstTime + "' AND DATE_FORMAT(pay_date,'%Y-%m-%d') <= '" + lastTime + "'";
        } else if(lastTime != null && !"".equals(lastTime)) {
            whereSql += "AND DATE_FORMAT(pay_date,'%Y-%m-%d') >= '" + firstTime + "'AND DATE_FORMAT(pay_date,'%Y-%m-%d') <= '" + lastTime + "'";
        }
        whereSql += " GROUP BY MONTH ";
        List<Object[]> list = getDAO().queryNativeSQL("SELECT DATE_FORMAT(pay_date,'%m')AS MONTH," +
                "(SELECT COUNT(*) FROM `hw_pollutant_payment` t0 WHERE t0.PAYMENT_STATUS='1' AND DATE_FORMAT(t0.pay_date,'%m') = DATE_FORMAT(t.pay_date,'%m'))" +
                "FROM `hw_pollutant_payment` t" + whereSql);
        return list;
    }
}