package com.harmonywisdom.dshbcbp.videodevice.action;

import com.harmonywisdom.dshbcbp.videodevice.bean.VideoDevice;
import com.harmonywisdom.dshbcbp.videodevice.service.VideoDeviceService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryOperator;
import com.harmonywisdom.framework.dao.QueryParam;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;
import java.util.UUID;

public class VideoDeviceAction extends BaseAction<VideoDevice, VideoDeviceService> {
    @AutoService
    private VideoDeviceService videoDeviceService;

    @Override
    protected VideoDeviceService getService() {
        return videoDeviceService;
    }

    public void loadData() throws Exception{
        /*int i=0;
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new File("C:\\zuzhishu2.xml"));
        Element root = document.getRootElement();//Organization

        List<Element> Department1=root.elements();
        for (Element element1 : Department1) {
//            System.out.println("element1: "+element1.getName());
            List<Element> Department2 = element1.elements();
            for (Element element2 : Department2) {
//                System.out.println("element2: "+element2.getName());
                String element2Name=element2.attributeValue("name");
                List<Element> Department3 = element2.elements();
                for (Element element3 : Department3) {
                    String element3Name=element3.attributeValue("name");
//                    System.out.println("element3: "+element3.getName()+", name: "+element3.attributeValue("name"));
                    List<Element> Channel = element3.elements();
                    for (Element c : Channel) {
//                        System.out.println("c: "+c.getName());
                        if (c.getName().equals("Channel")){
                            String channelId = c.attributeValue("id");

                            VideoDevice v=new VideoDevice();
                            String uuid = UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
                            v.setId(uuid);
                            v.setChannalId(channelId);
                            String channelName = c.attributeValue("name");
                            if (StringUtils.isNotEmpty(channelName)){
                                v.setAddr(channelName);
                            }else {
                                v.setAddr(element3Name);
                            }
                            v.setType("1");
                            v.setUnit(element2Name);
                            videoDeviceService.save(v);

                        }

                    }
                }
            }
        }
        System.out.println("=================ok");*/
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

    /**
     * 地图圈选公安视频
     */
    public void circleVideo(){
        String radius = request.getParameter("radius");
        String longitude = request.getParameter("longitude");
        String latitude = request.getParameter("latitude");

        List<VideoDevice> list = videoDeviceService.circleByVideo(radius,longitude,latitude);
        write(list);



    }


}