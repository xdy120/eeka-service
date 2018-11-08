package com.greatonce.oms.api.qimen.custom.response;

import java.io.Serializable;

public class OmsReceiverModifyResponse implements Serializable {

  private Integer status; // 0成功 1失败
  private String method;
  private String content;

  public OmsReceiverModifyResponse(Integer status, String method, String content) {
    this.status = status;
    this.method = method;
    this.content = content;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
