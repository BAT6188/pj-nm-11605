package com.harmonywisdom.dshbcbp.office.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.harmonywisdom.apportal.sdk.person.IPerson;
import com.harmonywisdom.apportal.sdk.person.PersonServiceUtil;
import com.harmonywisdom.dshbcbp.office.bean.PartyOrgIperson;
import com.harmonywisdom.dshbcbp.office.bean.TIPerson;
import com.harmonywisdom.dshbcbp.office.dao.PartyOrgIpersonDAO;
import com.harmonywisdom.dshbcbp.office.service.PartyOrgIpersonService;
import com.harmonywisdom.framework.dao.*;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("partyOrgIpersonService")
public class PartyOrgIpersonServiceImpl extends BaseService<PartyOrgIperson, String> implements PartyOrgIpersonService {
    @Autowired
    private PartyOrgIpersonDAO partyOrgIpersonDAO;

    @Override
    protected BaseDAO<PartyOrgIperson, String> getDAO() {
        return partyOrgIpersonDAO;
    }

    @Override
    public Map<String, Object> savePartyOrgIperson(PartyOrgIperson partyOrgIperson) {
        List<TIPerson> iPersons = JSONArray.parseArray(partyOrgIperson.getPersonList(), TIPerson.class);
        if(iPersons!=null && iPersons.size()>0){
            for(TIPerson iPerson:iPersons){
                PartyOrgIperson saveEntity = new PartyOrgIperson(partyOrgIperson.getPartyOrgId(),iPerson.getPersonId());
                partyOrgIpersonDAO.save(saveEntity);
            }
        }
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("success",true);
        returnMap.put("saveData",iPersons);
        return returnMap;
    }

    public QueryResult<PartyOrgIperson> find(QueryCondition var1) {
        QueryResult<PartyOrgIperson> partyOrgIpersonQueryResult = this.getDAO().find(var1);
        if(partyOrgIpersonQueryResult.getTotal()>0){
            for(PartyOrgIperson poi:partyOrgIpersonQueryResult.getRows()){
                IPerson iPerson = PersonServiceUtil.getPerson(poi.getIpersonId());
                poi.setIPerson(iPerson);
            }
        }
        return partyOrgIpersonQueryResult;
    }

    public QueryResult<PartyOrgIperson> findBySample(PartyOrgIperson var1, Paging var2, String var3, Direction var4) {
        QueryResult<PartyOrgIperson> partyOrgIpersonQueryResult = this.getDAO().findBySample(var1, var2, var3, var4);
        if(partyOrgIpersonQueryResult.getTotal()>0){
            for(PartyOrgIperson poi:partyOrgIpersonQueryResult.getRows()){
                IPerson iPerson = PersonServiceUtil.getPerson(poi.getIpersonId());
                poi.setIPerson(iPerson);
            }
        }
        return partyOrgIpersonQueryResult;
    }
}