package com.harmonywisdom.dshbcbp.composite.action;

import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
import com.harmonywisdom.dshbcbp.common.dict.util.DateUtil;
import com.harmonywisdom.dshbcbp.composite.bean.ProjectAcceptance;
import com.harmonywisdom.dshbcbp.composite.service.ProjectAcceptanceService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

public class ProjectAcceptanceAction extends BaseAction<ProjectAcceptance, ProjectAcceptanceService> {
    @AutoService
    private ProjectAcceptanceService projectAcceptanceService;
    @AutoService
    private AttachmentService attachmentService;
    @Override
    protected ProjectAcceptanceService getService() {
        return projectAcceptanceService;
    }

    public void findByAcceptanceProjectId(){
        String buildProjectId = request.getParameter("buildProjectId");
        ProjectAcceptance acceptance = getService().findByBuildProjectId(buildProjectId);
        write(acceptance);
    }

    @Override
    public void save() {
        String replyAccTime=request.getParameter("replyAccTime");
        String acceptTimeStr = request.getParameter("acceptTime");
        String acceptOrg = request.getParameter("acceptOrg");
        String projectId=request.getParameter("projectId");
        if(replyAccTime!=null &&acceptTimeStr!=null&&acceptOrg!=null){
            StringUtils.isNotBlank(entity.getIsAccLicense());
            projectAcceptanceService.updateBuildProject(DateUtil.strToDate(acceptTimeStr,"yyyy-MM-dd"),acceptOrg,DateUtil.strToDate(replyAccTime,"yyyy-MM-dd"),DateUtil.strToDate(replyAccTime,"yyyy-MM-dd"),projectId);
        }

        //获取删除的附件IDS
        String attachmentIdsRemoveId = request.getParameter("removeId");
        if(StringUtils.isNotBlank(attachmentIdsRemoveId)){
            //删除附件
            attachmentService.removeByIds(attachmentIdsRemoveId.split(","));
        }

        super.save();

        if(StringUtils.isNotBlank(entity.getAttachmentIds())){
            attachmentService.updateBusinessId(entity.getId(),entity.getAttachmentIds().split(","));
        }
        //write(true);
    }
}