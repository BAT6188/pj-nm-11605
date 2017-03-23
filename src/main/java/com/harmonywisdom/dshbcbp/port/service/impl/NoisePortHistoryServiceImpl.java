package com.harmonywisdom.dshbcbp.port.service.impl;

import com.harmonywisdom.dshbcbp.common.dict.util.DateUtil;
import com.harmonywisdom.dshbcbp.port.bean.NoisePort;
import com.harmonywisdom.dshbcbp.port.bean.NoisePortHistory;
import com.harmonywisdom.dshbcbp.port.dao.NoisePortDAO;
import com.harmonywisdom.dshbcbp.port.dao.NoisePortHistoryDAO;
import com.harmonywisdom.dshbcbp.port.service.NoisePortHistoryService;
import com.harmonywisdom.dshbcbp.webservice.noisePortHistory.GetNoiseRealTimeResponseGetNoiseRealTimeResult;
import com.harmonywisdom.dshbcbp.webservice.noisePortHistory.WebServiceLocator;
import com.harmonywisdom.dshbcbp.webservice.noisePortHistory.WebServiceSoap_PortType;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import org.apache.axis.message.MessageElement;
import org.apache.axis.message.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("noisePortHistoryService")
public class NoisePortHistoryServiceImpl extends BaseService<NoisePortHistory, String> implements NoisePortHistoryService {
    @Autowired
    private NoisePortHistoryDAO noisePortHistoryDAO;

    @Autowired
    private NoisePortDAO noisePortDAO;

    DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    Long dayStart;
    Long dayEnd;

    Map<String,Double> day=new HashMap<>();
    Map<String,Double> night=new HashMap<>();

    {
        try {
            //昼间是早晨6点到晚上22点
            //夜间是晚上22点到早晨6点
            dayStart=dateFormat.parse("06:00:00").getTime();
            dayEnd=dateFormat.parse("21:59:59").getTime();
        } catch (ParseException e) {
            log.error("异常",e);
        }
        day.put("1019",55D);//红星美凯龙
        day.put("1021",55D);//鄂尔多斯国宾馆
        day.put("1004",55D);//东胜区政府
        day.put("1005",55D);//康和丽舍小区
        day.put("1006",65D);//装备制造基地
        day.put("1020",75D);//创业大厦
        day.put("",75D);//现在鄂托克大街没有数据
        day.put("1003",75D);//王府井百货

        night.put("1019",45D);//红星美凯龙
        night.put("1021",45D);//鄂尔多斯国宾馆
        night.put("1004",45D);//东胜区政府
        night.put("1005",45D);//康和丽舍小区
        night.put("1006",55D);//装备制造基地
        night.put("1020",55D);//创业大厦
        night.put("",55D);//现在鄂托克大街没有数据
        night.put("1003",55D);//王府井百货
    }

    @Override
    protected BaseDAO<NoisePortHistory, String> getDAO() {
        return noisePortHistoryDAO;
    }

    /**
     * 定时读取、保存噪声实时数据
     */
    public void saveNoiseRealTimeByWebservce(){
        try {
            WebServiceLocator locator = new WebServiceLocator();
            WebServiceSoap_PortType webServiceSoap = locator.getWebServiceSoap();
            GetNoiseRealTimeResponseGetNoiseRealTimeResult noiseRealTime = webServiceSoap.getNoiseRealTime("jiekou", "12345");
            MessageElement[] any = noiseRealTime.get_any();
            MessageElement messageElement = any[1];
            List children = messageElement.getChildren();
            MessageElement messageElement1 = (MessageElement) children.get(0);
            List<MessageElement> children1 = messageElement1.getChildren();
            List<NoisePortHistory> list = new ArrayList<>();
            for (MessageElement rows : children1) {
                NoisePortHistory h = new NoisePortHistory();
                h.setType("1");
                List<MessageElement> children2 = rows.getChildren();
                for (MessageElement data : children2) {
                    List<Text> children3 = data.getChildren();
                    String text = children3.get(0).toString();

                    if ("ID".equals(data.getName())) {
                        h.setId(text);
                    } else if ("POINT_CODE".equals(data.getName())) {
                        h.setPortId(text);
                    } else if ("POINT_NAME".equals(data.getName())) {
                        h.setName(text);
                    } else if ("CHAR_RECTIME".equals(data.getName())) {
                        h.setMonitorTime(DateUtil.strToDate(text,"yyyy-MM-dd HH:mm:ss"));
                    } else if ("LEQ".equals(data.getName())) {
                        h.setLeqdb(Double.valueOf(text));
                    } else if ("SD".equals(data.getName())) {
                        h.setSd(Double.valueOf(text));
                    } else if ("LMAX".equals(data.getName())) {
                        h.setLmax(Double.valueOf(text));
                    } else if ("L5".equals(data.getName())) {
                        h.setLFive(Double.valueOf(text));
                    } else if ("L10".equals(data.getName())) {
                        h.setLTen(Double.valueOf(text));
                    } else if ("L50".equals(data.getName())) {
                        h.setLFifty(Double.valueOf(text));
                    } else if ("L90".equals(data.getName())) {
                        h.setLNinety(Double.valueOf(text));
                    } else if ("L95".equals(data.getName())) {
                        h.setLNinetyFive(Double.valueOf(text));
                    } else if ("LMIN".equals(data.getName())) {
                        h.setLmin(Double.valueOf(text));
                    } else if ("LE".equals(data.getName())) {
                        h.setLe(Double.valueOf(text));
                    } else if ("UPPER_LIMIT".equals(data.getName())) {
                        h.setDayMax(Double.valueOf(text));
                    } else if ("LOWER_LIMIT".equals(data.getName())) {
                        h.setNightMax(Double.valueOf(text));
                    }

                }
                list.add(h);
            }

            for (NoisePortHistory noisePortHistory : list) {
                NoisePort noisePort = noisePortDAO.findById(noisePortHistory.getPortId());
                if (noisePort==null){
                    noisePort=new NoisePort();
                }

                String format = dateFormat.format(noisePortHistory.getMonitorTime());
                long monitorTime = dateFormat.parse(format).getTime();
                if (monitorTime>dayStart && monitorTime<dayEnd){
                    //昼间是早晨6点到晚上22点
                    Double leqDayLimit = day.get(noisePortHistory.getPortId());
                    if(null!=leqDayLimit){
                        if (noisePortHistory.getLeqdb()<leqDayLimit){
                            noisePortHistory.setDataStatus("0");
                            noisePort.setPortStatus("0");
                        }else {
                            noisePortHistory.setDataStatus("1");
                            noisePort.setPortStatus("1");
                        }
                    }
                }else {
                    //夜间是晚上22点到早晨6点
                    Double leqNightLimit =night.get(noisePortHistory.getPortId());
                    if (null!=leqNightLimit){
                        if (noisePortHistory.getLeqdb()<leqNightLimit){
                            noisePortHistory.setDataStatus("0");
                            noisePort.setPortStatus("0");
                        }else {
                            noisePortHistory.setDataStatus("1");
                            noisePort.setPortStatus("1");
                        }
                    }
                }
                saveOrUpdate(noisePortHistory);
                if (noisePort.getId()!=null){
                    noisePortDAO.update(noisePort);
                }

            }
            log.info("定时读取、保存噪声实时数据成功");
        } catch (Exception e) {
            log.error("定时读取、保存噪声实时数据异常",e);
        }
    }
}