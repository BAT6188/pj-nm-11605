package com.harmonywisdom.dshbcbp.demo.dao;

import com.harmonywisdom.dshbcbp.demo.bean.Demo;
import com.harmonywisdom.framework.test.BaseDAOTestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class DemoDAOTest extends BaseDAOTestCase {
    @Autowired
    private DemoDAO testDAO;

    @Test
    public void testSave() throws Exception {
        Demo demo = new Demo();
        demo.setName("foo");
        demo.setAge(20);

        String id = testDAO.save(demo);
        assertNotNull(id);

        Demo result = testDAO.findById(id);
        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("foo", result.getName());
        assertEquals("Age should be 20", Integer.valueOf(20), result.getAge());
    }
}