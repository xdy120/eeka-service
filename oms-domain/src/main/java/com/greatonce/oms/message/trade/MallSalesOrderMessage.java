package com.greatonce.oms.message.trade;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.message.Message;

/**
 *
 * @author Lcc
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2018/7/11
 */
public class MallSalesOrderMessage extends Message {

  private final Long mallSalesOrderId;
  private final Long storeId;
  private final String tradeId;
  private final String routingKey;

  protected MallSalesOrderMessage(Long mallSalesOrderId, Long storeId, String tradeId,
      String subKey) {
    this.mallSalesOrderId = mallSalesOrderId;
    this.storeId = storeId;
    this.tradeId = tradeId;
    this.routingKey = "oms.trade.sales.order" + (Assert.isEmpty(subKey) ? subKey : "." + subKey);
  }

  @Override
  public String routingKey() {
    return routingKey;
  }

  public Long getMallSalesOrderId() {
    return mallSalesOrderId;
  }

  public Long getStoreId() {
    return storeId;
  }

  public String getTradeId() {
    return tradeId;
  }
}
