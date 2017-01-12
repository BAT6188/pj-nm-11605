package com.harmonywisdom.dshbcbp.exelaw.action;

import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
import com.harmonywisdom.dshbcbp.common.dict.util.DateUtil;
import com.harmonywisdom.dshbcbp.composite.bean.Block;
import com.harmonywisdom.dshbcbp.composite.bean.BlockLevel;
import com.harmonywisdom.dshbcbp.composite.service.BlockLevelService;
import com.harmonywisdom.dshbcbp.composite.service.BlockService;
import com.harmonywisdom.dshbcbp.dispatch.bean.DispatchTask;
import com.harmonywisdom.dshbcbp.dispatch.service.DispatchTaskService;
import com.harmonywisdom.dshbcbp.exelaw.bean.SiteMonitoring;
import com.harmonywisdom.dshbcbp.exelaw.service.SiteMonitoringService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.Direction;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryOperator;
import com.harmonywisdom.framework.dao.QueryParam;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

public class SiteMonitoringAction extends BaseAction<SiteMonitoring, SiteMonitoringService> {
    @AutoService
    private SiteMonitoringService siteMonitoringService;

    @AutoService
    private DispatchTaskService dispatchTaskService;


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
        String mobileOperType = request.getParameter("mobileOperType");
        QueryParam params = new QueryParam();
        if(StringUtils.isNotBlank(entity.getDispatchId())){
            params.andParam(new QueryParam("dispatchId", QueryOperator.EQ,entity.getDispatchId()));
        }
        if(StringUtils.isNotBlank(entity.getEnterpriseId())){
            params.andParam(new QueryParam("enterpriseId", QueryOperator.EQ,entity.getEnterpriseId()));
        }
        if (StringUtils.isNotBlank(entity.getEnterpriseName())) {
            params.andParam(new QueryParam("enterpriseName", QueryOperator.LIKE,entity.getEnterpriseName()));
        }
        if(StringUtils.isNotBlank(entity.getCheckPeople())){
            params.andParam(new QueryParam("checkPeople",QueryOperator.LIKE,entity.getCheckPeople()));
        }

        if("1".equals(mobileOperType)){//下拉
//            log.debug("下拉："+DateUtil.dateToStr(entity.getMobileTimestamp(),"yyyy-MM-dd HH:mm:ss"));
            if (null!=entity.getMobileTimestamp()){
                params.andParam(new QueryParam("mobileTimestamp",QueryOperator.GT, entity.getMobileTimestamp()));
            }
        }else if("2".equals(mobileOperType)){//上拉
//            log.debug("上拉："+DateUtil.dateToStr(entity.getMobileTimestamp(),"yyyy-MM-dd HH:mm:ss"));
            if (null!=entity.getMobileTimestamp()){
                params.andParam(new QueryParam("mobileTimestamp",QueryOperator.LT, entity.getMobileTimestamp()));
            }
        }


        QueryCondition condition = new QueryCondition();
        if (params.getField() != null) {
            condition.setParam(params);
        }
        condition.setPaging(getPaging());
        if (StringUtils.isNotEmpty(mobileOperType)){
            condition.setOrderBy("mobileTimestamp", Direction.DESC);
        }
        return condition;
    }



    @Override
    public void save() {
        String dispatchId = entity.getDispatchId();
        if (StringUtils.isNotEmpty(dispatchId)){
            DispatchTask dispatchTask = dispatchTaskService.findById(dispatchId);
            dispatchTask.setMonitorReportStatus("1");
            dispatchTaskService.update(dispatchTask);
        }

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