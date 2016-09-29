package com.harmonywisdom.dshbcbp.exelaw.service.impl;

import com.harmonywisdom.dshbcbp.exelaw.bean.ProblemCorrect;
import com.harmonywisdom.dshbcbp.exelaw.dao.ProblemCorrectDAO;
import com.harmonywisdom.dshbcbp.exelaw.service.ProblemCorrectService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("problemCorrectService")
public class ProblemCorrectServiceImpl extends BaseService<ProblemCorrect, String> implements ProblemCorrectService {
    @Autowired
    private ProblemCorrectDAO problemCorrectDAO;

    @Override
    protected BaseDAO<ProblemCorrect, String> getDAO() {
        return problemCorrectDAO;
    }
}