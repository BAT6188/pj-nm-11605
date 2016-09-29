package com.harmonywisdom.dshbcbp.exelaw.service.impl;

import com.harmonywisdom.dshbcbp.exelaw.bean.PollutantPayment;
import com.harmonywisdom.dshbcbp.exelaw.dao.PollutantPaymentDAO;
import com.harmonywisdom.dshbcbp.exelaw.service.PollutantPaymentService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("pollutantPaymentService")
public class PollutantPaymentServiceImpl extends BaseService<PollutantPayment, String> implements PollutantPaymentService {
    @Autowired
    private PollutantPaymentDAO pollutantPaymentDAO;

    @Override
    protected BaseDAO<PollutantPayment, String> getDAO() {
        return pollutantPaymentDAO;
    }
}