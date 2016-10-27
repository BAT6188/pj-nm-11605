package com.harmonywisdom.dshbcbp.port.action;

import com.harmonywisdom.dshbcbp.attachment.service.AttachmentService;
import com.harmonywisdom.dshbcbp.port.bean.DustPort;
import com.harmonywisdom.dshbcbp.port.service.DustPortService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.Direction;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryOperator;
import com.harmonywisdom.framework.dao.QueryParam;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class DustPortAction extends BaseAction<DustPort, DustPortService> {
    @AutoService
    private DustPortService dustPortService;

    @Override
    protected DustPortService getService() {
        return dustPortService;
    }

    public void findByIds(){
        String[] ids = request.getParameterValues("ids");
        if (ids != null && ids.length > 0) {
            List<DustPort> list = getService().findByIds(ids);
            write(list);
        }
    }


}