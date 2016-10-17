package com.harmonywisdom.dshbcbp.office.action;

import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
import com.harmonywisdom.dshbcbp.office.bean.Manual;
import com.harmonywisdom.dshbcbp.office.service.ManualService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.Direction;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryOperator;
import com.harmonywisdom.framework.dao.QueryParam;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

public class ManualAction extends BaseAction<Manual, ManualService> {
    @AutoService
    private ManualService manualService;
    @AutoService
    private AttachmentService attachmentService;
    @Override
    protected ManualService getService() {
        return manualService;
    }

    @Override
    protected QueryCondition getQueryCondition() {
        QueryParam params = new QueryParam();
        if (StringUtils.isNotBlank(entity.getFileName())) {
            params.andParam(new QueryParam("fileName", QueryOperator.LIKE,entity.getFileName()));
        }

        if (StringUtils.isNotBlank(entity.getType())) {
            params.andParam(new QueryParam("type", QueryOperator.LIKE,entity.getType()));
        }

        QueryCondition condition = new QueryCondition();
        if (params.getField() != null) {
            condition.setParam(params);
        }
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