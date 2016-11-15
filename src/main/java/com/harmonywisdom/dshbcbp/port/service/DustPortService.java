package com.harmonywisdom.dshbcbp.port.service;

import com.harmonywisdom.dshbcbp.port.bean.DustPort;
import com.harmonywisdom.dshbcbp.utils.ZNodeDTO;
import com.harmonywisdom.framework.service.IBaseService;

import java.util.List;

public interface DustPortService extends IBaseService<DustPort, String> {

    /**
     * 根据文本搜索沙尘排口树
     * @param searchText
     * @return
     */
    List<ZNodeDTO> searchNode(String searchText);

    /**
     *
     * @param ids
     * @return
     */
    List<DustPort> findByIds(String...ids);

    void delete(String portId);
}