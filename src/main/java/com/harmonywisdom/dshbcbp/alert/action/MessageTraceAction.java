package com.harmonywisdom.dshbcbp.alert.action;

import com.harmonywisdom.dshbcbp.alert.bean.Message;
import com.harmonywisdom.dshbcbp.alert.bean.MessageTrace;
import com.harmonywisdom.dshbcbp.alert.service.MessageService;
import com.harmonywisdom.dshbcbp.alert.service.MessageTraceService;
import com.harmonywisdom.dshbcbp.common.dict.util.DateUtil;
import com.harmonywisdom.framework.action.BaseAction;
import com.harmonywisdom.framework.dao.Direction;
import com.harmonywisdom.framework.dao.QueryCondition;
import com.harmonywisdom.framework.dao.QueryOperator;
import com.harmonywisdom.framework.dao.QueryParam;
import com.harmonywisdom.framework.service.annotation.AutoService;
import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.List;

public class MessageTraceAction extends BaseAction<MessageTrace, MessageTraceService> {
    @AutoService
    private MessageTraceService messageTraceService;

    @AutoService
    private MessageService messageService;

    @Override
    protected MessageTraceService getService() {
        return messageTraceService;
    }

    @Override
    protected QueryCondition getQueryCondition() {

        String mobileOperType = request.getParameter("mobileOperType");

        QueryParam param = new QueryParam();
        if (StringUtils.isNotBlank(entity.getReceiverName())) {
            param.andParam(new QueryParam("receiverName", QueryOperator.LIKE,entity.getReceiverName()));
        }
        if (StringUtils.isNotBlank(entity.getReceiveStatus())) {
            param.andParam(new QueryParam("receiveStatus", QueryOperator.EQ,entity.getReceiveStatus()));
        }

        String businessId = request.getParameter("businessId");
        if(StringUtils.isNotBlank(businessId)){
            Message msg = messageService.findByBusinessId(businessId);
            if (msg != null){
                param.andParam(new QueryParam("msgId", QueryOperator.EQ, msg.getId()));
            }else{
                param.andParam(new QueryParam("msgId", QueryOperator.EQ, "EmptyMessage"));
            }
        }

        if("1".equals(mobileOperType)){//下拉
//            log.debug("下拉："+DateUtil.dateToStr(entity.getMobileTimestamp(),"yyyy-MM-dd HH:mm:ss"));
            if (null!=entity.getMobileTimestamp()){
                param.andParam(new QueryParam("mobileTimestamp",QueryOperator.GT, entity.getMobileTimestamp()));
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
        condition.setOrderBy("receiveTime", Direction.DESC);
        if (StringUtils.isNotEmpty(mobileOperType)){
            condition.setOrderBy("mobileTimestamp", Direction.DESC);
        }
        return condition;
    }

    /**
     * 获取用户未读消息数量
     */
    public void getNewMsgCountByUserId(){
        String userId = request.getParameter("userId");
        if(StringUtils.isNotBlank(userId)){
            int msgCount = getService().getNewMsgCountByUserId(userId);
            write(msgCount);
        }else{
            write(false);
        }
    }

    /**
     * 获取用户未读消息列表
     */
    public void getUserMsgList(){
        String userId = request.getParameter("userId");
        String isMobile = request.getParameter("isMobile");//1 客户端    0或null web端
        if(StringUtils.isNotBlank(userId)){//查询最新消息
            List<MessageTrace> messageTraces = getService().getNewMessagesByUserId(userId,isMobile);
            write(messageTraces);
        }else{
            write(false);
        }
    }

    /**
     * 获取用户历史消息列表
     */
    public void getHistoryByUserId(){
        String userId = request.getParameter("userId");
        String oldMsgAlertTime = request.getParameter("oldMsgAlertTime");
        if(StringUtils.isNotBlank(oldMsgAlertTime) && StringUtils.isNotBlank(userId)){//查询历史消息
            Date oldMsgCreateTime = DateUtil.strToDate(oldMsgAlertTime, "yyyy-MM-dd hh:mm:ss");
            List<MessageTrace> messageTraces = getService().getHistoryByUserId(userId,oldMsgCreateTime);
            if (messageTraces != null && messageTraces.size() > 0) {
                write(messageTraces);
            }
        }
        write(false);
    }

    /**
     * 设置web端消息为已读
     */
    public void setStatusReceived(){
        String[] ids = request.getParameterValues("ids");
        if (ids != null && ids.length > 0) {
            int count = getService().setStatusReceived(ids);
            write(count);
        }else{
            write(false);
        }
    }

    /**
     * 设置手机端端消息为已读
     */
    public void setMobileStatusReceived(){
        String[] ids = request.getParameterValues("ids");
        if (ids != null && ids.length > 0) {
            int count = getService().setStatusReceived(ids);
            write(count);
        }else{
            write(false);
        }
    }

    public void msgHasUnReceivedByBusinessId(){
        String businessId = request.getParameter("businessId");
        if (StringUtils.isNotBlank(businessId)){
            boolean hasUnReiceived = getService().msgHasUnReceivedByBusinessId(businessId);
            write(hasUnReiceived);
        }else{
            write(false);
        }


    }

}