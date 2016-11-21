package com.harmonywisdom.dshbcbp.office.action;

import com.harmonywisdom.apportal.sdk.org.IOrg;
import com.harmonywisdom.apportal.sdk.org.OrgServiceUtil;
import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
import com.harmonywisdom.dshbcbp.common.dict.util.DateUtil;
import com.harmonywisdom.dshbcbp.office.bean.PubInfo;
import com.harmonywisdom.dshbcbp.office.service.PubInfoService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.Direction;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryOperator;
import com.harmonywisdom.framework.dao.QueryParam;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class PubInfoAction extends BaseAction<PubInfo, PubInfoService> {
    @AutoService
    private PubInfoService pubInfoService;
    @AutoService
    private AttachmentService attachmentService;

    @Override
    protected PubInfoService getService() {
        return pubInfoService;
    }

    @Override
    protected QueryCondition getQueryCondition() {
        QueryParam param=new QueryParam();
        if (StringUtils.isNotBlank(entity.getTitle())) {
            param.andParam(new QueryParam("title", QueryOperator.LIKE,entity.getTitle()));
        }
        if (StringUtils.isNotBlank(entity.getType())) {
            param.andParam(new QueryParam("type", QueryOperator.LIKE,entity.getType()));
        }
//        String pubTime = request.getParameter("gTime");
        String pubStartTime = request.getParameter("pubStartTime");
        String pubEndTime = request.getParameter("pubEndTime");
        if (StringUtils.isNotBlank(pubStartTime)) {
            param.andParam(new QueryParam("pubTime", QueryOperator.GE, DateUtil.strToDate(pubStartTime, "yyyy-MM-dd")));
        }
        if (StringUtils.isNotBlank(pubEndTime)) {
            param.andParam(new QueryParam("pubTime", QueryOperator.LE, DateUtil.strToDate(pubEndTime, "yyyy-MM-dd")));
        }
        String orgCode = request.getParameter("orgCode");
        QueryParam statusParam=new QueryParam();
        if (orgCode != null) {
            statusParam.andParam(new QueryParam("pubOrgId", QueryOperator.LIKE, orgCode));
            statusParam.orParam(new QueryParam("status", QueryOperator.EQ, "1")); //已发布
        }else{
            statusParam.andParam(new QueryParam("status", QueryOperator.EQ, "1")); //已发布
        }
        param.andParam(statusParam);

        QueryCondition condition=new QueryCondition();
        if (param.getField()!=null) {
            condition.setParam(param);
        }
        condition.setPaging(getPaging());
        condition.setOrderBy("pubTime", Direction.DESC);
        return condition;
    }

    @Override
    public void save() {
        //获取删除的附件IDS

        String attachmentIdsRemoveId = request.getParameter("removeId");
        if(StringUtils.isNotBlank(attachmentIdsRemoveId)){
            //删除附件
            attachmentService.removeByIds(attachmentIdsRemoveId.split(","));
        }
        super.save();
        if (StringUtils.isNotBlank(entity.getAttachmentIds())){
            attachmentService.updateBusinessId(entity.getId(),entity.getAttachmentIds().split(","));
        }
    }

    /**
     * 删除实体时删除关联的附件
     */
    @Override
    public void delete() {
        String deleteId = request.getParameter("deletedId");
        if(StringUtils.isNotBlank(deleteId)){
            attachmentService.removeByBusinessIds(deleteId);
        }
        super.delete();
    }
    //公告发布
    public void pubsave(){
        String id = request.getParameter("id");
        if(id != null && !"".equals(id)){
            this.getService().updatePubInfo(id);
        }
        write(true);
    }

    /**
     * 企业查看信息公告
     */
    public void powerList(){
        List<PubInfo> pubInfoList = pubInfoService.companyByPower();
        write(pubInfoList);

    }

     public void findOrg() {
         List<IOrg> orgs = OrgServiceUtil.getOrgsByParentOrgId("root");
         if (orgs.size() > 0) {
             List<IOrg> authorizationOrgs = new ArrayList<>();
             for (IOrg iOrg : orgs) {
                 authorizationOrgs.add(iOrg);
                 List childOrgs = OrgServiceUtil.getOrgsByParentOrgId(iOrg.getOrgId());
                 authorizationOrgs.addAll(childOrgs);
             }
             write(authorizationOrgs);
         }else{
             write(false);
         }
     }

}