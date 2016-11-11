package com.harmonywisdom.dshbcbp.office.service;

import com.harmonywisdom.dshbcbp.office.bean.Manual;
import com.harmonywisdom.framework.service.IBaseService;

import java.util.List;

public interface ManualService extends IBaseService<Manual, String> {

    void deleteCatalog(String id);

    /**
     * 根据选中节点查询是否存在数据
     */
    List<Manual> findByZtreeId(String ztreeId);
}