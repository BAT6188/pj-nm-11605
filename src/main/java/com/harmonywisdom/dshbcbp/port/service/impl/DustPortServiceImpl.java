package com.harmonywisdom.dshbcbp.port.service.impl;

import com.harmonywisdom.dshbcbp.port.bean.DustPort;
import com.harmonywisdom.dshbcbp.port.bean.DustPortHistory;
import com.harmonywisdom.dshbcbp.port.dao.DustPortDAO;
import com.harmonywisdom.dshbcbp.port.dao.DustPortHistoryDAO;
import com.harmonywisdom.dshbcbp.port.service.DustPortService;
import com.harmonywisdom.dshbcbp.utils.ZNodeDTO;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import edu.emory.mathcs.backport.java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("dustPortService")
public class DustPortServiceImpl extends BaseService<DustPort, String> implements DustPortService {
    @Autowired
    private DustPortDAO dustPortDAO;
    @Autowired
    private DustPortHistoryDAO dustPortHistoryDAO;

    @Override
    protected BaseDAO<DustPort, String> getDAO() {
        return dustPortDAO;
    }

    @Override
    public List<ZNodeDTO> searchNode(String searchText) {
        List<DustPort> ports = getDAO().find("name like ?1", searchText);;
        if (ports != null && ports.size() > 0) {
            List<ZNodeDTO> nodes = new ArrayList<>();
            for (DustPort port : ports) {
                ZNodeDTO node = new ZNodeDTO(port.getId(), port.getName(),DustPort.class.getSimpleName());
                nodes.add(node);
            }
            return nodes;
        }
        return null;
    }

    @Override
    public List<DustPort> findByIds(String ...ids) {
        List<DustPort> list = getDAO().find("id in ?1", Arrays.asList(ids));
        return list;
    }

    @Override
    public void delete(String portId) {
        DustPortHistory dustPortHistory = new DustPortHistory();
        dustPortHistory.setPortId(portId);
        List<DustPortHistory> dustPortHistories = dustPortHistoryDAO.findBySample(dustPortHistory);
        if(dustPortHistories.size()>0){
            for(DustPortHistory dph:dustPortHistories){
                dustPortHistoryDAO.remove(dph);
            }
        }
        dustPortDAO.remove(portId);
    }
}