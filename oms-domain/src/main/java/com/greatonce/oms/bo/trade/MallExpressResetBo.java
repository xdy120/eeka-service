package com.greatonce.oms.bo.trade;

import com.greatonce.oms.bo.VersionBO;

/**
 *
 * @author Lcc
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2018/8/3
 */
public class MallExpressResetBo extends VersionBO {

  private String resetExpressNo;
  private String mallExpressId;
  private String mallExpressCode;
  private String mallExpressName;

  public String getResetExpressNo() {
    return resetExpressNo;
  }

  public void setResetExpressNo(String resetExpressNo) {
    this.resetExpressNo = resetExpressNo;
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

  public String getMallExpressId() {
    return mallExpressId;
  }

  public void setMallExpressId(String mallExpressId) {
    this.mallExpressId = mallExpressId;
  }
}
