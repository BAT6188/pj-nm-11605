package com.harmonywisdom.dshbcbp.enterprise.action;

import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
import com.harmonywisdom.dshbcbp.enterprise.bean.SolidControlFacility;
import com.harmonywisdom.dshbcbp.enterprise.service.SolidControlFacilityService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.Direction;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

public class SolidControlFacilityAction extends BaseAction<SolidControlFacility, SolidControlFacilityService> {
    @AutoService
    private SolidControlFacilityService solidControlFacilityService;

    @AutoService
    private AttachmentService attachmentService;

    @Override
    protected SolidControlFacilityService getService() {
        return solidControlFacilityService;
    }

    @Override
    protected QueryCondition getQueryCondition() {
        QueryCondition condition = new QueryCondition();
        condition.setPaging(getPaging());
        condition.setOrderBy("createTime", Direction.DESC);
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

        if (StringUtils.isNotBlank(entity.getAttachmentIds())){
            attachmentService.updateBusinessId(entity.getId(),entity.getAttachmentIds().split(","));
        }

        super.save();
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