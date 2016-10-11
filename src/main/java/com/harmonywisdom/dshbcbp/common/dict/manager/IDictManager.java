package com.harmonywisdom.dshbcbp.common.dict.manager;

import com.harmonywisdom.dshbcbp.common.dict.bean.DictBean;

import java.util.List;

/**
 * 字典管理接口
 * <p>
 * 使用{@link DictUtil}调用这些方法
 * </p>
 * Created by hotleave on 15-3-9.
 */
public interface IDictManager {
    /**
     * 是否使用缓存
     * <p>
     * 设置为true时会将{@link IDictManager#getDictList}方法和{@link IDictManager#getDict}方法的数据进行缓存, 设置为false时，需要自己来处理缓存逻辑
     * </p>
     *
     * @return true - 使用， false - 不使用
     */
    boolean isUseCache();

    /**
     * 是否需要排序
     * @return true - 需要， false - 不需要
     */
    boolean isNeedSort();

    /**
     * 根据字典代码查询字典中的所有字典项
     *
     * @param code 字典代码
     * @return 字典项列表
     */
    List<DictBean> getDictList(String code);

    /**
     * 根据字段代码和字典项的值获取字典项
     *
     * @param code  字典代码
     * @param value 字典项的值
     * @return 字典项
     */
    DictBean getDict(String code, String value);

    /**
     * 根据级别获取
     * @param code
     * @param parentCode
     * @return
     */
    List<DictBean> getDictList(String code,String parentCode);
}
