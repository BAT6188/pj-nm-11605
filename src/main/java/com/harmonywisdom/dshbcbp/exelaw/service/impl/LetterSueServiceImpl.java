package com.harmonywisdom.dshbcbp.exelaw.service.impl;

import com.harmonywisdom.dshbcbp.exelaw.bean.LetterSue;
import com.harmonywisdom.dshbcbp.exelaw.dao.LetterSueDAO;
import com.harmonywisdom.dshbcbp.exelaw.service.LetterSueService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("letterSueService")
public class LetterSueServiceImpl extends BaseService<LetterSue, String> implements LetterSueService {
    @Autowired
    private LetterSueDAO letterSueDAO;

    @Override
    protected BaseDAO<LetterSue, String> getDAO() {
        return letterSueDAO;
    }
}