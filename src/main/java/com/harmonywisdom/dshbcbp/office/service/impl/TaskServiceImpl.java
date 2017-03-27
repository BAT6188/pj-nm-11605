package com.harmonywisdom.dshbcbp.office.service.impl;

import com.harmonywisdom.apportal.sdk.person.IPerson;
import com.harmonywisdom.apportal.sdk.person.PersonServiceUtil;
import com.harmonywisdom.dshbcbp.alert.bean.Message;
import com.harmonywisdom.dshbcbp.alert.bean.MessageTrace;
import com.harmonywisdom.dshbcbp.alert.service.MessageService;
import com.harmonywisdom.dshbcbp.office.bean.Task;
import com.harmonywisdom.dshbcbp.office.dao.TaskDAO;
import com.harmonywisdom.dshbcbp.office.dao.TaskFeedBackDAO;
import com.harmonywisdom.dshbcbp.office.service.TaskService;
import com.harmonywisdom.dshbcbp.utils.ApportalUtil;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
        List<Task> tasks = taskDAO.queryJPQL("from Task t where t.taskType=? and t.taskStatus in (?,?) and t.warnStatus=? and SYSDATE()>=t.warnTime ",Task.class,"2","1","2","1");
        if(tasks.size()>0){
            long currentTime = new Date().getTime();
            for(Task t:tasks){
                long deadlineTime = t.getTaskDeadline().getTime();
                if(deadlineTime>currentTime){
                    sendMessage(t,"任务提醒！");
                }else if(!t.getTaskStatus().equals("1")){
                    sendMessage(t,"任务逾期提醒！");
                }

            }
        }
    }

    @Override
    public void sendMessage(Task task,String title) {
        Message message = new Message();
        IPerson iPerson = PersonServiceUtil.getPerson(task.getTaskPubUserId());
        message.setBusinessId(task.getId());
        message.setContent(task.getTaskContent());
        message.setDetailsUrl("container/gov/office/task.jsp?role=feedbacker");
        message.setMsgType(Message.MSG_TYPE_TASK_FEEDBACK);
        message.setTitle(task.getTaskName()+ title);
        message.setSenderId(iPerson.getUserId());
        message.setSenderName(iPerson.getUserName());

        List<MessageTrace> receivers = new ArrayList<>();
        List<IPerson> iPersonList = ApportalUtil.getIPersonListByOrgCode(task.getDispatchDutyDepartmentCode());
        if(iPersonList.size()>0){
            for(IPerson p:iPersonList){
                MessageTrace mt = new MessageTrace();
                mt.setReceiverId(p.getUserId());
                mt.setReceiverName(p.getUserName());
                receivers.add(mt);
            }
        }
        messageService.sendMessage(message, receivers);
    }
}