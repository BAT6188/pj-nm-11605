package com.harmonywisdom.dshbcbp.schedule.action;

import com.harmonywisdom.dshbcbp.schedule.bean.Schedule;
import com.harmonywisdom.dshbcbp.schedule.service.ScheduleService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class ScheduleAction extends BaseAction<Schedule, ScheduleService> {
    @AutoService
    private ScheduleService scheduleService;

    @Override
    protected ScheduleService getService() {
        return scheduleService;
    }
}