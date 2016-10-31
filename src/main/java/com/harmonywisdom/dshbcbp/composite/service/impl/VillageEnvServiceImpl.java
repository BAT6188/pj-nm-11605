package com.harmonywisdom.dshbcbp.composite.service.impl;

import com.harmonywisdom.dshbcbp.composite.bean.Video;
import com.harmonywisdom.dshbcbp.composite.bean.VillageEnv;
import com.harmonywisdom.dshbcbp.composite.dao.VillageEnvDAO;
import com.harmonywisdom.dshbcbp.composite.service.VideoService;
import com.harmonywisdom.dshbcbp.composite.service.VillageEnvService;
import com.harmonywisdom.dshbcbp.utils.ZNodeDTO;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import edu.emory.mathcs.backport.java.util.Arrays;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("villageEnvService")
public class VillageEnvServiceImpl extends BaseService<VillageEnv, String> implements VillageEnvService {
    @Autowired
    private VillageEnvDAO villageEnvDAO;

    @Autowired
    private VideoService videoService;

    @Override
    protected BaseDAO<VillageEnv, String> getDAO() {
        return villageEnvDAO;
    }

    @Override
    public List<ZNodeDTO> searchNode(String searchText) {
        List<VillageEnv> villages = getDAO().find("name like ?1 ", searchText);
        if (villages != null && villages.size() > 0) {
            List<ZNodeDTO> nodes = new ArrayList<>();
            for (VillageEnv village : villages) {
                ZNodeDTO node = new ZNodeDTO(village.getId(), village.getName(), VillageEnv.class.getSimpleName());
                nodes.add(node);
            }
            return nodes;
        }
        return null;
    }

    @Override
    public List<VillageEnv> findByIds(String ...ids) {
        List<VillageEnv> list = getDAO().find("id in ?1", Arrays.asList(ids));
        for (VillageEnv village: list) {
            List<Video> videos = videoService.find("unitId=?1", village.getId());
            village.setVideos(videos);
        }
        return list;
    }
}