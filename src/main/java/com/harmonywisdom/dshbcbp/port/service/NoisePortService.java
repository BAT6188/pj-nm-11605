package com.harmonywisdom.dshbcbp.port.service;

import com.harmonywisdom.dshbcbp.port.bean.NoisePort;
import com.harmonywisdom.dshbcbp.utils.ZNodeDTO;
import com.harmonywisdom.framework.service.IBaseService;

import java.util.List;

public interface NoisePortService extends IBaseService<NoisePort, String> {

    /**
     * 根据文本搜索【环保站】噪音排口树
     * @param searchText
     * @return
     */
    List<ZNodeDTO> searchNode(String searchText);

    /**
     *
     * @param ids
     * @return
     */
    List<NoisePort> findByIds(String...ids);

    void delete(String portId);

    /**
     * 一张图圈选噪声监测
     * @param radius
     * @param longitude
     * @param latitude
     * @return
     */
    List<NoisePort> circleQueryNoise(String radius, String longitude, String latitude);
}