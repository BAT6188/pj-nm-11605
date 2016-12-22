package com.harmonywisdom.dshbcbp.videodevice.service.impl;

import com.harmonywisdom.dshbcbp.utils.MapUtils;
import com.harmonywisdom.dshbcbp.utils.ZNodeDTO;
import com.harmonywisdom.dshbcbp.videodevice.bean.VideoDevice;
import com.harmonywisdom.dshbcbp.videodevice.dao.VideoDeviceDAO;
import com.harmonywisdom.dshbcbp.videodevice.service.VideoDeviceService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import com.harmonywisdom.framework.util.collection.MapUtil;
import org.apache.commons.lang.StringUtils;
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

    /**
     * 查询企业周边摄像头数量
     * @param videoLength
     * @param longitude
     * @param latitude
     * @return
     */
    @Override
    public List<VideoDevice> queryVideoAmount(String videoLength, String longitude, String latitude) {
        if(StringUtils.isNotBlank(videoLength)) {
            Double i = Double.parseDouble(videoLength)*1000;
            Double x = Double.parseDouble(longitude);
            Double y = Double.parseDouble(latitude);
            double degreeeDistance = MapUtils.meterDistanceToDegreeDistance(x, y, i);
            Double x1 = x - degreeeDistance;
            Double y1 = y - degreeeDistance;
            Double x2 = x + degreeeDistance;
            Double y2 = y + degreeeDistance;
            double minLon = x1 < x2 ? x1 : x2;
            double maxLon = x1 < x2 ? x2 : x1;
            double minLat = y1 < y2 ? y1 : y2;
            double maxLat = y1 < y2 ? y2 : y1;
            List<VideoDevice> list = getDAO().queryJPQL("from VideoDevice t where t.longitude > ? and t.longitude < ? and t.latitude > ? and " +
                    "t.latitude < ?", minLon, maxLon, minLat, maxLat);
            if (list != null && list.size() > 0) {
                return list;
            }
        }
        return null;
    }


    /**
     * 查询矩形范围
     */
    private List<VideoDevice> queryByRectangle(double x1,double y1,double x2, double y2) {
        double minLon = x1 < x2 ? x1 : x2;
        double maxLon = x1 < x2 ? x2 : x1;
        double minLat = y1 < y2 ? y1 : y2;
        double maxLat = y1 < y2 ? y2 : y1;
        //已知矩形对角两个坐标，小于最大x,y坐标，大于最小x,y坐标，为矩形内的数据
        return this.getDAO().queryJPQL("from VideoDevice t where t.longitude > ? and t.longitude < ? and t.latitude > ? and " +
                "t.latitude < ?",minLon, maxLon, minLat, maxLat);
    }

    /**
     * 地图圆形区域查询公安视频
     * @param radius
     * @param longitude
     * @param latitude
     * @return
     */
    @Override
    public List<VideoDevice> circleByVideo(String radius, String longitude, String latitude) {
        if(StringUtils.isNotBlank(radius)) {
            Double i = Double.parseDouble(radius);
            Double x = Double.parseDouble(longitude);
            Double y = Double.parseDouble(latitude);
            Double x1 = x - i;
            Double y1 = y - i;
            Double x2 = x + i;
            Double y2 = y + i;

            //2.查询矩形内的数据
            List<VideoDevice> rectInnerNoisePort = queryByRectangle(x1, y1, x2, y2);
            //3.查找小于半径内的数据 (已知两点坐标，求距离)
            if (rectInnerNoisePort != null && rectInnerNoisePort.size() > 0) {
                List<VideoDevice> result = new ArrayList<VideoDevice>();
                for (VideoDevice videoDevice : rectInnerNoisePort) {
                    if (videoDevice.getLongitude() == null || videoDevice.getLatitude() == null) {
                        continue;
                    }
                    double cLon = videoDevice.getLongitude();
                    double clat = videoDevice.getLatitude();
                    if ((cLon-x)*(cLon-x)+(clat-y)*(clat-y) <= i * i) {
                        result.add(videoDevice);
                    }
                }
                return result;
            }
        }

        return null;
    }
}