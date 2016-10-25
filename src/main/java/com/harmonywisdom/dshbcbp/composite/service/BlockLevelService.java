package com.harmonywisdom.dshbcbp.composite.service;

import com.harmonywisdom.dshbcbp.composite.bean.Block;
import com.harmonywisdom.dshbcbp.composite.bean.BlockLevel;
import com.harmonywisdom.dshbcbp.utils.ZNodeDTO;
import com.harmonywisdom.framework.service.IBaseService;

import java.util.List;

public interface BlockLevelService extends IBaseService<BlockLevel, String> {

     /**
      * 获取网格树
      * @return
      */
     List<ZNodeDTO> getBlockTree();
     List<Block> getBlock();

}