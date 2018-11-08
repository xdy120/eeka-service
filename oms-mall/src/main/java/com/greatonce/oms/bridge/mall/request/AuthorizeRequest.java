package com.greatonce.oms.bridge.mall.request;


import com.greatonce.oms.domain.base.Store;

/**
 * AuthorizeRequest
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/3/19
 */
public class AuthorizeRequest extends MallRequest {


  private String code;

  public AuthorizeRequest(Store store) {
    super(store);
  }

  public AuthorizeRequest(Store store, String code) {
    super(store);
    this.code = code;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}
