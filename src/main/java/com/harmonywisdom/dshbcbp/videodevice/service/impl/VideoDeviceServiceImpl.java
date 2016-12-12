package com.harmonywisdom.dshbcbp.videodevice.service.impl;

import com.harmonywisdom.dshbcbp.composite.bean.Video;
import com.harmonywisdom.dshbcbp.composite.bean.VillageEnv;
import com.harmonywisdom.dshbcbp.utils.ZNodeDTO;
import com.harmonywisdom.dshbcbp.videodevice.bean.VideoDevice;
import com.harmonywisdom.dshbcbp.videodevice.dao.VideoDeviceDAO;
import com.harmonywisdom.dshbcbp.videodevice.service.VideoDeviceService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import edu.emory.mathcs.backport.java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    /**
     * 查询企业周边摄像头数量
     * @param videoLength
     * @param longitude
     * @param latitude
     * @return
     */
    @Override
    public List<VideoDevice> queryVideoAmount(String videoLength, String longitude, String latitude) {
        Double i = Double.parseDouble(videoLength);
        Double x = Double.parseDouble(longitude);
        Double y = Double.parseDouble(latitude);
        Double x1 = x-i;
        Double y1 = y-i;
        Double x2 = x+i;
        Double y2 = y+i;
        double minLon = x1 < x2 ? x1 : x2;
        double maxLon = x1 < x2 ? x2 : x1;
        double minLat = y1 < y2 ? y1 : y2;
        double maxLat = y1 < y2 ? y2 : y1;
        List<VideoDevice> list = getDAO().queryJPQL("from VideoDevice t where t.longitude > ? and t.longitude < ? and t.latitude > ? and " +
                "t.latitude < ?",minLon, maxLon, minLat, maxLat);
        if(list != null && list.size()>0){
            return list;
        }
        return null;
    }
}