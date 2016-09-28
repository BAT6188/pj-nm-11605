package com.harmonywisdom.dshbcbp.enterprise.service.impl;

import com.harmonywisdom.dshbcbp.enterprise.bean.SelfCheckReport;
import com.harmonywisdom.dshbcbp.enterprise.dao.SelfCheckReportDAO;
import com.harmonywisdom.dshbcbp.enterprise.service.SelfCheckReportService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("selfCheckReportService")
public class SelfCheckReportServiceImpl extends BaseService<SelfCheckReport, String> implements SelfCheckReportService {
    @Autowired
    private SelfCheckReportDAO selfCheckReportDAO;

    @Override
    protected BaseDAO<SelfCheckReport, String> getDAO() {
        return selfCheckReportDAO;
    }
}