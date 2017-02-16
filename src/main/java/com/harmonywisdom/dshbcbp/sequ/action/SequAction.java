package com.harmonywisdom.dshbcbp.sequ.action;

import com.harmonywisdom.dshbcbp.sequ.bean.Sequ;
import com.harmonywisdom.dshbcbp.sequ.service.SequService;
import com.harmonywisdom.dshbcbp.videodevice.bean.VideoDevice;
import com.harmonywisdom.dshbcbp.videodevice.service.VideoDeviceService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.service.annotation.AutoService;

import java.util.List;

public class SequAction extends BaseAction<Sequ, SequService> {
    @AutoService
    private SequService sequService;
    @AutoService
    private VideoDeviceService videoDeviceService;

    @Override
    protected SequService getService() {
        return sequService;
    }

    public void test(){
        /*List<Sequ> all = sequService.findAll();
        System.out.println(all.size());
        for (Sequ sequ : all) {
            VideoDevice byId = videoDeviceService.findById(sequ.getVd_id());
            try {
                String lon = sequ.getLon();
                String du_lon = lon.substring(0, 3);
                String fen_lon = lon.substring(4, 6);
                Double fenD_lon=Double.valueOf(fen_lon)/60;
                String miao_lon = lon.substring(7);
                Double miaoD_lon=Double.valueOf(miao_lon)/3600;
                Double lonD=Double.valueOf(du_lon)+fenD_lon+miaoD_lon;

                String lat = sequ.getLat();
                String du = lat.substring(0, 2);
                String fen = lat.substring(3, 5);
                Double fenD=Double.valueOf(fen)/60;
                String miao = lat.substring(6);
                Double miaoD=Double.valueOf(miao)/3600;
                Double latD=Double.valueOf(du)+fenD+miaoD;

                byId.setLongitude(lonD);
                byId.setLatitude(latD);
                videoDeviceService.update(byId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("==========ok");*/
    }
}