package com.harmonywisdom.dshbcbp.enterprise.action;

import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
import com.harmonywisdom.dshbcbp.enterprise.bean.GasControlFacility;
import com.harmonywisdom.dshbcbp.enterprise.service.GasControlFacilityService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.Direction;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryOperator;
import com.harmonywisdom.framework.dao.QueryParam;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

public class GasControlFacilityAction extends BaseAction<GasControlFacility, GasControlFacilityService> {
    @AutoService
    private GasControlFacilityService gasControlFacilityService;

    @AutoService
    private AttachmentService attachmentService;

    @Override
    protected GasControlFacilityService getService() {
        return gasControlFacilityService;
    }

    @Override
    protected QueryCondition getQueryCondition() {
        QueryParam params = new QueryParam();
        if (StringUtils.isNotBlank(entity.getName())) {
            params.andParam(new QueryParam("name", QueryOperator.LIKE,entity.getName()));
        }
        if(StringUtils.isNotBlank(entity.getCrafts())){
            params.andParam(new QueryParam("crafts",QueryOperator.LIKE,entity.getCrafts()));
        }
        if (entity.getStatus() != null) {
            params.andParam(new QueryParam("status", QueryOperator.LIKE,entity.getStatus()));
        }

        QueryCondition condition = new QueryCondition();
        if (params.getField() != null) {
            condition.setParam(params);
        }
        condition.setPaging(getPaging());
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
        if(StringUtils.isNotBlank(entity.getAttachmentId())){
            attachmentService.updateBusinessId(entity.getId(),entity.getAttachmentId().split(","));

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