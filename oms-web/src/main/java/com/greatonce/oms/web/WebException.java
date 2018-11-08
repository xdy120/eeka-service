package com.greatonce.oms.web;

/**
 * Web异常类
 *
 * @author buer
 * @version 2017-10-21 12:03
 */
public class WebException extends RuntimeException {

  private int errorCode;

  public WebException(int errorCode, String message) {
    super(message);
    this.errorCode = errorCode;
  }

  public int getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(int errorCode) {
    this.errorCode = errorCode;
  }
}
