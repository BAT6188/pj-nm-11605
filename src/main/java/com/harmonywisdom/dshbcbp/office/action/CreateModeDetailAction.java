package com.harmonywisdom.dshbcbp.office.action;

import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
import com.harmonywisdom.dshbcbp.office.bean.CreateModeDetail;
import com.harmonywisdom.dshbcbp.office.service.CreateModeDetailService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.Direction;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryOperator;
import com.harmonywisdom.framework.dao.QueryParam;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

public class CreateModeDetailAction extends BaseAction<CreateModeDetail, CreateModeDetailService> {
    @AutoService
    private CreateModeDetailService createModeDetailService;

    @AutoService
    private AttachmentService attachmentService;

    @Override
    protected CreateModeDetailService getService() {
        return createModeDetailService;
    }

    @Override
    protected QueryCondition getQueryCondition() {
        QueryParam params = new QueryParam();
//        if (StringUtils.isNotBlank(entity.getName())) {
//            params.andParam(new QueryParam("name", QueryOperator.LIKE,entity.getName()));
//        }
//
//        if (entity.getAge() != null) {
//            params.andParam(new QueryParam("age", QueryOperator.LIKE,entity.getAge()));
//        }

        QueryCondition condition = new QueryCondition();
        if (params.getField() != null) {
            condition.setParam(params);
        }
        condition.setPaging(getPaging());
        condition.setOrderBy("age", Direction.DESC);
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
        String[] attachmentIds = getParamValues("attachmentIds");
        for (String attachmentId : attachmentIds) {
            if (StringUtils.isNotBlank(attachmentId)){
                attachmentService.updateBusinessId(entity.getId(),attachmentId.split(","));
            }
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