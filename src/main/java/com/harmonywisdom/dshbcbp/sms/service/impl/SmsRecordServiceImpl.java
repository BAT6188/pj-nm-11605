package com.harmonywisdom.dshbcbp.sms.service.impl;

import com.harmonywisdom.dshbcbp.sms.bean.SmsRecord;
import com.harmonywisdom.dshbcbp.sms.bean.SmsSendStatus;
import com.harmonywisdom.dshbcbp.sms.dao.SmsRecordDAO;
import com.harmonywisdom.dshbcbp.sms.service.SmsRecordService;
import com.harmonywisdom.dshbcbp.sms.service.SmsSendStatusService;
import com.harmonywisdom.framework.dao.BaseDAO;
import com.harmonywisdom.framework.service.BaseService;
import com.harmonywisdom.framework.service.SpringUtil;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("smsRecordService")
public class SmsRecordServiceImpl extends BaseService<SmsRecord, String> implements SmsRecordService {
    @Autowired
    private SmsRecordDAO smsRecordDAO;

    @Autowired
    private SmsSendStatusService smsSendStatusService;

    @Override
    protected BaseDAO<SmsRecord, String> getDAO() {
        return smsRecordDAO;
    }

    /**
     * 注意：短信记录实体中的短信发送时间sendTime为空时，该条短信立即发送（即要立即发送的短信，sendTime必须为空），不为空时，该条短信到预定的时间再发送
     * @param smsRsecord 短信记录信息
     * @param receivers 接收人list
     * @return
     */
    @Override
    public List<SmsSendStatus> sendSms(SmsRecord smsRsecord, List<SmsSendStatus> receivers) {
        if (receivers == null || receivers.size() <= 0) {
            return null;
        }

        //方式一：先保存短信记录到本地数据库，然后调用短信接口，最后更新本地数据库中短信记录的发送状态
        /*getDAO().save(smsRsecord);//保存短信记录
        Set<SmsSendStatus> statuses = new HashSet<>();
        for (SmsSendStatus status : receivers) {
            status.setSmsRecordId(smsRsecord.getId());
            status.setStatus(SmsSendStatus.SEND_STATUS_NO_SENT);
            statuses.add(status);
        }
        smsSendStatusService.saveBatch(statuses);//保存发送联系人，并初始化为未发送状态
        try {
            sendSmsByDB(smsRsecord,receivers);//批量发送短信
            for (SmsSendStatus statuse : statuses) {
                statuse.setStatus(SmsSendStatus.SEND_STATUS_SENT);
            }
            smsSendStatusService.updateBatch(statuses);//更新联系人中发送状态为已发送
            if (smsRsecord.getSendTime()==null){
                smsRsecord.setSendTime(new Date());
                getDAO().update(smsRsecord);//更新短信记录表中为立即发送的短信 的发送时间
            }
        } catch (Exception e) {
        }*/

        //方式二：先调用短信发送接口，然后再保存短信记录到本地数据库
        try {
            sendSmsByDB(smsRsecord,receivers);//批量发送短信
            if (smsRsecord.getSendTime()==null){
                smsRsecord.setSendTime(new Date());//更新短信记录表中需要立即发送短信 的发送时间
            }
            getDAO().save(smsRsecord);//保存短信记录
            Set<SmsSendStatus> statuses = new HashSet<>();
            for (SmsSendStatus status : receivers) {
                status.setSmsRecordId(smsRsecord.getId());
                status.setStatus(SmsSendStatus.SEND_STATUS_SENT);
                statuses.add(status);
            }
            smsSendStatusService.saveBatch(statuses);//保存发送联系人
        } catch (Exception e) {
            e.printStackTrace();
        }

        return receivers;
    }

    /**
     * 调用发送短信数据库接口
     * @param smsRsecord
     * @param receivers
     * @throws Exception
     */
    public void sendSmsByDB(SmsRecord smsRsecord, List<SmsSendStatus> receivers)throws Exception{
        String sql="INSERT INTO API_MT_SMS (SM_ID,SRC_ID,MOBILES,CONTENT,IS_WAP,URL,SEND_TIME" +
                ",SM_TYPE,MSG_FMT,TP_PID,TP_UDHI,FEE_TERMINAL_ID,FEE_TYPE,FEE_CODE" +
                ",FEE_USER_TYPE) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        ComboPooledDataSource dataSource =  SpringUtil.getBean("smsDataSource");
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            log.error("打开数据库连接出现问题",e);
            throw e;
        }
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < receivers.size(); i++) {
                String smId="1";
                pstmt.setString(1,smId);  //SM_ID Decimal(8,0)  TODO 生成方式未定     (短信ID， 0到99999999之间的任何一个数值。 缺省值0。 当SM_ID为 0时， 表示这类短信不需要辨别其回执、回复。)
                receivers.get(i).setMtSmId(smId);
                pstmt.setString(2,"1");  //SRC_ID Decimal(8,0)
                pstmt.setString(3, receivers.get(i).getReceiverPhone());
                pstmt.setString(4,smsRsecord.getContent());
                pstmt.setInt(5,0);
                pstmt.setString(6,"");
                Date sendTime = smsRsecord.getSendTime();
                if (sendTime==null){//如果发送时间为空，立即发送
                    pstmt.setDate(7,null);
                }else {//如果发送时间不为空，则到设置的定时时间再发送
                    pstmt.setDate(7,new java.sql.Date(sendTime.getTime()));
                }
                pstmt.setInt(8,0);
                pstmt.setInt(9,0);
                pstmt.setInt(10,0);
                pstmt.setInt(11,0);
                pstmt.setString(12,null);
                pstmt.setString(13,null);
                pstmt.setString(14,null);
                pstmt.setInt(15,0);
                //加入批处理
                pstmt.addBatch();
            }
            pstmt.executeBatch();    //执行批处理
            ResultSet rs = pstmt.getGeneratedKeys();
            int j=0;
            while (rs.next()) {
                Long id = rs.getLong(1);
                System.out.println("数据主键：" + id);
                receivers.get(j).setMtAutoSn(id);
                j++;
            }
            conn.commit();
            log.info("调用短信发送接口成功");
        } catch (SQLException e) {
            try {
                conn.rollback();
                log.info("短信发送接口数据回滚");
            } catch (SQLException e1) {
                log.error("",e1);
            }
            log.error("用短信发送接口错误",e);
            throw e;
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
               log.error("",e);
            }
        }
    }





}