package com.harmonywisdom.dshbcbp.office.service.impl;

import com.harmonywisdom.dshbcbp.office.bean.WorkSum;
import com.harmonywisdom.dshbcbp.office.dao.WorkSumDAO;
import com.harmonywisdom.dshbcbp.office.service.WorkSumService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("workSumService")
public class WorkSumServiceImpl extends BaseService<WorkSum, String> implements WorkSumService {
    @Autowired
    private WorkSumDAO workSumDAO;

    @Override
    protected BaseDAO<WorkSum, String> getDAO() {
        return workSumDAO;
    }
}