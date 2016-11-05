package com.harmonywisdom.dshbcbp.composite.service;

import com.harmonywisdom.dshbcbp.composite.bean.Block;
import com.harmonywisdom.dshbcbp.utils.ZNodeDTO;
import com.harmonywisdom.framework.service.IBaseService;

import java.util.List;

public interface BlockService extends IBaseService<Block, String> {

    /**
     * 根据文本搜索网格树
     * @param searchText
     * @return
     */
    List<ZNodeDTO> searchNode(String searchText);

    /**
     *
     * @param id
     * @return
     */
    List<Block> findByLevelId(String id);

    /**
     *
     * @param ids
     * @return
     */
    List<Block> findByIds(String...ids);
}