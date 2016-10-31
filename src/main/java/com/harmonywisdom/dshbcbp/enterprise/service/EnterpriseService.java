package com.harmonywisdom.dshbcbp.enterprise.service;

import com.harmonywisdom.dshbcbp.composite.bean.Block;
import com.harmonywisdom.dshbcbp.common.dict.bean.DictBean;
import com.harmonywisdom.dshbcbp.enterprise.bean.Enterprise;
import com.harmonywisdom.dshbcbp.utils.ZNodeDTO;
import com.harmonywisdom.framework.service.IBaseService;

import java.util.List;

public interface EnterpriseService extends IBaseService<Enterprise, String> {

    /**
     * 根据文本搜索企业树
     * @param searchText
     * @return
     */
    List<ZNodeDTO> searchNode(String searchText);

    /**
     *
     * @param ids
     * @return
     */
    List<Enterprise> findByIds(String ...ids);

    /**
     * 获取企业排口树结构
     * @param id
     * @return
     */
    public List<DictBean> getEnterprisePortZtree(String id);
}