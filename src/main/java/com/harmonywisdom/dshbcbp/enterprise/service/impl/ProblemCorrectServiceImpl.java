package com.harmonywisdom.dshbcbp.enterprise.service.impl;

import com.harmonywisdom.dshbcbp.enterprise.bean.ProblemCorrect;
import com.harmonywisdom.dshbcbp.enterprise.dao.ProblemCorrectDAO;
import com.harmonywisdom.dshbcbp.enterprise.service.ProblemCorrectService;
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