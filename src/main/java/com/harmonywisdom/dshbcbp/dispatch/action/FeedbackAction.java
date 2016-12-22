package com.harmonywisdom.dshbcbp.dispatch.action;

import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
import com.harmonywisdom.dshbcbp.dispatch.bean.DispatchTask;
import com.harmonywisdom.dshbcbp.dispatch.bean.Feedback;
import com.harmonywisdom.dshbcbp.dispatch.bean.MonitorCase;
import com.harmonywisdom.dshbcbp.dispatch.service.DispatchTaskService;
import com.harmonywisdom.dshbcbp.dispatch.service.FeedbackService;
import com.harmonywisdom.dshbcbp.dispatch.service.MonitorCaseService;
import com.harmonywisdom.framework.action.BaseAction;
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

    @Override
    protected FeedbackService getService() {
        return feedbackService;
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
        if (StringUtils.isNotBlank(entity.getAttachmentIds())){
            attachmentService.updateBusinessId(entity.getId(),entity.getAttachmentIds().split(","));
        }
    }
}