package com.greatonce.oms.bridge.mall.response;

import com.greatonce.oms.bridge.mall.request.MallRequest;

/**
 * CREATED by zhangqin on 2017/1/3.
 */
public abstract class MallResponse<T extends MallRequest> {

  protected boolean isSuccess;
  protected String result;
  protected T request;

  public MallResponse(T request) {
    this(request, true, null);
  }

  public MallResponse(T request, boolean isSuccess, String result) {
    this.request = request;
    this.isSuccess = isSuccess;
    this.result = result;
  }

  public T getRequest() {
    return request;
  }

  public void setRequest(T request) {
    this.request = request;
  }

  public boolean isSuccess() {
    return isSuccess;
  }

  public void setSuccess(boolean success) {
    isSuccess = success;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  @Override
  public String toString() {
    return "MallResponse{" + "isSuccess=" + isSuccess + ", result='" + result + '\'' + '}';
  }
}
