package com.harmonywisdom.dshbcbp.composite.service.impl;

import com.harmonywisdom.dshbcbp.composite.bean.Block;
import com.harmonywisdom.dshbcbp.composite.dao.BlockDAO;
import com.harmonywisdom.dshbcbp.composite.service.BlockService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("blockService")
public class BlockServiceImpl extends BaseService<Block, String> implements BlockService {
    @Autowired
    private BlockDAO blockDAO;

    @Override
    protected BaseDAO<Block, String> getDAO() {
        return blockDAO;
    }
}