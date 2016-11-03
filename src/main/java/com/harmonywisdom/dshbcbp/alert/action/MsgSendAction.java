package com.harmonywisdom.dshbcbp.alert.action;

import com.harmonywisdom.apportal.sdk.org.IOrg;
import com.harmonywisdom.apportal.sdk.org.OrgServiceUtil;
import com.harmonywisdom.apportal.sdk.person.IPerson;
import com.harmonywisdom.apportal.sdk.person.PersonServiceUtil;
import com.harmonywisdom.dshbcbp.common.dict.bean.OrgPerson;
import com.harmonywisdom.dshbcbp.office.bean.Contacts;
import com.harmonywisdom.dshbcbp.office.service.ContactsService;
import com.harmonywisdom.dshbcbp.utils.PinyinUtil;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class MsgSendAction extends BaseAction<Contacts, ContactsService> {

    @AutoService
    private ContactsService contactsService;
    @Override
    protected ContactsService getService() {
        return contactsService;
    }
    /**
     * 组织机构人员获取(系统发送)
     */
    public void getOrgPersonList(){
        List<OrgPerson> orgPersonList = new ArrayList<OrgPerson>();
        String[] orgCode = request.getParameterValues("orgCode");
        String type = request.getParameter("type");
        if (orgCode.length == 1) {
            orgPersonList = findOrgPersonByOrgCode(orgCode[0],"-1",type);
        } else {
            for (String code : orgCode) {
                List<OrgPerson> thisOPList = findOrgPersonByOrgCode(code,"-1",type);
                for(OrgPerson op:thisOPList){
                    orgPersonList.add(op);
                }
            }
        }
        write(orgPersonList);
    }

    /**
     * 获取组织机构的人员
     * @param orgCode
     * @param orgParentId
     * @param type
     * @return
     */
    public List<OrgPerson> findOrgPersonByOrgCode(String orgCode,String orgParentId,String type){
        List<OrgPerson> orgPersonList = new ArrayList<>();

        IOrg iOrg = OrgServiceUtil.getOrgByOrgCode(orgCode);
        OrgPerson org = new OrgPerson();
        org.setId(iOrg.getOrgId());
        org.setParentId(orgParentId);
        org.setName(iOrg.getOrgName());
        org.setIcon("common/images/ztree/department.png");
        orgPersonList.add(org);

        List<IPerson> personList = PersonServiceUtil.getPersonByOrgId(iOrg.getOrgId());
        List<IOrg> orgs = OrgServiceUtil.getOrgsByParentOrgId(iOrg.getOrgId());
        if(StringUtils.isNotBlank(type) && (type.equals("1") || type.equals("2"))){
            if(personList.size()>0){
                for (IPerson iPerson:personList){
                    OrgPerson orgPerson = new OrgPerson();
                    orgPerson.setId(iPerson.getPersonId());
                    orgPerson.setName(iPerson.getUserName());
                    String job = (String) iPerson.getExtattrMap().get("job");
                    if(StringUtils.isNotBlank(job)){
                        orgPerson.setJob(job);
                    }
                    orgPerson.setParentId(iOrg.getOrgId());
                    if(iPerson.getSex()==2){
                        orgPerson.setIcon("common/images/ztree/female_lady_user_woman.png");
                    }else{
                        orgPerson.setIcon("common/images/ztree/head_male_man_user.png");
                    }
                    orgPersonList.add(orgPerson);
                }
            }else{
                if(orgs.size()==0){
                    OrgPerson noOrg = new OrgPerson();
                    noOrg.setId("false");
                    noOrg.setParentId(iOrg.getOrgId());
                    noOrg.setName("没有查询到相关人员");
                    noOrg.setIcon("common/images/ztree/remind.png");
                    orgPersonList.add(noOrg);
                }
            }
        }
        if(StringUtils.isNotBlank(type) && (type.equals("1") || type.equals("3"))){
            if(orgs.size()>0){
                for(IOrg iOrgChild:orgs){
                    List<OrgPerson> orgPersons = findOrgPersonByOrgCode(iOrgChild.getOrgCode(),iOrg.getOrgId(),"1");
                    if(orgPersons.size()>0){
                        for(OrgPerson op:orgPersons){
                            orgPersonList.add(op);
                        }
                    }
                }
            }
        }
        return orgPersonList;
    }

    /**
     * 获取组织机构通讯录人员
     */
    public void getOrgContactsList(){
        String orgCode = request.getParameter("orgCode");
        List<OrgPerson> orgPersonList = findOrgContactsPerson(orgCode,"-1");
        write(orgPersonList);
    }
    public List<OrgPerson> findOrgContactsPerson(String orgCode,String orgParentId){
        List<OrgPerson> orgPersonList = new ArrayList<>();

        IOrg iOrg = OrgServiceUtil.getOrgByOrgCode(orgCode);
        OrgPerson org = new OrgPerson();
        org.setId(iOrg.getOrgId());
        org.setParentId(orgParentId);
        org.setName(iOrg.getOrgName());
        org.setIcon("common/images/ztree/department.png");
        org.setPinyinCodes(PinyinUtil.getAllPinYinCodes(iOrg.getOrgName()));
        orgPersonList.add(org);

        Contacts contacts = new Contacts();
        contacts.setOrgId(iOrg.getOrgId());
        List<Contacts> contactsList = contactsService.findBySample(contacts);
        List<IOrg> orgs = OrgServiceUtil.getOrgsByParentOrgId(iOrg.getOrgId());
        if(contactsList.size()>0){
            for (Contacts c:contactsList){
                OrgPerson orgPerson = new OrgPerson();
                orgPerson.setId(c.getId());
                orgPerson.setName(c.getName());
                orgPerson.setJob(c.getPosition());
                orgPerson.setParentId(iOrg.getOrgId());
                orgPerson.setDepartment(c.getDepartment());
                orgPerson.setMobilePhone(c.getPhone());
                orgPerson.setOfficePhone(c.getTel());
                orgPerson.setPinyinCodes(PinyinUtil.getAllPinYinCodes(c.getName()));
                if(StringUtils.isNotBlank(c.getSex()) && c.getSex().equals("2")){
                    orgPerson.setIcon("common/images/ztree/female_lady_user_woman.png");
                }else{
                    orgPerson.setIcon("common/images/ztree/head_male_man_user.png");
                }
                orgPersonList.add(orgPerson);
            }
        }else{
            if(orgs.size()==0){
                OrgPerson noOrg = new OrgPerson();
                noOrg.setId("false");
                noOrg.setParentId(iOrg.getOrgId());
                noOrg.setName("没有查询到相关人员");
                noOrg.setIcon("common/images/ztree/remind.png");
                orgPersonList.add(noOrg);
            }
        }
        if(orgs.size()>0){
            for(IOrg iOrgChild:orgs){
                List<OrgPerson> orgPersons = findOrgContactsPerson(iOrgChild.getOrgCode(),iOrg.getOrgId());
                if(orgPersons.size()>0){
                    for(OrgPerson op:orgPersons){
                        orgPersonList.add(op);
                    }
                }
            }
        }
        return orgPersonList;
    }
}