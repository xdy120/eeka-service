package com.greatonce.oms.consumer.trade.translator.order.impl.marketing;

import com.greatonce.oms.consumer.trade.translator.order.TranslatorOrderCondition;
import com.greatonce.oms.domain.marketing.GiftStrategyRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/13
 */
@Component
@TranslatorOrderCondition
public class RuleTranslatorFactory {

  @Autowired
  private MoneyRuleTranslator moneyRuleTranslator;
  @Autowired
  private QuantityRuleTranslator quantityRuleTranslator;
  @Autowired
  private LuckyBagRuleTranslator luckyBagRuleTranslator;

  /**
   * 获取不同规则的转换器
   */
  public RuleTranslator getTranslator(GiftStrategyRule rule) {
    switch (rule.getRuleType()) {
      case LUCK_BAG:
        return luckyBagRuleTranslator;
      case MONEY:
        return moneyRuleTranslator;
      default:
        return quantityRuleTranslator;
    }
  }
}
