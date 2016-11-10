package com.harmonywisdom.dshbcbp.alert.service;

import com.harmonywisdom.dshbcbp.alert.bean.Message;
import com.harmonywisdom.dshbcbp.alert.bean.MessageTrace;
import com.harmonywisdom.framework.service.IBaseService;

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
    List<MessageTrace> getNewMessagesByUserId(String userId);

    /**
     * 设置消息跟踪状态为已读
     * @param ids
     * @return
     */
    int setStatusReceived(String... ids);

}