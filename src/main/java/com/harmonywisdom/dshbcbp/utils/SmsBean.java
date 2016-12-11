package com.harmonywisdom.dshbcbp.utils;



public class SmsBean
  implements ISmsBean
{
  private boolean success;
  private String errMsg;

  public String getErrMsg()
  {
    return this.errMsg; }

  public void setErrMsg(String errMsg) {
    this.errMsg = errMsg; }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public boolean getSuccess() {
    return this.success;
  }
}
