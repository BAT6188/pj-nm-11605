package com.harmonywisdom.dshbcbp.office.action;

import com.harmonywisdom.dshbcbp.office.bean.MeetingNotice;
import com.harmonywisdom.dshbcbp.office.service.MeetingNoticeService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class MeetingNoticeAction extends BaseAction<MeetingNotice, MeetingNoticeService> {
    @AutoService
    private MeetingNoticeService meetingNoticeService;

    @Override
    protected MeetingNoticeService getService() {
        return meetingNoticeService;
    }
}