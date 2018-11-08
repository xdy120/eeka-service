package com.greatonce.oms.bo.trade;

/**
 * @author Lcc
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/7/14
 */
public class SalesOrderExpressNoticeBo extends SalesOrderBO {

  /**
   * 快递单号.
   */
  private String expressNo;
  /**
   * 物流通知全局快递ID.
   */
  private String mallExpressId;
  /**
   * 物流通知全局快递编码.
   */
  private String mallExpressCode;
  /**
   * 物流通知全局快递名称.
   */
  private String mallExpressName;
  /**
   * 物流通知快递名称.
   */
  private String expressName;
  /**
   * 是否拆单物流通知.
   */
  private boolean isSplit;

  public String getExpressNo() {
    return expressNo;
  }

  public void setExpressNo(String expressNo) {
    this.expressNo = expressNo;
  }

  public String getMallExpressCode() {
    return mallExpressCode;
  }

  public void setMallExpressCode(String mallExpressCode) {
    this.mallExpressCode = mallExpressCode;
  }

  public String getMallExpressName() {
    return mallExpressName;
  }

  public void setMallExpressName(String mallExpressName) {
    this.mallExpressName = mallExpressName;
  }

  public String getExpressName() {
    return expressName;
  }

  public void setExpressName(String expressName) {
    this.expressName = expressName;
  }

  public String getMallExpressId() {
    return mallExpressId;
  }

  public void setMallExpressId(String mallExpressId) {
    this.mallExpressId = mallExpressId;
  }

  public boolean isSplit() {
    return isSplit;
  }

  public void setSplit(boolean split) {
    isSplit = split;
  }
}
