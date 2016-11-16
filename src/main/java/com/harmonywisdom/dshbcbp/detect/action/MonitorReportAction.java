package com.harmonywisdom.dshbcbp.detect.action;

import com.alibaba.fastjson.JSON;
import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
import com.harmonywisdom.dshbcbp.composite.bean.Block;
import com.harmonywisdom.dshbcbp.composite.bean.BlockLevel;
import com.harmonywisdom.dshbcbp.composite.service.BlockLevelService;
import com.harmonywisdom.dshbcbp.composite.service.BlockService;
import com.harmonywisdom.dshbcbp.detect.bean.MonitorReport;
import com.harmonywisdom.dshbcbp.detect.service.MonitorReportService;
import com.harmonywisdom.dshbcbp.dispatch.action.DispatchTaskAction;
import com.harmonywisdom.dshbcbp.exelaw.bean.TrustMonitor;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.Direction;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryOperator;
import com.harmonywisdom.framework.dao.QueryParam;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

public class MonitorReportAction extends BaseAction<MonitorReport, MonitorReportService> {
    @AutoService
    private MonitorReportService monitorReportService;

    @AutoService
    private AttachmentService attachmentService;

    @AutoService
    private BlockLevelService blockLevelService;

    @AutoService
    private BlockService blockService;

    @Override
    protected MonitorReportService getService() {
        return monitorReportService;
    }


    @Override
    protected QueryCondition getQueryCondition() {
        QueryParam params = new QueryParam();
        String start_monitorTime = request.getParameter("start_monitorTime");
        String end_monitorTime = request.getParameter("end_monitorTime");
        if (StringUtils.isNotBlank(entity.getMonitorName())) {
            params.andParam(new QueryParam("monitorName", QueryOperator.LIKE,"%"+entity.getMonitorName()+"%"));
        }
        if (StringUtils.isNotBlank(entity.getBlockLevelId())) {
            params.andParam(new QueryParam("blockLevelId", QueryOperator.EQ,entity.getBlockLevelId()));
        }
        if (StringUtils.isNotBlank(entity.getBlockId())) {
            params.andParam(new QueryParam("blockId", QueryOperator.EQ,entity.getBlockId()));
        }
        if (StringUtils.isNotBlank(entity.getType())) {
            params.andParam(new QueryParam("type", QueryOperator.EQ,entity.getType()));
        }
        if (StringUtils.isNotBlank(start_monitorTime)) {
            params.andParam(new QueryParam("monitorTime", QueryOperator.GE,start_monitorTime));
        }
        if (StringUtils.isNotBlank(end_monitorTime)) {
            params.andParam(new QueryParam("monitorTime", QueryOperator.LE,end_monitorTime));
        }

        QueryCondition condition = new QueryCondition();
        if (params.getField() != null) {
            condition.setParam(params);
        }
        condition.setPaging(getPaging());
//        condition.setOrderBy("age", Direction.DESC);
        return condition;
    }

    public void saveSendPerson(){
        String sourceId = request.getParameter("sourceId");
        MonitorReport mr = monitorReportService.findById(sourceId);

        String[] ids = getParamValues("ids");
        mr.setSendPersonId(JSON.toJSONString(ids));
        String[] names = getParamValues("names");
        mr.setSendPersonName(DispatchTaskAction.arrayToString(names));

        mr.setStatus("1");
        monitorReportService.update(mr);

        write(sourceId);
    }

    @Override
    public void save() {
        String blockLevelId = entity.getBlockLevelId();
        if (StringUtils.isNotEmpty(blockLevelId)){
            BlockLevel bl = blockLevelService.findById(blockLevelId);
            entity.setBlockLevelName(bl.getName());
        }

        String blockId = entity.getBlockId();
        if (StringUtils.isNotEmpty(blockId)){
            Block b = blockService.findById(blockId);
            entity.setBlockName(b.getOrgName());
        }

        //获取删除的附件IDS
        String attachmentIdsRemoveId = request.getParameter("removeId");
        if(StringUtils.isNotBlank(attachmentIdsRemoveId)){
            //删除附件
            attachmentService.removeByIds(attachmentIdsRemoveId.split(","));
        }
        entity.setStatus("0");
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