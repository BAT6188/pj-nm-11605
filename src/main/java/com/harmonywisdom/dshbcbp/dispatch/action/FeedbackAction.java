package com.harmonywisdom.dshbcbp.dispatch.action;

import com.harmonywisdom.dshbcbp.dispatch.bean.DispatchTask;
import com.harmonywisdom.dshbcbp.dispatch.bean.Feedback;
import com.harmonywisdom.dshbcbp.dispatch.service.DispatchTaskService;
import com.harmonywisdom.dshbcbp.dispatch.service.FeedbackService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class FeedbackAction extends BaseAction<Feedback, FeedbackService> {
    @AutoService
    private FeedbackService feedbackService;

    @AutoService
    private DispatchTaskService dispatchTaskService;

    @Override
    protected FeedbackService getService() {
        return feedbackService;
    }

    @Override
    public void save() {
        String dispathId = entity.getDispatchId();
        DispatchTask dispatchTask = dispatchTaskService.findById(dispathId);
        dispatchTask.setStatus("3");
        dispatchTaskService.update(dispatchTask);

        super.save();
    }
}