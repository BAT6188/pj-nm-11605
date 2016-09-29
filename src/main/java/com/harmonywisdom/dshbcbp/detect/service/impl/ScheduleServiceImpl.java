package com.harmonywisdom.dshbcbp.detect.service.impl;

import com.harmonywisdom.dshbcbp.detect.bean.Schedule;
import com.harmonywisdom.dshbcbp.detect.dao.ScheduleDAO;
import com.harmonywisdom.dshbcbp.detect.service.ScheduleService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("scheduleService")
public class ScheduleServiceImpl extends BaseService<Schedule, String> implements ScheduleService {
    @Autowired
    private ScheduleDAO scheduleDAO;

    @Override
    protected BaseDAO<Schedule, String> getDAO() {
        return scheduleDAO;
    }
}