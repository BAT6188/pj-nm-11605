package com.harmonywisdom.dshbcbp.enterprise.service.impl;

import com.harmonywisdom.dshbcbp.enterprise.bean.Enterprise;
import com.harmonywisdom.dshbcbp.enterprise.dao.EnterpriseDAO;
import com.harmonywisdom.dshbcbp.enterprise.service.EnterpriseService;
import com.harmonywisdom.dshbcbp.utils.ZNodeDTO;
import com.harmonywisdom.framework.dao.*;
import com.harmonywisdom.framework.service.BaseService;
import edu.emory.mathcs.backport.java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("enterpriseService")
public class EnterpriseServiceImpl extends BaseService<Enterprise, String> implements EnterpriseService {
    @Autowired
    private EnterpriseDAO enterpriseDAO;

    @Override
    protected BaseDAO<Enterprise, String> getDAO() {
        return enterpriseDAO;
    }

    @Override
    public List<ZNodeDTO> searchNode(String searchText) {
        List<Enterprise> enterprises = getDAO().find("name like ?1", searchText);
        if (enterprises != null && enterprises.size() > 0) {
            List<ZNodeDTO> nodes = new ArrayList<>();
            for (Enterprise enterprise : enterprises) {
                ZNodeDTO node = new ZNodeDTO(enterprise.getId(), enterprise.getName(), Enterprise.class.getSimpleName());
                nodes.add(node);
            }
            return nodes;
        }
        return null;
    }

    @Override
    public List<Enterprise> findByIds(String ...ids) {
        List<Enterprise> list = getDAO().find("id in ?1", Arrays.asList(ids));
        return list;
    }
}