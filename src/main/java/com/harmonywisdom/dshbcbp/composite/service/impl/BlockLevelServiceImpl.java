package com.harmonywisdom.dshbcbp.composite.service.impl;

import com.harmonywisdom.dshbcbp.composite.bean.BlockLevel;
import com.harmonywisdom.dshbcbp.composite.dao.BlockLevelDAO;
import com.harmonywisdom.dshbcbp.composite.service.BlockLevelService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("blockLevelService")
public class BlockLevelServiceImpl extends BaseService<BlockLevel, String> implements BlockLevelService {
    @Autowired
    private BlockLevelDAO blockLevelDAO;

    @Override
    protected BaseDAO<BlockLevel, String> getDAO() {
        return blockLevelDAO;
    }
}