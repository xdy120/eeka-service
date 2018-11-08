package com.greatonce.oms.bridge.wms.response;

import com.greatonce.oms.bridge.wms.request.WmsRequest;

/**
 * WmsResponse
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/4/2
 */
public abstract class WmsResponse<T extends WmsRequest> {

  private boolean success;
  private String message;
  private T request;

  public WmsResponse(T request) {
    this.request = request;
    this.success = true;
  }

  public WmsResponse(T request, boolean success, String message) {
    this.success = success;
    this.message = message;
    this.request = request;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public T getRequest() {
    return request;
  }

  public void setRequest(T request) {
    this.request = request;
  }
}
