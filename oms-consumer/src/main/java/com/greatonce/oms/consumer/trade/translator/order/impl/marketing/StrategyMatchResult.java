package com.greatonce.oms.consumer.trade.translator.order.impl.marketing;


import com.greatonce.oms.domain.base.Express;
import com.greatonce.oms.domain.marketing.GiftStrategy;
import com.greatonce.oms.domain.trade.SalesOrder;

import java.util.Collection;
import java.util.List;

/**
 * 赠品赠送结果
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/3/13
 */
public class StrategyMatchResult {

  private final SalesOrder salesOrder;
  private final GiftStrategy giftStrategy;
  private Express express;
  private Collection<StrategyRuleMatchResult> gifts;

  public StrategyMatchResult(SalesOrder salesOrder, GiftStrategy giftStrategy) {
    this.salesOrder = salesOrder;
    this.giftStrategy = giftStrategy;
  }

  public StrategyMatchResult(SalesOrder salesOrder, GiftStrategy giftStrategy,
      List<StrategyRuleMatchResult> gifts) {
    this.salesOrder = salesOrder;
    this.giftStrategy = giftStrategy;
    this.gifts = gifts;
  }

  public SalesOrder getSalesOrder() {
    return salesOrder;
  }

  public GiftStrategy getGiftStrategy() {
    return giftStrategy;
  }

  public boolean hasGift() {
    return express != null || gifts != null && !gifts.isEmpty();
  }

  public Express getExpress() {
    return express;
  }

  public void setExpress(Express express) {
    this.express = express;
  }

  public Collection<StrategyRuleMatchResult> getGifts() {
    return gifts;
  }

  public void setGifts(Collection<StrategyRuleMatchResult> gifts) {
    this.gifts = gifts;
  }
}
