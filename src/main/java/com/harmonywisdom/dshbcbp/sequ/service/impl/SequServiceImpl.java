package com.harmonywisdom.dshbcbp.sequ.service.impl;

import com.harmonywisdom.dshbcbp.sequ.bean.Sequ;
import com.harmonywisdom.dshbcbp.sequ.dao.SequDAO;
import com.harmonywisdom.dshbcbp.sequ.service.SequService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("sequService")
public class SequServiceImpl extends BaseService<Sequ, String> implements SequService {
    @Autowired
    private SequDAO sequDAO;

    @Override
    protected BaseDAO<Sequ, String> getDAO() {
        return sequDAO;
    }
}