package com.harmonywisdom.dshbcbp.composite.service.impl;

import com.harmonywisdom.dshbcbp.composite.bean.Block;
import com.harmonywisdom.dshbcbp.composite.bean.BlockLevel;
import com.harmonywisdom.dshbcbp.composite.dao.BlockDAO;
import com.harmonywisdom.dshbcbp.composite.service.BlockLevelService;
import com.harmonywisdom.dshbcbp.composite.service.BlockService;
import com.harmonywisdom.dshbcbp.utils.ZNodeDTO;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("blockService")
public class BlockServiceImpl extends BaseService<Block, String> implements BlockService {
    @Autowired
    private BlockDAO blockDAO;

    @Autowired
    private BlockLevelService blockLevelService;

    @Override
    protected BaseDAO<Block, String> getDAO() {
        return blockDAO;
    }

    @Override
    public List<ZNodeDTO> searchNode(String searchText) {
        List<Block> blocks = getDAO().find("orgName like ?1 or principal like ?2", searchText, searchText);

        if (blocks != null && blocks.size() > 0) {
            List<ZNodeDTO> nodes = new ArrayList<ZNodeDTO>();
            //查询网格级别
            List<BlockLevel> levels = blockLevelService.findAll();
            for (BlockLevel level: levels) {
                //级别node下添加网格node
                List<ZNodeDTO> blockNodes = new ArrayList<ZNodeDTO>();
                for (Block block : blocks) {
                    if (block.getBlockLevelId().equals(level.getId())) {
                        String nodeName = block.getOrgName();
                        if (level.getLevel() == 2) {//二级网格使用负责人做为node名称，因为局长都属于一个单位
                            nodeName = block.getPrincipal();
                        }
                        ZNodeDTO blockNode = new ZNodeDTO(block.getId(), nodeName, Block.class.getSimpleName());
                        blockNodes.add(blockNode);
                    }
                }
                if (blockNodes.size() > 0) {
                    //创建网格级别node
                    ZNodeDTO levelNode = new ZNodeDTO(level.getId(), level.getName(), Block.class.getSimpleName());
                    if(searchText != "%%" && !"%%".equals(searchText)){
                        levelNode.setOpen(true);
                    }
                    levelNode.setChildren(blockNodes);
                    nodes.add(levelNode);
                }
            }
            if (nodes.size() > 0) {
                return nodes;
            }
        }
        return null;
    }

    @Override
    public List<Block> findByLevelId(String id) {
        List<Block> list=getDAO().queryJPQL("from Block where blockLevelId=?",id);
        return list;
    }

    @Override
    public List<Block> findByIds(String ...ids) {
        List<Block> list = getDAO().find("id in ?1", Arrays.asList(ids));
        return list;
    }
}