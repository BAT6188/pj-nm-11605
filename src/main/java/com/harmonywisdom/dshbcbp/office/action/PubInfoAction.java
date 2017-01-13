package com.harmonywisdom.dshbcbp.office.action;

import com.harmonywisdom.apportal.sdk.org.IOrg;
import com.harmonywisdom.apportal.sdk.org.OrgServiceUtil;
import com.harmonywisdom.apportal.sdk.person.IPerson;
import com.harmonywisdom.apportal.sdk.person.PersonServiceUtil;
import com.harmonywisdom.core.user.impl.UserProfile;
import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
import com.harmonywisdom.dshbcbp.office.bean.PubInfo;
import com.harmonywisdom.dshbcbp.office.service.PubInfoRelTableService;
import com.harmonywisdom.dshbcbp.office.service.PubInfoService;
import com.harmonywisdom.dshbcbp.utils.ApportalUtil;
import com.harmonywisdom.dshbcbp.utils.MyDateUtils;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.*;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

import java.util.*;

public class PubInfoAction extends BaseAction<PubInfo, PubInfoService> {
    @AutoService
    private PubInfoService pubInfoService;
    @AutoService
    private PubInfoRelTableService pubInfoRelTableService;
    @AutoService
    private AttachmentService attachmentService;

    @Override
    protected PubInfoService getService() {
        return pubInfoService;
    }

    @Override
    protected QueryCondition getQueryCondition() {
        String StrGrade = request.getParameter("grades");
        String enterpriseReleaseStatus = request.getParameter("enterpriseStatus");
        String mobileOperType = request.getParameter("mobileOperType");


        QueryParam param = new QueryParam();
        if(StrGrade != null && !"".equals(StrGrade)){
            param.andParam(new QueryParam("grade", QueryOperator.LIKE,"%"+StrGrade+"%"));
        }
        if(enterpriseReleaseStatus != null && !"".equals(enterpriseReleaseStatus)){
            param.andParam(new QueryParam("status",QueryOperator.EQ,enterpriseReleaseStatus));
        }
        if (StringUtils.isNotBlank(entity.getTitle())) {
            param.andParam(new QueryParam("title", QueryOperator.LIKE, entity.getTitle()));
        }
        if (StringUtils.isNotBlank(entity.getType())) {
            param.andParam(new QueryParam("type", QueryOperator.LIKE, entity.getType()));
        }
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        if(StringUtils.isNotBlank(startTime)){
            param.andParam(new QueryParam("pubTime", QueryOperator.GE, MyDateUtils.getFullDate(startTime,true)));
        }
        if(StringUtils.isNotBlank(endTime)){
            param.andParam(new QueryParam("pubTime", QueryOperator.LE,MyDateUtils.getFullDate(endTime,false)));
        }

/*        //组织机构code
        String orgCode = request.getParameter("orgCode");
        QueryParam statusParam = new QueryParam();
        if (orgCode != null) {
            statusParam.andParam(new QueryParam("pubOrgId", QueryOperator.LIKE, orgCode));
            statusParam.orParam(new QueryParam("status", QueryOperator.EQ, "1")); //已发布
        } else {
            statusParam.andParam(new QueryParam("status", QueryOperator.EQ, "1")); //已发布
        }
        param.andParam(statusParam);*/
        String orgCode = request.getParameter("orgCode");
        /*IOrg iOrg = ApportalUtil.getIOrgOfCurrentUser(request);
        if(iOrg!=null){this.entity.setPubOrgId(iOrg.getOrgId());}*/
        if (StringUtils.isNotBlank(entity.getStatus())) {
            param.andParam(new QueryParam("status", QueryOperator.EQ,entity.getStatus()));
            param.andParam(new QueryParam("pubOrgId", QueryOperator.LIKE,orgCode));
        }else{
            param.andParam(new QueryParam("status", QueryOperator.EQ,"1"));
            QueryParam otherparam=new QueryParam();
            otherparam.andParam(new QueryParam("pubOrgId", QueryOperator.LIKE,orgCode));
            otherparam.andParam(new QueryParam("status", QueryOperator.EQ,"0"));
            param.andParam(otherparam);
        }

        if("1".equals(mobileOperType)){//下拉
//            log.debug("下拉："+DateUtil.dateToStr(entity.getMobileTimestamp(),"yyyy-MM-dd HH:mm:ss"));
            if (null!=entity.getMobileTimestamp()){
                param.andParam(new QueryParam("mobileTimestamp",QueryOperator.GT, entity.getMobileTimestamp()));
            }
        }else if("2".equals(mobileOperType)){//上拉
//            log.debug("上拉："+DateUtil.dateToStr(entity.getMobileTimestamp(),"yyyy-MM-dd HH:mm:ss"));
            if (null!=entity.getMobileTimestamp()){
                param.andParam(new QueryParam("mobileTimestamp",QueryOperator.LT, entity.getMobileTimestamp()));
            }
        }

        QueryCondition condition = new QueryCondition();
        if (param.getField() != null) {
            condition.setParam(param);
        }
        condition.setPaging(getPaging());
        condition.setOrderBy("pubTime", Direction.DESC);
        condition.setOrderBy("status", Direction.ASC);
        if (StringUtils.isNotEmpty(mobileOperType)){
            condition.setOrderBy("mobileTimestamp", Direction.DESC);
        }
        return condition;
    }

    @Override
    public void list() {
        QueryCondition var1 = this.getQueryCondition();
        QueryResult<PubInfo> queryResult = new QueryResult<PubInfo>();
        IOrg iOrg = null;
        if (request.getSession().getAttribute(UserProfile.J2EE_USER_NAME)!= null) {
            iOrg = ApportalUtil.getIOrgOfCurrentUser(request);
        }
        if(iOrg!=null){this.entity.setPubOrgId(iOrg.getOrgCode());}
        try {
            queryResult = var1!=null?this.getService().find(var1,this.entity):this.getService().findBySample(this.entity, this.getPaging(), this.getOrderBy(), this.getDirection());
        } catch (Exception var2) {
            this.log.error(var2.getMessage(), var2);
            queryResult = new QueryResult();
        }
        write(queryResult);
    }

    @Override
    public void save() {
        //获取删除的附件IDS

        String attachmentIdsRemoveId = request.getParameter("removeId");
        if (StringUtils.isNotBlank(attachmentIdsRemoveId)) {
            //删除附件
            attachmentService.removeByIds(attachmentIdsRemoveId.split(","));
        }
        super.save();
        pubInfoService.savePubInfoRelTable(entity);
        if (StringUtils.isNotBlank(entity.getAttachmentIds())) {
            attachmentService.updateBusinessId(entity.getId(), entity.getAttachmentIds().split(","));
        }
    }

    /**
     * 删除实体时删除关联的附件
     */
    @Override
    public void delete() {
        String deleteId = request.getParameter("deletedId");
        if (StringUtils.isNotBlank(deleteId)) {
            attachmentService.removeByBusinessIds(deleteId);
        }
        super.delete();
        pubInfoRelTableService.executeJPQL("delete from PubInfoRelTable where pubInfoId=?",deleteId);
    }

    //公告发布
    public void pubsave() {
        String id = request.getParameter("id");
        if (id != null && !"".equals(id)) {
            this.getService().updatePubInfo(id);
        }
        PubInfo pubInfo = pubInfoService.findById(id);
        String[] grade = pubInfo.getGrade().split(",");
        if(grade!=null){
        List<IPerson> authorizationIPerson = new ArrayList<>();
        List list = Arrays.asList(grade);
        for (int i = 0; i < list.size(); i++) {
            IOrg iOrgs = OrgServiceUtil.getOrgByOrgCode((String) list.get(i));
            List<IOrg> orgs =  OrgServiceUtil.getOrgsByParentOrgId(iOrgs.getOrgId());
            if (orgs.size() > 0) {
                for (IOrg iOrgChild : orgs) {
                    List<IPerson> persons = PersonServiceUtil.getPersonByOrgId(iOrgChild.getOrgId());
                    if(persons.size()>0){
                        for (IPerson iPerson:persons){
                            if(iPerson.getUserId().equals(pubInfo.getUserID())){
                                persons.remove(iPerson);
                            }
                        }
                    }
                    authorizationIPerson.addAll(persons);
                }
            } else {
                List<IPerson> persons = PersonServiceUtil.getPersonByOrgId(iOrgs.getOrgId());
                if(persons.size()>0){
                    for (IPerson iPerson:persons){
                        if(iPerson.getUserId().equals(pubInfo.getUserID())){
                            persons.remove(iPerson);
                        }
                    }
                }
                authorizationIPerson.addAll(persons);
            }
        }
        Map map=new HashMap();
        map.put("persons",authorizationIPerson);
        map.put("pubInfos",pubInfo);
        write(map);
    }
    }


//    private List<IOrg> getChilrenOrgByPerentId(String perentOrgId){
//        if(StringUtils.isNotBlank(perentOrgId)){
//            List<IOrg> chilrenOrgs = OrgServiceUtil.getOrgsByParentOrgId(perentOrgId);
//            return chilrenOrgs;
//        }else{
//            return null;
//        }
//    }

    /**
     * 企业查看信息公告
     */
    public void powerList() {
        List<PubInfo> pubInfoList = pubInfoService.companyByPower();
        write(pubInfoList);

    }


    public void findOrg() {
        List<IOrg> orgs = OrgServiceUtil.getOrgsByParentOrgId("root");
        if (orgs.size() > 0) {
            List<IOrg> authorizationOrgs = new ArrayList<>();
            for (IOrg iOrg : orgs) {
                List<IOrg> childParent = OrgServiceUtil.getOrgsByParentOrgId(iOrg.getOrgId());
                if(childParent.size()>0) {
                    for (IOrg childOrg:childParent) {
                        List childOrgs = OrgServiceUtil.getOrgsByParentOrgId(childOrg.getOrgId());
                        authorizationOrgs.addAll(childOrgs);
                    }
                }
            }
            IOrg company = new IOrg() {
                @Override
                public String getOrgId() {
                    return "company";
                }

                @Override
                public String getOrgVersion() {
                    return null;
                }

                @Override
                public String getOrgShortName() {
                    return null;
                }

                @Override
                public String getOrgName() {
                    return "企业";
                }

                @Override
                public String getOrgCode() {
                    return "company";
                }

                @Override
                public String getOrgType() {
                    return null;
                }

                @Override
                public String getContact() {
                    return null;
                }

                @Override
                public String getOrgGrade() {
                    return null;
                }

                @Override
                public Long getOrgLevel() {
                    return null;
                }

                @Override
                public Double getSerialIndex() {
                    return null;
                }

                @Override
                public String getOrgDesc() {
                    return null;
                }

                @Override
                public String getParentId() {
                    return null;
                }

                @Override
                public String getOrgStatus() {
                    return null;
                }

                @Override
                public String getOrgLevelCode() {
                    return null;
                }

                @Override
                public String getDeltag() {
                    return null;
                }

                @Override
                public String getOrgMail() {
                    return null;
                }

                @Override
                public Map getExtattrMap() {
                    return null;
                }

                @Override
                public String[] getExtattrArry() {
                    return new String[0];
                }
            };
            authorizationOrgs.add(company);
            write(authorizationOrgs);
        } else {
            write(false);
        }
    }

}