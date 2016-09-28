package com.harmonywisdom.dshbcbp.production.service.impl;

import com.harmonywisdom.dshbcbp.production.bean.Kiln;
import com.harmonywisdom.dshbcbp.production.dao.KilnDAO;
import com.harmonywisdom.dshbcbp.production.service.KilnService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("kilnService")
public class KilnServiceImpl extends BaseService<Kiln, String> implements KilnService {
    @Autowired
    private KilnDAO kilnDAO;

    @Override
    protected BaseDAO<Kiln, String> getDAO() {
        return kilnDAO;
    }
}