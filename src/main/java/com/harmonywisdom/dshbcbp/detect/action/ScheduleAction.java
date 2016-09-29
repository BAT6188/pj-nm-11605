package com.harmonywisdom.dshbcbp.detect.action;

import com.harmonywisdom.dshbcbp.detect.bean.Schedule;
import com.harmonywisdom.dshbcbp.detect.service.ScheduleService;
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