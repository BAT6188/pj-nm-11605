package com.harmonywisdom.dshbcbp.enterprise.service;

import com.harmonywisdom.dshbcbp.common.dict.bean.DictBean;
import com.harmonywisdom.dshbcbp.enterprise.bean.Enterprise;
import com.harmonywisdom.dshbcbp.utils.ZNodeDTO;
import com.harmonywisdom.framework.service.IBaseService;

import java.util.List;
import java.util.Map;

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

    /**
     * 获取餐饮油烟企业排口树结构
     * @param id
     * @return
     */
    public List<DictBean> getFumesEnterprisePortZtree(String id);

    /**
     * 查询企业报警状态
     * @param ids
     * @return {enterpriseId,status}
     */
    List<Map<String,String>> queryEnterpriseAlertStatus(String... ids);

    String updateEnterprise(Enterprise enterprise);
}