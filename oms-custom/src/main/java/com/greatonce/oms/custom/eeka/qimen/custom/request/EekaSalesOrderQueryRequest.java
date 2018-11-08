package com.greatonce.oms.custom.eeka.qimen.custom.request;

import com.greatonce.oms.bridge.wms.qimen.request.OmsQimenCustomRequest;

public class EekaSalesOrderQueryRequest extends OmsQimenCustomRequest {

  private Integer pageSize;
  private Integer page;
  private String nickname;
  private String telephone;
  private String dispatchOrderCode;
  private String expressNo;
  private String contact;
  private String mallPaidTimeBegin;
  private String mallPaidTimeEnd;

  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public Integer getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  public String getDispatchOrderCode() {
    return dispatchOrderCode;
  }

  public void setDispatchOrderCode(String dispatchOrderCode) {
    this.dispatchOrderCode = dispatchOrderCode;
  }

  public String getExpressNo() {
    return expressNo;
  }

  public void setExpressNo(String expressNo) {
    this.expressNo = expressNo;
  }

  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  public String getMallPaidTimeBegin() {
    return mallPaidTimeBegin;
  }

  public void setMallPaidTimeBegin(String mallPaidTimeBegin) {
    this.mallPaidTimeBegin = mallPaidTimeBegin;
  }

  public String getMallPaidTimeEnd() {
    return mallPaidTimeEnd;
  }

  public void setMallPaidTimeEnd(String mallPaidTimeEnd) {
    this.mallPaidTimeEnd = mallPaidTimeEnd;
  }
}
