package com.harmonywisdom.dshbcbp.exelaw.action;

import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
import com.harmonywisdom.dshbcbp.common.dict.util.DateUtil;
import com.harmonywisdom.dshbcbp.enterprise.bean.Enterprise;
import com.harmonywisdom.dshbcbp.enterprise.service.EnterpriseService;
import com.harmonywisdom.dshbcbp.exelaw.bean.SelfCheckReport;
import com.harmonywisdom.dshbcbp.exelaw.service.SelfCheckReportService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.Direction;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryOperator;
import com.harmonywisdom.framework.dao.QueryParam;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

public class SelfCheckReportAction extends BaseAction<SelfCheckReport, SelfCheckReportService> {
    @AutoService
    private SelfCheckReportService selfCheckReportService;

    @AutoService
    private EnterpriseService enterpriseService;

    @AutoService
    private AttachmentService attachmentService;

    @Override
    protected SelfCheckReportService getService() {
        return selfCheckReportService;
    }

    public void saveFeedback(){
        String id = entity.getId();
        SelfCheckReport s = selfCheckReportService.findById(id);
        s.setStatus("1");
        s.setFeedbackTime(entity.getFeedbackTime());
        s.setFeedbackContent(entity.getFeedbackContent());

        selfCheckReportService.update(s);
        write("ok");
    }

    @Override
    protected QueryCondition getQueryCondition() {
        String start_findDate = request.getParameter("start_findDate");
        String end_findDate = request.getParameter("end_findDate");

        QueryParam params = new QueryParam();
        if (StringUtils.isNotBlank(entity.getEnterpriseId())) {
            params.andParam(new QueryParam("enterpriseId", QueryOperator.EQ,entity.getEnterpriseId()));
        }
        if (StringUtils.isNotBlank(entity.getEnterpriseName())) {
            params.andParam(new QueryParam("enterpriseName", QueryOperator.LIKE,"%"+entity.getEnterpriseName()+"%"));
        }
        if (StringUtils.isNotBlank(entity.getName())) {
            params.andParam(new QueryParam("name", QueryOperator.LIKE,"%"+entity.getName()+"%"));
        }
        if (StringUtils.isNotEmpty(start_findDate)){
            params.andParam(new QueryParam("findDate", QueryOperator.GE, DateUtil.strToDate(start_findDate,"yyyy-MM-dd HH:mm")));
        }
        if (StringUtils.isNotEmpty(end_findDate)){
            params.andParam(new QueryParam("findDate", QueryOperator.LE, DateUtil.strToDate(end_findDate,"yyyy-MM-dd HH:mm")));
        }

        QueryCondition condition = new QueryCondition();
        if (params.getField() != null) {
            condition.setParam(params);
        }
        condition.setPaging(getPaging());
        condition.setOrderBy("findDate", Direction.DESC);
        return condition;
    }

    @Override
    public void save() {
        String enterpriseId = entity.getEnterpriseId();
        if (StringUtils.isNotEmpty(enterpriseId)){
            Enterprise enterprise = enterpriseService.findById(enterpriseId);
            entity.setEnterpriseName(enterprise.getName());
            entity.setBlockLevelId(enterprise.getBlockLevelId());
            entity.setBlockLevelName(enterprise.getBlockLevelName());
            entity.setBlockId(enterprise.getBlockId());
            entity.setBlockName(enterprise.getBlockName());
        }

        entity.setStatus("0");

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