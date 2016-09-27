package com.harmonywisdom.dshbcbp.demo.service.impl;

import com.harmonywisdom.dshbcbp.demo.bean.Demo;
import com.harmonywisdom.dshbcbp.demo.service.DemoService;
import com.harmonywisdom.framework.test.BaseServiceTestCase;
import com.harmonywisdom.framework.utils.UUIDGenerator;
import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class DemoServiceImplTest extends BaseServiceTestCase {
    @Autowired
    private DemoService demoService;

    private String id = UUIDGenerator.generateUUID();

    @After
    public void after() {
        demoService.remove(id);
    }

    @Test
    public void testSave() throws Exception {
        Demo demo = new Demo();
        demo.setId(id);
        demo.setName("foo");
        demo.setAge(0);

        String result = demoService.save(demo);
        assertEquals(id, result);

        Demo queryFromDB = demoService.findById(id);
        assertNotNull(queryFromDB);
        assertEquals(demo.getName(), queryFromDB.getName());
    }
}