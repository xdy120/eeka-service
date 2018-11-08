package com.greatonce.oms.bo.trade;

import java.util.List;

/**
 * @author buer
 * @version 2017-12-27 19:53
 */
public class DownloadSalesOrderBO {

  private Long storeId;
  private List<String> tradeIds;

  public Long getStoreId() {
    return storeId;
  }

  public void setStoreId(Long storeId) {
    this.storeId = storeId;
  }

  public List<String> getTradeIds() {
    return tradeIds;
  }

  public void setTradeIds(List<String> tradeIds) {
    this.tradeIds = tradeIds;
  }
}
