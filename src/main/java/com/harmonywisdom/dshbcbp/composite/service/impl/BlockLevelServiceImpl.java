package com.harmonywisdom.dshbcbp.composite.service.impl;

import com.harmonywisdom.dshbcbp.composite.bean.Block;
import com.harmonywisdom.dshbcbp.composite.bean.BlockLevel;
import com.harmonywisdom.dshbcbp.composite.dao.BlockLevelDAO;
import com.harmonywisdom.dshbcbp.composite.service.BlockLevelService;
import com.harmonywisdom.dshbcbp.composite.service.BlockService;
import com.harmonywisdom.dshbcbp.utils.ZNodeDTO;
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

    @Autowired
    private BlockService blockService;

    @Override
    protected BaseDAO<BlockLevel, String> getDAO() {
        return blockLevelDAO;
    }

    @Override
    public List<ZNodeDTO> getBlockTree() {
        List<ZNodeDTO> nodes = new ArrayList<ZNodeDTO>();
        List<BlockLevel> levels = getDAO().findAll();

        for (BlockLevel level: levels) {
            //添加网格级别node
            ZNodeDTO levelNode = new ZNodeDTO(level.getId(), level.getName());
            //级别node下添加网格node
            List<Block> blocks = blockService.find("where blockLevelId=?", level.getId());
            if (blocks != null && blocks.size() > 0) {
                List<ZNodeDTO> blockNodes = new ArrayList<ZNodeDTO>();
                for (Block block : blocks) {
                    ZNodeDTO blockNode = new ZNodeDTO(block.getId(), block.getOrgName());
                    blockNodes.add(blockNode);
                }
                levelNode.setChildren(blockNodes);
            }
            nodes.add(levelNode);
        }
        return nodes;
    }
}