package com.harmonywisdom.dshbcbp.sms.bean;

/**
 * 短信回执vo
 */
public class ApiRptSmsVo{
    private String sm_id;
    private String MOBILE;
    private String RPT_CODE;
    private String RPT_DESC;
    private String RPT_TIME;

    public String getSm_id() {
        return sm_id;
    }

    public void setSm_id(String sm_id) {
        this.sm_id = sm_id;
    }

    public String getMOBILE() {
        return MOBILE;
    }

    public void setMOBILE(String MOBILE) {
        this.MOBILE = MOBILE;
    }

    public String getRPT_CODE() {
        return RPT_CODE;
    }

    public void setRPT_CODE(String RPT_CODE) {
        this.RPT_CODE = RPT_CODE;
    }

    public String getRPT_DESC() {
        return RPT_DESC;
    }

    public void setRPT_DESC(String RPT_DESC) {
        this.RPT_DESC = RPT_DESC;
    }

    public String getRPT_TIME() {
        return RPT_TIME;
    }

    public void setRPT_TIME(String RPT_TIME) {
        this.RPT_TIME = RPT_TIME;
    }
}