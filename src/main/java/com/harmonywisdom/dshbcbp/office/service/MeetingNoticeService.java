package com.harmonywisdom.dshbcbp.office.service;

import com.harmonywisdom.dshbcbp.office.bean.MeetingNotice;
import com.harmonywisdom.framework.service.IBaseService;

public interface MeetingNoticeService extends IBaseService<MeetingNotice, String> {

    void updateMeeting(String pesonIds,String pesonNames,String id);

    void updateMeetingIsSms(String id);
}