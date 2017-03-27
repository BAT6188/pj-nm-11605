package com.harmonywisdom.dshbcbp.office.service.impl;

import com.harmonywisdom.dshbcbp.office.bean.TaskFeedBack;
import com.harmonywisdom.dshbcbp.office.dao.TaskFeedBackDAO;
import com.harmonywisdom.dshbcbp.office.service.TaskFeedBackService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("taskFeedBackService")
public class TaskFeedBackServiceImpl extends BaseService<TaskFeedBack, String> implements TaskFeedBackService {
    @Autowired
    private TaskFeedBackDAO taskFeedBackDAO;

    @Override
    protected BaseDAO<TaskFeedBack, String> getDAO() {
        return taskFeedBackDAO;
    }
}