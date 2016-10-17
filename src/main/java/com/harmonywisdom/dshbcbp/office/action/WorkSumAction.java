package com.harmonywisdom.dshbcbp.office.action;

import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
import com.harmonywisdom.dshbcbp.office.bean.WorkSum;
import com.harmonywisdom.dshbcbp.office.service.WorkSumService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryOperator;
import com.harmonywisdom.framework.dao.QueryParam;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

public class WorkSumAction extends BaseAction<WorkSum, WorkSumService> {
    @AutoService
    private WorkSumService workSumService;

    @AutoService
    private AttachmentService attachmentService;
    @Override
    protected WorkSumService getService() {
        return workSumService;
    }

    @Override
    protected QueryCondition getQueryCondition() {
        QueryParam param=new QueryParam();

        if (StringUtils.isNotBlank(entity.getTitle())) {
            param.andParam(new QueryParam("title", QueryOperator.LIKE,entity.getTitle()));
        }
        if (StringUtils.isNotBlank(entity.getType())) {
            param.andParam(new QueryParam("type", QueryOperator.LIKE,entity.getType()));
        }
        String pubTime = request.getParameter("pubTime");
        if (StringUtils.isNotBlank(pubTime)) {
            param.andParam(new QueryParam("pubTime", QueryOperator.EQ,entity.getPubTime()));
        }

        QueryCondition condition=new QueryCondition();
        if (param.getField()!=null) {
            condition.setParam(param);
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