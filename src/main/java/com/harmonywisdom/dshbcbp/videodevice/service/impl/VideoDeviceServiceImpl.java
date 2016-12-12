package com.harmonywisdom.dshbcbp.videodevice.service.impl;

import com.harmonywisdom.dshbcbp.utils.ZNodeDTO;
import com.harmonywisdom.dshbcbp.videodevice.bean.VideoDevice;
import com.harmonywisdom.dshbcbp.videodevice.dao.VideoDeviceDAO;
import com.harmonywisdom.dshbcbp.videodevice.service.VideoDeviceService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("videoDeviceService")
public class VideoDeviceServiceImpl extends BaseService<VideoDevice, String> implements VideoDeviceService {
    @Autowired
    private VideoDeviceDAO videoDeviceDAO;

    @Override
    protected BaseDAO<VideoDevice, String> getDAO() {
        return videoDeviceDAO;
    }

    @Override
    public List<ZNodeDTO> searchNode(String searchText) {
        List<VideoDevice> villages = getDAO().find("unit like ?1 ", searchText);
        if (villages != null && villages.size() > 0) {
            List<ZNodeDTO> nodes = new ArrayList<>();
            for (VideoDevice videoDevice : villages) {
                ZNodeDTO node = new ZNodeDTO(videoDevice.getId(), videoDevice.getUnit()+videoDevice.getChannalId()+"通道",
                        VideoDevice.class.getSimpleName());
                nodes.add(node);
            }
            return nodes;
        }
        return null;
    }

    @Override
    public List<VideoDevice> findByIds(String ...ids) {
        List<VideoDevice> list = getDAO().find("id in ?1", Arrays.asList(ids));
        return list;
    }
}