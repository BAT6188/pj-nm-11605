package com.harmonywisdom.dshbcbp.sms.service.impl;

import com.harmonywisdom.dshbcbp.sms.bean.SmsRecord;
import com.harmonywisdom.dshbcbp.sms.bean.SmsSendStatus;
import com.harmonywisdom.dshbcbp.sms.dao.SmsRecordDAO;
import com.harmonywisdom.dshbcbp.sms.service.SmsRecordService;
import com.harmonywisdom.dshbcbp.sms.service.SmsSendStatusService;
import com.harmonywisdom.dshbcbp.utils.SmsSender;
import com.harmonywisdom.framework.dao.*;
import com.harmonywisdom.framework.service.BaseService;
import com.harmonywisdom.framework.service.SpringUtil;
import com.mascloud.sdkclient.Client;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.*;
import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service("smsRecordService")
public class SmsRecordServiceImpl extends BaseService<SmsRecord, String> implements SmsRecordService {

    /**
     * 短信发送表
     */
    private static final String api_mt_sms="api_mt_003";

    /**
     * 短信回执表
     */
    private static final String api_rpt_sms="api_rpt_003";

    private static Long sequence_init=1L;

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
    public List<SmsSendStatus> sendSms(SmsRecord smsRsecord, List<SmsSendStatus> receivers) throws Exception{
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
            sendSmsByAPI(smsRsecord,receivers);//批量发送短信
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
        if (smsRsecord.getSendTime()==null){
            sendSmsByAPI(smsRsecord,receivers);//发送 发送时间为空（立即发送）的短信
            smsRsecord.setSendTime(new Date());//更新短信记录表中需要立即发送短信 的发送时间

            getDAO().save(smsRsecord);//保存短信记录
            Set<SmsSendStatus> statuses = new HashSet<>();
            for (SmsSendStatus status : receivers) {
                status.setSmsRecordId(smsRsecord.getId());
                status.setStatus(SmsSendStatus.SEND_STATUS_SENT);
                statuses.add(status);
            }
            smsSendStatusService.saveBatch(statuses);//保存发送联系人
        }else {
            log.info("定时发送短信，发送时间："+smsRsecord.getSendTime());

            getDAO().save(smsRsecord);//保存短信记录
            Set<SmsSendStatus> statuses = new HashSet<>();
            for (SmsSendStatus status : receivers) {
                status.setSmsRecordId(smsRsecord.getId());
                status.setStatus(SmsSendStatus.SEND_STATUS_NO_SENT);
                statuses.add(status);
            }
            smsSendStatusService.saveBatch(statuses);//保存发送联系人
        }

        return receivers;
    }

    /**
     * 定时发送短信
     */
    public void quartzSendSms(){
        QueryParam param = new QueryParam();
        param.andParam(new QueryParam("status", QueryOperator.EQ, SmsSendStatus.SEND_STATUS_NO_SENT));
        QueryCondition condition = new QueryCondition();
        if (param.getField() != null) {
            condition.setParam(param);
        }

        QueryResult<SmsSendStatus> smsSendStatusQueryResult = smsSendStatusService.find(condition);
        if (smsSendStatusQueryResult!=null){
            List<SmsSendStatus> rows = smsSendStatusQueryResult.getRows();
            for (SmsSendStatus row : rows) {
                QueryParam param1 = new QueryParam();
                param1.andParam(new QueryParam("id", QueryOperator.EQ, row.getId()));
                param1.andParam(new QueryParam("SEND_TIME", QueryOperator.LE, new Date()));
                QueryCondition condition1 = new QueryCondition();
                if (param1.getField() != null) {
                    condition1.setParam(param1);
                }
                QueryResult<SmsRecord> smsRecordQueryResult = find(condition1);
                if (smsRecordQueryResult!=null){
                    List<SmsRecord> record1 = smsRecordQueryResult.getRows();
                    if(record1!=null&&record1.size()>0){
                        SmsRecord record = record1.get(0);
                        List<SmsSendStatus> receivers=new ArrayList<>();
                        receivers.add(row);
                        try {
                            sendSmsByAPI(record,receivers);

                            row.setStatus(SmsSendStatus.SEND_STATUS_SENT);
                            smsSendStatusService.save(row);
                            log.info("定时发送短信成功，需要定时发送的短信时间为："+record.getSendTime());
                        } catch (Exception e) {
                            log.error("定时发送短信失败");
                        }
                    }
                }
            }
        }

    }

    /**
     * 调用发送短信API接口
     * @param smsRsecord
     * @param receivers
     * @throws Exception
     */
    public void sendSmsByAPI(SmsRecord smsRsecord, List<SmsSendStatus> receivers)throws Exception{
        //更换成 中国移动集团政企云MAS平台SDK接口文档-V1.2.4-JAVA 接口
        final Client client =  Client.getInstance();
        // 正式环境IP，登录验证URL，用户名，密码，集团客户名称
        client.login("http://mas.ecloud.10086.cn/app/sdk/login", "adminh", "log1q","鄂尔多斯市东胜区环境保护局.");
        for (SmsSendStatus receiver : receivers) {
            String receiverPhone = receiver.getReceiverPhone();
            if (StringUtils.isNotEmpty(receiverPhone)){
                String[] mobiles={receiverPhone};
                int sendResult = client. sendDSMS (mobiles,
                        smsRsecord.getContent(), "",  1,"JI6VrGwL", UUID.randomUUID().toString(),true);
                log.info("调用短信发送接口成功，返回结果："+sendResult);
            }else {
                log.error(receiver.getReceiverName()+"的手机号为空");
            }
        }

        /*//原接口
        SmsSender smssender= null;
        try {
            smssender = new SmsSender();
            for (SmsSendStatus receiver : receivers) {
                String receiverPhone = receiver.getReceiverPhone();
                if (StringUtils.isNotEmpty(receiverPhone)){
                    String[] _addressReceiver={receiverPhone};
                    smssender.sendSms(smsRsecord.getContent(),_addressReceiver,smsRsecord.getSendTime(),getSequence());
                }else {
                    log.debug(receiver.getReceiverName()+"的手机号为空");
                }
            }
            log.info("调用短信发送接口成功");
        }finally {
            if(smssender != null){
                smssender.release();
            }
        }*/


        /*String sql="INSERT INTO "+api_mt_sms+" (SM_ID,SRC_ID,MOBILES,CONTENT,IS_WAP,URL,SEND_TIME" +
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
                String smId=String.valueOf(getSequence());
                pstmt.setString(1,smId);  //SM_ID Decimal(8,0)  TODO 生成方式未定     (短信ID， 0到99999999之间的任何一个数值。 缺省值0。 当SM_ID为 0时， 表示这类短信不需要辨别其回执、回复。)
                pstmt.setBigDecimal(1,new BigDecimal(Double.valueOf(smId)));
                receivers.get(i).setMtSmId(smId);
                pstmt.setBigDecimal(2,new BigDecimal(Double.valueOf(smId)));  //SRC_ID Decimal(8,0)
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
                pstmt.setString(12,"");
                pstmt.setString(13,"");
                pstmt.setString(14,"");
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
        }*/
    }

    public Long getSequence(){
        synchronized (this) {
            sequence_init++;
            if (sequence_init.equals(99999999L)){
                sequence_init=1L;
            }
            return sequence_init;
        }
    }

    /**
     * 根据回执更新本地短信发送状态
     */
    public void updateStatusByReceipt(){
        ComboPooledDataSource dataSource =  SpringUtil.getBean("smsDataSource");
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<SmsSendStatus> smsSendStatuses = smsSendStatusService.find("status=?", SmsSendStatus.SEND_STATUS_SENT);
        log.debug("成功"+smsSendStatuses);
        for (SmsSendStatus smsSendStatuse : smsSendStatuses) {
            String mtSmId = smsSendStatuse.getMtSmId();
            try {
                PreparedStatement pstmt = conn.prepareStatement("SELECT a.SM_ID,a.MOBILE,a.RPT_CODE,a.RPT_DESC,a.RPT_TIME from "+api_rpt_sms+" a where a.SM_ID=?");
                pstmt.setString(1,mtSmId);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()){
                    String rpt_code = rs.getString("RPT_CODE");
                    smsSendStatuse.setRptCode(rpt_code);
                    if ("0".equals(rpt_code)){
                        smsSendStatuse.setStatus(SmsSendStatus.SEND_STATUS_RECEIVED);
                    }else{
                        smsSendStatuse.setStatus(SmsSendStatus.SEND_STATUS_FAILED);
                    }
                    smsSendStatusService.update(smsSendStatuse);

                }
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


    }



}