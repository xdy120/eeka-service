package com.greatonce.oms.message.trade;

import com.greatonce.oms.message.Message;

/**
 * 退单已下载消息
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/7
 */
public class MallRefundOrderDownloadMessage extends Message {

  private final Long mallRefundOrderId;
  private final Long storeId;
  private final String tradeId;
  private final String mallRefundId;

  /**
   * 构造方法.
   *
   * @param mallRefundOrderId OMS下载退款单ID
   * @param storeId 店铺ID
   * @param tradeId 交易号
   * @param mallRefundId 商城退款单ID
   */
  public MallRefundOrderDownloadMessage(Long mallRefundOrderId, Long storeId,
      String tradeId, String mallRefundId) {
    this.mallRefundOrderId = mallRefundOrderId;
    this.storeId = storeId;
    this.tradeId = tradeId;
    this.mallRefundId = mallRefundId;
  }

  public Long getMallRefundOrderId() {
    return mallRefundOrderId;
  }

  public Long getStoreId() {
    return storeId;
  }

  public String getTradeId() {
    return tradeId;
  }

  public String getMallRefundId() {
    return mallRefundId;
  }

  @Override
  public String routingKey() {
    return "oms.trade.refund.order.download";
  }
}
