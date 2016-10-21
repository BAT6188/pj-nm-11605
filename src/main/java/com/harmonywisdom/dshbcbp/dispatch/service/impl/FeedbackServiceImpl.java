package com.harmonywisdom.dshbcbp.dispatch.service.impl;

import com.harmonywisdom.dshbcbp.dispatch.bean.Feedback;
import com.harmonywisdom.dshbcbp.dispatch.dao.FeedbackDAO;
import com.harmonywisdom.dshbcbp.dispatch.service.FeedbackService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("feedbackService")
public class FeedbackServiceImpl extends BaseService<Feedback, String> implements FeedbackService {
    @Autowired
    private FeedbackDAO feedbackDAO;

    @Override
    protected BaseDAO<Feedback, String> getDAO() {
        return feedbackDAO;
    }
}