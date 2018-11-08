package com.greatonce.oms.consumer.trade.translator.order.impl.marketing;

import com.greatonce.oms.consumer.trade.translator.order.impl.marketing.wrapper.GiftStrategyGiftWrapper;

/**
 * ActivityGift
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/3/13
 */
public class StrategyRuleMatchResult {

  /**
   * 送出赠品
   */
  private final GiftStrategyGiftWrapper gift;
  /**
   * 赠送数量
   */
  private int quantity;

  public StrategyRuleMatchResult(GiftStrategyGiftWrapper gift, int quantity) {
    this.quantity = quantity;
    this.gift = gift;
  }

  public GiftStrategyGiftWrapper getGift() {
    return gift;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}
