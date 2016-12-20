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

    /**
     * 查询企业所有已标绘排口
     * @param enterpriseId
     * @return{排口类型,对应排口list}
     */
    Map<String,List> findMarkedPortsByEnterpriseId(String enterpriseId);

    /**
     * 企业用户登录
     * @param userName 登录名称
     * @param password
     * @return
     * key->status
     *  -2用户名错误 -1：密码错误 1:登陆成功
     */
    Map<String,Object> doLogin(String userName, String password);

    Enterprise getByUserName(String userName);

    /**
     * 删除企业
     * @param enterpriseId
     * @return
     */
    String delete(String enterpriseId);

    /**
     * 一张图圈选企业
     * @param radius
     * @param longitude
     * @param latitude
     * @return
     */
    List<Enterprise> queryEnterprises(String radius, String longitude, String latitude);
}