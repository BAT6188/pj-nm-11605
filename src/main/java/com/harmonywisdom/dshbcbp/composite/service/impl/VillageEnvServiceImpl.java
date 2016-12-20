package com.harmonywisdom.dshbcbp.composite.service.impl;

import com.harmonywisdom.dshbcbp.composite.bean.Video;
import com.harmonywisdom.dshbcbp.composite.bean.VillageEnv;
import com.harmonywisdom.dshbcbp.composite.dao.VillageEnvDAO;
import com.harmonywisdom.dshbcbp.composite.service.VideoService;
import com.harmonywisdom.dshbcbp.composite.service.VillageEnvService;
import com.harmonywisdom.dshbcbp.utils.ZNodeDTO;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
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

    /**
     *一张图圈选农村生态环境
     * @param radius
     * @param longitude
     * @param latitude
     * @return
     */
//    @Override
//    public List<VillageEnv> circleQueryEnvironmens(String radius, String longitude, String latitude) {
//        if(StringUtils.isNotBlank(radius)) {
//            Double i = Double.parseDouble(radius);
//            Double x = Double.parseDouble(longitude);
//            Double y = Double.parseDouble(latitude);
//            Double x1 = x - i;
//            Double y1 = y - i;
//            Double x2 = x + i;
//            Double y2 = y + i;
//            double minLon = x1 < x2 ? x1 : x2;
//            double maxLon = x1 < x2 ? x2 : x1;
//            double minLat = y1 < y2 ? y1 : y2;
//            double maxLat = y1 < y2 ? y2 : y1;
//            List<VillageEnv> list = getDAO().queryJPQL("from VillageEnv t where t.longitude > ? and t.longitude < ? and t.latitude > ? and " +
//                    "t.latitude < ?", minLon, maxLon, minLat, maxLat);
//            if (list != null && list.size() > 0) {
//                return list;
//            }
//        }
//        return null;
//    }
}