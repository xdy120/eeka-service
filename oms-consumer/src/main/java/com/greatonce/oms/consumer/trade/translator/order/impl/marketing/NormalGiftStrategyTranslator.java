package com.greatonce.oms.consumer.trade.translator.order.impl.marketing;

import com.greatonce.oms.consumer.trade.translator.order.OrderTranslatableContext;
import com.greatonce.oms.consumer.trade.translator.order.TranslatorOrderCondition;
import com.greatonce.oms.consumer.trade.translator.order.impl.marketing.wrapper.GiftStrategyRuleWrapper;
import com.greatonce.oms.consumer.trade.translator.order.impl.marketing.wrapper.GiftStrategyWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/13
 */
@Component
@TranslatorOrderCondition
public class NormalGiftStrategyTranslator extends GiftStrategyTranslator {

  @Autowired
  private RuleTranslatorFactory ruleTranslatorFactory;

  @Override
  public StrategyMatchResult giveGift(OrderTranslatableContext context,
      GiftStrategyWrapper strategy) {
    GiftStrategyRuleWrapper ruleWrapper = strategy.getRuleWrappers().get(0);
    RuleTranslator ruleTranslator = ruleTranslatorFactory.getTranslator(ruleWrapper);
    if (ruleTranslator.match(context, strategy, ruleWrapper)) {
      return ruleTranslator.giveGift(context, strategy, ruleWrapper);
    }
    return null;
  }
}
