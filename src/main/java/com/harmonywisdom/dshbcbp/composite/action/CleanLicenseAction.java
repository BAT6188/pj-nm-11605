package com.harmonywisdom.dshbcbp.composite.action;

import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
import com.harmonywisdom.dshbcbp.common.dict.util.DateUtil;
import com.harmonywisdom.dshbcbp.composite.bean.CleanLicense;
import com.harmonywisdom.dshbcbp.composite.service.CleanLicenseService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.Direction;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryOperator;
import com.harmonywisdom.framework.dao.QueryParam;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

public class CleanLicenseAction extends BaseAction<CleanLicense, CleanLicenseService> {
    @AutoService
    private CleanLicenseService cleanLicenseService;
    @AutoService
    private AttachmentService attachmentService;

    @Override
    protected CleanLicenseService getService() {
        return cleanLicenseService;
    }

    @Override
    protected QueryCondition getQueryCondition() {
        QueryParam param=new QueryParam();
        if (StringUtils.isNotBlank(entity.getName())) {
            param.andParam(new QueryParam("name", QueryOperator.LIKE,entity.getName()));
        }
        String startDate = request.getParameter("startDate");
        if (StringUtils.isNotBlank(startDate)) {
            param.andParam(new QueryParam("startDate", QueryOperator.EQ, DateUtil.strToDate(startDate,"yyyy-MM-dd")));
        }
        String endDate = request.getParameter("endDate");
        if (StringUtils.isNotBlank(endDate)) {
            param.andParam(new QueryParam("endDate", QueryOperator.EQ, DateUtil.strToDate(endDate,"yyyy-MM-dd")));
        }
        QueryCondition condition=new QueryCondition();
        if (param.getField()!=null) {
            condition.setParam(param);
        }
        condition.setPaging(getPaging());
        condition.setOrderBy("pubDate", Direction.DESC);
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