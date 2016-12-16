package com.harmonywisdom.dshbcbp.alert.action;

import com.harmonywisdom.apportal.sdk.org.IOrg;
import com.harmonywisdom.apportal.sdk.org.OrgServiceUtil;
import com.harmonywisdom.apportal.sdk.person.IPerson;
import com.harmonywisdom.apportal.sdk.person.PersonServiceUtil;
import com.harmonywisdom.dshbcbp.common.dict.bean.ZtreeObj;
import com.harmonywisdom.dshbcbp.office.bean.Contacts;
import com.harmonywisdom.dshbcbp.office.service.ContactsService;
import com.harmonywisdom.dshbcbp.utils.PinyinUtil;
import com.harmonywisdom.dshbcbp.utils.SortList;
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
        List<ZtreeObj> ztreeObjList = new ArrayList<ZtreeObj>();
        String[] orgCode = request.getParameterValues("orgCode");
        String type = request.getParameter("type");
        String findType = request.getParameter("findType");
        findType = StringUtils.isNotBlank(findType)?findType:"null";
        if (orgCode.length == 1) {
            if(findType.equals("2")){
                ztreeObjList=(List<ZtreeObj>)request.getSession().getAttribute("findOrgContactsPerson"+orgCode[0]);
                if (null==ztreeObjList){
                    ztreeObjList = findOrgContactsPerson(orgCode[0],"-1");
                    request.getSession().setAttribute("findOrgContactsPerson"+orgCode[0],ztreeObjList);
                }
            }else{
                ztreeObjList=(List<ZtreeObj>)request.getSession().getAttribute("findOrgPersonByOrgCode"+orgCode[0]);
                if (null==ztreeObjList){
                    ztreeObjList = findOrgPersonByOrgCode(orgCode[0],"-1",type);
                    request.getSession().setAttribute("findOrgPersonByOrgCode"+orgCode[0],ztreeObjList);
                }
            }
        } else {
            for (String code : orgCode) {
                List<ZtreeObj> thisOPList = new ArrayList<ZtreeObj>();
                if(findType.equals("2")){
                    ztreeObjList=(List<ZtreeObj>)request.getSession().getAttribute("findOrgContactsPerson"+code);
                    if (null==ztreeObjList){
                        thisOPList = findOrgContactsPerson(code,"-1");
                        request.getSession().setAttribute("findOrgContactsPerson"+code,ztreeObjList);
                    }
                }else{
                    ztreeObjList=(List<ZtreeObj>)request.getSession().getAttribute("findOrgPersonByOrgCode"+code);
                    if (null==ztreeObjList){
                        thisOPList = findOrgPersonByOrgCode(code,"-1",type);
                        request.getSession().setAttribute("findOrgPersonByOrgCode"+code,ztreeObjList);
                    }
                }
                for(ZtreeObj op:thisOPList){
                    ztreeObjList.add(op);
                }
            }
        }
        write(ztreeObjList);
    }

    /**
     * 获取组织机构的人员
     * @param orgCode
     * @param orgParentId
     * @param type
     * @return
     */
    public List<ZtreeObj> findOrgPersonByOrgCode(String orgCode, String orgParentId, String type){
        List<ZtreeObj> ztreeObjList = new ArrayList<>();

        IOrg iOrg = OrgServiceUtil.getOrgByOrgCode(orgCode);
        if(iOrg.getDeltag().equals("1")) return ztreeObjList;
        ztreeObjList.add(coverToOrgPerson(iOrg,null,null,orgParentId));

        List<IPerson> personList = PersonServiceUtil.getPersonByOrgId(iOrg.getOrgId());
        List<IOrg> orgs = OrgServiceUtil.getOrgsByParentOrgId(iOrg.getOrgId());
        if(StringUtils.isNotBlank(type) && (type.equals("1") || type.equals("2"))){
            if(personList.size()>0){
                //调用排序通用类
                SortList<IPerson> sortList = new SortList<IPerson>();
                sortList.Sort(personList, "serialIndex", "asc");
                for (IPerson iPerson:personList){
                    ztreeObjList.add(coverToOrgPerson(null,iPerson,null,iOrg.getOrgId()));
                }
            }/*else{
                if(orgs.size()==0){
                    ztreeObjList.add(coverToOrgPerson(null,null,null,iOrg.getOrgId()));
                }
            }*/
        }
        if(StringUtils.isNotBlank(type) && (type.equals("1") || type.equals("3"))){
            if(orgs.size()>0){
                //调用排序通用类
                SortList<IOrg> orgList = new SortList<IOrg>();
                orgList.Sort(orgs, "serialIndex", "asc");
                for(IOrg iOrgChild:orgs){
                    List<ZtreeObj> ztreeObjs = findOrgPersonByOrgCode(iOrgChild.getOrgCode(),iOrg.getOrgId(),"1");
                    if(ztreeObjs.size()>0){
                        for(ZtreeObj op: ztreeObjs){
                            ztreeObjList.add(op);
                        }
                    }
                }
            }
        }
        return ztreeObjList;
    }

    /**
     * 获取通讯录联系人
     * @param orgCode
     * @param orgParentId
     * @return
     */
    public List<ZtreeObj> findOrgContactsPerson(String orgCode, String orgParentId){
        List<ZtreeObj> ztreeObjList = new ArrayList<>();

        IOrg iOrg = OrgServiceUtil.getOrgByOrgCode(orgCode);
        if(iOrg.getDeltag().equals("1")) return ztreeObjList;
        ztreeObjList.add(coverToOrgPerson(iOrg,null,null,orgParentId));
        Contacts contacts = new Contacts();
        contacts.setOrgId(iOrg.getOrgId());
        List<Contacts> contactsList = contactsService.findBySample(contacts);
        List<IOrg> orgs = OrgServiceUtil.getOrgsByParentOrgId(iOrg.getOrgId());
        if(contactsList.size()>0){
            //调用排序通用类
            SortList<Contacts> sortList = new SortList<Contacts>();
            sortList.Sort(contactsList, "sort", "desc");
            for (Contacts c:contactsList){
                ztreeObjList.add(coverToOrgPerson(null,null,c,iOrg.getOrgId()));
            }
        }/*else{
            if(orgs.size()==0){
                ztreeObjList.add(coverToOrgPerson(null,null,null,iOrg.getOrgId()));
            }
        }*/
        if(orgs.size()>0){
            //调用排序通用类
            SortList<IOrg> orgList = new SortList<IOrg>();
            orgList.Sort(orgs, "serialIndex", "asc");
            for(IOrg iOrgChild:orgs){
                List<ZtreeObj> ztreeObjs = findOrgContactsPerson(iOrgChild.getOrgCode(),iOrg.getOrgId());
                if(ztreeObjs.size()>0){
                    for(ZtreeObj op: ztreeObjs){
                        ztreeObjList.add(op);
                    }
                }
            }
        }
        return ztreeObjList;
    }

    /**
     * 类型转换
     * @param iOrg
     * @param iPerson
     * @param contacts
     * @param parentId
     * @return
     */
    public ZtreeObj coverToOrgPerson(IOrg iOrg, IPerson iPerson, Contacts contacts, String parentId){
        ZtreeObj ztreeObj = new ZtreeObj();
        if(iOrg!=null){
            ztreeObj.setCouldChose(false);
            ztreeObj.setId(iOrg.getOrgId());
            ztreeObj.setParentId(parentId);
            ztreeObj.setName(iOrg.getOrgName());
            ztreeObj.setIcon("common/images/ztree/department.png");
            ztreeObj.setPinyinCodes(PinyinUtil.getAllPinYinCodes(iOrg.getOrgName()));
        }
        if(iPerson!=null){
            ztreeObj.setCouldChose(true);
            ztreeObj.setId(iPerson.getPersonId());
            ztreeObj.setName(iPerson.getUserName());
            if("1".equals(iPerson.getPcode())){
                //ztreeObj.setName(iPerson.getUserName()+"(党员)");
                ztreeObj.setPcode(iPerson.getPcode());
                ztreeObj.setPinyinCodes(PinyinUtil.getAllPinYinCodes(iPerson.getUserName()+"党员"));
            }else{
                //ztreeObj.setName(iPerson.getUserName());
                ztreeObj.setPinyinCodes(PinyinUtil.getAllPinYinCodes(iPerson.getUserName()));
            }
            ztreeObj.setUserId(iPerson.getUserId());
            ztreeObj.setMobilePhone(iPerson.getMobile());
            String job = (String) iPerson.getExtattrMap().get("job");
            if(StringUtils.isNotBlank(job)){
                ztreeObj.setJob(job);
            }
            ztreeObj.setParentId(parentId);
            if(iPerson.getSex()==2){
                ztreeObj.setIcon("common/images/ztree/female_lady_user_woman.png");
            }else{
                ztreeObj.setIcon("common/images/ztree/head_male_man_user.png");
            }
            ztreeObj.setIperson(iPerson);
        }
        if(contacts!=null){
            ztreeObj.setCouldChose(true);
            ztreeObj.setId(contacts.getId());
            ztreeObj.setName(contacts.getName());
            ztreeObj.setJob(contacts.getPosition());
            ztreeObj.setParentId(parentId);
            ztreeObj.setDepartment(contacts.getDepartment());
            ztreeObj.setMobilePhone(contacts.getPhone());
            ztreeObj.setOfficePhone(contacts.getTel());
            ztreeObj.setPinyinCodes(PinyinUtil.getAllPinYinCodes(contacts.getName()));
            if(StringUtils.isNotBlank(contacts.getSex()) && contacts.getSex().equals("2")){
                ztreeObj.setIcon("common/images/ztree/female_lady_user_woman.png");
            }else{
                ztreeObj.setIcon("common/images/ztree/head_male_man_user.png");
            }
        }
        if(iOrg == null && iPerson==null && contacts== null && StringUtils.isNotBlank(parentId)){
            ztreeObj.setCouldChose(false);
            ztreeObj.setId("false");
            ztreeObj.setParentId(parentId);
            ztreeObj.setName("没有查询到相关人员");
            ztreeObj.setIcon("common/images/ztree/remind.png");
        }
        return ztreeObj;
    }

    /**
     * 获取组织机构ztree
     */
    public void getOrgZtree(){
        List<ZtreeObj> ztreeObjList = new ArrayList<ZtreeObj>();
        String[] orgCode = request.getParameterValues("orgCode");
        if (orgCode.length == 1) {
            if(orgCode[0].equals("root")){
                List<IOrg> orgs = OrgServiceUtil.getOrgsByParentOrgId("root");
                if(orgs.size()>0){
                    for(IOrg iOrg:orgs){
                        List<ZtreeObj> thisOPList = findOrg(iOrg.getOrgCode(),"-1");
                        for(ZtreeObj op:thisOPList){
                            ztreeObjList = (List<ZtreeObj>)request.getSession().getAttribute("ztreeObjListaddop" + orgCode[0]);
                            if (ztreeObjList==null){
                                ztreeObjList.add(op);
                                request.getSession().setAttribute("ztreeObjListaddop" + orgCode[0],ztreeObjList);
                            }
                        }
                    }
                }
            }else{
                ztreeObjList = (List<ZtreeObj>)request.getSession().getAttribute("ztreeObjListfindOrg" + orgCode[0]);
                if (ztreeObjList==null){
                    ztreeObjList =  findOrg(orgCode[0],"-1");
                    request.getSession().setAttribute("ztreeObjListfindOrg" + orgCode[0],ztreeObjList);
                }
            }
        } else {
            for (String code : orgCode) {
                List<ZtreeObj> thisOPList = findOrg(code,"-1");
                for(ZtreeObj op:thisOPList){
                    ztreeObjList = (List<ZtreeObj>)request.getSession().getAttribute("ztreeObjListthisOPList" + orgCode[0]);
                    if (ztreeObjList==null){
                        ztreeObjList.add(op);
                        request.getSession().setAttribute("ztreeObjListthisOPList" + orgCode[0],ztreeObjList);
                    }
                }
            }
        }
        write(ztreeObjList);
    }

    public List<ZtreeObj> findOrg(String orgCode, String orgParentId){
        List<ZtreeObj> ztreeObjList = new ArrayList<>();
        IOrg iOrg = OrgServiceUtil.getOrgByOrgCode(orgCode);
        if(iOrg.getDeltag().equals("1")) return ztreeObjList;
        ztreeObjList.add(coverToOrgPerson(iOrg,null,null,orgParentId));
        List<IOrg> orgs = OrgServiceUtil.getOrgsByParentOrgId(iOrg.getOrgId());
        if(orgs.size()>0){
            SortList<IOrg> orgList = new SortList<IOrg>();
            orgList.Sort(orgs, "serialIndex", "asc");
            for(IOrg iOrgChild:orgs){
                List<ZtreeObj> ztreeObjs = findOrg(iOrgChild.getOrgCode(),iOrg.getOrgId());
                if(ztreeObjs.size()>0){
                    for(ZtreeObj op: ztreeObjs){
                        ztreeObjList.add(op);
                    }
                }
            }
        }
        return ztreeObjList;
    }
}