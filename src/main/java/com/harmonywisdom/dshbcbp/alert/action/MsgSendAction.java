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
     * 人员获取
     */
    public void getOrgPersonList(){
        List<OrgPerson> orgPersonList = new ArrayList<OrgPerson>();
        String[] orgCode = request.getParameterValues("orgCode");
        String type = request.getParameter("type");
        String findType = request.getParameter("findType");
        findType = StringUtils.isNotBlank(findType)?findType:"null";
        if (orgCode.length == 1) {
            if(findType.equals("2")){
                orgPersonList = findOrgContactsPerson(orgCode[0],"-1");
            }else{
                orgPersonList = findOrgPersonByOrgCode(orgCode[0],"-1",type);
            }
        } else {
            for (String code : orgCode) {
                List<OrgPerson> thisOPList = new ArrayList<OrgPerson>();
                if(findType.equals("2")){
                    thisOPList = findOrgContactsPerson(code,"-1");
                }else{
                    thisOPList = findOrgPersonByOrgCode(code,"-1",type);
                }
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
        orgPersonList.add(coverToOrgPerson(iOrg,null,null,orgParentId));

        List<IPerson> personList = PersonServiceUtil.getPersonByOrgId(iOrg.getOrgId());
        List<IOrg> orgs = OrgServiceUtil.getOrgsByParentOrgId(iOrg.getOrgId());
        if(StringUtils.isNotBlank(type) && (type.equals("1") || type.equals("2"))){
            if(personList.size()>0){
                for (IPerson iPerson:personList){
                    orgPersonList.add(coverToOrgPerson(null,iPerson,null,iOrg.getOrgId()));
                }
            }else{
                if(orgs.size()==0){
                    orgPersonList.add(coverToOrgPerson(null,null,null,iOrg.getOrgId()));
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
     * 获取通讯录联系人
     * @param orgCode
     * @param orgParentId
     * @return
     */
    public List<OrgPerson> findOrgContactsPerson(String orgCode,String orgParentId){
        List<OrgPerson> orgPersonList = new ArrayList<>();

        IOrg iOrg = OrgServiceUtil.getOrgByOrgCode(orgCode);
        orgPersonList.add(coverToOrgPerson(iOrg,null,null,orgParentId));

        Contacts contacts = new Contacts();
        contacts.setOrgId(iOrg.getOrgId());
        List<Contacts> contactsList = contactsService.findBySample(contacts);
        List<IOrg> orgs = OrgServiceUtil.getOrgsByParentOrgId(iOrg.getOrgId());
        if(contactsList.size()>0){
            for (Contacts c:contactsList){
                orgPersonList.add(coverToOrgPerson(null,null,c,iOrg.getOrgId()));
            }
        }else{
            if(orgs.size()==0){
                orgPersonList.add(coverToOrgPerson(null,null,null,iOrg.getOrgId()));
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

    /**
     * 类型转换
     * @param iOrg
     * @param iPerson
     * @param contacts
     * @param parentId
     * @return
     */
    public OrgPerson coverToOrgPerson(IOrg iOrg,IPerson iPerson,Contacts contacts,String parentId){
        OrgPerson orgPerson = new OrgPerson();
        if(iOrg!=null){
            orgPerson.setId(iOrg.getOrgId());
            orgPerson.setParentId(parentId);
            orgPerson.setName(iOrg.getOrgName());
            orgPerson.setIcon("common/images/ztree/department.png");
            orgPerson.setPinyinCodes(PinyinUtil.getAllPinYinCodes(iOrg.getOrgName()));
        }
        if(iPerson!=null){
            orgPerson.setId(iPerson.getPersonId());
            orgPerson.setName(iPerson.getUserName());
            String job = (String) iPerson.getExtattrMap().get("job");
            if(StringUtils.isNotBlank(job)){
                orgPerson.setJob(job);
            }
            orgPerson.setParentId(parentId);
            orgPerson.setPinyinCodes(PinyinUtil.getAllPinYinCodes(iPerson.getUserName()));
            if(iPerson.getSex()==2){
                orgPerson.setIcon("common/images/ztree/female_lady_user_woman.png");
            }else{
                orgPerson.setIcon("common/images/ztree/head_male_man_user.png");
            }
        }
        if(contacts!=null){
            orgPerson.setId(contacts.getId());
            orgPerson.setName(contacts.getName());
            orgPerson.setJob(contacts.getPosition());
            orgPerson.setParentId(parentId);
            orgPerson.setDepartment(contacts.getDepartment());
            orgPerson.setMobilePhone(contacts.getPhone());
            orgPerson.setOfficePhone(contacts.getTel());
            orgPerson.setPinyinCodes(PinyinUtil.getAllPinYinCodes(contacts.getName()));
            if(StringUtils.isNotBlank(contacts.getSex()) && contacts.getSex().equals("2")){
                orgPerson.setIcon("common/images/ztree/female_lady_user_woman.png");
            }else{
                orgPerson.setIcon("common/images/ztree/head_male_man_user.png");
            }
        }
        if(iOrg == null && iPerson==null && contacts== null && StringUtils.isNotBlank(parentId)){
            orgPerson.setId("false");
            orgPerson.setParentId(parentId);
            orgPerson.setName("没有查询到相关人员");
            orgPerson.setIcon("common/images/ztree/remind.png");
        }
        return orgPerson;
    }
}