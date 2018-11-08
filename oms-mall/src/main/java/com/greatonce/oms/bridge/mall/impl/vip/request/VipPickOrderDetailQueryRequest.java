package com.greatonce.oms.bridge.mall.impl.vip.request;

import com.greatonce.oms.bridge.mall.request.MallRequest;
import com.greatonce.oms.domain.base.Store;

/**
 * @author buer
 * @version 2017-08-24 15:25
 */
public class VipPickOrderDetailQueryRequest extends MallRequest {

  private String pickNo;
  private Integer page;

  public VipPickOrderDetailQueryRequest(Store store) {
    super(store);
  }

  public String getPickNo() {
    return pickNo;
  }

  public void setPickNo(String pickNo) {
    this.pickNo = pickNo;
  }

  public Integer getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }
}
