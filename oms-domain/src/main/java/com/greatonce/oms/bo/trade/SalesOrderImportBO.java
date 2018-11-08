package com.greatonce.oms.bo.trade;

import com.greatonce.oms.domain.enums.trade.SalesOrderType;
import java.util.List;
import java.util.Map;

/**
 * @author buer
 * @version 2018-01-15 14:32
 */
public class SalesOrderImportBO {

  private Long storeId;
  private Boolean needDelivery;
  private SalesOrderType orderType;
  private List<Map<String, String>> list;

  public Long getStoreId() {
    return storeId;
  }

  public void setStoreId(Long storeId) {
    this.storeId = storeId;
  }

  public Boolean isNeedDelivery() {
    return needDelivery;
  }

  public void setNeedDelivery(Boolean needDelivery) {
    this.needDelivery = needDelivery;
  }

  public List<Map<String, String>> getList() {
    return list;
  }

  public void setList(List<Map<String, String>> list) {
    this.list = list;
  }

  public SalesOrderType getOrderType() {
    return orderType;
  }

  public void setOrderType(SalesOrderType orderType) {
    this.orderType = orderType;
  }
}
