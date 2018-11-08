package com.greatonce.oms.bridge.wms.qimen.response;

import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/4/21
 */
public class OmsQimenCustomResponse implements Serializable {

  private String flag;
  private String code;
  private String message;
  @JSONField(name = "sub_code")
  private String subCode;
  @JSONField(name = "sub_message")
  private String subMessage;
  private long requestId;

  public OmsQimenCustomResponse(long requestId) {
    this.requestId = requestId;
    this.setFlag("success");
  }

  public OmsQimenCustomResponse(long requestId, String message) {
    this.requestId = requestId;
    this.setFlag("success");
    this.setMessage(message);
  }

  public OmsQimenCustomResponse(long requestId, String subCode, String subMessage) {
    this.setFlag("failure");
    this.subCode = subCode;
    this.subMessage = subMessage;
    this.requestId = requestId;
  }

  public String getFlag() {
    return flag;
  }

  public void setFlag(String flag) {
    this.flag = flag;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getSubCode() {
    return subCode;
  }

  public void setSubCode(String subCode) {
    this.subCode = subCode;
  }

  public String getSubMessage() {
    return subMessage;
  }

  public void setSubMessage(String subMessage) {
    this.subMessage = subMessage;
  }

  public boolean isSuccess() {
    return !"failure".equalsIgnoreCase(flag);
  }

  public Long getRequestId() {
    return requestId;
  }

  public void setRequestId(Long requestId) {
    this.requestId = requestId;
  }
}
