package com.harmonywisdom.dshbcbp.demo.action;

import com.harmonywisdom.framework.test.BaseActionTestCase;
import org.junit.Test;

import static org.junit.Assert.*;

public class DemoActionTest extends BaseActionTestCase {

    @Test
    public void testList() throws Exception {
        request.addParameter("name", "foo");

        String result = executeActionURL("/demo/S_list.action");

        assertNotNull(result);

        System.out.println(result);
    }

}