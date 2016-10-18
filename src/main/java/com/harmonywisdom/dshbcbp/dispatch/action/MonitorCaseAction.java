package com.harmonywisdom.dshbcbp.dispatch.action;

import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
import com.harmonywisdom.dshbcbp.common.dict.util.DateUtil;
import com.harmonywisdom.dshbcbp.dispatch.bean.MonitorCase;
import com.harmonywisdom.dshbcbp.dispatch.service.MonitorCaseService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.Direction;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryOperator;
import com.harmonywisdom.framework.dao.QueryParam;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang3.StringUtils;


public class MonitorCaseAction extends BaseAction<MonitorCase, MonitorCaseService> {
    @AutoService
    private MonitorCaseService monitorCaseService;

    @AutoService
    private AttachmentService attachmentService;

    @Override
    protected MonitorCaseService getService() {
        return monitorCaseService;
    }

    @Override
    protected QueryCondition getQueryCondition() {
        QueryParam params = new QueryParam();
        String enterpriseName = entity.getEnterpriseName();
        String source = entity.getSource();
        String reason = entity.getReason();
        String startConnTime = entity.getStartConnTime();
        String endConnTime = entity.getEndConnTime();

        String startSendTime = entity.getStartSendTime();
        String endSendTime = entity.getEndSendTime();

        if(null==source){
            source="1";
        }
        if(StringUtils.isNotEmpty(enterpriseName)){
            params.andParam(new QueryParam("enterpriseName",QueryOperator.LIKE,"%"+enterpriseName+"%"));
        }

        if (StringUtils.isNotEmpty(reason)){
            params.andParam(new QueryParam("reason",QueryOperator.EQ,reason));
        }

        if (StringUtils.isNotEmpty(startConnTime)){
            params.andParam(new QueryParam("connTime",QueryOperator.GE, DateUtil.strToDate(startConnTime,"yyyy-MM-dd HH:mm")));
        }

        if (StringUtils.isNotEmpty(endConnTime)){
            params.andParam(new QueryParam("connTime",QueryOperator.LE,DateUtil.strToDate(endConnTime,"yyyy-MM-dd HH:mm")));
        }

        if (StringUtils.isNotEmpty(startSendTime)){
            params.andParam(new QueryParam("sendTime",QueryOperator.GE, DateUtil.strToDate(startSendTime,"yyyy-MM-dd HH:mm")));
        }

        if (StringUtils.isNotEmpty(endSendTime)){
            params.andParam(new QueryParam("sendTime",QueryOperator.LE,DateUtil.strToDate(endSendTime,"yyyy-MM-dd HH:mm")));
        }



        params.andParam(new QueryParam("source",QueryOperator.EQ,source));

        QueryCondition condition = new QueryCondition();
        if (params.getField() != null) {
            condition.setParam(params);
        }

        condition.setPaging(getPaging());
        condition.setOrderBy("eventTime", Direction.DESC);
        return condition;
    }

    @Override
    public void save() {
        //获取删除的附件IDS

        String attachmentIdsRemoveId = request.getParameter("removeId");
        if(org.apache.commons.lang.StringUtils.isNotBlank(attachmentIdsRemoveId)){
            //删除附件
            attachmentService.removeByIds(attachmentIdsRemoveId.split(","));
        }

        super.save();

        if (org.apache.commons.lang.StringUtils.isNotBlank(entity.getAttachmentIds())){
            attachmentService.updateBusinessId(entity.getId(),entity.getAttachmentIds().split(","));
        }
    }

    /**
     * 删除实体时删除关联的附件
     */
    @Override
    public void delete() {
        String deleteId = request.getParameter("deletedId");
        if(org.apache.commons.lang.StringUtils.isNotBlank(deleteId)){
            attachmentService.removeByBusinessIds(deleteId);
        }
        super.delete();
    }

}