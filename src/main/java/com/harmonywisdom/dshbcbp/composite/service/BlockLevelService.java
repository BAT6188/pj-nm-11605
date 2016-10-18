package com.harmonywisdom.dshbcbp.composite.service;

import com.harmonywisdom.dshbcbp.composite.bean.BlockLevel;
import com.harmonywisdom.framework.service.IBaseService;

import java.util.List;

public interface BlockLevelService extends IBaseService<BlockLevel, String> {

     List<BlockLevel> findByParentId(String id);
}