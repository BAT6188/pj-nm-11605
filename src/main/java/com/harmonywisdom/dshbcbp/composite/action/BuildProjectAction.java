package com.harmonywisdom.dshbcbp.composite.action;

import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
import com.harmonywisdom.dshbcbp.common.dict.util.DateUtil;
import com.harmonywisdom.dshbcbp.composite.bean.BuildProject;
import com.harmonywisdom.dshbcbp.composite.bean.ProjectAcceptance;
import com.harmonywisdom.dshbcbp.composite.bean.ProjectEIA;
import com.harmonywisdom.dshbcbp.composite.service.BuildProjectService;
import com.harmonywisdom.dshbcbp.composite.service.ProjectAcceptanceService;
import com.harmonywisdom.dshbcbp.composite.service.ProjectEIAService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryOperator;
import com.harmonywisdom.framework.dao.QueryParam;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class BuildProjectAction extends BaseAction<BuildProject, BuildProjectService> {
    @AutoService
    private BuildProjectService buildProjectService;

    private ProjectEIA projectEIA;

    @AutoService
    private ProjectEIAService projectEIAService;
    @AutoService
    private ProjectAcceptanceService projectAcceptanceService;
    @AutoService
    private AttachmentService attachmentService;

    @Override
    protected BuildProjectService getService() {
        return buildProjectService;
    }

    @Override
    protected QueryCondition getQueryCondition() {
        QueryParam param=new QueryParam();
        if (StringUtils.isNotBlank(entity.getName())) {
            param.andParam(new QueryParam("name", QueryOperator.LIKE,entity.getName()));
        }
        if (StringUtils.isNotBlank(entity.getBuildNature())) {
            param.andParam(new QueryParam("buildNature", QueryOperator.EQ,entity.getBuildNature()));
        }
        if (StringUtils.isNotBlank(entity.getArea())) {
            param.andParam(new QueryParam("area", QueryOperator.EQ,entity.getArea()));
        }
        QueryCondition condition=new QueryCondition();
        if (param.getField()!=null) {
            condition.setParam(param);
        }
        condition.setPaging(getPaging());
        return condition;
    }

    public void listProject(){
        getQueryCondition();
        List<BuildProject> list=buildProjectService.getAll();
        write(true);
    }

    public void saveHp() {
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
        //环评信息保存
        String euName=request.getParameter("euName");
        String euTel=request.getParameter("euTel");
        String euAddress=request.getParameter("euAddress");
        String certificateCode=request.getParameter("certificateCode");
        String certificateMoney=request.getParameter("certificateMoney");
        String replyTime=request.getParameter("replyTime");
        String replyCode=request.getParameter("replyCode");
        String replyOrg=request.getParameter("replyOrg");
        String isLicense=request.getParameter("isLicense");
        String replyOpinion=request.getParameter("replyOpinion");
        ProjectEIA projectEIA=new ProjectEIA();
        projectEIA.setProjectId(entity.getId());
        projectEIA.setEuName(euName);
        projectEIA.setEuTel(euTel);
        projectEIA.setEuAddress(euAddress);
        projectEIA.setCertificateCode(certificateCode);
        projectEIA.setCertificateMoney(Double.valueOf(certificateMoney));
        projectEIA.setReplyCode(replyCode);
        projectEIA.setReplyTime(DateUtil.strToDate(replyTime,"yyyy-MM-dd"));
        projectEIA.setReplyOrg(replyOrg);
        projectEIA.setIsLicense(isLicense);
        projectEIA.setReplyOpinion(replyOpinion);
        projectEIAService.saveOrUpdate(projectEIA);


    }
    public void saveYs() {
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
        String replyTime=request.getParameter("replyTime");
        String replyCode=request.getParameter("replyCode");
        String replyOrg=request.getParameter("replyOrg");
        String isLicense=request.getParameter("isLicense");
        String replyOpinion=request.getParameter("replyOpinion");
        ProjectAcceptance acceptance=new ProjectAcceptance();
        acceptance.setProjectId(entity.getId());
        acceptance.setReplyTime(DateUtil.strToDate(replyTime,"yyyy-MM-dd"));
        acceptance.setReplyOrg(replyOrg);
        acceptance.setReplyCode(replyCode);
        acceptance.setIsLicense(isLicense);
        acceptance.setReplyOpinion(replyOpinion);
        projectAcceptanceService.saveOrUpdate(acceptance);
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
}