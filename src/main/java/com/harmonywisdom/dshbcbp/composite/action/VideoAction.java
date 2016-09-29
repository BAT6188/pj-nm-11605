package com.harmonywisdom.dshbcbp.composite.action;

import com.harmonywisdom.dshbcbp.composite.bean.Video;
import com.harmonywisdom.dshbcbp.composite.service.VideoService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

public class VideoAction extends BaseAction<Video, VideoService> {
    @AutoService
    private VideoService videoService;

    @Override
    protected VideoService getService() {
        return videoService;
    }
}