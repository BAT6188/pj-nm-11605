package com.harmonywisdom.dshbcbp.composite.service.impl;

import com.harmonywisdom.dshbcbp.composite.bean.BlockFirst;
import com.harmonywisdom.dshbcbp.composite.dao.BlockFirstDAO;
import com.harmonywisdom.dshbcbp.composite.service.BlockFirstService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("blockFirstService")
public class BlockFirstServiceImpl extends BaseService<BlockFirst, String> implements BlockFirstService {
    @Autowired
    private BlockFirstDAO blockFirstDAO;

    @Override
    protected BaseDAO<BlockFirst, String> getDAO() {
        return blockFirstDAO;
    }
}