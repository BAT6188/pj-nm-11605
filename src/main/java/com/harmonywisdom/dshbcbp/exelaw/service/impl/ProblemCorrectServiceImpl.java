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

    /**
     * 整改情况更新状态
     * @param id
     */
    @Override
    public int updateStatus(String id) {
        int problemCorrect = getDAO().executeJPQL("update ProblemCorrect t set t.progress='2' where t.id=? ",id);
        return problemCorrect;

    }
}