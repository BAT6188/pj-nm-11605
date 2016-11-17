package com.harmonywisdom.dshbcbp.exelaw.action;

import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
import com.harmonywisdom.dshbcbp.common.dict.util.DateUtil;
import com.harmonywisdom.dshbcbp.exelaw.bean.LetterSue;
import com.harmonywisdom.dshbcbp.exelaw.service.LetterSueService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.Direction;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryOperator;
import com.harmonywisdom.framework.dao.QueryParam;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

public class LetterSueAction extends BaseAction<LetterSue, LetterSueService> {
    @AutoService
    private LetterSueService letterSueService;

    @AutoService
    private AttachmentService attachmentService;


    @Override
    protected LetterSueService getService() {
        return letterSueService;
    }

    @Override
    protected QueryCondition getQueryCondition() {
        String start_visitTime = request.getParameter("start_visitTime");
        String end_visitTime = request.getParameter("end_visitTime");
        QueryParam params = new QueryParam();
        if (StringUtils.isNotBlank(start_visitTime)) {
            params.andParam(new QueryParam("visitTime", QueryOperator.GE, DateUtil.strToDate(start_visitTime,"yyyy-MM-dd HH:mm")));
        }
        if (StringUtils.isNotBlank(end_visitTime)) {
            params.andParam(new QueryParam("visitTime", QueryOperator.LE,DateUtil.strToDate(end_visitTime,"yyyy-MM-dd HH:mm")));
        }
        if (StringUtils.isNotBlank(entity.getEnterpriseId())) {
            params.andParam(new QueryParam("enterpriseId", QueryOperator.EQ,entity.getEnterpriseId()));
        }


        QueryCondition condition = new QueryCondition();
        if (params.getField() != null) {
            condition.setParam(params);
        }
        condition.setPaging(getPaging());
        condition.setOrderBy("visitTime", Direction.DESC);
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