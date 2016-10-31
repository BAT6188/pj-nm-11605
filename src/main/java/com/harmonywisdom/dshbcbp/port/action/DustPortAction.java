package com.harmonywisdom.dshbcbp.port.action;

import com.harmonywisdom.dshbcbp.port.bean.DustPort;
import com.harmonywisdom.dshbcbp.port.service.DustPortService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

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

    /**
     * 根据ID获取排口信息
     */
    public void getEntityById(){
        write(dustPortService.findById(entity.getId()));
    }
}