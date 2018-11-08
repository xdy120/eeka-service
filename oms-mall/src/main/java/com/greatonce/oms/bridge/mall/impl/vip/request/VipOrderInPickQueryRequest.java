package com.greatonce.oms.bridge.mall.impl.vip.request;

import com.greatonce.oms.bridge.mall.request.MallRequest;
import com.greatonce.oms.domain.base.Store;

/**
 * @author buer
 * @version 2017-08-24 15:10
 */
public class VipOrderInPickQueryRequest extends MallRequest {

  private String pickNo;
  private String po;
  private Integer page;

  public VipOrderInPickQueryRequest(Store store) {
    super(store);
  }

  public String getPickNo() {
    return pickNo;
  }

  public void setPickNo(String pickNo) {
    this.pickNo = pickNo;
  }

  public String getPo() {
    return po;
  }

  public void setPo(String po) {
    this.po = po;
  }

  public Integer getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }
}
