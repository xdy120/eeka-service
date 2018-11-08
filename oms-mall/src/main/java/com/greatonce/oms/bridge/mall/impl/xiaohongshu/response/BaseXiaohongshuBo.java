package com.greatonce.oms.bridge.mall.impl.xiaohongshu.response;

public class BaseXiaohongshuBo {

  private boolean success;
  private String error_code;
  private String error_msg;

  public boolean getSuccess() {
    return success;
  }


  public void setSuccess(boolean success) {
    this.success = success;
  }


  public String getError_code() {
    return error_code;
  }


  public void setError_code(String error_code) {
    this.error_code = error_code;
  }


  public String getError_msg() {
    return error_msg;
  }


  public void setError_msg(String error_msg) {
    this.error_msg = error_msg;
  }
}
