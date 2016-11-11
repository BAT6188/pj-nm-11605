package com.harmonywisdom.dshbcbp.office.service.impl;

import com.harmonywisdom.dshbcbp.office.bean.ManualCatalog;
import com.harmonywisdom.dshbcbp.office.dao.ManualCatalogDAO;
import com.harmonywisdom.dshbcbp.office.service.ManualCatalogService;
import com.harmonywisdom.dshbcbp.office.service.ManualService;
import com.harmonywisdom.dshbcbp.utils.ZNodeDTO;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("manualCatalogService")
public class ManualCatalogServiceImpl extends BaseService<ManualCatalog, String> implements ManualCatalogService {
    @Autowired
    private ManualCatalogDAO manualCatalogDAO;
    @Autowired
    private ManualService manualService;
    @Override
    protected BaseDAO<ManualCatalog, String> getDAO() {
        return manualCatalogDAO;
    }

    @Override
    public List<ZNodeDTO> getManualCatalog(String id) {
        List<ZNodeDTO> nodes=new ArrayList<>();
        List<ManualCatalog> manualCatalogs=getDAO().findAll();
        if(id==null || "".equals(id)){
            ZNodeDTO rootNode = new ZNodeDTO("root", "环保手册",true);
            nodes.add(rootNode);
        }else{
            List<ManualCatalog> catalogs = manualCatalogDAO.find("where manualId=?", id);
//            List<ManualCatalog> catalogs=getDAO().findAll();
            if (catalogs != null && catalogs.size() > 0) {
                List<ZNodeDTO> catalogsNodes = new ArrayList<ZNodeDTO>();
                for (ManualCatalog catalog : catalogs) {
                    ZNodeDTO catalogNode = new ZNodeDTO(catalog.getId(), catalog.getName());
                    if ("root".equals(id)) {
                        catalogNode.setIsParent(true);
                    }
                    catalogsNodes.add(catalogNode);
                }
                return catalogsNodes;
            }
        }
        return nodes;
    }

    @Override
    public List<ManualCatalog> findSelectTreeId(String ztreeId) {
        List<ManualCatalog> list=getDAO().queryJPQL("from ManualCatalog entity where entity.id=? ",ztreeId);
        return list ;
    }
}