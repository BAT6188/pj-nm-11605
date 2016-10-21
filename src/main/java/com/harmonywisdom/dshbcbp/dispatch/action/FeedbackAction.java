package com.harmonywisdom.dshbcbp.dispatch.action;

import com.harmonywisdom.dshbcbp.dispatch.bean.DispathTask;
import com.harmonywisdom.dshbcbp.dispatch.bean.Feedback;
import com.harmonywisdom.dshbcbp.dispatch.service.DispathTaskService;
import com.harmonywisdom.dshbcbp.dispatch.service.FeedbackService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class FeedbackAction extends BaseAction<Feedback, FeedbackService> {
    @AutoService
    private FeedbackService feedbackService;

    @AutoService
    private DispathTaskService dispathTaskService;

    @Override
    protected FeedbackService getService() {
        return feedbackService;
    }

    @Override
    public void save() {
        String dispathId = entity.getDispathId();
        DispathTask dispathTask = dispathTaskService.findById(dispathId);
        dispathTask.setStatus("3");
        dispathTaskService.update(dispathTask);

        super.save();
    }
}