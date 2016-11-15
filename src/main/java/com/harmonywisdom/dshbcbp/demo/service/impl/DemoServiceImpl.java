package com.harmonywisdom.dshbcbp.demo.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.harmonywisdom.dshbcbp.demo.bean.Demo;
import com.harmonywisdom.dshbcbp.demo.dao.DemoDAO;
import com.harmonywisdom.dshbcbp.demo.service.DemoService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hotleave on 14-9-16.
 */
@Service("demoService")
public class DemoServiceImpl extends BaseService<Demo, String> implements DemoService {
    @Autowired
    private DemoDAO demoDAO;

    @Override
    protected BaseDAO<Demo, String> getDAO() {
        return demoDAO;
    }

    @Override
    public Demo findById(String s) {
        Demo demo = super.findById(s);
        List<Demo> demoList = getDAO().findAll();
        demo.setDemoList(JSONArray.toJSONString(demoList));
        return demo;
    }
}
