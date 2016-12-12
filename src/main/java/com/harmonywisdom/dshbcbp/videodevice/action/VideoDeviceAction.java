package com.harmonywisdom.dshbcbp.videodevice.action;

import com.harmonywisdom.dshbcbp.videodevice.bean.VideoDevice;
import com.harmonywisdom.dshbcbp.videodevice.service.VideoDeviceService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryOperator;
import com.harmonywisdom.framework.dao.QueryParam;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class VideoDeviceAction extends BaseAction<VideoDevice, VideoDeviceService> {
    @AutoService
    private VideoDeviceService videoDeviceService;

    @Override
    protected VideoDeviceService getService() {
        return videoDeviceService;
    }

    public void findByIds(){
        String[] ids = request.getParameterValues("ids");
        if (ids != null && ids.length > 0) {
            List<VideoDevice> list = getService().findByIds(ids);
            write(list);
        }
    }

    @Override
    protected QueryCondition getQueryCondition() {
        QueryParam params = new QueryParam();
        if (StringUtils.isNotBlank(entity.getAddr())) {
            params.andParam(new QueryParam("addr", QueryOperator.LIKE,entity.getAddr()));
        }
        if (StringUtils.isNotBlank(entity.getType())) {
            params.andParam(new QueryParam("type", QueryOperator.LIKE,entity.getType()));
        }

        QueryCondition condition = new QueryCondition();
        if (params.getField() != null) {
            condition.setParam(params);
        }
        condition.setPaging(getPaging());
        return condition;
    }

    /**
     * 查询企业周边视频
     */
    public void querySurroundingVideo(){
        String videoLength = request.getParameter("videoLength");
        String longitude = request.getParameter("longitude");
        String latitude = request.getParameter("latitude");

        List<VideoDevice> list = videoDeviceService.queryVideoAmount(videoLength,longitude,latitude);
        write(list);

    }

}