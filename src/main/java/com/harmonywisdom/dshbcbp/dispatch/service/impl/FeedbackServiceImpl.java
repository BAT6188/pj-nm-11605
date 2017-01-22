package com.harmonywisdom.dshbcbp.dispatch.service.impl;

import com.harmonywisdom.dshbcbp.dispatch.bean.DispatchTask;
import com.harmonywisdom.dshbcbp.dispatch.bean.Feedback;
import com.harmonywisdom.dshbcbp.dispatch.dao.DispatchTaskDAO;
import com.harmonywisdom.dshbcbp.dispatch.dao.FeedbackDAO;
import com.harmonywisdom.dshbcbp.dispatch.service.FeedbackService;
import com.harmonywisdom.framework.dao.*;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

@Service("feedbackService")
public class FeedbackServiceImpl extends BaseService<Feedback, String> implements FeedbackService {
    @Autowired
    private FeedbackDAO feedbackDAO;

    @Autowired
    private DispatchTaskDAO dispatchTaskDAO;


    @Override
    protected BaseDAO<Feedback, String> getDAO() {
        return feedbackDAO;
    }

    @Override
    public QueryResult<Feedback> find(QueryCondition queryCondition) {
        QueryResult<Feedback> feedbackQueryResult = super.find(queryCondition);
        feedbackQueryResult=convert(feedbackQueryResult);
        return feedbackQueryResult;
    }

    @Override
    public QueryResult<Feedback> findBySample(Feedback feedback, Paging paging, String s, Direction direction) {
        QueryResult<Feedback> feedbackQueryResult = super.findBySample(feedback, paging, s, direction);
        feedbackQueryResult=convert(feedbackQueryResult);
        return feedbackQueryResult;
    }

    private QueryResult<Feedback> convert(QueryResult<Feedback> feedbackQueryResult){
        List<Feedback> rows = feedbackQueryResult.getRows();
        if (rows.size()>0){
            DispatchTask dispatchTask = dispatchTaskDAO.findById(rows.get(0).getDispatchId());
            if (null!=dispatchTask){
                for (Feedback row : rows) {
                    row.setCaseReason(dispatchTask.getCaseReason());
                }
            }

        }

        return feedbackQueryResult;
    }
}