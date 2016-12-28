package com.harmonywisdom.dshbcbp.office.service;

import com.harmonywisdom.dshbcbp.office.bean.PartyOrgIperson;
import com.harmonywisdom.framework.dao.Direction;
import com.harmonywisdom.framework.dao.Paging;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryResult;
import com.harmonywisdom.framework.service.IBaseService;

import java.util.Map;

public interface PartyOrgIpersonService extends IBaseService<PartyOrgIperson, String> {

    Map<String,Object> savePartyOrgIperson(PartyOrgIperson partyOrgIperson);

    QueryResult<PartyOrgIperson> find(QueryCondition var1);

    QueryResult<PartyOrgIperson> findBySample(PartyOrgIperson var1, Paging var2, String var3, Direction var4);
}