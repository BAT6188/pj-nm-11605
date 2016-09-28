package com.harmonywisdom.dshbcbp.office.service.impl;

import com.harmonywisdom.dshbcbp.office.bean.PubInfo;
import com.harmonywisdom.dshbcbp.office.dao.PubInfoDAO;
import com.harmonywisdom.dshbcbp.office.service.PubInfoService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("pubInfoService")
public class PubInfoServiceImpl extends BaseService<PubInfo, String> implements PubInfoService {
    @Autowired
    private PubInfoDAO pubInfoDAO;

    @Override
    protected BaseDAO<PubInfo, String> getDAO() {
        return pubInfoDAO;
    }
}