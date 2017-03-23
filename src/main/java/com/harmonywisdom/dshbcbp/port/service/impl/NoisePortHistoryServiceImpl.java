package com.harmonywisdom.dshbcbp.port.service.impl;

import com.harmonywisdom.dshbcbp.common.dict.util.DateUtil;
import com.harmonywisdom.dshbcbp.port.bean.NoisePortHistory;
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

import java.util.ArrayList;
import java.util.List;

@Service("noisePortHistoryService")
public class NoisePortHistoryServiceImpl extends BaseService<NoisePortHistory, String> implements NoisePortHistoryService {
    @Autowired
    private NoisePortHistoryDAO noisePortHistoryDAO;

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
                saveOrUpdate(noisePortHistory);
            }
            log.info("定时读取、保存噪声实时数据成功");
        } catch (Exception e) {
            log.error("定时读取、保存噪声实时数据异常",e);
        }
    }
}