package com.greatonce.oms.bridge.mall.impl.jd.entity;

import java.io.Serializable;

/**
 * 京东响应反序列化对象.
 *
 * @author Lcc
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2018/8/24
 */
public class JdResponseConverter implements Serializable {

  private JdResponseData data;
  private int statusCode;
  private String statusMessage;

  public JdResponseData getData() {
    return data;
  }

  public void setData(JdResponseData data) {
    this.data = data;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(int statusCode) {
    this.statusCode = statusCode;
  }

  public String getStatusMessage() {
    return statusMessage;
  }

  public void setStatusMessage(String statusMessage) {
    this.statusMessage = statusMessage;
  }
}
