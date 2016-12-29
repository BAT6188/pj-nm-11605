package com.harmonywisdom.dshbcbp.office.action;

import com.harmonywisdom.dshbcbp.office.bean.PartyOrgIperson;
import com.harmonywisdom.dshbcbp.office.service.PartyOrgIpersonService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class PartyOrgIpersonAction extends BaseAction<PartyOrgIperson, PartyOrgIpersonService> {
    @AutoService
    private PartyOrgIpersonService partyOrgIpersonService;

    @Override
    protected PartyOrgIpersonService getService() {
        return partyOrgIpersonService;
    }

    public void savePartyOrgIperson(){
        if(StringUtils.isNotBlank(entity.getPersonList())){
            write(partyOrgIpersonService.savePartyOrgIperson(entity));
        }else{
            Map<String,Object> returnMap = new HashMap<>();
            returnMap.put("success",false);
            write(returnMap);
        }
    }
}