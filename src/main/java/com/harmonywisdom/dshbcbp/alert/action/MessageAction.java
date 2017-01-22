package com.harmonywisdom.dshbcbp.alert.action;

import com.alibaba.fastjson.JSONArray;
import com.harmonywisdom.dshbcbp.alert.bean.Message;
import com.harmonywisdom.dshbcbp.alert.bean.MessageTrace;
import com.harmonywisdom.dshbcbp.alert.service.MessageService;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.Direction;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryOperator;
import com.harmonywisdom.framework.dao.QueryParam;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class MessageAction extends BaseAction<Message, MessageService> {
    @AutoService
    private MessageService messageService;

    @Override
    protected MessageService getService() {
        return messageService;
    }

    @Override
    protected QueryCondition getQueryCondition() {
        String mobileOperType = request.getParameter("mobileOperType");

        QueryParam param = new QueryParam();
        if("1".equals(mobileOperType)){//下拉
//            log.debug("下拉："+DateUtil.dateToStr(entity.getMobileTimestamp(),"yyyy-MM-dd HH:mm:ss"));
            if (null!=entity.getMobileTimestamp()){
                param.andParam(new QueryParam("mobileTimestamp", QueryOperator.GT, entity.getMobileTimestamp()));
            }
        }else if("2".equals(mobileOperType)){//上拉
//            log.debug("上拉："+DateUtil.dateToStr(entity.getMobileTimestamp(),"yyyy-MM-dd HH:mm:ss"));
            if (null!=entity.getMobileTimestamp()){
                param.andParam(new QueryParam("mobileTimestamp",QueryOperator.LT, entity.getMobileTimestamp()));
            }
        }

        QueryCondition condition = new QueryCondition();
        if (param.getField() != null) {
            condition.setParam(param);
        }
        condition.setPaging(getPaging());
        if (StringUtils.isNotEmpty(mobileOperType)){
            condition.setOrderBy("mobileTimestamp", Direction.DESC);
        }
        return condition;
    }

    /**
     * 发送系统消息
     */
    public void sendMessage(){
        String receiversStr = request.getParameter("receivers");
        if (entity != null && StringUtils.isNotBlank(receiversStr)) {
            List<MessageTrace> receivers = JSONArray.parseArray(receiversStr, MessageTrace.class);
            getService().sendMessage(entity, receivers);
            write(true);
        }else{
            write(false);
        }
    }

    /**
     * 设置当前session 是否消息提醒
     */
    public void setIsAlert(){
        String isAlert = request.getParameter("isAlert");
        request.getSession().setAttribute("msgIsAlert", isAlert);
        write(true);
    }
}