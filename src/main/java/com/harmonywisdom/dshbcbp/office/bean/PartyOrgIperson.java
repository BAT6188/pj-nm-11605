package com.harmonywisdom.dshbcbp.office.bean;

import com.harmonywisdom.apportal.sdk.person.IPerson;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "HW_PARTY_ORG_IPERSON")
public class PartyOrgIperson implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    private String id;

    @Column(name = "party_org_id",length = 64)
    private String partyOrgId;

    @Column(name = "iperson_id",length = 64)
    private String ipersonId;

    @Transient
    private IPerson iPerson;
    @Transient
    private String personList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPartyOrgId() {
        return partyOrgId;
    }

    public void setPartyOrgId(String partyOrgId) {
        this.partyOrgId = partyOrgId;
    }

    public String getIpersonId() {
        return ipersonId;
    }

    public void setIpersonId(String ipersonId) {
        this.ipersonId = ipersonId;
    }

    public IPerson getIPerson() {
        return iPerson;
    }

    public void setIPerson(IPerson iPerson) {
        this.iPerson = iPerson;
    }

    public String getPersonList() {
        return personList;
    }

    public void setPersonList(String personList) {
        this.personList = personList;
    }

    public PartyOrgIperson(String partyOrgId, String ipersonId) {
        this.partyOrgId = partyOrgId;
        this.ipersonId = ipersonId;
    }

    public PartyOrgIperson() {
    }
}