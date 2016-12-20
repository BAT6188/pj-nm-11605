package com.harmonywisdom.dshbcbp.utils;

import com.jasson.im.api.MOItem;
import com.jasson.im.api.RPTItem;

import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.text.DateFormat;
import java.util.*;

/**
 * Created by Administrator on 2016/12/20.
 */
public class APIClientTest {
    public static final int IMAPI_SUCC = 0;
    public static final int IMAPI_CONN_ERR = -1;
    public static final int IMAPI_CONN_CLOSE_ERR = -2;
    public static final int IMAPI_INS_ERR = -3;
    public static final int IMAPI_DEL_ERR = -4;
    public static final int IMAPI_QUERY_ERR = -5;
    public static final int IMAPI_DATA_ERR = -6;
    public static final int IMAPI_API_ERR = -7;
    public static final int IMAPI_DATA_TOOLONG = -8;
    public static final int IMAPI_INIT_ERR = -9;
    public static final int IMAPI_IFSTATUS_INVALID = -10;
    public static final int IMAPI_GATEWAY_CONN_ERR = -11;
    public static final int SM_TYPE_NORMAL = 0;
    public static final int SM_TYPE_PDU = 2;
    private String dbUser = null;
    private String dbPwd = null;
    private String apiCode_ = null;
    private String dbUrl = null;
    private Connection conn = null;

    public APIClientTest() {
    }

    public int init(String dbIP, String dbUser, String dbPwd, String apiCode) {
        this.release();
        this.dbUser = dbUser;
        this.dbPwd = dbPwd;
        this.apiCode_ = apiCode;
        this.dbUrl = "jdbc:mysql://" + dbIP + "/im";
        return this.testConnect();
    }

    public int init(String dbIP, String dbUser, String dbPwd, String apiCode, String dbName) {
        this.release();
        this.dbUser = dbUser;
        this.dbPwd = dbPwd;
        this.apiCode_ = apiCode;
        this.dbUrl = "jdbc:mysql://" + dbIP + "/" + dbName;
        return this.testConnect();
    }

    public int sendSM(String mobile, String content, long smID) {
        return this.sendSM(new String[]{mobile}, content, smID, smID);
    }

    public int sendSM(String[] mobiles, String content, long smID) {
        return this.sendSM(mobiles, content, smID, smID);
    }

    public int sendSM(String[] mobiles, String content, long smID, long srcID) {
        return this.sendSM(mobiles, content, smID, srcID, "");
    }

    public int sendSM(String[] mobiles, String content, String sendTime, long smID, long srcID) {
        return this.sendSM(mobiles, content, smID, srcID, "", sendTime);
    }

    public int sendSM(String mobile, String content, long smID, String url) {
        return this.sendSM(new String[]{mobile}, content, smID, url);
    }

    public int sendSM(String[] mobiles, String content, long smID, String url) {
        return this.sendSM(mobiles, content, smID, smID, url);
    }

    public int sendSM(String[] mobiles, String content, long smID, long srcID, String url) {
        return this.sendSM(mobiles, content, smID, srcID, url, (String)null);
    }

    public int sendSM(String[] mobiles, String content, long smID, long srcID, String url, String sendTime) {
        return this.sendSmAndPdu(mobiles, content, smID, srcID, url, sendTime, 0, 0, 0, "", "", "", 0, 0);
    }

    public int sendPDU(String[] mobiles, byte[] content, long smID, int msgFmt, int tpPID, int tpUdhi, String feeTerminalID, String feeType, String feeCode, int feeUserType) {
        return this.sendPDU(mobiles, content, smID, smID, msgFmt, tpPID, tpUdhi, feeTerminalID, feeType, feeCode, feeUserType);
    }

    public int sendPDU(String[] mobiles, byte[] content, long smID, long srcID, int msgFmt, int tpPID, int tpUdhi, String feeTerminalID, String feeType, String feeCode, int feeUserType) {
        String contentStr = this.binary2Hex(content);
        return this.sendSmAndPdu(mobiles, contentStr, smID, srcID, "", (String)null, msgFmt, tpPID, tpUdhi, feeTerminalID, feeType, feeCode, feeUserType, 2);
    }

    private int sendSmAndPdu(String[] mobiles, String content, long smID, long srcID, String url, String sendTime, int msgFmt, int tpPID, int tpUdhi, String feeTerminalID, String feeType, String feeCode, int feeUserType, int smType) {
        if(this.dbUrl == null) {
            return -9;
        } else if(mobiles != null && mobiles.length != 0) {
            StringBuffer mobileBuf = new StringBuffer();

            for(int contenttmp = 0; contenttmp < mobiles.length; ++contenttmp) {
                mobileBuf.append(",").append(mobiles[contenttmp]);
            }

            if(mobileBuf.length() > 1) {
                mobileBuf.delete(0, 1);
                String var20 = this.replaceSpecilAlhpa(content);
                if(var20.length() < 1) {
                    return -6;
                } else {
                    if(var20.length() > 2000) {
                        var20 = var20.substring(0, 2000);
                    }

                    if(this.checkSmID(smID) && this.checkSmID(srcID)) {
                        url = this.nullConvert(url).trim();
                        if(url.length() > 0) {
                            if(url.length() > 85) {
                                return -8;
                            }

                            if((url + var20).length() > 120) {
                                return -8;
                            }
                        }

                        int ret = this.checkGatConn();
                        if(ret != 1) {
                            return ret;
                        } else if(sendTime != null && !"".equals(sendTime) && isDateTime(sendTime) == null) {
                            return -6;
                        } else {
                            if(2 == smType) {
                                feeTerminalID = this.nullConvert(feeTerminalID).trim();
                                feeType = this.nullConvert(feeType).trim();
                                feeCode = this.nullConvert(feeCode).trim();
                            }

                            return this.mTInsert(mobileBuf.toString(), var20, smID, srcID, url, sendTime, msgFmt, tpPID, tpUdhi, feeTerminalID, feeType, feeCode, feeUserType, smType);
                        }
                    } else {
                        return -6;
                    }
                }
            } else {
                return -6;
            }
        } else {
            return -6;
        }
    }

    public MOItem[] receiveSM() {
        return this.receiveSM(-1);
    }

    public MOItem[] receiveSM(int amount) {
        return this.receiveSM(-1L, amount);
    }

    public MOItem[] receiveSM(long srcID, int amount) {
        if(this.dbUrl == null) {
            return null;
        } else {
            if(this.conn == null) {
                int st = this.initConnect();
                if(st != 0) {
                    return null;
                }
            }

            Statement st1 = null;
            ResultSet rs = null;
            String getMoSql = "select * from api_mo_" + this.apiCode_;
            if(srcID != -1L) {
                getMoSql = getMoSql + " where SM_ID=" + srcID;
            }

            if(amount != -1) {
                getMoSql = getMoSql + " limit " + amount;
            } else {
                getMoSql = getMoSql + " limit 5000";
            }

            String delMoSql = "delete from api_mo_" + this.apiCode_ + " where AUTO_SN in (";
            ArrayList moList = new ArrayList();
            StringBuffer snBuf = new StringBuffer("-1");

            label84: {
                try {
                    st1 = this.conn.createStatement();
                    rs = st1.executeQuery(getMoSql);

                    while(rs.next()) {
                        MOItem moItem = new MOItem();
                        moItem.setSmID(rs.getLong("SM_ID"));
                        moItem.setContent(this.iso2gbk(rs.getString("CONTENT")));
                        moItem.setMobile(rs.getString("MOBILE"));
                        moItem.setMoTime(rs.getString("MO_TIME"));
                        moItem.setMsgFmt(rs.getInt("MSG_FMT"));
                        snBuf.append(",").append(rs.getString("AUTO_SN"));
                        moList.add(moItem);
                    }

                    rs.close();
                    st1.executeUpdate(delMoSql + snBuf.toString() + ")");
                    break label84;
                } catch (Exception var14) {
                    var14.printStackTrace();
                    this.releaseConn();
                } finally {
                    this.closeStatment(st1);
                }

                return null;
            }

            MOItem[] moItem1 = new MOItem[0];
            return (MOItem[])moList.toArray(moItem1);
        }
    }

    public RPTItem[] receiveRPT() {
        return this.receiveRPT(-1);
    }

    public RPTItem[] receiveRPT(int amount) {
        return this.receiveRPT(-1L, amount);
    }

    public RPTItem[] receiveRPT(long smID, int amount) {
        if(this.dbUrl == null) {
            return null;
        } else {
            ResultSet rs = null;
            Statement st = null;
            if(this.conn == null) {
                int getRPTSql = this.initConnect();
                if(getRPTSql != 0) {
                    return null;
                }
            }

            String getRPTSql1 = "select * from api_rpt_" + this.apiCode_;
            if(smID != -1L) {
                getRPTSql1 = getRPTSql1 + " where SM_ID=" + smID;
            }

            if(amount != -1) {
                getRPTSql1 = getRPTSql1 + " limit " + amount;
            } else {
                getRPTSql1 = getRPTSql1 + " limit 5000";
            }

            String delRPTSql = "delete from api_rpt_" + this.apiCode_ + " where AUTO_SN in (";
            RPTItem[] rptItem = (RPTItem[])null;
            ArrayList rptList = new ArrayList();
            StringBuffer snBuf = new StringBuffer("-1");

            label86: {
                try {
                    st = this.conn.createStatement();
                    rs = st.executeQuery(getRPTSql1);

                    while(rs.next()) {
                        RPTItem ex = new RPTItem();
                        ex.setSmID(rs.getLong("SM_ID"));
                        ex.setCode(rs.getInt("RPT_CODE"));
                        ex.setMobile(rs.getString("MOBILE"));
                        ex.setDesc(this.iso2gbk(rs.getString("RPT_DESC")));
                        ex.setRptTime(rs.getString("RPT_TIME"));
                        snBuf.append(",").append(rs.getString("AUTO_SN"));
                        rptList.add(ex);
                    }

                    rs.close();
                    st.executeUpdate(delRPTSql + snBuf.toString() + ")");
                    break label86;
                } catch (SQLException var16) {
                    var16.printStackTrace();
                    this.releaseConn();
                } catch (Exception var17) {
                    var17.printStackTrace();
                } finally {
                    this.closeStatment(st);
                }

                return null;
            }

            rptItem = new RPTItem[0];
            return (RPTItem[])rptList.toArray(rptItem);
        }
    }

    public void release() {
        this.dbUser = null;
        this.dbPwd = null;
        this.apiCode_ = null;
        this.dbUrl = null;
        this.releaseConn();
    }

    private int testConnect() {
        Statement st = null;
        ResultSet rs = null;

        try {
            if(this.conn != null) {
                this.releaseConn();
            }

            this.getConn();
            st = this.conn.createStatement();
        } catch (Exception var15) {
            var15.printStackTrace();
            return -1;
        }

        try {
            String tableName = "api_mo_" + this.apiCode_;
            rs = st.executeQuery("select * from " + tableName + " limit 1");
            rs.close();
            return 0;
        } catch (SQLException var13) {
            var13.printStackTrace();
        } finally {
            try {
                st.close();
            } catch (Exception var12) {
                var12.printStackTrace();
            }

        }

        return -7;
    }

    private int initConnect() {
        try {
            this.getConn();
            return 0;
        } catch (Exception var2) {
            var2.printStackTrace();
            return -1;
        }
    }

    private void getConn() throws ClassNotFoundException, SQLException {
        Class.forName("org.gjt.mm.mysql.Driver");
        this.conn = DriverManager.getConnection(this.dbUrl, this.dbUser, this.dbPwd);
    }

    private void releaseConn() {
        if(this.conn != null) {
            try {
                this.conn.close();
            } catch (SQLException var2) {
                var2.printStackTrace();
            }
        }

        this.conn = null;
    }

    private int mTInsert(String mobile, String content, long smID, long srcID, String url, String sendTime, int msgFmt, int tpPID, int tpUdhi, String feeTerminalID, String feeType, String feeCode, int feeUserType, int smType) {
        if(this.conn == null) {
            int sendMTSql = this.initConnect();
            if(sendMTSql != 0) {
                return -1;
            }
        }

        String sendMTSql1 = "";
        sendMTSql1 = "insert into api_mt_" + this.apiCode_ + " (SM_ID,SRC_ID,MOBILES,CONTENT,IS_WAP,URL,SEND_TIME,MSG_FMT,TP_PID,TP_UDHI,FEE_TERMINAL_ID,FEE_TYPE,FEE_CODE,FEE_USER_TYPE,SM_TYPE) values (" + smID + "," + srcID + ",\'" + mobile + "\',\'" + content + "\', ";
        if(url != null && url.trim().length() != 0) {
            sendMTSql1 = sendMTSql1 + "1,\'" + url + "\',";
        } else {
            sendMTSql1 = sendMTSql1 + "0,\'\',";
        }

        if(sendTime != null && !"".equals(sendTime.trim())) {
            sendMTSql1 = sendMTSql1 + "\'" + sendTime + "\',";
        } else {
            sendMTSql1 = sendMTSql1 + " null ,";
        }

        sendMTSql1 = sendMTSql1 + msgFmt + "," + tpPID + "," + tpUdhi + ",\'" + feeTerminalID + "\',\'" + feeType + "\',\'" + feeCode + "\'," + feeUserType + "," + smType + ")";
        Statement st = null;

        try {
            st = this.conn.createStatement();
            st.executeUpdate(this.gb2Iso(sendMTSql1));
            return 0;
        } catch (Exception var23) {
            var23.printStackTrace();
            this.releaseConn();
        } finally {
            this.closeStatment(st);
        }

        return -3;
    }

    private void closeStatment(Statement st) {
        try {
            st.close();
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    private String replaceSpecilAlhpa(String content) {
        if(content != null && content.trim().length() != 0) {
            String spec_char = "\\\'";
            String retStr = "";

            for(int i = 0; i < content.length(); ++i) {
                if(spec_char.indexOf(content.charAt(i)) >= 0) {
                    retStr = retStr + "\\";
                }

                retStr = retStr + content.charAt(i);
            }

            return retStr;
        } else {
            return "";
        }
    }

    private boolean checkSmID(long smID) {
        return smID >= 0L && smID <= 99999999L;
    }

    private String gb2Iso(String str) {
        if(str == null) {
            return "";
        } else {
            String temp = "";

            try {
                byte[] e = str.trim().getBytes("GBK");
                temp = new String(e, "iso8859-1");
            } catch (UnsupportedEncodingException var4) {
                temp = str;
                var4.printStackTrace();
            }

            return temp;
        }
    }

    private int checkGatConn() {
        byte ret = 1;
        ResultSet rs = null;
        Statement st = null;
        if(this.conn == null) {
            this.initConnect();
        }

        String sql = "select if_status,conn_succ_status from tbl_api_info as api where api.if_code=\'" + this.apiCode_ + "\' limit 1";

        try {
            st = this.conn.createStatement();
            rs = st.executeQuery(sql);

            while(rs.next()) {
                if("2".equals(rs.getString("if_status"))) {
                    ret = -10;
                }

                if("0".equals(rs.getString("conn_succ_status"))) {
                    ret = -11;
                }
            }

            rs.close();
            return ret;
        } catch (SQLException var14) {
            var14.printStackTrace();
        } finally {
            try {
                st.close();
            } catch (Exception var13) {
                var13.printStackTrace();
            }

        }

        return -7;
    }

    public static String isDateTime(String str) {
        if(str == null) {
            return null;
        } else if(str.length() != 19) {
            return null;
        } else {
            int temp = Integer.parseInt(str.substring(5, 7));
            if(12 >= temp && temp >= 1) {
                temp = Integer.parseInt(str.substring(8, 10));
                if(31 >= temp && temp >= 1) {
                    temp = Integer.parseInt(str.substring(11, 13));
                    if(23 >= temp && temp >= 0) {
                        temp = Integer.parseInt(str.substring(14, 16));
                        if(59 >= temp && temp >= 0) {
                            temp = Integer.parseInt(str.substring(17, 19));
                            if(59 >= temp && temp >= 0) {
                                java.util.Date returnDate = null;
                                DateFormat df = DateFormat.getDateInstance();

                                try {
                                    returnDate = df.parse(str);
                                    return returnDate.toString();
                                } catch (Exception var5) {
                                    var5.printStackTrace();
                                    return null;
                                }
                            } else {
                                return null;
                            }
                        } else {
                            return null;
                        }
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }
    }

    private String binary2Hex(byte[] bys) {
        if(bys != null && bys.length >= 1) {
            StringBuffer sb = new StringBuffer(100);

            for(int i = 0; i < bys.length; ++i) {
                if(bys[i] >= 16) {
                    sb.append(Integer.toHexString(bys[i]));
                } else if(bys[i] >= 0) {
                    sb.append("0" + Integer.toHexString(bys[i]));
                } else {
                    sb.append(Integer.toHexString(bys[i]).substring(6, 8));
                }
            }

            return sb.toString();
        } else {
            return null;
        }
    }

    private String iso2gbk(String str) {
        if(str == null) {
            return "";
        } else {
            String temp = "";

            try {
                byte[] e = str.trim().getBytes("iso8859-1");
                temp = new String(e, "GBK");
            } catch (UnsupportedEncodingException var4) {
                var4.printStackTrace();
                temp = str;
            }

            return temp;
        }
    }

    private String nullConvert(String str) {
        if(str == null) {
            str = "";
        }

        return str;
    }
}
