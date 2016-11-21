package com.harmonywisdom.dshbcbp.composite.action;

import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
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
import com.harmonywisdom.framework.dao.QueryResult;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BuildProjectAction extends BaseAction<BuildProject, BuildProjectService> {
    @AutoService
    private BuildProjectService buildProjectService;

    private ProjectEIA projectEIA;
    private ProjectAcceptance projectAcceptance;


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
        if(StringUtils.isNotBlank(entity.getEnterpriseId())){
            param.andParam(new QueryParam("enterpriseId", QueryOperator.LIKE,entity.getEnterpriseId()));
        }
        if (StringUtils.isNotBlank(entity.getName())) {
            param.andParam(new QueryParam("name", QueryOperator.LIKE,entity.getName()));
        }

        String startTime = request.getParameter("startDate");
        String endTime = request.getParameter("endDate");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            if(StringUtils.isNotBlank(startTime)){
                Date starttime = sdf.parse(startTime+" 00:00:00");
                param.andParam(new QueryParam("replyTime", QueryOperator.GE,starttime));
            }
            if(StringUtils.isNotBlank(endTime)){
                Date endtime = sdf.parse(endTime+" 23:59:59");
                param.andParam(new QueryParam("replyTime", QueryOperator.LE,endtime));
            }
        } catch (ParseException e) {
            e.printStackTrace();
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

    @Override
    public void list(){
        QueryResult<BuildProject> qr = this.query();
        List<BuildProject> list = qr.getRows();
        for(BuildProject bp:list){
            bp.setProjectEIA(projectEIAService.findByBuildProjectId(bp.getId()));
            bp.setProjectAcceptance(projectAcceptanceService.findByBuildProjectId(bp.getId()));
        }
        qr.setRows(list);
        write(qr);
    }

    @Override
    public void save(){
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
        if(StringUtils.isNotBlank(deleteId)){
            projectAcceptanceService.deleteAcceptanceBuildProjectId(deleteId);
        }
        if(StringUtils.isNotBlank(deleteId)){
            projectEIAService.deleteProjectEIABuildProjectId(deleteId);
        }
        super.delete();
    }

    public  void findByName(){
        String name=request.getParameter("name");
        List<BuildProject> list=buildProjectService.findByName(name);
        write(list);
    }

    public void findByBuildId(){
        String projectId=request.getParameter("projectId");
        List<ProjectEIA> list=buildProjectService.findEIAById(projectId);
        write(list);
    }
    public void findAcceptanceBuildId(){
        String projectId=request.getParameter("projectId");
        List<ProjectAcceptance> list=buildProjectService.findAcceptanceById(projectId);
        write(list);
    }
}