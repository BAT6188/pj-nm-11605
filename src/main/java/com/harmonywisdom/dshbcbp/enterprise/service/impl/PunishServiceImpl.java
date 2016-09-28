package com.harmonywisdom.dshbcbp.enterprise.service.impl;

import com.harmonywisdom.dshbcbp.enterprise.bean.Punish;
import com.harmonywisdom.dshbcbp.enterprise.dao.PunishDAO;
import com.harmonywisdom.dshbcbp.enterprise.service.PunishService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("punishService")
public class PunishServiceImpl extends BaseService<Punish, String> implements PunishService {
    @Autowired
    private PunishDAO punishDAO;

    @Override
    protected BaseDAO<Punish, String> getDAO() {
        return punishDAO;
    }
}