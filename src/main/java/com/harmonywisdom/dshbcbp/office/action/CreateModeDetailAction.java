package com.harmonywisdom.dshbcbp.office.action;

import com.harmonywisdom.apportal.sdk.org.IOrg;
import com.harmonywisdom.apportal.sdk.org.OrgServiceUtil;
import com.harmonywisdom.apportal.sdk.org.domain.Org;
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

    public void getOrgList(){
        List<Org> allNotDelOrg = OrgServiceUtil.getAllNotDelOrg();
        write(allNotDelOrg);
    }

    @Override
    protected QueryCondition getQueryCondition() {
        QueryParam params = new QueryParam();
        if (StringUtils.isNotEmpty(entity.getCreateModeId())) {
            params.andParam(new QueryParam("createModeId", QueryOperator.EQ,entity.getCreateModeId()));
        }

        if (StringUtils.isNotBlank(entity.getResponsibleDepartmentId())) {
            params.andParam(new QueryParam("responsibleDepartmentId", QueryOperator.EQ,entity.getResponsibleDepartmentId()));
        }

        if (StringUtils.isNotEmpty(entity.getType())) {
            params.andParam(new QueryParam("type", QueryOperator.EQ,entity.getType()));
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