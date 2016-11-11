package com.harmonywisdom.dshbcbp.office.service;

import com.harmonywisdom.dshbcbp.office.bean.ManualCatalog;
import com.harmonywisdom.dshbcbp.utils.ZNodeDTO;
import com.harmonywisdom.framework.service.IBaseService;

import java.util.List;

public interface ManualCatalogService extends IBaseService<ManualCatalog, String> {
    //查询树
    List<ZNodeDTO> getManualCatalog(String id);

    /**
     * 根据选中节点获取name
     * @param ztreeId
     * @return
     */
    List<ManualCatalog> findSelectTreeId(String ztreeId);
}