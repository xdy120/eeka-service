package com.greatonce.oms.consumer.trade.translator.order.impl.marketing;

import com.greatonce.oms.consumer.trade.translator.order.OrderTranslatableContext;
import com.greatonce.oms.consumer.trade.translator.order.TranslatorOrderCondition;
import com.greatonce.oms.consumer.trade.translator.order.impl.marketing.wrapper.GiftStrategyRuleWrapper;
import com.greatonce.oms.consumer.trade.translator.order.impl.marketing.wrapper.GiftStrategyWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 金额规则转化
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/3/14
 */
@Component
@TranslatorOrderCondition
public class MoneyRuleTranslator extends NormalRuleTranslator {

  private static final Logger LOGGER = LoggerFactory.getLogger(RuleTranslator.class);

  @Override
  public boolean match(OrderTranslatableContext context, GiftStrategyWrapper strategyWrapper,
      GiftStrategyRuleWrapper ruleWrapper) {
    if (!super.match(context, strategyWrapper, ruleWrapper)) {
      return false;
    }

    if (context.getSalesOrder().getActualAmount() < ruleWrapper.getSetting().getMinAmount()) {
      LOGGER.info("{},{},支付金额不足，不参加活动", context, strategyWrapper.getGiftStrategyCode());
      return false;
    }
    return true;
  }
}