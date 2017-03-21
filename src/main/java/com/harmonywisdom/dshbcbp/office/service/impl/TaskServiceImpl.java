package com.harmonywisdom.dshbcbp.office.service.impl;

import com.harmonywisdom.dshbcbp.office.bean.Task;
import com.harmonywisdom.dshbcbp.office.dao.TaskDAO;
import com.harmonywisdom.dshbcbp.office.service.TaskService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("taskService")
public class TaskServiceImpl extends BaseService<Task, String> implements TaskService {
    @Autowired
    private TaskDAO taskDAO;

    @Override
    protected BaseDAO<Task, String> getDAO() {
        return taskDAO;
    }
}