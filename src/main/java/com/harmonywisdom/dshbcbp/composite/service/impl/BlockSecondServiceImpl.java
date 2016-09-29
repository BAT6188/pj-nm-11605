package com.harmonywisdom.dshbcbp.composite.service.impl;

import com.harmonywisdom.dshbcbp.composite.bean.BlockSecond;
import com.harmonywisdom.dshbcbp.composite.dao.BlockSecondDAO;
import com.harmonywisdom.dshbcbp.composite.service.BlockSecondService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("blockSecondService")
public class BlockSecondServiceImpl extends BaseService<BlockSecond, String> implements BlockSecondService {
    @Autowired
    private BlockSecondDAO blockSecondDAO;

    @Override
    protected BaseDAO<BlockSecond, String> getDAO() {
        return blockSecondDAO;
    }
}