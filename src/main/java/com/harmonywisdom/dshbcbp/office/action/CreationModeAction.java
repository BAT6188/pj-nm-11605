package com.harmonywisdom.dshbcbp.office.action;

import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
import com.harmonywisdom.dshbcbp.common.dict.util.DateUtil;
import com.harmonywisdom.dshbcbp.office.bean.CreationMode;
import com.harmonywisdom.dshbcbp.office.service.CreationModeService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.Direction;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryOperator;
import com.harmonywisdom.framework.dao.QueryParam;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

public class CreationModeAction extends BaseAction<CreationMode, CreationModeService> {
    @AutoService
    private CreationModeService creationModeService;
    @AutoService
    private AttachmentService attachmentService;

    @Override
    protected CreationModeService getService() {
        return creationModeService;
    }
    @Override
    protected QueryCondition getQueryCondition() {
        QueryParam params = new QueryParam();
        if (StringUtils.isNotBlank(entity.getTitle())) {
            params.andParam(new QueryParam("title", QueryOperator.LIKE,entity.getTitle()));
        }
        String pubTime = request.getParameter("cTime");
        if (StringUtils.isNotBlank(pubTime)) {
            params.andParam(new QueryParam("pubTime", QueryOperator.EQ, DateUtil.strToDate(pubTime,"yyyy-MM-dd")));
        }
        QueryCondition condition = new QueryCondition();
        if (params.getField() != null) {
            condition.setParam(params);
        }
        condition.setPaging(getPaging());
        condition.setOrderBy("pubTime", Direction.DESC);
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