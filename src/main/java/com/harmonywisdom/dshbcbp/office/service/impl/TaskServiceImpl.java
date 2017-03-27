package com.harmonywisdom.dshbcbp.office.service.impl;

import com.harmonywisdom.dshbcbp.alert.service.MessageService;
import com.harmonywisdom.dshbcbp.office.bean.Task;
import com.harmonywisdom.dshbcbp.office.dao.TaskDAO;
import com.harmonywisdom.dshbcbp.office.dao.TaskFeedBackDAO;
import com.harmonywisdom.dshbcbp.office.service.TaskService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("taskService")
public class TaskServiceImpl extends BaseService<Task, String> implements TaskService {
    @Autowired
    private TaskDAO taskDAO;
    @Autowired
    private TaskFeedBackDAO taskFeedBackDAO;
    @AutoService
    private MessageService messageService;

    @Override
    protected BaseDAO<Task, String> getDAO() {
        return taskDAO;
    }

    @Override
    public void saveTaskWarnInfoOnTime() {
        List<Task> tasks = taskDAO.queryJPQL("from Task t where t.taskType=? and t.warnStatus=? and SYSDATE()>=t.warnTime ",Task.class,"2","1");
        if(tasks.size()>0){
            for(Task t:tasks){

            }
        }

        List<Task> overDeadlineTasks = taskDAO.queryJPQL("from Task t where t.taskType=? and SYSDATE()>t.taskDeadline and t.taskStatus in (?,?)",Task.class,"2","1","2");
        if(overDeadlineTasks.size()>0){
            for(Task t:overDeadlineTasks){

            }
        }

    }
}