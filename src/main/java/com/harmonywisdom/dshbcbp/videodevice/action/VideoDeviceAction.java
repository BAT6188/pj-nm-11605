package com.harmonywisdom.dshbcbp.videodevice.action;

import com.harmonywisdom.dshbcbp.videodevice.bean.VideoDevice;
import com.harmonywisdom.dshbcbp.videodevice.service.VideoDeviceService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

import java.util.List;

public class VideoDeviceAction extends BaseAction<VideoDevice, VideoDeviceService> {
    @AutoService
    private VideoDeviceService videoDeviceService;

    @Override
    protected VideoDeviceService getService() {
        return videoDeviceService;
    }

    public void findByIds(){
        String[] ids = request.getParameterValues("ids");
        if (ids != null && ids.length > 0) {
            List<VideoDevice> list = getService().findByIds(ids);
            write(list);
        }
    }
}