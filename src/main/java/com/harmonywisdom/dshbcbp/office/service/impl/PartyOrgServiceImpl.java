package com.harmonywisdom.dshbcbp.office.service.impl;

import com.harmonywisdom.dshbcbp.office.bean.PartyOrg;
import com.harmonywisdom.dshbcbp.office.dao.PartyOrgDAO;
import com.harmonywisdom.dshbcbp.office.service.PartyOrgService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("partyOrgService")
public class PartyOrgServiceImpl extends BaseService<PartyOrg, String> implements PartyOrgService {
    @Autowired
    private PartyOrgDAO partyOrgDAO;

    @Override
    protected BaseDAO<PartyOrg, String> getDAO() {
        return partyOrgDAO;
    }
}