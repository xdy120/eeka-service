package com.greatonce.oms.bo.trade;

import com.greatonce.oms.bo.VersionBO;

/**
 * @author buer
 * @version 2017-12-27 19:45
 */
public class SuggestExpressBO extends VersionBO {

  private Long expressId;
  private String expressNo;

  public Long getExpressId() {
    return expressId;
  }

  public void setExpressId(Long expressId) {
    this.expressId = expressId;
  }

  public String getExpressNo() {
    return expressNo;
  }

  public void setExpressNo(String expressNo) {
    this.expressNo = expressNo;
  }

}
