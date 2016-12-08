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
}