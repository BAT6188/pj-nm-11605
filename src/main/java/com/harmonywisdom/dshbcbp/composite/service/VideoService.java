package com.harmonywisdom.dshbcbp.composite.service;

import com.harmonywisdom.dshbcbp.composite.bean.Video;
import com.harmonywisdom.framework.service.IBaseService;

public interface VideoService extends IBaseService<Video, String> {

    void deleteVideo(String unitId);
}