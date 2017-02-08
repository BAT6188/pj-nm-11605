package com.harmonywisdom.dshbcbp.dispatch.action;

import com.harmonywisdom.dshbcbp.alert.bean.Message;
import com.harmonywisdom.dshbcbp.alert.service.MessageService;
import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
import com.harmonywisdom.dshbcbp.dispatch.bean.DispatchTask;
import com.harmonywisdom.dshbcbp.dispatch.bean.Feedback;
import com.harmonywisdom.dshbcbp.dispatch.bean.MonitorCase;
import com.harmonywisdom.dshbcbp.dispatch.service.DispatchTaskService;
import com.harmonywisdom.dshbcbp.dispatch.service.FeedbackService;
import com.harmonywisdom.dshbcbp.dispatch.service.MonitorCaseService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.Direction;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryOperator;
import com.harmonywisdom.framework.dao.QueryParam;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

public class FeedbackAction extends BaseAction<Feedback, FeedbackService> {
    @AutoService
    private FeedbackService feedbackService;

    @AutoService
    private MonitorCaseService monitorCaseService;

    @AutoService
    private DispatchTaskService dispatchTaskService;

    @AutoService
    private AttachmentService attachmentService;

    @AutoService
    private MessageService messageService;

    @Override
    protected FeedbackService getService() {
        return feedbackService;
    }

    @Override
    protected QueryCondition getQueryCondition(){
        QueryParam param=new QueryParam();
        String mobileOperType = request.getParameter("mobileOperType");

        String dispatchId = entity.getDispatchId();
        if (StringUtils.isNotEmpty(dispatchId)){
            param.andParam(new QueryParam("dispatchId", QueryOperator.EQ,entity.getDispatchId()));
        }

        if("1".equals(mobileOperType)){//下拉
//            log.debug("下拉："+DateUtil.dateToStr(entity.getMobileTimestamp(),"yyyy-MM-dd HH:mm:ss"));
            if (null!=entity.getMobileTimestamp()){
                param.andParam(new QueryParam("mobileTimestamp", QueryOperator.GT, entity.getMobileTimestamp()));
            }
        }else if("2".equals(mobileOperType)){//上拉
//            log.debug("上拉："+DateUtil.dateToStr(entity.getMobileTimestamp(),"yyyy-MM-dd HH:mm:ss"));
            if (null!=entity.getMobileTimestamp()){
                param.andParam(new QueryParam("mobileTimestamp",QueryOperator.LT, entity.getMobileTimestamp()));
            }
        }
        QueryCondition condition=new QueryCondition();
        if (param.getField()!=null) {
            condition.setParam(param);
        }
        condition.setPaging(getPaging());
        if (StringUtils.isNotEmpty(mobileOperType)){
            condition.setOrderBy("mobileTimestamp", Direction.DESC);
        }
        return condition;

    }



    @Override
    public void save() {
        String dispathId = entity.getDispatchId();
        DispatchTask dispatchTask = dispatchTaskService.findById(dispathId);
        dispatchTask.setCaseReason(request.getParameter("caseReason"));
        dispatchTask.setStatus(DispatchTask.status_3);//置为已反馈状态
        dispatchTaskService.update(dispatchTask);

        MonitorCase monitorCase = monitorCaseService.findById(dispatchTask.getMonitorCaseId());
        monitorCase.setStatus(MonitorCase.status_2);
        monitorCaseService.update(monitorCase);

        super.save();

        //发送系统消息
//        Message message=new Message();
//        message.setMsgType("13");//反馈给环保站人员
//        message.setTitle("反馈信息");
//        message.setContent(entity.getExeDesc());
//        message.setBusinessId(entity.getId());
//        message.setSenderId(request.getParameter("userId"));
//        message.setSenderName(entity.getLawerName());

        if (StringUtils.isNotBlank(entity.getAttachmentIds())){
            attachmentService.updateBusinessId(entity.getId(),entity.getAttachmentIds().split(","));
        }
    }

    @Override
    public void list() {
        super.list();
    }
}