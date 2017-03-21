package com.harmonywisdom.dshbcbp.office.action;

import com.harmonywisdom.dshbcbp.office.bean.Task;
import com.harmonywisdom.dshbcbp.office.service.TaskService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class TaskAction extends BaseAction<Task, TaskService> {
    @AutoService
    private TaskService taskService;

    @Override
    protected TaskService getService() {
        return taskService;
    }
}