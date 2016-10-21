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
     * @param firstTime
     * @param lastTime
     * @return
     */
    @Override
    public List<Object[]> findByColumnChart(Date firstTime, Date lastTime) {
        List<Object[]> list = getDAO().queryNativeSQL("SELECT DATE_FORMAT(pay_date,'%m')AS MONTH," +
                "(SELECT COUNT(*) FROM `hw_pollutant_payment` t0 WHERE t0.PAYMENT_STATUS='1'AND DATE_FORMAT(t0.pay_date,'%m') = DATE_FORMAT(t.pay_date,'%m'))," +
                "(SELECT COUNT(*) FROM `hw_pollutant_payment` t0 WHERE t0.PAYMENT_STATUS='2'AND DATE_FORMAT(t0.pay_date,'%m') = DATE_FORMAT(t.pay_date,'%m'))" +
                "FROM `hw_pollutant_payment` t WHERE DATE_FORMAT(pay_date,'%Y-%m-%d')> ? AND DATE_FORMAT(pay_date,'%Y-%m-%d')<=? GROUP BY MONTH",firstTime,lastTime);
       return list;
    }

    /**
     * 排污统计highChart
     * 获取饼状图数据
     * @param firstTime
     * @param endTime
     * @return
     */
    @Override
    public List<Object[]> findByPieChart(Date firstTime, Date endTime) {
        List<Object[]> list = getDAO().queryNativeSQL("SELECT DATE_FORMAT(pay_date,'%m')AS MONTH," +
                "(SELECT COUNT(*) FROM `hw_pollutant_payment` t0 WHERE t0.PAYMENT_STATUS='1' AND DATE_FORMAT(t0.pay_date,'%m') = DATE_FORMAT(t.pay_date,'%m'))" +
                "FROM `hw_pollutant_payment` t WHERE DATE_FORMAT(pay_date,'%Y-%m-%d')> '2016-01-01' AND DATE_FORMAT(pay_date,'%Y-%m-%d')<= '2016-12-30'GROUP BY MONTH");
        return list;
    }
}