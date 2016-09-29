package com.harmonywisdom.dshbcbp.composite.service.impl;

import com.harmonywisdom.dshbcbp.composite.bean.Video;
import com.harmonywisdom.dshbcbp.composite.dao.VideoDAO;
import com.harmonywisdom.dshbcbp.composite.service.VideoService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("videoService")
public class VideoServiceImpl extends BaseService<Video, String> implements VideoService {
    @Autowired
    private VideoDAO videoDAO;

    @Override
    protected BaseDAO<Video, String> getDAO() {
        return videoDAO;
    }
}