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
import com.harmonywisdom.framework.utils.ReflectionUtils;
import org.apache.commons.lang.StringUtils;

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

        if (StringUtils.isNotBlank(entity.getType())) {
            param.andParam(new QueryParam("type", QueryOperator.NE,0));
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
        super.delete();
    }

    public ProjectEIA getProjectEIAModel() {
        ProjectEIA projectEIA = new ProjectEIA();
        try {
            Class var1 = ProjectEIA.class;
            if((var1 = (Class) ReflectionUtils.getGenericClass(this.getClass())).getDeclaredConstructor(new Class[0]) != null) {
                projectEIA = (ProjectEIA) var1.newInstance();
            }
        } catch (Exception var2) {
            this.log.error("没有无参数的构造方法, 无法创建Entity实例", var2);
        }

        return projectEIA;
    }
}