package com.harmonywisdom.dshbcbp.enterprise.service.impl;

import com.harmonywisdom.dshbcbp.enterprise.bean.LetterSue;
import com.harmonywisdom.dshbcbp.enterprise.dao.LetterSueDAO;
import com.harmonywisdom.dshbcbp.enterprise.service.LetterSueService;
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