package com.harmonywisdom.dshbcbp.composite.service;

import com.harmonywisdom.dshbcbp.composite.bean.Block;
import com.harmonywisdom.dshbcbp.composite.bean.VillageEnv;
import com.harmonywisdom.dshbcbp.utils.ZNodeDTO;
import com.harmonywisdom.framework.service.IBaseService;

import java.util.List;

public interface VillageEnvService extends IBaseService<VillageEnv, String> {

    /**
     * 根据文本搜索农村生态环境树
     * @param searchText
     * @return
     */
    List<ZNodeDTO> searchNode(String searchText);

    /**
     *
     * @param ids
     * @return
     */
    List<VillageEnv> findByIds(String...ids);

    /**
     * 一张图圈选农村生态环境
     * @param radius
     * @param longitude
     * @param latitude
     * @return
     */
//    List<VillageEnv> circleQueryEnvironmens(String radius, String longitude, String latitude);
}