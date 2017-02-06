package com.harmonywisdom.dshbcbp.alert.service;

import com.harmonywisdom.dshbcbp.alert.bean.Message;
import com.harmonywisdom.dshbcbp.alert.bean.MessageTrace;
import com.harmonywisdom.framework.service.IBaseService;

import java.util.Date;
import java.util.List;

public interface MessageTraceService extends IBaseService<MessageTrace, String> {

    /**
     * 获取用户未消息数量
     * @param userId
     * @return
     */
    int getNewMsgCountByUserId(String userId);

    /**
     * 查找用户未读消息
     * @param userId
     * @return
     */
    List<MessageTrace> getNewMessagesByUserId(String userId,String isMobile);

    /**
     * 获取用户历史消息
     * @param userId
     * @param oldMsgAlertTime 当前列表显示最老一条消息的提醒时间
     * @return
     */
    List<MessageTrace> getHistoryByUserId(String userId,Date oldMsgAlertTime);

    /**
     * 设置消息跟踪状态为已读
     * @param ids
     * @return
     */
    int setStatusReceived(String... ids);

    /**
     * 设置消息跟踪状态为已读
     * @param ids
     * @return
     */
    int setMobileStatusReceived(String... ids);

    /**
     * 根据业务id查看发送的消息是否有人未接收
     * @param businessId
     * @return
     */
    boolean msgHasUnReceivedByBusinessId(String businessId);

}