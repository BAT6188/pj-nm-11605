package com.harmonywisdom.dshbcbp.exelaw.action;

import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
import com.harmonywisdom.dshbcbp.composite.bean.Block;
import com.harmonywisdom.dshbcbp.composite.bean.BlockLevel;
import com.harmonywisdom.dshbcbp.composite.service.BlockLevelService;
import com.harmonywisdom.dshbcbp.composite.service.BlockService;
import com.harmonywisdom.dshbcbp.exelaw.bean.SiteMonitoring;
import com.harmonywisdom.dshbcbp.exelaw.service.SiteMonitoringService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryOperator;
import com.harmonywisdom.framework.dao.QueryParam;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

public class SiteMonitoringAction extends BaseAction<SiteMonitoring, SiteMonitoringService> {
    @AutoService
    private SiteMonitoringService siteMonitoringService;

    @AutoService
    private AttachmentService attachmentService;

    @AutoService
    private BlockLevelService blockLevelService;

    @AutoService
    private BlockService blockService;


    @Override
    protected SiteMonitoringService getService() {
        return siteMonitoringService;
    }

    @Override
    protected QueryCondition getQueryCondition() {
        QueryParam params = new QueryParam();
        if(StringUtils.isNotBlank(entity.getEnterpriseId())){
            params.andParam(new QueryParam("enterpriseId", QueryOperator.LIKE,entity.getEnterpriseId()));
        }
        if (StringUtils.isNotBlank(entity.getEnterpriseName())) {
            params.andParam(new QueryParam("enterpriseName", QueryOperator.LIKE,entity.getEnterpriseName()));
        }
        if(StringUtils.isNotBlank(entity.getCheckPeople())){
            params.andParam(new QueryParam("checkPeople",QueryOperator.LIKE,entity.getCheckPeople()));
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

        String blockLevelId = entity.getBlockLevelId();
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(blockLevelId)){
            BlockLevel bl = blockLevelService.findById(blockLevelId);
            entity.setBlockLevelName(bl.getName());
        }

        String blockId = entity.getBlockId();
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(blockId)){
            Block b = blockService.findById(blockId);
            entity.setBlockName(b.getOrgName());
        }

        super.save();
        if(StringUtils.isNotBlank(entity.getAttachmentIds())){
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