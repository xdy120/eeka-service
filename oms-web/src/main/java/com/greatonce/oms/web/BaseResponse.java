package com.greatonce.oms.web;

/**
 * @author buer
 * @version 2017-10-21 10:43
 */
public class BaseResponse {

  /**
   * 返回内容
   */
  private Object response;
  /**
   * 调用是否成功
   */
  private boolean success;
  /**
   * 错误代码
   */
  private Integer errorCode;
  /**
   * 错误信息
   */
  private String errorMessage;

  public BaseResponse() {
    this.success = true;
  }

  public BaseResponse(Object response) {
    this.success = true;
    this.response = response;
  }

  public BaseResponse(int errorCode, String errorMessage) {
    this.success = false;
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }

  public Object getResponse() {
    return response;
  }

  public void setResponse(Object response) {
    this.response = response;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public Integer getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(Integer errorCode) {
    this.errorCode = errorCode;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }
}
