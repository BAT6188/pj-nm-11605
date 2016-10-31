package com.harmonywisdom.dshbcbp.composite.service.impl;

import com.harmonywisdom.dshbcbp.composite.bean.Block;
import com.harmonywisdom.dshbcbp.composite.bean.BlockLevel;
import com.harmonywisdom.dshbcbp.composite.bean.VillageEnv;
import com.harmonywisdom.dshbcbp.composite.dao.BlockLevelDAO;
import com.harmonywisdom.dshbcbp.composite.service.BlockLevelService;
import com.harmonywisdom.dshbcbp.composite.service.BlockService;
import com.harmonywisdom.dshbcbp.composite.service.VillageEnvService;
import com.harmonywisdom.dshbcbp.enterprise.bean.Enterprise;
import com.harmonywisdom.dshbcbp.enterprise.service.EnterpriseService;
import com.harmonywisdom.dshbcbp.port.bean.DustPort;
import com.harmonywisdom.dshbcbp.port.bean.NoisePort;
import com.harmonywisdom.dshbcbp.port.service.DustPortService;
import com.harmonywisdom.dshbcbp.port.service.NoisePortService;
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

    @Autowired
    private NoisePortService noisePortService;

    @Autowired
    private DustPortService dustPortService;

    @Autowired
    private EnterpriseService enterpriseService;

    @Autowired
    private VillageEnvService villageEnvService;
    @Override
    protected BaseDAO<BlockLevel, String> getDAO() {
        return blockLevelDAO;
    }

    @Override
    public List<ZNodeDTO> getBlockTree() {
        return blockService.searchNode("");
    }

    @Override
    public List<Block> getBlock() {
        List<Block> nodes = new ArrayList<Block>();
        List<BlockLevel> levels = getDAO().findAll();

        for (BlockLevel level: levels) {
            //添加网格级别node
            Block levelNode = new Block(level.getId(),level.getName());
            //级别node下添加网格node
            List<Block> blocks = blockService.find("where blockLevelId=?", level.getId());
            if (blocks != null && blocks.size() > 0) {
                List<Block> blockNodes = new ArrayList<Block>();
                for (Block block : blocks) {
                    Block blockNode = new Block(block.getId(), block.getOrgName());
                    blockNodes.add(blockNode);
                }
                levelNode.setChildren(blockNodes);
            }
            nodes.add(levelNode);
        }
        return nodes;
    }

    @Override
    public List<ZNodeDTO> getOneImageTree(String searchText) {
        List<ZNodeDTO> nodes = new ArrayList<ZNodeDTO>();
        if (searchText == null){
            searchText = "";
        }
        searchText = "%"+searchText+"%";
        //查询网格
        List<ZNodeDTO> blockNodes = blockService.searchNode(searchText);
        if (blockNodes != null) {
            nodes.addAll(blockNodes);
        }
        //查询环保局排口信息，沙尘暴排口
        List<ZNodeDTO> dustPorts = dustPortService.searchNode(searchText);
        if (dustPorts != null) {
            ZNodeDTO dustMainNode = new ZNodeDTO(DustPort.class.getSimpleName(),"沙尘暴监测",DustPort.class.getSimpleName());
            dustMainNode.setChildren(dustPorts);
            nodes.add(dustMainNode);
        }
        //查询企业
        List<ZNodeDTO> enterprises = enterpriseService.searchNode(searchText);
        if (enterprises != null) {
            ZNodeDTO enterpriseMainNode = new ZNodeDTO(Enterprise.class.getSimpleName(),"企业",Enterprise.class.getSimpleName());
            enterpriseMainNode.setChildren(enterprises);
            nodes.add(enterpriseMainNode);
        }
        //查询农村生态环境
        List<ZNodeDTO> villages = villageEnvService.searchNode(searchText);
        if (villages != null) {
            ZNodeDTO villageMainNode = new ZNodeDTO(VillageEnv.class.getSimpleName(),"农村生态环境",VillageEnv.class.getSimpleName());
            villageMainNode.setChildren(villages);
            nodes.add(villageMainNode);
        }

        return nodes;
    }
}