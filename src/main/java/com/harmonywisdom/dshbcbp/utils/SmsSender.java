package com.harmonywisdom.dshbcbp.utils;



import com.harmonywisdom.dshbcbp.common.dict.util.DateUtil;
import com.jasson.im.api.APIClient;

import java.util.Date;
import java.util.StringTokenizer;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SmsSender
{
  private static long smId = 1L;
  public static String host = "10.1.5.90";
  public static String dbName = "mas";
  public static String apiId = "002";
  public static String name = "mas";
  public static String pwd = "HUATENGSOFT_mas08";
  public static boolean smsFlag = true;
  private static APIClient handler = new APIClient();
  private static Log logger = LogFactory.getFactory().getInstance(SmsSender.class);

  public SmsSender() { init();
  }

  public void init()
  {
    int connectRe = handler.init(host, name, pwd, apiId, dbName);
    if (connectRe == 0) {
      logger.error("初始化成功");
    }
    else if (connectRe == -1) {
      logger.error("连接失败");
    }
    else if (connectRe == -7)
      logger.error("apiID不存在");
  }

  public ISmsBean sendSms(String message, String[] _addressReceiver,Date sendTime) {
    SmsBean smsBean = new SmsBean();
    if (!(smsFlag)) {
      smsBean.setErrMsg("警告：短信发送功能未开启，不能继续操作。请在系统配置管理中修改。");
      smsBean.setSuccess(false);

      return smsBean;
    }
    if (host.length() == 0) {
      smsBean.setErrMsg("警告：短信数据库地址为空，不能继续操作。请在系统配置管理中修改。");
      smsBean.setSuccess(false);

      return smsBean;
    }
    if (dbName.length() == 0) {
      smsBean.setErrMsg("警告：短信数据库名称为空，不能继续操作。请在系统配置管理中修改。");
      smsBean.setSuccess(false);

      return smsBean;
    }
    if (apiId.length() == 0) {
      smsBean.setErrMsg("警告：短信接口编码为空，不能继续操作。请在系统配置管理中修改。");
      smsBean.setSuccess(false);

      return smsBean;
    }
    if (name.length() == 0) {
      smsBean.setErrMsg("警告：短信接口登录名为空，不能继续操作。请在系统配置管理中修改。");
      smsBean.setSuccess(false);

      return smsBean;
    }
    if (pwd.length() == 0) {
      smsBean.setErrMsg("警告：短信接口登陆密码为空，不能继续操作。请在系统配置管理中修改。");
      smsBean.setSuccess(false);

      return smsBean;
    }
    String addressReceiver = "";
    for (int i = 0; i < _addressReceiver.length; ++i) {
      addressReceiver = addressReceiver + _addressReceiver[i] + ",";
    }
    addressReceiver = addressReceiver.substring(0, addressReceiver.length() - 1);
    String tmpSrcID = "66";
    String msg = "";

    if ((addressReceiver == null) || (addressReceiver.trim().length() == 0))
    {
      msg = "请输入手机号码，多个号码用英文逗号隔开";
      logger.error(msg);
      smsBean.setErrMsg(msg);
      smsBean.setSuccess(false);
    }

    if ((message == null) || (message.trim().length() == 0))
    {
      msg = "请输入短信内容";
      logger.error(msg);
      smsBean.setErrMsg(msg);
      smsBean.setSuccess(false);
    }

    Vector mobileList = new Vector();
    StringTokenizer st = new StringTokenizer(addressReceiver, ",");
    while (st.hasMoreElements())
    {
      String tmp = (String)st.nextElement();
      if (tmp.indexOf("-") != -1)
      {
        long min = Long.parseLong(tmp.substring(0, tmp.indexOf("-")));
        long max = Long.parseLong(tmp.substring(tmp.indexOf("-") + 1));

        long i = min;
        while (i <= max)
        {
          mobileList.addElement(Long.toString(i));
          i += 1L;
        }
      }
      else
      {
        mobileList.addElement(tmp);
      }
    }
    int len = mobileList.size();
    String[] mobiles = new String[len];
    for (int i = 0; i < len; ++i)
    {
      mobiles[i] = ((String)mobileList.elementAt(i));
    }
    int result = 0;
    if (sendTime==null){//如果发送时间为空，立即发送
      result = handler.sendSM(mobiles, message, smId, Long.parseLong(tmpSrcID));
    }else {//如果发送时间不为空，则到设置的定时时间再发送
      result = handler.sendSM(mobiles, message, DateUtil.dateToStr(sendTime,"yyyy-MM-dd HH:mm:ss"), smId, Long.parseLong(tmpSrcID));
    }

    if (result == 0)
    {
      msg = "短信发送成功";
      logger.error(msg);
      smsBean.setErrMsg(msg);
      smsBean.setSuccess(true);
    }
    else if (result == -9) {
      msg = "未初始化";
      logger.error(msg);
      smsBean.setErrMsg(msg);
      smsBean.setSuccess(false);
    }
    else if (result == -1) {
      msg = "数据库连接失败";
      logger.error(msg);
      smsBean.setErrMsg(msg);
      smsBean.setSuccess(false);
    }
    else if (result == -6) {
      msg = "参数错误";
      logger.error(msg);
      smsBean.setErrMsg(msg);
      smsBean.setSuccess(false);
    }
    else if (result == -8) {
      msg = "消息内容太长";
      logger.error(msg);
      smsBean.setErrMsg(msg);
      smsBean.setSuccess(false);
    }
    else if (result == -3) {
      msg = "数据库插入错误";
      logger.error(msg);
      smsBean.setErrMsg(msg);
      smsBean.setSuccess(false);
    }
    else {
      msg = "出现其他错误";
      logger.error(msg);
      smsBean.setErrMsg(msg);
      smsBean.setSuccess(false);
    }
    return smsBean;
  }
}