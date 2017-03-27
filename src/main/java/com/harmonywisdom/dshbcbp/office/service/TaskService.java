package com.harmonywisdom.dshbcbp.office.service;

import com.harmonywisdom.dshbcbp.office.bean.Task;
import com.harmonywisdom.framework.service.IBaseService;

public interface TaskService extends IBaseService<Task, String> {

    void saveTaskWarnInfoOnTime();


}