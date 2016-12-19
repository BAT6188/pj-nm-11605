package com.harmonywisdom.dshbcbp.composite.service.impl;

import com.harmonywisdom.dshbcbp.common.dict.bean.ZtreeObj;
import com.harmonywisdom.dshbcbp.composite.bean.Block;
import com.harmonywisdom.dshbcbp.composite.bean.BlockLevel;
import com.harmonywisdom.dshbcbp.composite.bean.VillageEnv;
import com.harmonywisdom.dshbcbp.composite.dao.BlockLevelDAO;
import com.harmonywisdom.dshbcbp.composite.service.BlockLevelService;
import com.harmonywisdom.dshbcbp.composite.service.BlockService;
import com.harmonywisdom.dshbcbp.composite.service.VillageEnvService;
import com.harmonywisdom.dshbcbp.enterprise.bean.Enterprise;
import com.harmonywisdom.dshbcbp.enterprise.service.EnterpriseService;
import com.harmonywisdom.dshbcbp.port.bean.AirEquipment;
import com.harmonywisdom.dshbcbp.port.bean.DustPort;
import com.harmonywisdom.dshbcbp.port.bean.NoisePort;
import com.harmonywisdom.dshbcbp.port.service.AirEquipmentService;
import com.harmonywisdom.dshbcbp.port.service.DustPortService;
import com.harmonywisdom.dshbcbp.port.service.NoisePortService;
import com.harmonywisdom.dshbcbp.utils.ZNodeDTO;
import com.harmonywisdom.dshbcbp.videodevice.bean.VideoDevice;
import com.harmonywisdom.dshbcbp.videodevice.service.VideoDeviceService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.apache.commons.lang.StringUtils;
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

    @Autowired
    private VideoDeviceService videoDeviceService;

    @Autowired
    private AirEquipmentService airEquipmentService;

    @Override
    protected BaseDAO<BlockLevel, String> getDAO() {
        return blockLevelDAO;
    }

    @Override
    public List<ZNodeDTO> getBlockTree() {
        return blockService.searchNode("");
    }

    @Override
    public List<ZNodeDTO> getBlock(String id) {
        List<ZNodeDTO> nodes = new ArrayList<ZNodeDTO>();
        List<BlockLevel> levels = getDAO().findAll();
        if(id==null){
            //网格级别加载
            for (BlockLevel level: levels) {
                ZNodeDTO levelNode = new ZNodeDTO(level.getId(),level.getName(),true);
                nodes.add(levelNode);
                }
         } else {
            BlockLevel level = getDAO().findById(id);
            boolean is2Level = (level.getLevel() == 2);
             //网格加载
            List<Block> blocks = blockService.find("where blockLevelId=?", id);
                if (blocks != null && blocks.size() > 0) {
                    List<ZNodeDTO> blockNodes = new ArrayList<ZNodeDTO>();
                    for (Block block : blocks) {
                        String nodeName;
                        if (is2Level) {
                            nodeName = block.getPrincipal();
                        }else{
                            nodeName = block.getOrgName();
                        }
                        ZNodeDTO blockNode = new ZNodeDTO(block.getId(), nodeName);
                        blockNodes.add(blockNode);
                    }
                    return blockNodes;
                }
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
            ZNodeDTO blocks = new ZNodeDTO(Block.class.getSimpleName(),"网格分布",true,Block.class.getSimpleName());
            blocks.setOpen(true);
            blocks.setChildren(blockNodes);
//            nodes.addAll(blocks);
            nodes.add(blocks);
        }

        //查询企业
        List<ZNodeDTO> enterprises = enterpriseService.searchNode(searchText);
        if (enterprises != null) {
            ZNodeDTO enterpriseMainNode = new ZNodeDTO(Enterprise.class.getSimpleName(),"企业分布",true,Enterprise.class.getSimpleName());
            enterpriseMainNode.setOpen(true);
            enterpriseMainNode.setChildren(enterprises);
            nodes.add(enterpriseMainNode);
        }

        ZNodeDTO monitoring = new ZNodeDTO("monitoring","在线监控",true,"monitoring");
        List<ZNodeDTO> monitoringChildren = new ArrayList<>();
        monitoring.setChildren(monitoringChildren);
        monitoring.setOpen(true);
        //查询环保局排口信息，噪声和沙尘暴
        List<ZNodeDTO> noisePorts = noisePortService.searchNode(searchText);
        if (noisePorts != null) {
            ZNodeDTO noiseMainNode = new ZNodeDTO(NoisePort.class.getSimpleName(),"噪声监测",true,NoisePort.class.getSimpleName());
            monitoring.setChildren(noisePorts);
            noiseMainNode.setChildren(noisePorts);
            if(searchText != "%%" && !"%%".equals(searchText)){
                noiseMainNode.setOpen(true);
            }
            monitoringChildren.add(noiseMainNode);
            monitoring.setChildren(monitoringChildren);
        }
        List<ZNodeDTO> dustPorts = dustPortService.searchNode(searchText);
        if (dustPorts != null) {
            ZNodeDTO dustMainNode = new ZNodeDTO(DustPort.class.getSimpleName(),"沙尘暴监测",true,DustPort.class.getSimpleName());
//            dustMainNode.setChildren(dustPorts);
//            nodes.add(dustMainNode);
            monitoring.setChildren(dustPorts);
            dustMainNode.setChildren(dustPorts);
            if(searchText != "%%" && !"%%".equals(searchText)){
                dustMainNode.setOpen(true);
            }
            monitoringChildren.add(dustMainNode);
            monitoring.setChildren(monitoringChildren);
        }

        //空气质量在线监测
        List<ZNodeDTO> airEquipment = airEquipmentService.searchNode(searchText);
        if(airEquipment != null){
            ZNodeDTO airMainNode = new ZNodeDTO(AirEquipment.class.getSimpleName(),"空气质量在线加测",true,AirEquipment.class.getSimpleName());
            monitoring.setChildren(airEquipment);
            airMainNode.setChildren(airEquipment);
            if(searchText != "%%" && !"%%".equals(searchText)){
                airMainNode.setOpen(true);
            }
            monitoringChildren.add(airMainNode);
            monitoring.setChildren(monitoringChildren);

        }


        //查询公安视频
        List<ZNodeDTO> videoDevices = videoDeviceService.searchNode(searchText);
        if (videoDevices != null) {
            ZNodeDTO videoDeviceMainNode = new ZNodeDTO(VideoDevice.class.getSimpleName(),"公安视频",true,VideoDevice.class.getSimpleName());
//            videoDeviceMainNode.setChildren(videoDevices);
//            nodes.add(videoDeviceMainNode);
            monitoring.setChildren(videoDevices);
            videoDeviceMainNode.setChildren(videoDevices);
            if(searchText != "%%" && !"%%".equals(searchText)){
                videoDeviceMainNode.setOpen(true);
            }
            monitoringChildren.add(videoDeviceMainNode);
            monitoring.setChildren(monitoringChildren);
        }

        //查询农村生态环境
        List<ZNodeDTO> villages = villageEnvService.searchNode(searchText);
        if (villages != null) {
            ZNodeDTO villageMainNode = new ZNodeDTO(VillageEnv.class.getSimpleName(),"农村生态环境",true,VillageEnv.class.getSimpleName());
            villageMainNode.setChildren(villages);
            villageMainNode.setOpen(true);
            nodes.add(villageMainNode);
        }
        if(monitoringChildren !=null && monitoringChildren.size()>0){
            nodes.add(monitoring);
        }

        return nodes;
    }

    @Override
    public List<ZtreeObj> getAllBlockZtree() {
        List<ZtreeObj> ztreeObjs = new ArrayList<>();
        List<BlockLevel> blockLevels = blockLevelDAO.findAll();
        for (BlockLevel bl:blockLevels){
            ztreeObjs.add(new ZtreeObj(bl.getId(),bl.getName(),"-1"));
            List<Block> blocks = blockService.findByLevelId(bl.getId());
            if(blocks.size()>0){
                for(Block b:blocks){
                    String blockLeader = "无";
                    if(StringUtils.isNotBlank(b.getBlockLeader())){
                        blockLeader = b.getBlockLeader();
                    }
                    ztreeObjs.add(new ZtreeObj(b.getId(),b.getOrgName()+" ( "+blockLeader+" )",bl.getId()));
                }
            }
        }
        return ztreeObjs;
    }

    @Override
    public List<BlockLevel> getAllBlockLevelAndChild() {
        List<BlockLevel> blockLevels = blockLevelDAO.findAll();
        for(BlockLevel blockLevel:blockLevels){
            List<Block> child = blockService.findByLevelId(blockLevel.getId());
            blockLevel.setBlocks(child);
        }
        return blockLevels;
    }
}