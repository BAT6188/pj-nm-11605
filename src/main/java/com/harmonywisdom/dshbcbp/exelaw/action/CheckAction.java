package com.harmonywisdom.dshbcbp.exelaw.action;

import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
import com.harmonywisdom.dshbcbp.common.dict.util.DateUtil;
import com.harmonywisdom.dshbcbp.exelaw.bean.Check;
import com.harmonywisdom.dshbcbp.exelaw.service.CheckService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.Direction;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryOperator;
import com.harmonywisdom.framework.dao.QueryParam;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

public class CheckAction extends BaseAction<Check, CheckService> {
    @AutoService
    private CheckService checkService;

    @AutoService
    private AttachmentService attachmentService;


    @Override
    protected CheckService getService() {
        return checkService;
    }

    @Override
    protected QueryCondition getQueryCondition() {
        String start_time = request.getParameter("start_time");
        String end_time = request.getParameter("end_visitTime");
        String checker = request.getParameter("checker");

        QueryParam params = new QueryParam();
        if (StringUtils.isNotBlank(start_time)) {
            params.andParam(new QueryParam("time", QueryOperator.GE, DateUtil.strToDate(start_time,"yyyy-MM-dd HH:mm")));
        }
        if (StringUtils.isNotBlank(end_time)) {
            params.andParam(new QueryParam("time", QueryOperator.LE,DateUtil.strToDate(end_time,"yyyy-MM-dd HH:mm")));
        }
        if (StringUtils.isNotBlank(checker)) {
            params.andParam(new QueryParam("checker", QueryOperator.LIKE,"%"+checker+"%"));
        }
        if (StringUtils.isNotBlank(entity.getEnterpriseId())) {
            params.andParam(new QueryParam("enterpriseId", QueryOperator.EQ,entity.getEnterpriseId()));
        }

        QueryCondition condition = new QueryCondition();
        if (params.getField() != null) {
            condition.setParam(params);
        }
        condition.setPaging(getPaging());
        condition.setOrderBy("time", Direction.DESC);
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