package com.harmonywisdom.dshbcbp.composite.service.impl;

import com.harmonywisdom.dshbcbp.composite.bean.BlockThird;
import com.harmonywisdom.dshbcbp.composite.dao.BlockThirdDAO;
import com.harmonywisdom.dshbcbp.composite.service.BlockThirdService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("blockThirdService")
public class BlockThirdServiceImpl extends BaseService<BlockThird, String> implements BlockThirdService {
    @Autowired
    private BlockThirdDAO blockThirdDAO;

    @Override
    protected BaseDAO<BlockThird, String> getDAO() {
        return blockThirdDAO;
    }
}