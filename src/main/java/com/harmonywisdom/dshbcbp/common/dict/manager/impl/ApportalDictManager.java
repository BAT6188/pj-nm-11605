package com.harmonywisdom.dshbcbp.common.dict.manager.impl;

import com.harmonywisdom.apportal.sdk.dictdata.DictDataServiceUtil;
import com.harmonywisdom.apportal.sdk.dictdata.domain.DictData;
import com.harmonywisdom.dshbcbp.common.dict.bean.DictBean;
import com.harmonywisdom.dshbcbp.common.dict.manager.IDictManager;
import com.harmonywisdom.dshbcbp.common.dict.util.DictUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Apportal数据字典
 * <p/>
 * <p>
 * 请使用{@link DictUtil}操作数据字典
 * </p>
 * Created by hotleave on 15-3-9.
 */
public class ApportalDictManager implements IDictManager {
    @Override
    public boolean isUseCache() {
        return true;
    }

    @Override
    public boolean isNeedSort() {
        return false;
    }

    @Override
    public List<DictBean> getDictList(String code) {
        List<DictData> list = DictDataServiceUtil.getDictData(code);
        List<DictBean> result = new ArrayList<DictBean>(list.size());

        for (DictData dictData : list) {
            result.add(covert(dictData));
        }

        return result;
    }

    @Override
    public DictBean getDict(String code, String value) {
        DictData dictData = DictDataServiceUtil.getDictDataByCode(code, value);

        return covert(dictData);
    }

    @Override
    public List<DictBean> getDictList(String code, String parentCode) {
        List<DictData> list = DictDataServiceUtil.getDictData(code);
        List<DictBean> result = new ArrayList<DictBean>(list.size());

        if(StringUtils.isNotBlank(parentCode)){
            for (DictData dictData : list) {
                if(dictData.getDictdataText().equals(parentCode)){
                    result.add(covert(dictData));
                }
            }
        }else{
            for (DictData dictData : list) {
                result.add(covert2(dictData));
            }
        }

        return result;
    }

    private DictBean covert(DictData dictData) {
        DictBean bean = new DictBean();
        bean.setCode(dictData.getDictdataCode());
        bean.setName(dictData.getDictdataName());
        if (dictData.getSerialIndex() != null) {
            bean.setSerial(dictData.getSerialIndex());
        }
        return bean;
    }

    private DictBean covert2(DictData dictData) {
        DictBean bean = new DictBean();
        bean.setCode(dictData.getDictdataCode());
        bean.setName(dictData.getDictdataName());
        bean.setParentCode(dictData.getDictdataText());
        bean.setSerial(dictData.getSerialIndex());

        return bean;
    }
}
