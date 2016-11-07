package com.harmonywisdom.dshbcbp.exelaw.action;

import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
import com.harmonywisdom.dshbcbp.common.dict.util.DateUtil;
import com.harmonywisdom.dshbcbp.exelaw.bean.Punish;
import com.harmonywisdom.dshbcbp.exelaw.service.PunishService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.Direction;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryOperator;
import com.harmonywisdom.framework.dao.QueryParam;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

public class PunishAction extends BaseAction<Punish, PunishService> {
    @AutoService
    private PunishService punishService;

    @AutoService
    private AttachmentService attachmentService;

    @Override
    protected PunishService getService() {
        return punishService;
    }

    @Override
    protected QueryCondition getQueryCondition() {
        QueryParam params = new QueryParam();
        String start_filingDate = request.getParameter("start_filingDate");
        String end_filingDate = request.getParameter("end_filingDate");
        if (StringUtils.isNotBlank(entity.getCaseName())) {
            params.andParam(new QueryParam("caseName", QueryOperator.LIKE, "%"+entity.getCaseName()+"%"));
        }
        if (StringUtils.isNotBlank(start_filingDate)) {
            params.andParam(new QueryParam("filingDate", QueryOperator.GE, DateUtil.strToDate(start_filingDate,"yyyy-MM-dd HH:mm")));
        }
        if (StringUtils.isNotBlank(end_filingDate)) {
            params.andParam(new QueryParam("filingDate", QueryOperator.LE, DateUtil.strToDate(end_filingDate,"yyyy-MM-dd HH:mm")));
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