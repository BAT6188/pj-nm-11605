package com.harmonywisdom.dshbcbp.composite.service;

import com.harmonywisdom.dshbcbp.common.dict.bean.ZtreeObj;
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
     List<ZNodeDTO> getBlock(String id);

     /**
      * 获取一张图页面左侧树
      * @return
      */
     List<ZNodeDTO> getOneImageTree(String searchText);

     List<ZtreeObj> getAllBlockZtree();
}