package com.harmonywisdom.dshbcbp.common.dict.util;

import com.harmonywisdom.dshbcbp.common.dict.bean.DictBean;
import com.harmonywisdom.dshbcbp.common.dict.manager.IDictManager;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 数据字典工具
 * <p>
 * 需要在Spring配置文件中指定<code>dictManager</code> Bean, 并指向{@link IDictManager}的具体实现
 * </p>
 * Created by hotleave on 15-3-9.
 */
public class DictUtil implements ApplicationContextAware {
    private static final String DATA_CACHE_KEY_TEMPLATE = "%s_%s";

    private static Map<String, List<DictBean>> cache;
    private static Map<String, DictBean> dataCache;

    private static IDictManager manager;
    private static boolean useCache;
    private static boolean needSort;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        manager = (IDictManager) applicationContext.getBean("dictManager");
        useCache = manager.isUseCache();
        needSort = manager.isNeedSort();
        if (useCache) {
            cache = new ConcurrentHashMap<String, List<DictBean>>(30);
            dataCache = new ConcurrentHashMap<String, DictBean>(150);
        }
    }

    /**
     * 获取字典码对应的所有字典数据
     *
     * @param code 字典码
     * @return 指定字典码的所有字典项
     */
    public static List<DictBean> getDictList(String code) {
        assert code != null;

        if (!useCache) {
            return manager.getDictList(code);
        }

        if (!cache.containsKey(code)) {
            // 从数据库中加载
            List<DictBean> list = manager.getDictList(code);

            if (needSort) {
                Collections.sort(list);
            }

            cache.put(code, list);
        }

        return cache.get(code);
    }

    /**
     * 获取字典项
     *
     * @param code  字典码
     * @param value 字典项的值
     * @return 字典项的值
     */
    public static DictBean getDict(String code, String value) {
        if (!useCache) {
            return manager.getDict(code, value);
        }

        String key = String.format(DATA_CACHE_KEY_TEMPLATE, code, value);
        if (!dataCache.containsKey(key)) {
            DictBean dictBean = manager.getDict(code, value);
            dataCache.put(key, dictBean);
        }

        return dataCache.get(key);
    }

    /**
     * 获取字典项的名称
     *
     * @param code  字典代码
     * @param value 字典项的值
     * @return 字典项的名称
     */
    public static String getDictName(String code, String value) {
        DictBean data = getDict(code, value);
        if (data != null) {
            return data.getName();
        }
        return null;
    }
}
