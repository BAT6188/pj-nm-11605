package com.harmonywisdom.dshbcbp.office.action;

import com.harmonywisdom.apportal.sdk.org.IOrg;
import com.harmonywisdom.apportal.sdk.org.OrgServiceUtil;
import com.harmonywisdom.apportal.sdk.org.domain.Org;
import com.harmonywisdom.apportal.sdk.person.PersonServiceUtil;
import com.harmonywisdom.apportal.sdk.person.domain.Person;
import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
import com.harmonywisdom.dshbcbp.office.bean.CreateMode;
import com.harmonywisdom.dshbcbp.office.bean.CreateModeDetail;
import com.harmonywisdom.dshbcbp.office.service.CreateModeDetailService;
import com.harmonywisdom.dshbcbp.office.service.CreateModeService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.Direction;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryOperator;
import com.harmonywisdom.framework.dao.QueryParam;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class CreateModeDetailAction extends BaseAction<CreateModeDetail, CreateModeDetailService> {
    @AutoService
    private CreateModeDetailService createModeDetailService;

    @AutoService
    private CreateModeService createModeService;

    @AutoService
    private AttachmentService attachmentService;

    @Override
    protected CreateModeDetailService getService() {
        return createModeDetailService;
    }

    /**
     * 保存上报，并计算父表 是否应该为上报完成状态（如果对应的子表的每条记录completeStatus都为完成状态，则置父表status为上报完成状态）
     */
    public void saveUpload(){
        String id = entity.getId();
        //获取删除的附件IDS
        String attachmentIdsRemoveId = request.getParameter("removeId");
        if(StringUtils.isNotBlank(attachmentIdsRemoveId)){
            //删除附件
            attachmentService.removeByIds(attachmentIdsRemoveId.split(","));
        }
        if (StringUtils.isNotBlank(entity.getAttachmentIds())){
            attachmentService.updateBusinessId(id,entity.getAttachmentIds().split(","));
        }

        CreateModeDetail cmd = createModeDetailService.findById(id);
        cmd.setCompleteStatus("1");
        createModeDetailService.update(cmd);

        //检测父表上报状态（status）是否应该设置为已完成
        String createModeId = cmd.getCreateModeId();
        List<CreateModeDetail> createModeDetails = createModeDetailService.find("createModeId=?", createModeId);
        boolean isComplete=true;
        for (CreateModeDetail createModeDetail : createModeDetails) {
            if (!"1".equals(createModeDetail.getCompleteStatus())){
                isComplete=false;
            }
        }
        if (isComplete){
            CreateMode createMode = createModeService.findById(createModeId);
            createMode.setStatus("1");
            createModeService.update(createMode);
            log.debug("已设置相应父表状态为上报完成状态");
        }
        write("ok");
    }
    public void getOrgList(){
        List<Org> allNotDelOrg = OrgServiceUtil.getAllNotDelOrg();
        write(allNotDelOrg);
    }

    public void getUserIdByOrgid(){
        String id = request.getParameter("orgId");
        List<Person> persons = PersonServiceUtil.getPersonByOrgId(id);
        write(persons);
    }

    @Override
    protected QueryCondition getQueryCondition() {
        QueryParam params = new QueryParam();
        if (StringUtils.isNotEmpty(entity.getCreateModeId())) {
            params.andParam(new QueryParam("createModeId", QueryOperator.EQ,entity.getCreateModeId()));
        }

        if (StringUtils.isNotEmpty(entity.getCreateModeName())) {
            params.andParam(new QueryParam("createModeName", QueryOperator.LIKE,"%"+entity.getCreateModeName()+"%"));
        }

        if (StringUtils.isNotBlank(entity.getResponsibleDepartmentId())) {
            params.andParam(new QueryParam("responsibleDepartmentId", QueryOperator.EQ,entity.getResponsibleDepartmentId()));
        }

        if (StringUtils.isNotEmpty(entity.getType())) {
            params.andParam(new QueryParam("type", QueryOperator.EQ,entity.getType()));
        }

        if (StringUtils.isNotEmpty(entity.getCompleteStatus())) {
            params.andParam(new QueryParam("completeStatus", QueryOperator.EQ,entity.getCompleteStatus()));
        }

        QueryCondition condition = new QueryCondition();
        if (params.getField() != null) {
            condition.setParam(params);
        }
        condition.setPaging(getPaging());
//        condition.setOrderBy("age", Direction.DESC);
        return condition;
    }

    @Override
    public void save() {
        entity.setCompleteStatus("0");
        String createModeId = entity.getCreateModeId();
        if (StringUtils.isNotEmpty(createModeId)){
            CreateMode cm = createModeService.findById(createModeId);
            entity.setPublishOrgId(cm.getPublishOrgId());
            entity.setPublishOrgName(cm.getPublishOrgName());
        }
        IOrg org = OrgServiceUtil.getOrgByOrgId(entity.getResponsibleDepartmentId());
        entity.setResponsibleDepartmentName(org.getOrgName());

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
}