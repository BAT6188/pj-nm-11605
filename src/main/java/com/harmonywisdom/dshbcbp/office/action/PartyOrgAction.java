package com.harmonywisdom.dshbcbp.office.action;

import com.harmonywisdom.dshbcbp.office.bean.PartyOrg;
import com.harmonywisdom.dshbcbp.office.service.PartyOrgService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class PartyOrgAction extends BaseAction<PartyOrg, PartyOrgService> {
    @AutoService
    private PartyOrgService partyOrgService;

    @Override
    protected PartyOrgService getService() {
        return partyOrgService;
    }

    public void getPartyOrgZtree(){
        write(partyOrgService.findAll());
    }
}