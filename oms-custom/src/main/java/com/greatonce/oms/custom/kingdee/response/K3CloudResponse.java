package com.greatonce.oms.custom.kingdee.response;

import com.alibaba.fastjson.annotation.JSONField;
import java.util.List;

/**
 * K3请求.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-09-11
 */
public class K3CloudResponse<T> {

  @JSONField(name = "Result")
  private Result<T> result;

  public Result<T> getResult() {
    return result;
  }

  public void setResult(Result<T> result) {
    this.result = result;
  }

  public static class Result<T> {

    @JSONField(name = "Id")
    private String id;
    @JSONField(name = "ResponseStatus")
    private ResponseStatus status;
    @JSONField(name = "NeedReturnData")
    private List<T> data;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public ResponseStatus getStatus() {
      return status;
    }

    public void setStatus(ResponseStatus status) {
      this.status = status;
    }

    public List<T> getData() {
      return data;
    }

    public void setData(List<T> data) {
      this.data = data;
    }
  }

  public static class ResponseStatus {

    @JSONField(name = "ErrorCode")
    private Integer errorCode;
    @JSONField(name = "MsgCode")
    private Integer messageCode;
    @JSONField(name = "IsSuccess")
    private Boolean success;
    @JSONField(name = "Errors")
    private List<Error> errors;
    @JSONField(name = "SuccessEntitys")
    private List<Object> successEntities;
    @JSONField(name = "SuccessMessages")
    private List<Object> successMessages;

    public Integer getErrorCode() {
      return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
      this.errorCode = errorCode;
    }

    public Integer getMessageCode() {
      return messageCode;
    }

    public void setMessageCode(Integer messageCode) {
      this.messageCode = messageCode;
    }

    public Boolean isSuccess() {
      return success;
    }

    public void setSuccess(Boolean success) {
      this.success = success;
    }

    public List<Error> getErrors() {
      return errors;
    }

    public void setErrors(
        List<Error> errors) {
      this.errors = errors;
    }

    public List<Object> getSuccessEntities() {
      return successEntities;
    }

    public void setSuccessEntities(List<Object> successEntities) {
      this.successEntities = successEntities;
    }

    public List<Object> getSuccessMessages() {
      return successMessages;
    }

    public void setSuccessMessages(List<Object> successMessages) {
      this.successMessages = successMessages;
    }
  }

  public static class Error {

    @JSONField(name = "FieldName")
    private String fieldName;
    @JSONField(name = "Message")
    private String message;
    @JSONField(name = "DIndex")
    private String index;

    public String getFieldName() {
      return fieldName;
    }

    public void setFieldName(String fieldName) {
      this.fieldName = fieldName;
    }

    public String getMessage() {
      return message;
    }

    public void setMessage(String message) {
      this.message = message;
    }

    public String getIndex() {
      return index;
    }

    public void setIndex(String index) {
      this.index = index;
    }
  }
}
