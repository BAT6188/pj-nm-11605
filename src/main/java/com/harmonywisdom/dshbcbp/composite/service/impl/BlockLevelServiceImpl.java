package com.harmonywisdom.dshbcbp.composite.service.impl;

import com.harmonywisdom.dshbcbp.composite.bean.BlockLevel;
import com.harmonywisdom.dshbcbp.composite.dao.BlockLevelDAO;
import com.harmonywisdom.dshbcbp.composite.service.BlockLevelService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("blockLevelService")
public class BlockLevelServiceImpl extends BaseService<BlockLevel, String> implements BlockLevelService {
    @Autowired
    private BlockLevelDAO blockLevelDAO;

    @Override
    protected BaseDAO<BlockLevel, String> getDAO() {
        return blockLevelDAO;
    }

    @Override
    public List<BlockLevel> findByParentId(String id) {
        List<BlockLevel> list =new ArrayList<BlockLevel>();
        list=blockLevelDAO.find("entity.parentId='"+id+"'");
        return list;
    }
}