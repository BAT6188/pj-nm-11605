package com.harmonywisdom.dshbcbp.composite.service.impl;

import com.harmonywisdom.dshbcbp.composite.bean.BlockFourth;
import com.harmonywisdom.dshbcbp.composite.dao.BlockFourthDAO;
import com.harmonywisdom.dshbcbp.composite.service.BlockFourthService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("blockFourthService")
public class BlockFourthServiceImpl extends BaseService<BlockFourth, String> implements BlockFourthService {
    @Autowired
    private BlockFourthDAO blockFourthDAO;

    @Override
    protected BaseDAO<BlockFourth, String> getDAO() {
        return blockFourthDAO;
    }
}