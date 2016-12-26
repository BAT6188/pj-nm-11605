package com.harmonywisdom.dshbcbp.office.action;

import com.harmonywisdom.apportal.sdk.org.IOrg;
import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
import com.harmonywisdom.dshbcbp.office.bean.ShareMeans;
import com.harmonywisdom.dshbcbp.office.service.ShareMeansService;
import com.harmonywisdom.dshbcbp.utils.ApportalUtil;
import com.harmonywisdom.dshbcbp.utils.MyDateUtils;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.*;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

public class ShareMeansAction extends BaseAction<ShareMeans, ShareMeansService> {
    @AutoService
    private ShareMeansService shareMeansService;

    @AutoService
    private AttachmentService attachmentService;

    @Override
    protected ShareMeansService getService() {
        return shareMeansService;
    }

    @Override
    protected QueryCondition getQueryCondition() {
        QueryParam param = new QueryParam();

        if (StringUtils.isNotBlank(entity.getTitle())) {
            param.andParam(new QueryParam("title", QueryOperator.LIKE, entity.getTitle()));
        }
        if (StringUtils.isNotBlank(entity.getType())) {
            param.andParam(new QueryParam("type", QueryOperator.LIKE, entity.getType()));
        }
//        String orgCode=request.getParameter("orgCode");
       /* QueryParam statusParam=new QueryParam();
        if (orgCode != null) {
            statusParam.andParam(new QueryParam("pubOrgId", QueryOperator.LIKE, orgCode));
            statusParam.orParam(new QueryParam("status", QueryOperator.EQ, "1")); //已发布
        }else{
            statusParam.andParam(new QueryParam("status", QueryOperator.EQ, "1")); //已发布
        }
        param.andParam(statusParam);*/
/*        String pubTime = request.getParameter("pTime");
        if (StringUtils.isNotBlank(pubTime)) {
            param.andParam(new QueryParam("pubTime", QueryOperator.EQ, DateUtil.strToDate(pubTime, "yyyy-MM-dd")));
        }*/
        IOrg iOrg = ApportalUtil.getIOrgOfCurrentUser(request);
        if(iOrg!=null){this.entity.setPubOrgId(iOrg.getOrgId());}
        if (StringUtils.isNotBlank(entity.getStatus())) {
            param.andParam(new QueryParam("status", QueryOperator.EQ,entity.getStatus()));
            param.andParam(new QueryParam("pubOrgId", QueryOperator.EQ,entity.getPubOrgId()));
        }else{
            param.andParam(new QueryParam("status", QueryOperator.EQ,"1"));
            QueryParam otherparam=new QueryParam();
            otherparam.andParam(new QueryParam("pubOrgId", QueryOperator.EQ,entity.getPubOrgId()));
            otherparam.andParam(new QueryParam("status", QueryOperator.EQ,"0"));
            param.andParam(otherparam);
        }
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        if(StringUtils.isNotBlank(startTime)){
            param.andParam(new QueryParam("pubTime", QueryOperator.GE, MyDateUtils.getFullDate(startTime,true)));
        }
        if(StringUtils.isNotBlank(endTime)){
            param.andParam(new QueryParam("pubTime", QueryOperator.LE,MyDateUtils.getFullDate(endTime,false)));
        }

        QueryCondition condition = new QueryCondition();
        if (param.getField() != null) {
            condition.setParam(param);
        }
        condition.setPaging(getPaging());
        condition.setOrderBy("pubTime", Direction.DESC);
        return condition;
    }

    @Override
    public void list() {
        QueryCondition var1 = this.getQueryCondition();
        QueryResult<ShareMeans> queryResult = new QueryResult<ShareMeans>();
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
            if (StringUtils.isNotBlank(entity.getAttachmentIds())) {
                attachmentService.updateBusinessId(entity.getId(), entity.getAttachmentIds().split(","));
            }
            write(true);
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
    }

    //公告发布
    public void pubsave() {
        String id = request.getParameter("id");
        if (id != null && !"".equals(id)) {
            this.getService().updateShareMeans(id);
        }
        write(true);
    }
}