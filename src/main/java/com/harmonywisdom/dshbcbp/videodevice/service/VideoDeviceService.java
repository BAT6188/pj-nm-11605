package com.harmonywisdom.dshbcbp.videodevice.service;

import com.harmonywisdom.dshbcbp.utils.ZNodeDTO;
import com.harmonywisdom.dshbcbp.videodevice.bean.VideoDevice;
import com.harmonywisdom.framework.service.IBaseService;

import java.util.List;

public interface VideoDeviceService extends IBaseService<VideoDevice, String> {

    /**
     * 根据文本搜索公安视频treeNode
     * @param searchText
     * @return
     */
    List<ZNodeDTO> searchNode(String searchText);

    List<VideoDevice> findByIds(String... ids);

    /**
     * 查询企业周边摄像头数据
     * @param videoLength
     * @param longitude
     * @param latitude
     * @return
     */
    List<VideoDevice> queryVideoAmount(String videoLength, String longitude, String latitude);

    /**
     * 地图圈选公安视频
     * @param radius
     * @param longitude
     * @param latitude
     * @return
     */
    List<VideoDevice> circleByVideo(String radius, String longitude, String latitude);
}