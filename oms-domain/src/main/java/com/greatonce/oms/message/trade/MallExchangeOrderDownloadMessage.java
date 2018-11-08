package com.greatonce.oms.message.trade;

import com.greatonce.oms.message.Message;

/**
 * 退单已下载消息.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/7
 */
public class MallExchangeOrderDownloadMessage extends Message {

  private final Long mallExchangeOrderId;
  private final Long storeId;
  private final String tradeId;
  private final String mallExchangeId;

  /**
   * 构造方法.
   *
   * @param mallExchangeOrderId OMS下载换货单ID
   * @param storeId 店铺ID
   * @param tradeId 交易号
   * @param mallExchangeId 商城换货单ID
   */
  public MallExchangeOrderDownloadMessage(Long mallExchangeOrderId, Long storeId,
      String tradeId, String mallExchangeId) {
    this.mallExchangeOrderId = mallExchangeOrderId;
    this.storeId = storeId;
    this.tradeId = tradeId;
    this.mallExchangeId = mallExchangeId;
  }

  public Long getMallExchangeOrderId() {
    return mallExchangeOrderId;
  }

  public Long getStoreId() {
    return storeId;
  }

  public String getTradeId() {
    return tradeId;
  }

  public String getMallExchangeId() {
    return mallExchangeId;
  }

  @Override
  public String routingKey() {
    return "oms.trade.exchange.order.download";
  }
}
