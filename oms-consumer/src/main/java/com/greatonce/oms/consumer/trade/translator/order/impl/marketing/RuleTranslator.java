package com.greatonce.oms.consumer.trade.translator.order.impl.marketing;

import com.greatonce.oms.consumer.trade.translator.order.OrderTranslatableContext;
import com.greatonce.oms.consumer.trade.translator.order.impl.marketing.wrapper.GiftStrategyRuleWrapper;
import com.greatonce.oms.consumer.trade.translator.order.impl.marketing.wrapper.GiftStrategyWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/13
 */
public abstract class RuleTranslator {

  private static final Logger LOGGER = LoggerFactory.getLogger(RuleTranslator.class);

  /**
   * 活动匹配.
   */
  public boolean match(OrderTranslatableContext context, GiftStrategyWrapper strategyWrapper,
      GiftStrategyRuleWrapper ruleWrapper) {
    if (strategyWrapper.isOos()) {
      LOGGER.info("{},{},赠品已送完，不参加活动", context, strategyWrapper.getGiftStrategyCode());
      return false;
    }
    return true;
  }

  /**
   * 赠送商品
   */
  public abstract StrategyMatchResult giveGift(OrderTranslatableContext context,
      GiftStrategyWrapper giftStrategy, GiftStrategyRuleWrapper rule);
}
